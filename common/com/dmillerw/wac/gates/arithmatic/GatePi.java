package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

import static com.dmillerw.wac.gates.DataType.NUMBER;

public class GatePi extends Gate {

	@Override
	public String getName() {
		return "Pi";
	}

	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return null;
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return new DataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityGate chip) {
		chip.outputs[0] = Math.PI;
	}

}
