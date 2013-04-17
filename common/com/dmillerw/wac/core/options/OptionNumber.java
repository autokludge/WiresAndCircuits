package com.dmillerw.wac.core.options;

import net.minecraft.nbt.NBTTagCompound;

public class OptionNumber extends Option {

	public int data;
	
	public OptionNumber() {}
	
	public OptionNumber(int data) {
		this.data = data;
	}
	
	@Override
	public OptionType getType() {
		return OptionType.INTEGER;
	}

	@Override
	public Option readFromNBT(NBTTagCompound nbt) {
		this.data = nbt.getInteger("data");
		return this;
	}

	@Override
	public Option writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("data", (int) data);
		return this;
	}

}
