package com.dmillerw.wac.core.options;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Configurable {

	private String title;
	
	public Map<String, Option> options = new HashMap<String, Option>();
	
	public Configurable(String title) {
		this.title = title;
	}
	
	public void saveSettings(NBTTagCompound nbt) {
		NBTTagList optionsList = new NBTTagList();
		for (Entry<String, Option> option : options.entrySet()) {
			NBTTagCompound optionTag = new NBTTagCompound();
			optionTag.setString("id", option.getKey());
			optionTag.setString("name", option.getValue().getName());
			optionTag.setString("description", option.getValue().getDescription());
			optionTag.setString("type", option.getValue().getType().toString());
			optionTag.setString("category", option.getValue().category);
			option.getValue().writeToNBT(optionTag);
			optionsList.appendTag(optionTag);
		}
		nbt.setTag("options", optionsList);
	}

	public void loadSettings(NBTTagCompound nbt) {
		options.clear();
		
		if (nbt.hasKey("options")) {
			NBTTagList options = nbt.getTagList("options");
			for (int i=0; i<options.tagCount(); i++) {
				NBTTagCompound optionTag = (NBTTagCompound) options.tagAt(i);
				Option option = Option.createFromNBT(optionTag);
				this.options.put(optionTag.getString("id"), option);
			}
		}
	}

	public void setOption(String id, Option value) {
		options.put(id, value);
	}

	public Option getOption(String id) {
		return options.get(id);
	}

	public OptionType getOptionType(String id) {
		return getOption(id).getType();
	}

	public String getConfigurationTitle() {
		return title;
	}
	
}
