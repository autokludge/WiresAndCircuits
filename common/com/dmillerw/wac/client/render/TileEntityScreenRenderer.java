package com.dmillerw.wac.client.render;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.interfaces.ISideAttachment;
import com.dmillerw.wac.tileentity.TileEntityScreen;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityScreenRenderer extends TileEntitySpecialRenderer {

	//TODO Make this more intelligent (resize based on text width, etc)
	@SideOnly(Side.CLIENT)
	public void renderScreenAt(TileEntityScreen tile, double x, double y, double z, float f) {
		String toDisplay = String.valueOf(tile.input);

		GL11.glPushMatrix();
		GL11.glTranslated(x+.5, y+.5, z+.5);
		FontRenderer font = FMLClientHandler.instance().getClient().fontRenderer;
		GL11.glTranslatef(0, 1, 0);
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		GL11.glTranslated(x+.5, y+.5, z+.5);
		
		ISideAttachment attached = tile;
		
		byte rotation = 0;
		if (attached.getSideAttached().getOpposite() == ForgeDirection.NORTH) {
			rotation = 0;
			GL11.glRotatef(180, 0, 0, 1);
		} else if (attached.getSideAttached().getOpposite() == ForgeDirection.WEST) {
			rotation = 1;
			GL11.glRotatef(180, 1, 0, 0);
		} else if (attached.getSideAttached().getOpposite() == ForgeDirection.SOUTH) {
			rotation = 2;
			GL11.glRotatef(180, 0, 0, 1);
		} else if (attached.getSideAttached().getOpposite() == ForgeDirection.EAST) {
			rotation = 3;
			GL11.glRotatef(180, 1, 0, 0);
		}
		
		GL11.glRotatef((rotation * 360) / 4, 0F, 1F, 0F);
		GL11.glTranslatef(0, 0, -0.51F);
		for (int i=0; i<7; i++) {
			GL11.glScalef(.5F, .5F, .5F);
		}
		
		GL11.glTranslatef(-font.getStringWidth(toDisplay) / 2, -font.FONT_HEIGHT / 2, 0);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glTranslated(0, 0, 29);
		font.drawString(toDisplay, 0, 0, 0xFFFFFF);
		GL11.glPopMatrix();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float f) {
		renderScreenAt((TileEntityScreen) tile, x, y, z, f);
	}
	
}
