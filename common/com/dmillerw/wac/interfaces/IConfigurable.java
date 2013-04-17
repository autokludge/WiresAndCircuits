package com.dmillerw.wac.interfaces;

import net.minecraft.nbt.NBTTagCompound;

import com.dmillerw.wac.core.options.Option;
import com.dmillerw.wac.core.options.OptionType;

public interface IConfigurable {

	public void saveSettings(NBTTagCompound nbt);
	
	public void loadSettings(NBTTagCompound nbt);
	
	public void setOption(String id, Option value);
	
	public Option getOption(String id);
	
	public OptionType getOptionType(String id);
	
	public String getConfigurationTitle();
	
}
