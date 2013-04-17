package com.dmillerw.wac.gates.datatypes;

public class Vector4D {

	public int x;
	public int y;
	public int z;
	public int w;
	
	public Vector4D() {
		this(0, 0, 0, 0);
	}
	
	public Vector4D(int x, int y, int z, int w) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
}
