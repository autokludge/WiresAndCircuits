package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityChip;

import static com.dmillerw.wac.gates.GateDataType.NUMBER;

public class GateSign extends Gate {

	@Override
	public String getName() {
		return "Sign";
	}

	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public GateDataType[] getInputDataTypes() {
		return new GateDataType[] {NUMBER};
	}

	@Override
	public GateDataType[] getOutputDataTypes() {
		return new GateDataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityChip chip) {
		double A = 0;
		
		if (chip.inputs[0] != null) A = (double) chip.inputs[0];
		
		if (A < 0) chip.outputs[0] = -1;
		if (A == 0) chip.outputs[0] = 0;
		if (A > 0) chip.outputs[0] = 1;
	}

}
