package com.dmillerw.wac.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.client.gui.controls.GuiListContainer;
import com.dmillerw.wac.client.gui.controls.GuiSlideControl;
import com.dmillerw.wac.client.gui.controls.GuiText;
import com.dmillerw.wac.client.gui.controls.GuiVerticalSlideControl;
import com.dmillerw.wac.gates.IOData;
import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIndexSelection extends GuiScreen {

	public int xSize = 134;
	public int ySize = 136;
	
	public ArrayList<IOData> indexes = new ArrayList<IOData>();

	public GuiSlideControl slider;
	
	public GuiListContainer container;
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		
		this.buttonList.add(slider = new GuiVerticalSlideControl(0, ((this.width - this.xSize) / 2) + 114, ((this.height - this.ySize) / 2) + 18, 110, 0F, 0F, 100F).setDrawBackground(false));
		
		container = new GuiListContainer(this.mc, ((this.width - this.xSize) / 2) + 9, ((this.height - this.ySize) / 2) + 18, 100, 110, this, slider);
		container.xSize = this.xSize;
		container.ySize = this.ySize;
		
//		container.registerGuiElement(new GuiText(this.mc.fontRenderer, "Test String", 0, 0, 0xFFFFFF));
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + ModInfo.MOD_ID.toLowerCase() + "/textures/gui/index.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		if (slider.enabled) {
			int scroll = Mouse.getDWheel();

			//TODO Make sure scrolling (and slider) values limit themselves based on element mapping length
			if (scroll != 0) {
				if (scroll > 0) {
					scroll = -1;
					this.slider.slideValue += (float) ((scroll - (this.slider.yPosition + 4)) / (float) (this.height - 8)) / 16;
				} else if (scroll < 0) {
					scroll = 1;
					this.slider.slideValue -= (float) ((scroll - (this.slider.yPosition + 4)) / (float) (this.height - 8)) / 16;
				}
				this.slider.applyScrollLimits();
			}
			
			super.drawScreen(x, y, f);
		}
		
		container.drawScreen(x, y, f);
	}

}
