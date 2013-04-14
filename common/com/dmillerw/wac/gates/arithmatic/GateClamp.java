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
		double A = 0;
		double B = 0;
		double OUTA = 0;
		
		if (chip.inputs[0] != null) A = (double) chip.inputs[0];
		if (chip.inputs[1] != null) B = (double) chip.inputs[1];
		if (chip.outputs[0] != null) OUTA = (double)chip.inputs[0];
		
		if (OUTA < A) OUTA = A;
		if (OUTA < B) OUTA = B;
		chip.outputs[0] = OUTA;
	}
	
}
