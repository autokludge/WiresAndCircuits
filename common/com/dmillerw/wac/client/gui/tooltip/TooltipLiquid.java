package com.dmillerw.wac.client.gui.tooltip;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.LiquidTank;

public class TooltipLiquid extends TooltipSlot {

	public ILiquidTank tank;
	
	public TooltipLiquid(int x, int y, int w, int h, LiquidTank tank) {
		super(x, y, w, h, null);
		this.tank = tank;
	}

	@Override
	public List<String> getTooltip() {
		List<String> display = new ArrayList<String>();
		
		if (tank.getLiquid() != null) {
			display.add(tank.getLiquid().asItemStack().getDisplayName());
			display.add(tank.getLiquid().amount+"/"+tank.getCapacity());
		} else {
			display.add("None");
			display.add("0/"+tank.getCapacity());
		}
		
		return display;
	}
	
}
