package com.dmillerw.wac.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

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
	}
	
}
