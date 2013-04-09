package com.dmillerw.gswm.core;

import com.dmillerw.gswm.item.ItemIDs;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabGSWM extends CreativeTabs {

	public CreativeTabGSWM() {
		super("gswm");
	}

	@Override
	public int getTabIconItemIndex() {
		return ItemIDs.getShiftedID("itemDust");
	}
	
}
