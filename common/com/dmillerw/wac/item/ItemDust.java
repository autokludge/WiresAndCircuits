package com.dmillerw.wac.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

public class ItemDust extends Item {

	public Icon[] textures;
	
	public static String[] itemSubNames = new String[] {"dustGreenstone"};
	public static String[] itemNames = new String[] {"Greenstone Dust"};
	
	public ItemDust(int id) {
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(WACMain.wacCreativeTab);
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return textures[damage];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i=0; i<itemSubNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	public void updateIcons(IconRegister register) {
		textures = new Icon[itemSubNames.length];
		
		for (int i=0; i<itemSubNames.length; i++) {
			textures[i] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":dust/"+itemSubNames[i]);
		}
	}
	
}
