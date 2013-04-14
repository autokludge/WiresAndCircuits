package com.dmillerw.wac.interfaces;

import net.minecraftforge.common.ForgeDirection;

public interface IRotatable {

	public ForgeDirection getRotation();
	
	public void setRotation(ForgeDirection rotation);
	
}
