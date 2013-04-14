package com.dmillerw.wac.client;

import com.dmillerw.wac.client.render.TileEntityGateRenderer;
import com.dmillerw.wac.core.CommonProxy;
import com.dmillerw.wac.tileentity.TileEntityGate;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenders() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGate.class, new TileEntityGateRenderer());
	}
	
}
