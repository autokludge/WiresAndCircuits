package com.dmillerw.wac.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.dmillerw.wac.tileentity.TileEntityGate;
import com.dmillerw.wac.util.BlockCoord;
import com.dmillerw.wac.util.DataConnection;

public class TileEntityGateRenderer extends TileEntitySpecialRenderer {
	
	//VERY TEMPORARY
	public void renderWire(BlockCoord start, BlockCoord end) {
//		GL11.glTranslated(0, 0, 0);
//		GL11.glColor3f(1, 1, 1);
//		GL11.glLineWidth(5F);
//		Tessellator tess = Tessellator.instance;
//		tess.startDrawing(GL11.GL_LINES);
//		tess.addVertex(start.x, start.y, start.z);
//		tess.addVertex(end.x, end.y, end.z);
//		tess.draw();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		TileEntityGate gate = (TileEntityGate) tile;

		for (int i=0; i<gate.connectedOutputs.length; i++) {
			for (DataConnection connect : gate.connectedOutputs[i]) {
				renderWire(new BlockCoord((int)x, (int)y, (int)z), connect.gateLocation);
			}
		}
	}

}
