package com.dmillerw.wac.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateManager;
import com.dmillerw.wac.interfaces.IAttachedToSide;
import com.dmillerw.wac.interfaces.IGateContainer;
import com.dmillerw.wac.interfaces.ISavableGate;

public class TileEntityChip extends TileEntity implements IAttachedToSide, IGateContainer {
	
	private ForgeDirection attached;
	
	private int gateID;
	
	private Gate gate;
	
	public Object[] inputs;
	public Object[] outputs;
	
	public boolean dirty = true;
	
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) return;
		
		if (dirty && hasInputs()) {
			gate.logic(this);
			dirty = false;
		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
		setGate(nbt.getInteger("gateID"));
		
		if (gate instanceof ISavableGate) {
			((ISavableGate)gate).readFromNBT(nbt);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("attached", (byte) attached.ordinal());
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

	public void setGate(int id) {
		gateID = id;
		gate = GateManager.createGate(id);
		inputs = new Object[gate.getInputDataTypes().length];
		outputs = new Object[gate.getOutputDataTypes().length];
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
	
}
