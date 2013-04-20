package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

public class GateCeiling extends Gate {

	@Override
	public String getName() {
		return "Ceiling";
	}
	
	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {DataType.NUMBER};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return new DataType[] {DataType.NUMBER};
	}

	@Override
	public void logic(TileEntityGate chip) {
		double A = 0;
		
		if (inputs[0] != null) A = (double) inputs[0];

		outputs[0] = Math.ceil(A);
	}
	
}
