package com.dmillerw.wac.tileentity;

import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.interfaces.IDataHandler;
import com.dmillerw.wac.interfaces.ISideAttachment;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityScreen extends TileEntity implements ISideAttachment, IDataHandler {

	private ForgeDirection attached;
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("attached", (byte) attached.ordinal());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
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
	public DataType[] getInputDataTypes() {
		return new DataType[] {DataType.STRING};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return null;
	}

}
