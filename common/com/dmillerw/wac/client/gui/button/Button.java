package com.dmillerw.wac.client.gui.button;

import com.dmillerw.wac.interfaces.IClickHandler;

public class Button {

	public int id;
	public int x;
	public int y;
	public int w;
	public int h;
	
	public String display;
	
	public ButtonManager bm;
	
	public Button(int id, int x, int y, int w, int h, String display) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.display = display;
	}
	
	public void draw() {

	}
	
	public void drawMousedOver() {

	}
	
	public void handleMouse(int mouseX, int mouseY) {
		if (mouseInside(mouseX, mouseY)) {
			drawMousedOver();
		}
	}
	
	public void handleClick(int mouseX, int mouseY) {
		if (mouseInside(mouseX, mouseY)) {
			((IClickHandler)bm.parentGUI).handleButtonClick(this.id);
		}
	}
	
	public boolean mouseInside(int mouseX, int mouseY) {
		if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
			return true;
		}
		
		return false;
	}
	
}
