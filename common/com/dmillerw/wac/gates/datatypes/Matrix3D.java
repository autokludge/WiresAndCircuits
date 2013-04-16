package com.dmillerw.wac.gates.datatypes;

public class Matrix3D {

	public int[][] data;
	
	/** Fills by column. Fills one, then goes to the next one */
	public Matrix3D(int a1, int b1, int c1, int a2, int b2, int c2, int a3, int b3, int c3) {
		data = new int[3][3];
		
		data[1][1] = a1;
		data[1][2] = a2;
		data[1][3] = a3;
		data[2][1] = b1;
		data[2][2] = b2;
		data[2][3] = b3;
		data[3][1] = c1;
		data[3][2] = c2;
		data[3][3] = c3;
	}
	
}
