package com.dmillerw.wac.gates.arithmatic;

import static com.dmillerw.wac.gates.DataType.NUMBER;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

public class GateDivide extends Gate {

	@Override
	public String getName() {
		return "Divide";
	}
	
	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {NUMBER, NUMBER};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return new DataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityGate chip) {
		double A = 0;
		double B = 0;
		
		if (inputs[0] != null) A = (double) inputs[0];
		if (inputs[1] != null) B = (double) inputs[1];
		
		outputs[0] = A / B;
	}
	
}
