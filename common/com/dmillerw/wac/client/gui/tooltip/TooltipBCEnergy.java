package com.dmillerw.wac.client.gui.tooltip;

import java.util.ArrayList;
import java.util.List;

import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;

public class TooltipBCEnergy extends TooltipSlot {

	public TileEntityAmalgamFurnace tile;
	
	public TooltipBCEnergy(int x, int y, int w, int h, TileEntityAmalgamFurnace tile) {
		super(x, y, w, h, null);
		this.tile = tile;
	}

	@Override
	public List<String> getTooltip() {
		List<String> display = new ArrayList<String>();
		
		display.add(tile.fakePowerAmount+"/"+tile.power.getMaxEnergyStored()+" MJ");
		
		return display;
	}
	
}
