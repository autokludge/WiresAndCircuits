package com.dmillerw.wac.util;

import net.minecraft.nbt.NBTTagCompound;

public class DataConnection {

	public BlockCoord gateLocation;
	
	public int gateIndex;
	
	public DataConnection(BlockCoord loc, int index) {
		this.gateLocation = loc;
		this.gateIndex = index;
	}
	
	public DataConnection(NBTTagCompound nbt) {
		this(new BlockCoord(nbt.getInteger("x"), nbt.getInteger("y"), nbt.getInteger("z")), nbt.getInteger("index"));
	}
	
	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("x", gateLocation.x);
		nbt.setInteger("y", gateLocation.y);
		nbt.setInteger("z", gateLocation.z);
		nbt.setInteger("index", gateIndex);
		return nbt;
	}
	
}
