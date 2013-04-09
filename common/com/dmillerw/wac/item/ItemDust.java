package com.dmillerw.wac.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

public class ItemDust extends Item {

	//TODO Make array
	public Icon texture;
	
	public ItemDust(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(WACMain.wacCreativeTab);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	public void updateIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":dust/greenstone");
		System.out.println(texture.getIconName());
	}
	
}
