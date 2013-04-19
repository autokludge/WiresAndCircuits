package com.dmillerw.wac.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

public class GuiElementInfo {

	public int x;
	public int y;
	public int w;
	public int h;
	
	public GuiElementInfo(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public GuiElementInfo(Gui gui) {
		getFromGUIElement(gui);
	}
	
	public void getFromGUIElement(Gui gui) {
		if (gui instanceof GuiButton) {
			getFromGUIElement(((GuiButton)gui));
		} else if (gui instanceof GuiTextField) {
			getFromGUIElement(((GuiTextField)gui));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void getFromGUIElement(GuiButton button) {
		this.x = button.xPosition;
		this.y = button.yPosition;

		try {
			Class clazz = button.getClass();
			Field wF = clazz.getField("width");
			Field hF = clazz.getField("height");
			
			wF.setAccessible(true);
			hF.setAccessible(true);
			
			this.w = wF.getInt(clazz);
			this.h = hF.getInt(clazz);
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
			
			this.x = xF.getInt(clazz);
			this.y = yF.getInt(clazz);
			this.w = wF.getInt(clazz);
			this.h = hF.getInt(clazz);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public GuiElementInfo setXValue(int x) {
		this.x = x;
		return this;
	}
	
	public GuiElementInfo setYValue(int y) {
		this.y = y;
		return this;
	}
	
	public void applyToElement(Gui gui) {
		if (gui instanceof GuiButton) {
			applyToButton(((GuiButton)gui));
		} else if (gui instanceof GuiTextField) {
			applyToTextField(((GuiTextField)gui));
		}
	}
	
	private void applyToButton(GuiButton button) {
		button.xPosition = this.x;
		button.yPosition = this.y;
	}
	
	@SuppressWarnings("rawtypes")
	private void applyToTextField(GuiTextField text) {
		try {
			Class clazz = text.getClass();
			setFinalCoord(clazz, clazz.getField("xPos"), x);
			setFinalCoord(clazz, clazz.getField("yPos"), y);
		} catch(Exception e) {
			e.printStackTrace();
		}
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
