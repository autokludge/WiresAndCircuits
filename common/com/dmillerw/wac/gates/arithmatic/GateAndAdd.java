package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import static com.dmillerw.wac.gates.GateDataType.NUMBER;
import com.dmillerw.wac.tileentity.TileEntityChip;

public class GateAndAdd extends Gate {

	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public GateDataType[] getInputDataTypes() {
		return new GateDataType[] {NUMBER, NUMBER};
	}

	@Override
	public GateDataType[] getOutputDataTypes() {
		return new GateDataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityChip chip) {
		int output = 0;
		
		if ((int)chip.inputs[0] > 0 && (int)chip.inputs[1] > 0) {
			output = (int)chip.inputs[0] + (int)chip.inputs[1];
		}
		
		chip.outputs[0] = output;
	}
	
}
