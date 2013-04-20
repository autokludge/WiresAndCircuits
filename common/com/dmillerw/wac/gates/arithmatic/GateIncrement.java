package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

import static com.dmillerw.wac.gates.DataType.NUMBER;

public class GateIncrement extends Gate {

	@Override
	public String getName() {
		return "Increment";
	}

	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {NUMBER, NUMBER, NUMBER};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return new DataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityGate chip) {
		double A = 0;
		double B = 0;
		double C = 0;
		
		if (inputs[0] != null) A = (double) inputs[0];
		if (inputs[1] != null) B = (double) inputs[1];
		if (inputs[2] != null) C = (double) inputs[2];
		
		if (C > 0) {
			outputs[0] = 0;
		}
		
		if (B > 0) {
			outputs[0] = (double)outputs[0] + A;
		}
	}

}
