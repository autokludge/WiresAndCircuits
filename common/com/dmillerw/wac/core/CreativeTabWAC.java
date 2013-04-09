package com.dmillerw.wac.core;

import com.dmillerw.wac.item.ItemIDs;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabWAC extends CreativeTabs {

	public CreativeTabWAC() {
		super("gswm");
	}

	@Override
	public int getTabIconItemIndex() {
		return ItemIDs.getShiftedID("itemDust");
	}
	
}
