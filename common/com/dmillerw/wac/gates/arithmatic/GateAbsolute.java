package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

public class GateAbsolute extends Gate {

	@Override
	public String getName() {
		return "Absolute";
	}
	
	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
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
	public void logic(TileEntityGate chip) {
		double A = 0;
		
		if (chip.inputs[0] != null) A = (double) chip.inputs[0];
		
		chip.outputs[0] = Math.abs(A);
	}

}
