package com.dmillerw.gswm.item;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraftforge.common.Configuration;

import com.dmillerw.gswm.core.helper.LogHelper;

public class ItemIDs {

	public static Map<String, Integer> idMapping = new HashMap<String, Integer>();
	
	public static void initializeDefaults() {
		idMapping.put("itemDust", 10000);
	}

	public static void handleConfig(Configuration config) {
		//First fill the mapping with defaults, then check the config. 
		//This allows for filling of a config if necessary
		//or allowing for mod operation should the config error.
		//This also allows for handling the entire config through a loop
		initializeDefaults();
		
		try {
			config.load();
			
			for (Entry<String, Integer> entry : idMapping.entrySet()) {
				idMapping.put(entry.getKey(), config.getItem(entry.getKey(), entry.getValue()).getInt());
			}
		} catch(Exception e) {
			LogHelper.warn("Failed to load item ids! Assuming defaults!");
			
			//If one or more fails, simply use defaults to avoid complications
			initializeDefaults();
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}

	public static int getID(String name) {
		return idMapping.get(name);
	}

	public static int getShiftedID(String name) {
		return idMapping.get(name) == 0 ? 0 : idMapping.get(name) + 256;
	}
	
}
