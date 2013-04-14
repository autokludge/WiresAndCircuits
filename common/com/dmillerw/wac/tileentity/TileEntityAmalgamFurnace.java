package com.dmillerw.wac.tileentity;

import com.dmillerw.wac.interfaces.IRotatable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityAmalgamFurnace extends TileEntity implements IRotatable {

	private ForgeDirection rotation;
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("rotation", (byte) rotation.ordinal());
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
	public ForgeDirection getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(ForgeDirection rotation) {
		this.rotation = rotation;
	}

}
