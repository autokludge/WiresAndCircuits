package com.dmillerw.wac.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item itemIngot;
	public static Item wireSpool;
	
	public static void init() {
		itemIngot = new ItemIngot(ItemIDs.getID("itemIngot")).setUnlocalizedName("itemIngot");
		GameRegistry.registerItem(itemIngot, "itemIngot");
		for (int i=0; i<ItemIngot.itemNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(itemIngot, 1, i), ItemIngot.itemNames[i]);
		}

		wireSpool = new ItemWireSpool(ItemIDs.getID("wireSpool")).setUnlocalizedName("wireSpool");
		GameRegistry.registerItem(wireSpool, "wireSpool");
		for (int i=0; i<ItemWireSpool.itemNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(wireSpool, 1, i), ItemWireSpool.itemNames[i]);
		}
	}
	
}
