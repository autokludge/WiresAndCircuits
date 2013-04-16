package com.dmillerw.wac.core;

import com.dmillerw.wac.block.BlockMachine;
import com.dmillerw.wac.tileentity.TileEntityGate;
import com.dmillerw.wac.tileentity.TileEntityScreen;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerRenders() {}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityGate.class, "blockGate");
		GameRegistry.registerTileEntity(TileEntityScreen.class, "blockScreen");
		
		for (int i=0; i<BlockMachine.machineTileEntities.length; i++) {
			GameRegistry.registerTileEntity(BlockMachine.machineTileEntities[i], BlockMachine.machineTENames[i]);
		}
	}
	
}
