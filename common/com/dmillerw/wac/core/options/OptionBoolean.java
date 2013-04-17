package com.dmillerw.wac.core.options;

import net.minecraft.nbt.NBTTagCompound;

public class OptionBoolean extends Option {

	public boolean data;
	
	public OptionBoolean() {}
	
	public OptionBoolean(boolean data) {
		this.data = data;
	}
	
	@Override
	public OptionType getType() {
		return OptionType.BOOLEAN;
	}

	@Override
	public Option readFromNBT(NBTTagCompound nbt) {
		this.data = nbt.getBoolean("data");
		return this;
	}

	@Override
	public Option writeToNBT(NBTTagCompound nbt) {
		nbt.setBoolean("data", (boolean) data);
		return this;
	}
	
}
