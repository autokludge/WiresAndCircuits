package com.dmillerw.wac.gates.datatypes;

public class Matrix {

	public int[][] data;
	
	/** Fills by column. Fills one, then goes to the next one */
	public Matrix(int a1, int b1, int a2, int b2) {
		data = new int[2][2];
		
		data[1][1] = a1;
		data[1][2] = a2;
		data[2][1] = b1;
		data[2][2] = b2;
	}
	
}
