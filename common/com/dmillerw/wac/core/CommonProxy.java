package com.dmillerw.wac.core;

import com.dmillerw.wac.tileentity.TileEntityGate;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerRenders() {}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityGate.class, "blockChip");
	}
	
}
