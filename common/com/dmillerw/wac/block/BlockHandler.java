package com.dmillerw.wac.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.OreDictionary;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateManager;
import com.dmillerw.wac.item.ItemHandler;
import com.dmillerw.wac.item.block.ItemBlockGate;
import com.dmillerw.wac.item.block.ItemBlockCleanroom;
import com.dmillerw.wac.item.block.ItemBlockMachine;
import com.dmillerw.wac.item.block.ItemBlockOre;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BlockHandler {

	public static Block blockOre;
	public static Block blockCleanroom;
	public static Block blockGate;
	public static Block blockMachine;
	
	public static void init() {
		blockOre = new BlockOre(BlockIDs.getID("blockOre")).setUnlocalizedName("blockOre");
		GameRegistry.registerBlock(blockOre, ItemBlockOre.class, "blockOre");
		for (int i=0; i<BlockOre.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockOre, 1, i), BlockOre.blockNames[i]);
		}
		
		blockCleanroom = new BlockCleanroom(BlockIDs.getID("blockCleanroom")).setUnlocalizedName("blockCleanroom");
		GameRegistry.registerBlock(blockCleanroom, ItemBlockCleanroom.class, "blockCleanroom");
		for (int i=0; i<BlockCleanroom.blockNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockCleanroom, 1, i), BlockCleanroom.blockNames[i]);
		}
		
		blockGate = new BlockGate(BlockIDs.getID("blockGate")).setUnlocalizedName("blockGate");
		GameRegistry.registerBlock(blockGate, ItemBlockGate.class, "blockGate");
		for (int i=0; i<GateManager.gates.size(); i++) {
			Gate gate = GateManager.createGate(i);
			if (gate != null) {
				LanguageRegistry.addName(new ItemStack(blockGate, 1, i), gate.getCategory()+": "+gate.getName());
			}
		}
		
		blockMachine = new BlockMachine(BlockIDs.getID("blockMachine")).setUnlocalizedName("blockMachine");
		GameRegistry.registerBlock(blockMachine, ItemBlockMachine.class, "blockMachine");
		for (int i=0; i<BlockMachine.machineNames.length; i++) {
			LanguageRegistry.addName(new ItemStack(blockMachine, 1, i), BlockMachine.machineNames[i]);
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
