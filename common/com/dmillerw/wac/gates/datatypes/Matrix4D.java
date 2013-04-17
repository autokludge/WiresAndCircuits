package com.dmillerw.wac.gates.datatypes;

public class Matrix4D {

	public int[][] data;
	
	public Matrix4D() {
		data = new int[4][4];
		
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				data[i][j] = 0;
			}
		}
	}
	
	/** Fills by column. Fills one, then goes to the next one */
	public Matrix4D(int a1, int b1, int c1, int d1, int a2, int b2, int c2, int d2, int a3, int b3, int c3, int d3, int a4, int b4, int c4, int d4) {
		data = new int[4][4];
		
		data[1][1] = a1;
		data[1][2] = a2;
		data[1][3] = a3;
		data[1][4] = a4;
		data[2][1] = b1;
		data[2][2] = b2;
		data[2][3] = b3;
		data[2][4] = b4;
		data[3][1] = c1;
		data[3][2] = c2;
		data[3][3] = c3;
		data[3][4] = c4;
		data[4][1] = d1;
		data[4][2] = d2;
		data[4][3] = d3;
		data[4][4] = d4;
	}
	
}
