package com.dmillerw.wac.core;

import com.dmillerw.wac.block.BlockIDs;
import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.item.ItemIDs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabWAC extends CreativeTabs {

	private String subName;
	private String tabIconID;
	
	public CreativeTabWAC(String subName, String tabIconID) {
		super("gswm."+subName);
		this.subName = subName;
		this.tabIconID = tabIconID;
	}

	@Override
	public int getTabIconItemIndex() {
		if (BlockIDs.getID(tabIconID) != 0) {
			return BlockIDs.getID(tabIconID);
		} else if (ItemIDs.getID(tabIconID) != 0) {
			return ItemIDs.getShiftedID(tabIconID);
		} else {
			LogHelper.warn("Creative Tab for "+subName+" asked for ID "+tabIconID+", but it doesn't exist! Report this to me!");
			return Block.stone.blockID;
		}
	}
	
}
