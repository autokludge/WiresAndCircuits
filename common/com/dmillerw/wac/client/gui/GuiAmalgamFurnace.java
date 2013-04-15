package com.dmillerw.wac.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

import com.dmillerw.wac.inventory.ContainerAmalgamFurnace;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;

public class GuiAmalgamFurnace extends GuiContainer {

	private TileEntityAmalgamFurnace tile;
	
	public GuiAmalgamFurnace(EntityPlayer player, TileEntityAmalgamFurnace tile) {
		super(new ContainerAmalgamFurnace(player, tile));
		
		this.tile = tile;
	}
	
	public void drawScreen(int mouseX, int mouseY, float f) {
		super.drawScreen(mouseX, mouseY, f);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture("/mods/"+ModInfo.MOD_ID.toLowerCase()+"/textures/gui/thingy.png");
        int k = (this.width - this.xSize) / 2;
        int m = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, m, 0, 0, this.xSize, this.ySize);

        if (tile.getScaledBurnTime(52) > 0) {
        	int l = tile.getScaledBurnTime(52);
        	drawTexturedModalRect(j + 80, (k + 24 + 12) - l, 176, 12 - l, 14, l + 2);
        }
	}
	
	private void displayEnergyGauge(int j, int k, int line, int col, int squaled) {
		if (tile.power == null) {
			return;
		}
		int start = 0;

		mc.renderEngine.bindTexture("/mods/"+ModInfo.MOD_ID.toLowerCase()+"/textures/gui/thingy.png");

		while (true) {
			int x = 0;

			if (squaled > 16) {
				x = 16;
				squaled -= 16;
			} else {
				x = squaled;
				squaled = 0;
			}

			this.drawTexturedModalRect(j + col, k + line + 58 - x - start, 176, 6, 16, 52 - (52 - x));
			start = start + 16;

			if (x == 0 || squaled == 0) {
				break;
			}
		}
	}
	
}
