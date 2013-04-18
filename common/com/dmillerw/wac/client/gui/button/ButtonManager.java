package com.dmillerw.wac.client.gui.button;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public class ButtonManager {

	public List<Button> buttons;
	
	public GuiScreen parentGUI;
	
	public FontRenderer font;
	
	public ButtonManager(GuiScreen parent, FontRenderer font) {
		this.parentGUI = parent;
		buttons = new ArrayList<Button>();
		this.font = font;
	}
	
	public void registerButton(Button button) {
		button.bm = this;
		buttons.add(button);
	}
	
	public void handleMouse(int mouseX, int mouseY) {
		for (Button button : buttons) {
			button.handleMouse(mouseX, mouseY);
		}
	}
	
	public void handleClick(int mouseX, int mouseY) {
		for (Button button : buttons) {
			button.handleClick(mouseX, mouseY);
		}
	}
	
	public void draw() {
		for (Button button : buttons) {
			button.draw();
		}
	}
	
}
