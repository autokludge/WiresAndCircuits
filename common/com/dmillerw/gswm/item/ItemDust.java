package com.dmillerw.gswm.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;

import com.dmillerw.gswm.GSWMMain;
import com.dmillerw.gswm.lib.ModInfo;

public class ItemDust extends Item {

	//TODO Make array
	public Icon texture;
	
	public ItemDust(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(GSWMMain.gwsmCreativeTab);
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
