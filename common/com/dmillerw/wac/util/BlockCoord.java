package com.dmillerw.wac.util;

public class BlockCoord {

	public int x;
	public int y;
	public int z;
	
	public BlockCoord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean equals(BlockCoord coord) {
		if (coord == null) return false;
		return equals(coord.x, coord.y, coord.z);
	}
	
	public boolean equals(int x, int y, int z) {
		return (this.x == x) && (this.y == y) && (this.z == z);
	}
	
}
