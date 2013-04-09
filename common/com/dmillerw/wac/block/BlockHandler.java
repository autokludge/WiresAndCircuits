package com.dmillerw.wac.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;

public class BlockHandler {

	public static Block blockOre;
	
	public static void init() {
		//TODO ItemBlock implementation and metadata
		blockOre = new BlockOre(BlockIDs.getID("blockOre")).setUnlocalizedName("blockOre");
		GameRegistry.registerBlock(blockOre, "blockOre");
		LanguageRegistry.addName(blockOre, "Greenstone Ore");
	}
	
}
