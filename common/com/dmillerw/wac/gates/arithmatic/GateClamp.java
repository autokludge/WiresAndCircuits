package com.dmillerw.wac.gates.arithmatic;

import static com.dmillerw.wac.gates.GateDataType.NUMBER;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityChip;

public class GateClamp extends Gate {

	@Override
	public String getName() {
		return "Clamp";
	}
	
	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public GateDataType[] getInputDataTypes() {
		return new GateDataType[] {NUMBER, NUMBER, NUMBER};
	}

	@Override
	public GateDataType[] getOutputDataTypes() {
		return new GateDataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityChip chip) {
		int output = (int)chip.inputs[0];
		if (output < (int)chip.inputs[1]) output = (int)chip.inputs[1];
		if (output < (int)chip.inputs[2]) output = (int)chip.inputs[2];
		chip.outputs[0] = output;
	}
	
}
