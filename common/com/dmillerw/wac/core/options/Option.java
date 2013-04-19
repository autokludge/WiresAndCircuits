package com.dmillerw.wac.core.options;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;

import com.dmillerw.wac.core.helper.LogHelper;

//TODO Make sure each option has a getGuiElement method
public abstract class Option {

	public Object data;
	
	public String name;
	public String description;
	public String category = "General Settings";
	
	public static Option createFromNBT(NBTTagCompound nbt) {
		String name = nbt.getString("name");
		String description = nbt.getString("description");
		OptionType type = OptionType.valueOf(nbt.getString("type"));
		
		switch(type) {
			case INTEGER: return new OptionNumber().readFromNBT(nbt).setName(name).setDescription(description);
			case STRING: return new OptionString().readFromNBT(nbt).setName(name).setDescription(description);
			case BOOLEAN: return new OptionBoolean().readFromNBT(nbt).setName(name).setDescription(description);
			case SELECTION: return new OptionSelection().readFromNBT(nbt).setName(name).setDescription(description);
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
	
	public String getName() {
		return name;
	}
	
	public Option setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Option setDescription(String description) {
		this.description = description;
		return this;
	}
	
	public abstract OptionType getType();
	
	public abstract Option readFromNBT(NBTTagCompound nbt);
	
	public abstract Option writeToNBT(NBTTagCompound nbt);
	
}
