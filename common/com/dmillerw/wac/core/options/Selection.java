package com.dmillerw.wac.core.options;

import net.minecraft.nbt.NBTTagCompound;

public class Selection {

	public String name;
	public String description;
	
	public boolean selected;
	
	public Selection() {
		
	}
	
	public Selection(String name, String description, boolean selected) {
		this.name = name;
		this.description = description;
		this.selected = selected;
	}
	
	public void toggle() {
		this.selected = !this.selected;
	}
	
	public void writeToNBT(NBTTagCompound nbt) {
		nbt.setString("name", name);
		nbt.setString("description", description);
		nbt.setBoolean("selected", selected);
	}
	
	public void readFromNBT(NBTTagCompound nbt) {
		this.name = nbt.getString("name");
		this.description = nbt.getString("description");
		this.selected = nbt.getBoolean("selected");
	}
	
}
