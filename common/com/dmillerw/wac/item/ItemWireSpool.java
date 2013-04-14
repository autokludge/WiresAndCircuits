package com.dmillerw.wac.item;

import com.dmillerw.wac.WACMain;

import net.minecraft.item.Item;

public class ItemWireSpool extends Item {

	public ItemWireSpool(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(0);
		setCreativeTab(WACMain.wacCreativeTabItems);
	}
	
}
