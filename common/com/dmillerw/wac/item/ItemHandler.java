package com.dmillerw.wac.item;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ItemHandler {

	public static Item itemDust;
	
	public static void init() {
		//TODO Metadata
		itemDust = new ItemDust(ItemIDs.getID("itemDust")).setUnlocalizedName("itemDust");
		GameRegistry.registerItem(itemDust, "itemDust");
		LanguageRegistry.addName(itemDust, "Greenstone Dust");
	}
	
}
