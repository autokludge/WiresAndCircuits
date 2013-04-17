package com.dmillerw.wac.tank;

import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class OutputTank extends LiquidTank {

	public OutputTank(int capacity) {
		super(capacity);
	}

	@Override
	public int fill(LiquidStack resource, boolean doFill) {
		return 0;
	}
	
}
