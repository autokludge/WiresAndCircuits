package com.dmillerw.wac.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

import com.dmillerw.wac.client.gui.controls.GuiText;

public class GuiElementInfo {

	public int xOrig;
	public int yOrig;
	public int wOrig;
	public int hOrig;
	
	public int x;
	public int y;
	public int w;
	public int h;
	
	public GuiElementInfo(int x, int y, int w, int h) {
		this.x = this.xOrig = x;
		this.y = this.yOrig = y;
		this.w = this.wOrig = w;
		this.h = this.hOrig = h;
	}
	
	public GuiElementInfo(Gui gui) {
		getFromGUIElement(gui);
	}
	
	public void getFromGUIElement(Gui gui) {
		if (gui instanceof GuiButton) {
			getFromGUIElement(((GuiButton)gui));
		} else if (gui instanceof GuiTextField) {
			getFromGUIElement(((GuiTextField)gui));
		} else if (gui instanceof GuiText) {
			getFromGUIElement(((GuiText)gui));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void getFromGUIElement(GuiButton button) {
		this.x = this.xOrig = button.xPosition;
		this.y = this.yOrig = button.yPosition;

		try {
			Class clazz = button.getClass();
			Field wF = clazz.getField("width");
			Field hF = clazz.getField("height");
			
			wF.setAccessible(true);
			hF.setAccessible(true);
			
			this.w = this.wOrig = wF.getInt(clazz);
			this.h = this.hOrig = hF.getInt(clazz);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void getFromGUIElement(GuiTextField text) {
		try {
			Class clazz = text.getClass();
			Field xF = clazz.getField("xPos");
			Field yF = clazz.getField("yPos");
			Field wF = clazz.getField("width");
			Field hF = clazz.getField("height");
			
			xF.setAccessible(true);
			yF.setAccessible(true);
			wF.setAccessible(true);
			hF.setAccessible(true);
			
			this.x = this.xOrig = xF.getInt(clazz);
			this.y = this.yOrig = yF.getInt(clazz);
			this.w = this.wOrig = wF.getInt(clazz);
			this.h = this.hOrig = hF.getInt(clazz);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getFromGUIElement(GuiText text) {
		this.x = this.xOrig = text.x;
		this.y = this.yOrig = text.y;
		this.w = this.wOrig = text.font.getStringWidth(text.display);
		this.h = this.hOrig = text.font.FONT_HEIGHT;
	}
	
	public GuiElementInfo setXValue(int x) {
		this.x = x;
		return this;
	}
	
	public GuiElementInfo setYValue(int y) {
		this.y = y;
		return this;
	}
	
	public void applyCoordsToElement(Gui gui) {
		if (gui instanceof GuiButton) {
			applyCoordsToButton(((GuiButton)gui));
		} else if (gui instanceof GuiTextField) {
			applyCoordsToTextField(((GuiTextField)gui));
		} else if (gui instanceof GuiText) {
			applyCoordsToText(((GuiText)gui));
		}
	}
	
	public void reset() {
		this.x = xOrig;
		this.y = yOrig;
		this.w = wOrig;
		this.h = hOrig;
	}
	
	private void applyCoordsToButton(GuiButton button) {
		button.xPosition = this.x;
		button.yPosition = this.y;
	}
	
	@SuppressWarnings("rawtypes")
	private void applyCoordsToTextField(GuiTextField text) {
		try {
			Class clazz = text.getClass();
			setFinalCoord(clazz, clazz.getField("xPos"), x);
			setFinalCoord(clazz, clazz.getField("yPos"), y);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void applyCoordsToText(GuiText text) {
		text.x = x;
		text.y = y;
	}
	
	@SuppressWarnings("rawtypes")
	private void setFinalCoord(Class parentClass, Field field, int value) {
		try {
			field.setAccessible(true);
			
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			
			field.setInt(parentClass, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
