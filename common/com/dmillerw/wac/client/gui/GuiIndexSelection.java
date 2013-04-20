package com.dmillerw.wac.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.client.gui.controls.GuiListContainer;
import com.dmillerw.wac.client.gui.controls.GuiSlideControl;
import com.dmillerw.wac.client.gui.controls.GuiSpecialButton;
import com.dmillerw.wac.client.gui.controls.GuiVerticalSlideControl;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.gates.IOData;
import com.dmillerw.wac.interfaces.IGuiInfo;
import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIndexSelection extends GuiScreen implements IGuiInfo {

	public int xSize = 134;
	public int ySize = 136;
	
	public static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public String title;
	
	public ArrayList<IOData> indexes = new ArrayList<IOData>();

	public GuiSlideControl slider;
	
	public GuiListContainer container;
	
	public GuiIndexSelection(DataType[] io, byte type) {
		for (int i=0; i<io.length; i++) {
			indexes.add(new IOData(alphabet.charAt(i), type, io[i]));
		}
		title = type == 1 ? "Output" : "Input";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();
		
		container = new GuiListContainer(this.mc, ((this.width - this.xSize) / 2) + 9, ((this.height - this.ySize) / 2) + 18, 100, 110, this, slider);
		
		for (int i=0; i<indexes.size(); i++) {
			IOData data = indexes.get(i);
			container.registerGuiElement(new GuiSpecialButton(i, 0, 0 + (20 * i) + (GuiListContainer.Y_MARGIN * i), (container.w - (GuiListContainer.X_MARGIN * 2)), 20, data.indexChar+": "+data.type.toString()));
		}
		
		this.buttonList.add(slider = new GuiVerticalSlideControl(indexes.size(), ((this.width - this.xSize) / 2) + 114, ((this.height - this.ySize) / 2) + 18, 110, 0F, 0F, 100F).setDrawBackground(false));
		container.parentSlider = slider;
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + ModInfo.MOD_ID.toLowerCase() + "/textures/gui/index.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

		this.fontRenderer.drawString(title, k + 8, l + 6, 0x000000);
		
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
