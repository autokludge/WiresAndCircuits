package com.dmillerw.wac.interfaces;

import net.minecraftforge.common.ForgeDirection;

public interface ISideAttachment {

	public void setSideAttached(ForgeDirection side);
	
	public ForgeDirection getSideAttached();
	
}
