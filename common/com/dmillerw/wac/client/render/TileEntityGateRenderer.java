package com.dmillerw.wac.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.dmillerw.wac.tileentity.TileEntityGate;
import com.dmillerw.wac.util.BlockCoord;
import com.dmillerw.wac.util.GateConnection;

public class TileEntityGateRenderer extends TileEntitySpecialRenderer {
	
	//VERY TEMPORARY
	public void renderWire(BlockCoord start, BlockCoord end) {

	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		TileEntityGate gate = (TileEntityGate) tile;

		for (int i=0; i<gate.connectedOutputs.length; i++) {
			for (GateConnection connect : gate.connectedOutputs[i]) {
				renderWire(new BlockCoord((int)x, (int)y, (int)z), connect.gateLocation);
			}
		}
	}

}
