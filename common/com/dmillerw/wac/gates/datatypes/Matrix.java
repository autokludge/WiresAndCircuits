package com.dmillerw.wac.gates.datatypes;

public class Matrix {

	public int[][] data;
	
	public Matrix() {
		data = new int[2][2];
		
		for (int i=0; i<2; i++) {
			for (int j=0; j<2; j++) {
				data[i][j] = 0;
			}
		}
	}
	
	/** Fills by column. Fills one, then goes to the next one */
	public Matrix(int a1, int b1, int a2, int b2) {
		data = new int[2][2];
		
		data[1][1] = a1;
		data[1][2] = a2;
		data[2][1] = b1;
		data[2][2] = b2;
	}
	
}
