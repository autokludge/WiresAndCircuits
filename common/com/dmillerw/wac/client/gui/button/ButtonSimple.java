package com.dmillerw.wac.client.gui.button;

public class ButtonSimple extends Button {

	public ButtonSimple(int id, int x, int y, int w, int h, String display) {
		super(id, x, y, w, h, display);
	}
	
	public void draw() {
		bm.font.drawString(display, x, y, 0xFFFFFF);
	}
	
	public void drawMousedOver() {
		bm.font.drawString(display, x, y, 0x000cff);
	}
	
	public void handleClick(int mouseX, int mouseY) {

	}
	
}
