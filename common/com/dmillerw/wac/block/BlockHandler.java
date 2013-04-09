package com.dmillerw.wac.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

import com.dmillerw.wac.item.ItemHandler;
import com.dmillerw.wac.item.block.ItemBlockOre;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block blockOre;
	
	public static void init() {
		blockOre = new BlockOre(BlockIDs.getID("blockOre")).setUnlocalizedName("blockOre");
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "blockOre");
		for (int i=0; i<BlockOre.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockOre, 1, i), BlockOre.blockNames[i]);
		}
		
		addOreDictionarySupport();
	}
	
	public static void addOreDictionarySupport() {
		for (int i=0; i<BlockOre.blockSubNames.length; i++) {
			OreDictionary.registerOre(BlockOre.blockSubNames[i], new ItemStack(blockOre, 1, i));
		}
	}
	
	public static void addSmeltingRecipes() {
		//Ore blocks
		for (int i=0; i<BlockOre.blockNames.length; i++) {
			FurnaceRecipes.smelting().addSmelting(blockOre.blockID, i, new ItemStack(ItemHandler.itemIngot, 1, i), 0.5F);
		}
	}
	
}
