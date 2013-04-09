package com.dmillerw.wac.item;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHandler {

	public static Item itemIngot;
	public static Item cleanroomHat;
	public static Item cleanroomSuit;
	public static Item cleanroomPants;
	public static Item cleanroomShoes;
	
	public static void init() {
		itemIngot = new ItemIngot(ItemIDs.getID("itemIngot")).setUnlocalizedName("itemIngot");
		GameRegistry.registerItem(itemIngot, "itemIngot");
		for (int i=0; i<ItemIngot.itemNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(itemIngot, 1, i), ItemIngot.itemNames[i]);
		}
		
		cleanroomHat = new ItemCleanroomSuit(ItemIDs.getID("cleanroomHat"), EnumArmorMaterial.CLOTH, 0, 0).setUnlocalizedName("cleanroomHat");
		cleanroomSuit = new ItemCleanroomSuit(ItemIDs.getID("cleanroomSuit"), EnumArmorMaterial.CLOTH, 1, 0).setUnlocalizedName("cleanroomSuit");
		cleanroomPants = new ItemCleanroomSuit(ItemIDs.getID("cleanroomPants"), EnumArmorMaterial.CLOTH, 2, 0).setUnlocalizedName("cleanroomPants");
		cleanroomShoes = new ItemCleanroomSuit(ItemIDs.getID("cleanroomShoes"), EnumArmorMaterial.CLOTH, 3, 0).setUnlocalizedName("cleanroomShoes");
		
		GameRegistry.registerItem(cleanroomHat, "cleanroomHat");
		GameRegistry.registerItem(cleanroomSuit, "cleanroomSuit");
		GameRegistry.registerItem(cleanroomPants, "cleanroomPants");
		GameRegistry.registerItem(cleanroomShoes, "cleanroomShoes");
		
		LanguageRegistry.addName(cleanroomHat, "Cleanroom Hat");
		LanguageRegistry.addName(cleanroomSuit, "Cleanroom Suit");
		LanguageRegistry.addName(cleanroomPants, "Cleanroom Pants");
		LanguageRegistry.addName(cleanroomShoes, "Cleanroom Shoes");
	}
	
}
