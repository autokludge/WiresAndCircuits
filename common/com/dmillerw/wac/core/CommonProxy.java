package com.dmillerw.wac.core;

import com.dmillerw.wac.tileentity.TileEntityChip;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityChip.class, "blockChip");
	}
	
}
