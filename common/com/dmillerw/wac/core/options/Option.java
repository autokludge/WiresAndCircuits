package com.dmillerw.wac.core.options;

import com.dmillerw.wac.core.helper.LogHelper;

import net.minecraft.nbt.NBTTagCompound;

public abstract class Option {

	public Object data;
	
	public static Option createFromNBT(NBTTagCompound nbt) {
		OptionType type = OptionType.valueOf(nbt.getString("type"));
		
		switch(type) {
			case INTEGER: return new OptionNumber().readFromNBT(nbt);
			case STRING: return new OptionString().readFromNBT(nbt);
			case BOOLEAN: return new OptionBoolean().readFromNBT(nbt);
			case SELECTION: return new OptionSelection().readFromNBT(nbt);
			default: {
				LogHelper.warn("Failed to load an option!");
				return null;
			}
		}
	}
	
	public abstract OptionType getType();
	
	public abstract Option readFromNBT(NBTTagCompound nbt);
	
	public abstract Option writeToNBT(NBTTagCompound nbt);
	
}
