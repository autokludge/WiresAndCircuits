package com.dmillerw.wac.tileentity;

import java.util.ArrayList;
import java.util.List;

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
import com.dmillerw.wac.interfaces.IWireConnector;
import com.dmillerw.wac.util.Vector;

public class TileEntityGate extends TileEntity implements ISideAttachment, IGateContainer, IRotatable, IWireConnector {
	
	private ForgeDirection attached;
	private ForgeDirection rotation;
	
	private int gateID;
	
	private Gate gate;
	
	public Object[] inputs;
	public Object[] outputs;
	
	public List<Vector>[] outputConnections;
	public List<Vector>[] outputWirePoints;
	
	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) return;
		
		if (dirty) {
			gate.logic(this);
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
	public Packet getDescriptionPacket() {
		Packet132TileEntityData packet = new Packet132TileEntityData();
		NBTTagCompound tag = new NBTTagCompound();

		this.writeToNBT(tag);

		packet.xPosition = this.xCoord;
		packet.yPosition = this.yCoord;
		packet.zPosition = this.zCoord;
		packet.customParam1 = tag;

		return packet;
	}

	@Override
	public void onDataPacket(INetworkManager network, Packet132TileEntityData packet) {
		if (packet.xPosition == this.xCoord && packet.yPosition == this.yCoord && packet.zPosition == this.zCoord) {
			readFromNBT(packet.customParam1);
		}
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
			outputConnections = new List[outputs.length];
			outputWirePoints = new List[outputs.length];
			for (int i=0; i<outputs.length; i++) {
				outputConnections[i] = new ArrayList<Vector>();
				outputWirePoints[i] = new ArrayList<Vector>();
			}
		}
	}
	
	public int getGateID() {
		return gateID;
	}
	
	public Gate getGate() {
		return gate;
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

	@Override
	public List<Vector> getOutputConnections(int index) {
		return outputConnections[index];
	}

	@Override
	public void addOutputConnection(int index, Vector connection) {
		getOutputConnections(index).add(connection);
	}
	
	@Override
	public void setOutputConnections(int index, List<Vector> connections) {
		outputConnections[index] = connections;
	}

	public List<Vector> getPoints(int index) {
		return outputWirePoints[index];
	}

	public void addPoint(int index, Vector point) {
		outputWirePoints[index].add(point);
	}

}
