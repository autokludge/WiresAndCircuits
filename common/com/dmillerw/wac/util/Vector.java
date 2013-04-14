package com.dmillerw.wac.util;

import net.minecraft.tileentity.TileEntity;

public class Vector {

	public double x;
	public double y;
	public double z;
	
	public Vector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(TileEntity tile) {
		this(tile.xCoord, tile.yCoord, tile.zCoord);
	}
	
}
