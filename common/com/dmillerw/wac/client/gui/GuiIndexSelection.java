package com.dmillerw.wac.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.client.gui.controls.GuiListContainer;
import com.dmillerw.wac.client.gui.controls.GuiSlideControl;
import com.dmillerw.wac.client.gui.controls.GuiVerticalSlideControl;
import com.dmillerw.wac.gates.IOData;
import com.dmillerw.wac.interfaces.IGuiInfo;
import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIndexSelection extends GuiScreen implements IGuiInfo {

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
		
		container.registerGuiElement(new GuiTextField(this.mc.fontRenderer, 0, 0, container.w - (GuiListContainer.X_MARGIN * 2), 20));
		container.registerGuiElement(new GuiButton(1, 0, 0 + 20 + GuiListContainer.Y_MARGIN, container.w - (GuiListContainer.X_MARGIN * 2), 20, "It's a button"));
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
				} else if (scroll < 0) {
					scroll = 1;
				}
				
				this.slider.slide(scroll);
				this.slider.applyScrollLimits();
			}
			
			super.drawScreen(x, y, f);
		}
		
		container.drawScreen(x, y, f);
	}

	public void keyTyped(char par1, int par2) {
		super.keyTyped(par1, par2);
		container.keyTyped(par1, par2);
	}
	
	public void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		container.mouseClicked(par1, par2, par3);
	}
	
	@Override
	public int[] getGuiDimensions() {
		return new int[] {this.width, this.height, this.xSize, this.ySize, ((this.width - this.xSize) / 2), ((this.height - this.ySize) / 2)};
	}

}
