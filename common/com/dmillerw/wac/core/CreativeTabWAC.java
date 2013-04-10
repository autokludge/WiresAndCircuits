package com.dmillerw.wac.core;

import com.dmillerw.wac.block.BlockIDs;
import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.item.ItemIDs;

import cpw.mods.fml.common.registry.LanguageRegistry;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabWAC extends CreativeTabs {

	private String subName;
	private String configType;
	private String tabIconID;
	
	public CreativeTabWAC(String subName, String configType, String tabIconID, String localizedName) {
		super("wac."+subName);
		this.subName = subName;
		this.configType = configType;
		this.tabIconID = tabIconID;
		
		LanguageRegistry.instance().addStringLocalization("itemGroup.wac."+subName, localizedName);
	}

	@Override
	public int getTabIconItemIndex() {
		if (configType.equals("block")) {
			return BlockIDs.getID(tabIconID);
		} else if (configType.equals("item")) {
			return ItemIDs.getShiftedID(tabIconID);
		} else {
			LogHelper.warn("Creative Tab for "+subName+" asked for ID "+tabIconID+", but it doesn't exist! Report this to me!");
			return Block.stone.blockID;
		}
	}
	
}
