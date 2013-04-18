package com.dmillerw.wac.client.gui.list;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class ListContainer {

	public Minecraft mc;
	
	public GuiScreen parent;
	
	public int x;
	public int y;
	public int w;
	public int h;
	
	public ListContainer(Minecraft mc, GuiScreen parent, int x, int y, int w, int h) {
		this.mc = mc;
		
		this.parent = parent;
		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
}
