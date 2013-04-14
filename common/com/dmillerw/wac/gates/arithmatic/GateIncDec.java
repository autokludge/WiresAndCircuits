package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityChip;

import static com.dmillerw.wac.gates.GateDataType.NUMBER;

public class GateIncDec extends Gate {

	@Override
	public String getName() {
		return "Increment/Decrement";
	}

	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public GateDataType[] getInputDataTypes() {
		return new GateDataType[] {NUMBER, NUMBER, NUMBER, NUMBER};
	}

	@Override
	public GateDataType[] getOutputDataTypes() {
		return new GateDataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityChip chip) {
		if ((int)chip.inputs[3] > 0) {
			chip.outputs[0] = 0;
		}
		
		if ((int)chip.inputs[1] > 0) {
			chip.outputs[0] = (int)chip.outputs[0] + (int)chip.inputs[0];
		}
		
		if ((int)chip.inputs[2] > 0) {
			chip.outputs[0] = (int)chip.outputs[0] - (int)chip.inputs[0];
		}
	}

}
