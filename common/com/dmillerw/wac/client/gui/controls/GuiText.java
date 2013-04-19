package com.dmillerw.wac.client.gui.controls;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class GuiText extends Gui {

	public FontRenderer font;
	
	public String display;
	
	public int x;
	public int y;
	public int color;
	
	public GuiText(FontRenderer font, String display, int x, int y, int color) {
		this.font = font;
		
		this.display = display;
		
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void draw() {
		font.drawString(display, x, y, color);
	}
	
}
