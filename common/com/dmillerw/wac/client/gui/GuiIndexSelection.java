package com.dmillerw.wac.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIndexSelection extends GuiScreen {

	public void render(int index, List<String> indexes) {
		ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		FontRenderer font = Minecraft.getMinecraft().fontRenderer;
		int centerX = sr.getScaledWidth() / 2;
		int centerY = sr.getScaledHeight() / 2;
		
		for (int i=0; i<indexes.size(); i++){
			int textColor = 0xFFFFFF;
			
			if (index == i) {
				textColor = 0x777777;
			}
			
			this.drawCenteredString(font, indexes.get(i), centerX, centerY + ((font.FONT_HEIGHT + 5) * i), textColor);
		}
	}
	
}
