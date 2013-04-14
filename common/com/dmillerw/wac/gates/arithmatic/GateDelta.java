package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

import static com.dmillerw.wac.gates.GateDataType.NUMBER;

public class GateDelta extends Gate {

	double currA = 0;
	
	@Override
	public String getName() {
		return "Delta";
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
	public void logic(TileEntityGate chip) {
		double output = (double) chip.inputs[0];
		output -= currA;
		currA = output;
		chip.outputs[0] = output;
	}

}
