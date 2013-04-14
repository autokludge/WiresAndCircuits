package com.dmillerw.wac.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

public class ItemWireSpool extends Item {

	private Icon texture;
	
	public ItemWireSpool(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(0);
		setCreativeTab(WACMain.wacCreativeTabItems);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wireSpool");
	}
	
}
