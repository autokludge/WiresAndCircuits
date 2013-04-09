package com.dmillerw.wac.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item itemIngot;
	
	public static void init() {
		itemIngot = new ItemIngot(ItemIDs.getID("itemIngot")).setUnlocalizedName("itemIngot");
		GameRegistry.registerItem(itemIngot, "itemIngot");
		for (int i=0; i<ItemIngot.itemNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(itemIngot, 1, i), ItemIngot.itemNames[i]);
			System.out.println(i+":"+ItemIngot.itemNames[i]+":"+ItemIngot.itemSubNames[i]);
		}
	}
	
}
