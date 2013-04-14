package com.dmillerw.wac.client.render;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.tileentity.TileEntityGate;

public class TileEntityGateRenderer extends TileEntitySpecialRenderer {

	private RenderManager renderManager = RenderManager.instance;
	
	protected void renderLabel(TileEntityGate tile, List<String> display, double x, double y, double z, int i) {
        if (display == null || display.size() == 0) return;
		
		double d3 = getDistanceSqToEntity(tile, this.renderManager.livingPlayer);

        x += 0.5;
        z += 0.5;
        
        if (d3 <= (double)(i * i)) {
            FontRenderer fontrenderer = renderManager.getFontRenderer();
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GL11.glPushMatrix();
            GL11.glTranslated((float)x + 0.0F, (float)y + (0.25 * display.size()), (float)z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator tessellator = Tessellator.instance;
            byte b0 = 0;
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glScalef(.5F, .5F, .5F);
            int count = display.size();
            int j = 0;
            for (String string : display) {
            	if (fontrenderer.getStringWidth(string) / 2 > j) {
            		j = fontrenderer.getStringWidth(string) / 2;
            	}
            }
            tessellator.startDrawingQuads();
            tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.50F);
            tessellator.addVertex((double)(-j - 1), (double)(-1), 0.0D);
            tessellator.addVertex((double)(-j - 1), (double)((fontrenderer.FONT_HEIGHT * count)), 0.0D);
            tessellator.addVertex((double)(j + 1), (double)((fontrenderer.FONT_HEIGHT * count)), 0.0D);
            tessellator.addVertex((double)(j + 1), (double)(-1), 0.0D);
            tessellator.draw();
            for (String string : display) {
            	GL11.glEnable(GL11.GL_TEXTURE_2D);
                fontrenderer.drawString(string, -fontrenderer.getStringWidth(string) / 2, b0, 553648127);
                GL11.glEnable(GL11.GL_DEPTH_TEST);
                GL11.glDepthMask(true);
                fontrenderer.drawString(string, -fontrenderer.getStringWidth(string) / 2, b0, -1);
                GL11.glTranslated(0, fontrenderer.FONT_HEIGHT, 0);
            }
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
        }
    }
	
	public double getDistanceSqToEntity(TileEntity tile, Entity entity) {
		double d0 = tile.xCoord - entity.posX;
		double d1 = tile.yCoord - entity.posY;
		double d2 = tile.zCoord - entity.posZ;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		List<String> display = new ArrayList<String>();
		display.add("ARITHMATIC: Absolute");
		display.add("A:NUMBER");
		display.add("B:NUMBER");
		renderLabel((TileEntityGate) tile, display, x, y, z, 16);
	}

}
