package com.dmillerw.wac.client.gui.controls;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

import com.dmillerw.wac.util.GuiElementInfo;

public class GuiListContainer extends Gui {

	public Minecraft mc;
	
	public static final int X_MARGIN = 10;
	public static final int Y_MARGIN = 10;
	
	public float lastSlideValue = 0.0F;
	
	public int x;
	public int y;
	public int w;
	public int h;
	
	public int xSize;
	public int ySize;
	
	public HashMap<Gui, GuiElementInfo> elements = new HashMap<Gui, GuiElementInfo>();
	
	public GuiScreen parentGui;
	
	public GuiSlideControl parentSlider;
	
	public GuiListContainer(Minecraft mc, int x, int y, int w, int h, GuiScreen parentGui, GuiSlideControl parentSlider) {
		this.mc = mc;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.parentGui = parentGui;
		
		this.parentSlider = parentSlider;
	}
	
	public void drawScreen(int x, int y, float f) {
		for (Entry<Gui, GuiElementInfo> entry : elements.entrySet()) {
			Gui guiElement = entry.getKey();
			GuiElementInfo info = entry.getValue();
			
			if (lastSlideValue != parentSlider.slideValue) {
				info.setYValue((int)Math.floor(info.y - (this.h * parentSlider.slideValue)));
				info.applyToElement(guiElement);
			}
			
			if (isCoordSetInsideGUI(info)) {
				if (guiElement instanceof GuiButton) {
					((GuiButton)guiElement).drawButton(this.mc, x, y);
				} else if (guiElement instanceof GuiTextField) {
					((GuiTextField)guiElement).drawTextBox();
				}
			}
		}
		
		lastSlideValue = parentSlider.slideValue;
	}
	
	public boolean isCoordSetInsideGUI(GuiElementInfo info) {
		return isInside(info.x, info.y, info.x + info.w, info.y + info.h);
	}
	
	public boolean isInside(int x1, int y1, int x2, int y2) {
		int guiX1 = (parentGui.width - this.xSize) / 2;
		int guiY1 = (parentGui.height - this.ySize) / 2;
		int guiX2 = guiX1 + this.xSize;
		int guiY2 = guiY1 + this.ySize;
		
		return (x1 >= guiX1) && (y1 >= guiY1) && (x2 <= guiX2) && (y2 <= guiY2);
	}
	
}
