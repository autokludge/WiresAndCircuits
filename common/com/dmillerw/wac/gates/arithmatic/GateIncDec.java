package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

import static com.dmillerw.wac.gates.DataType.NUMBER;

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
	public DataType[] getInputDataTypes() {
		return new DataType[] {NUMBER, NUMBER, NUMBER, NUMBER};
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
		double D = 0;
		
		if (chip.inputs[0] != null) A = (double) chip.inputs[0];
		if (chip.inputs[1] != null) B = (double) chip.inputs[1];
		if (chip.inputs[2] != null) C = (double) chip.inputs[2];
		if (chip.inputs[3] != null) D = (double) chip.inputs[3];

		if (D > 0) {
			chip.outputs[0] = 0;
		}
		
		if (B > 0) {
			chip.outputs[0] = (int)chip.outputs[0] + A;
		}
		
		if (C > 0) {
			chip.outputs[0] = (int)chip.outputs[0] - A;
		}
	}

}
