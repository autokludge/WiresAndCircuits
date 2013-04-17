package com.dmillerw.wac.interfaces;

import net.minecraft.nbt.NBTTagCompound;

import com.dmillerw.wac.core.options.OptionType;

public interface IConfigurable {

	public void onSettingsChanged();
	
	public void saveSettings(NBTTagCompound nbt);
	
	public void loadSettings(NBTTagCompound nbt);
	
	public void saveOption(String id, OptionType type, Object value);
	
	public Object loadOption(String id);
	
	public OptionType getOptionType(String id);
	
	public String getOptionName(String id);
	
	public String getOptionComment(String id);
	
	public Object[] getAllOptions();
	
}
