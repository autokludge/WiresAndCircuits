package com.dmillerw.wac.client.gui;

import java.util.ArrayList;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

import org.lwjgl.opengl.GL11;

import com.dmillerw.wac.client.gui.button.ButtonManager;
import com.dmillerw.wac.client.gui.button.ButtonSimple;
import com.dmillerw.wac.core.options.Option;
import com.dmillerw.wac.interfaces.IClickHandler;
import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiConfigurable extends GuiScreen implements IClickHandler {

	private int xSize = 195;
	private int ySize = 136;
	private int activeCategory = 0;
	
	private int guiTop;
	private int guiLeft;
	
	public ArrayList<Option> options = new ArrayList<Option>();
	public ArrayList<String> categories = new ArrayList<String>();
	
	public FontRenderer font;
	
	public ButtonManager bm;
	
	public GuiConfigurable(ArrayList<Option> options) {
		this.options = options;

		this.font = FMLClientHandler.instance().getClient().fontRenderer;
		
		this.bm = new ButtonManager(this, font);
		
		for (Option option : options) {
			if (!categories.contains(option.category)) {
				categories.add(option.category);
			}
		}
	}
	
	@Override
	public void handleButtonClick(int id) {
		if (id == 1) {
			activeCategory++;
		} else if (id == 0) {
			activeCategory--;
		}
		
		clampCategory();
	}
	
	public void clampCategory() {
		if (activeCategory >= categories.size()) {
			activeCategory = categories.size() - 1;
		}
		
		if (activeCategory < 0) {
			activeCategory = 0;
		}
	}
	
	@Override
	public void initGui() {
		guiLeft = (this.width - this.xSize) / 2;
		guiTop = (this.height - this.ySize) / 2;
		
		bm.registerButton(new ButtonSimple(0, guiLeft + 182 - 6 - font.getCharWidth('>'), guiTop + 6, font.getCharWidth('<'), font.FONT_HEIGHT, "<"));
		bm.registerButton(new ButtonSimple(1, guiLeft + 182, guiTop + 6, font.getCharWidth('>'), font.FONT_HEIGHT, ">"));
	}

	@Override
	public void drawScreen(int x, int y, float f) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture("/mods/" + ModInfo.MOD_ID.toLowerCase() + "/textures/gui/config.png");
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		
		this.fontRenderer.drawString(categories.get(activeCategory), k + 8, l + 6, 0x000000);
		
		bm.draw();
		bm.handleMouse(x, y);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int something) {
		bm.handleClick(mouseX, mouseY);
	}
	
}
