package com.dmillerw.wac.client.gui.tooltip;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;

public class TooltipManager {

	public List<TooltipSlot> tooltips;
	
	public GuiScreen parentGUI;
	
	public TooltipManager(GuiScreen parent) {
		this.parentGUI = parent;
		tooltips = new ArrayList<TooltipSlot>();
	}
	
	public void registerTooltipSlot(TooltipSlot tooltip) {
		tooltip.tm = this;
		tooltips.add(tooltip);
	}
	
	public void handleMouseOver(int mouseX, int mouseY) {
		for (TooltipSlot tooltip : tooltips) {
			tooltip.handleMouseOver(mouseX, mouseY);
		}
	}
	
}
