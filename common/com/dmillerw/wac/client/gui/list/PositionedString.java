package com.dmillerw.wac.client.gui.list;

import net.minecraft.client.gui.FontRenderer;

public class PositionedString {

	public FontRenderer font;
	
	public String string;
	
	public int x;
	public int y;
	public int color;
	
	public PositionedString(FontRenderer font, String string, int x, int y, int color) {
		this.font = font;
		
		this.string = string;
		
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public void draw() {
		font.drawString(string, x, y, color);
	}
	
}
