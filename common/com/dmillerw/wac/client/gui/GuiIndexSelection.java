package com.dmillerw.wac.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;

import com.dmillerw.wac.gates.IOData;
import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIndexSelection extends GuiScreen {

	private int xSize = 134;
	private int ySize = 136;
	
	public ArrayList<IOData> indexes = new ArrayList<IOData>();

	@Override
	public void initGui() {

	}

	@Override
	public void drawScreen(int x, int y, float f) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + ModInfo.MOD_ID.toLowerCase() + "/textures/gui/index.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}

}
