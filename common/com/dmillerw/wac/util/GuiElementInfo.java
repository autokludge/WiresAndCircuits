package com.dmillerw.wac.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;

import com.dmillerw.wac.client.gui.controls.GuiText;

public class GuiElementInfo {

	public int x;
	public int y;
	public int w;
	public int h;
	
	public GuiElementInfo() {
		
	}
	
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
		} else if (gui instanceof GuiText) {
			getFromGUIElement(((GuiText)gui));
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void getFromGUIElement(GuiButton button) {
		this.x = button.xPosition;
		this.y = button.yPosition;

		try {
			Class clazz = button.getClass();
			Field wF = clazz.getDeclaredField("width");
			Field hF = clazz.getDeclaredField("height");
			
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
			Field xF = clazz.getDeclaredField("xPos");
			Field yF = clazz.getDeclaredField("yPos");
			Field wF = clazz.getDeclaredField("width");
			Field hF = clazz.getDeclaredField("height");
			
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
	
	public void getFromGUIElement(GuiText text) {
		this.x = text.x;
		this.y = text.y;
		this.w = text.font.getStringWidth(text.display);
		this.h = text.font.FONT_HEIGHT;
	}
	
	public GuiElementInfo setXValue(int x) {
		this.x = x;
		return this;
	}
	
	public GuiElementInfo setYValue(int y) {
		this.y = y;
		return this;
	}
	
	public GuiElementInfo setWValue(int w) {
		this.w = w;
		return this;
	}
	
	public GuiElementInfo setHValue(int h) {
		this.h = h;
		return this;
	}
	
	public void applyInfoToElement(Gui gui) {
		if (gui instanceof GuiButton) {
			applyCoordsToButton(((GuiButton)gui));
		} else if (gui instanceof GuiTextField) {
			applyCoordsToTextField(((GuiTextField)gui));
		} else if (gui instanceof GuiText) {
			applyCoordsToText(((GuiText)gui));
		}
	}
	
	public GuiElementInfo copy() {
		return new GuiElementInfo(x, y, w, h);
	}
	
	@SuppressWarnings("rawtypes")
	public void applyWidth(Gui gui) {
		if (gui instanceof GuiButton) {
			try {
				Class clazz = ((GuiButton)gui).getClass();
				setFinalCoord(gui, clazz.getDeclaredField("width"), w);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (gui instanceof GuiTextField) {
			try {
				Class clazz = ((GuiTextField)gui).getClass();
				setFinalCoord(gui, clazz.getDeclaredField("width"), w);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void applyCoordsToButton(GuiButton button) {
		button.xPosition = this.x;
		button.yPosition = this.y;
	}
	
	@SuppressWarnings("rawtypes")
	private void applyCoordsToTextField(GuiTextField text) {
		try {
			Class clazz = text.getClass();
			setFinalCoord(text, clazz.getDeclaredField("xPos"), x);
			setFinalCoord(text, clazz.getDeclaredField("yPos"), y);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void applyCoordsToText(GuiText text) {
		text.x = x;
		text.y = y;
	}
	
	private void setFinalCoord(Gui gui, Field field, int value) {
		try {
			field.setAccessible(true);
			
			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			
			field.setInt(gui, value);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
