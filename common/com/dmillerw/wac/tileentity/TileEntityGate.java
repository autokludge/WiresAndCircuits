package com.dmillerw.wac.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateManager;
import com.dmillerw.wac.interfaces.IConnectable;
import com.dmillerw.wac.interfaces.IGateContainer;
import com.dmillerw.wac.interfaces.IRotatable;
import com.dmillerw.wac.interfaces.ISavableGate;
import com.dmillerw.wac.interfaces.ISideAttachment;
import com.dmillerw.wac.util.DataConnection;

import cpw.mods.fml.common.FMLCommonHandler;

public class TileEntityGate extends TileEntity implements ISideAttachment, IGateContainer, IRotatable, IConnectable {
	
	private ForgeDirection attached;
	private ForgeDirection rotation;
	
	private int gateID;
	
	private Gate gate;
	
	public Object[] inputs;
	public Object[] outputs;
	
	public List<DataConnection>[] connectedOutputs;
	
	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (dirty) {
			gate.logic(this);
			
			for (int i=0; i<connectedOutputs.length; i++) {
				for (DataConnection connection : connectedOutputs[i]) {
					IConnectable container = (IConnectable) worldObj.getBlockTileEntity(connection.gateLocation.x, connection.gateLocation.y, connection.gateLocation.z);
					if (container != null) {
						container.receiveInput(connection.gateIndex, outputs[i]);
					}
					//TODO remove dead outputs
				}
			}
			
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			dirty = false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		setGate(nbt.getInteger("gateID"));
		
		NBTTagList connections = nbt.getTagList("connections");
		connectedOutputs = new ArrayList[connections.tagCount()];
		for (int i=0; i<connections.tagCount(); i++) {
			NBTTagList connections2 = (NBTTagList) connections.tagAt(i);
			List<DataConnection> list = new ArrayList<DataConnection>();
			
			for (int j=0; j<connections2.tagCount(); j++) {
				list.add(new DataConnection((NBTTagCompound) connections2.tagAt(j)));
			}
			
			connectedOutputs[i] = list;
		}
		
		if (gate instanceof ISavableGate) {
			((ISavableGate)gate).readFromNBT(nbt);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("attached", (byte) attached.ordinal());
		nbt.setByte("rotation", (byte) rotation.ordinal());
		nbt.setInteger("gateID", gateID);
		
		NBTTagList connections = new NBTTagList();
		for (List<DataConnection> list : connectedOutputs) {
			NBTTagList connections2 = new NBTTagList();
			
			for (DataConnection connect : list) {
				connections2.appendTag(connect.writeToNBT());
			}
			
			connections.appendTag(connections2);
		}
		nbt.setTag("connections", connections);
		
		if (gate instanceof ISavableGate) {
			((ISavableGate)gate).writeToNBT(nbt);
		}
	}
	
	@Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
    	System.out.println(FMLCommonHandler.instance().getEffectiveSide());
		readFromNBT(pkt.customParam1);
    	dirty = true;
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
	
	@Override
	public void setSideAttached(ForgeDirection side) {
		attached = side;
	}

	@Override
	public ForgeDirection getSideAttached() {
		return attached;
	}

	@Override
	public ForgeDirection getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(ForgeDirection rotation) {
		this.rotation = rotation;
	}
	
	@SuppressWarnings("unchecked")
	public void setGate(int id) {
		gateID = id;
		gate = GateManager.createGate(id);
		if (gate.getInputDataTypes() != null) {
			inputs = GateManager.generateBlankInputArray(gate);
		}
		if (gate.getOutputDataTypes() != null) {
			outputs = GateManager.generateBlankOutputArray(gate);
			connectedOutputs = new List[outputs.length];
			for (int i=0; i<connectedOutputs.length; i++) {
				connectedOutputs[i] = new ArrayList<DataConnection>();
			}
		}
		
		dirty = true;
	}
	
	public int getGateID() {
		return gateID;
	}
	
	public Object[] getOutputs() {
		return outputs;
	}
	
	public Object[] getInputs() {
		return inputs;
	}
	
	public Gate getGate() {
		return gate;
	}
	
	public void linkOutput(int index, DataConnection end) {
		connectedOutputs[index].add(end);
		dirty = true;
	}
	
	public void receiveInput(int index, Object value) {
		//TODO type checking
		inputs[index] = value;
		dirty = true;
	}
	
	public boolean hasInputs() {
		if (inputs == null) return false;
		
		for (Object obj : inputs) {
			if (obj != null) {
				return true;
			}
		}
		
		return false;
	}

}
