package com.dmillerw.wac.core.options;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

import com.dmillerw.wac.core.helper.LogHelper;

public abstract class Option {

	public Object data;
	
	public String category = "General Settings";
	
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
	
	public static ArrayList<Option> getOptionsFromCategory(ArrayList<Option> options, String category) {
		ArrayList<Option> toReturn = new ArrayList<Option>();
		
		for (Option option : options) {
			if (option.category.equals(category)) {
				toReturn.add(option);
			}
		}
		
		return toReturn;
	}
	
	public abstract OptionType getType();
	
	public abstract Option readFromNBT(NBTTagCompound nbt);
	
	public abstract Option writeToNBT(NBTTagCompound nbt);
	
}
