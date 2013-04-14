package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityChip;

public class GateAbsolute extends Gate {

	public GateAbsolute(int id) {
		super(id, GateCategory.ARITHMETIC);
	}

	@Override
	public GateDataType[] getInputDataTypes() {
		return new GateDataType[] {GateDataType.NUMBER};
	}

	@Override
	public GateDataType[] getOutputDataTypes() {
		return new GateDataType[] {GateDataType.NUMBER};
	}

	@Override
	public void logic(TileEntityChip chip) {
		chip.outputs[0] = Math.abs((int) chip.inputs[0]);
	}

}
