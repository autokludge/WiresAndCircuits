package com.dmillerw.wac.interfaces;

import net.minecraftforge.common.ForgeDirection;

public interface IAttachedToSide {

	public void setSideAttached(ForgeDirection side);
	
	public ForgeDirection getSideAttached();
	
}
