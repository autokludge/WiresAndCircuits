package com.dmillerw.wac.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateManager;
import com.dmillerw.wac.interfaces.IGateContainer;
import com.dmillerw.wac.interfaces.IRotatable;
import com.dmillerw.wac.interfaces.ISavableGate;
import com.dmillerw.wac.interfaces.ISideAttachment;

import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityGate extends TileEntity implements ISideAttachment, IGateContainer, IRotatable {
	
	private ForgeDirection attached;
	private ForgeDirection rotation;
	
	private int gateID;
	
	private Gate gate;
	
	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (dirty) {
			gate.logic(this);
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			updateClients();
			dirty = false;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		setGate(nbt.getInteger("gateID"));
		
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
		
		if (gate instanceof ISavableGate) {
			((ISavableGate)gate).writeToNBT(nbt);
		}
	}
	
	@Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		readFromNBT(pkt.customParam1);
    	dirty = true;
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
		dirty = true;
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
		dirty = true;
	}
	
	public void setGate(int id) {
		gateID = id;
		gate = GateManager.createGate(id);
		if (gate.getInputDataTypes() != null) {
			gate.inputs = GateManager.generateBlankInputArray(gate);
		}
		if (gate.getOutputDataTypes() != null) {
			gate.outputs = GateManager.generateBlankOutputArray(gate);
		}
		
		dirty = true;
	}
	
	public int getGateID() {
		return gateID;
	}
	
	public Object[] getOutputs() {
		return gate.outputs;
	}
	
	public Object[] getInputs() {
		return gate.inputs;
	}
	
	public Gate getGate() {
		return gate;
	}
	
	public void updateClients(){
		if(worldObj.isRemote) return;
		Packet132TileEntityData packet = (Packet132TileEntityData) this.getDescriptionPacket();
		PacketDispatcher.sendPacketToAllInDimension(packet, this.worldObj.provider.dimensionId);
	}
	
	public boolean hasInputs() {
		if (gate.inputs == null) return false;
		
		for (Object obj : gate.inputs) {
			if (obj != null) {
				return true;
			}
		}
		
		return false;
	}

}
