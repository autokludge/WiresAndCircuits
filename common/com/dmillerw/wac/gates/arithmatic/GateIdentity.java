package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

import static com.dmillerw.wac.gates.DataType.NUMBER;

public class GateIdentity extends Gate {

	@Override
	public String getName() {
		return "Identity";
	}

	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {NUMBER};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return new DataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityGate chip) {
		double A = 0;
		
		if (chip.inputs[0] != null) A = (double) chip.inputs[0];

		chip.outputs[0] = A;
	}

}
