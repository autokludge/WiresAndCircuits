package com.dmillerw.wac.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item itemDust;
	
	public static void init() {
		itemDust = new ItemDust(ItemIDs.getID("itemDust")).setUnlocalizedName("itemDust");
		GameRegistry.registerItem(itemDust, "itemDust");
		for (int i=0; i<ItemDust.itemNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(itemDust, 1, i), ItemDust.itemNames[i]);
		}
	}
	
}
