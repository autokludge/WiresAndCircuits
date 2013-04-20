package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;

import static com.dmillerw.wac.gates.DataType.NUMBER;
import com.dmillerw.wac.tileentity.TileEntityGate;

public class GateAddition extends Gate {

	@Override
	public String getName() {
		return "Addition";
	}
	
	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return new DataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityGate chip) {
		double[] inputs = new double[8];
		double output = 0;
		
		for (double num : inputs) {
			if (num > 0) {
				output += num;
			}
		}
		
		outputs[0] = output;
	}

}
