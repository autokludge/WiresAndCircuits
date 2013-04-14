package com.dmillerw.wac.interfaces;

import net.minecraft.nbt.NBTTagCompound;

public interface ISavableGate {

	public void writeToNBT(NBTTagCompound nbt);
	
	public void readFromNBT(NBTTagCompound nbt);
	
}
