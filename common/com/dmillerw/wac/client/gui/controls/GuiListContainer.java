package com.dmillerw.wac.client.gui.controls;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

import com.dmillerw.wac.interfaces.IGuiInfo;
import com.dmillerw.wac.util.GuiElementInfo;

public class GuiListContainer extends Gui {

	public Minecraft mc;
	
	public static final int X_MARGIN = 5;
	public static final int Y_MARGIN = 5;
	public static final int BACKGROUND_COLOR = 0xFFFFFF;
	
	public float lastSlideValue = 1.0F;
	
	public int x;
	public int y;
	public int w;
	public int h;
	
	public HashMap<Gui, GuiElementInfo> elements = new HashMap<Gui, GuiElementInfo>();
	
	public IGuiInfo guiInfo;
	
	public GuiSlideControl parentSlider;
	
	public GuiListContainer(Minecraft mc, int x, int y, int w, int h, IGuiInfo guiInfo, GuiSlideControl parentSlider) {
		this.mc = mc;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.guiInfo = guiInfo;
		
		this.parentSlider = parentSlider;
	}
	
	/** Registers an element to display in the container. Coords are relative to the container coords */
	@SuppressWarnings("static-access")
	public void registerGuiElement(Gui gui) {
		GuiElementInfo info = new GuiElementInfo(gui);
		info.setXValue(info.x + (this.x + X_MARGIN));
		info.setYValue(info.y + (this.y + Y_MARGIN));
		//TODO get width adjustment working
		if (info.w > (this.w - (this.X_MARGIN * 2))) {
			info.setWValue(this.w - (this.X_MARGIN * 2));
		}
		elements.put(gui, info);
	}
	
	public void drawScreen(int x, int y, float f) {
		if (elements.size() == 0) {
			parentSlider.enabled = false;
		}
		
		for (Entry<Gui, GuiElementInfo> entry : elements.entrySet()) {
			Gui guiElement = entry.getKey();
			GuiElementInfo info = entry.getValue().copy();

			info.setYValue((int)Math.floor(info.y - (this.h * parentSlider.slideValue)));
			info.applyInfoToElement(guiElement);
			//TODO get width adjustment working
			if (info.w > (this.w - (this.X_MARGIN * 2))) {
				info.applyWidth(guiElement);
			}
			
			if (isElementInsideContainer(info)) {
				if (guiElement instanceof GuiButton) {
					((GuiButton)guiElement).drawButton(this.mc, x, y);
				} else if (guiElement instanceof GuiTextField) {
					((GuiTextField)guiElement).drawTextBox();
				} else if (guiElement instanceof GuiText) {
					((GuiText)guiElement).draw();
				}
			}
		}
		
		lastSlideValue = parentSlider.slideValue;
	}
	
	public boolean isElementInsideContainer(GuiElementInfo info) {
		return isInside(info.y, info.y + info.h);
	}
	
	//TODO Temporary. Needs better logic to ensure a nice graphical look
	public boolean isInside(int y1, int y2) {
		return (y1 >= this.y + Y_MARGIN) && (y2 <= (this.y + (this.h - Y_MARGIN)));
	}
	
}
