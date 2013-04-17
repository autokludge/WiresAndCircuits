package com.dmillerw.wac.core.options;

import net.minecraft.nbt.NBTTagCompound;

public class OptionString extends Option {

	public String data;
	
	public OptionString() {}
	
	public OptionString(String data) {
		this.data = data;
	}
	
	@Override
	public OptionType getType() {
		return OptionType.STRING;
	}

	@Override
	public Option readFromNBT(NBTTagCompound nbt) {
		this.data = nbt.getString("data");
		return this;
	}

	@Override
	public Option writeToNBT(NBTTagCompound nbt) {
		nbt.setString("data", (String) data);
		return this;
	}

}
