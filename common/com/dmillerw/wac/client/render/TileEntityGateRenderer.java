package com.dmillerw.wac.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.tileentity.TileEntityGate;
import com.dmillerw.wac.util.BlockCoord;
import com.dmillerw.wac.util.GateConnection;

public class TileEntityGateRenderer extends TileEntitySpecialRenderer {
	
	//VERY TEMPORARY
	public void renderWire(BlockCoord start, BlockCoord end) {
		GL11.glPushMatrix();
		GL11.glColor3f(0,0,0);
		GL11.glTranslated(start.x, start.y, start.z);
	    GL11.glBegin(GL11.GL_LINES);
	    GL11.glVertex3f(0, 0, 0);
	    GL11.glVertex3f(end.x, end.y, end.z);
	    GL11.glEnd();
	    GL11.glPopMatrix();
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
