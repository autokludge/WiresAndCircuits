package com.dmillerw.wac.gates.arithmatic;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;

import static com.dmillerw.wac.gates.GateDataType.NUMBER;
import com.dmillerw.wac.tileentity.TileEntityChip;

public class GateAddition extends Gate {

	@Override
	public String getName() {
		return "Addition";
	}
	
	@Override
	public GateCategory getCategory() {
		return GateCategory.ARITHMETIC;
	}

	@Override
	public GateDataType[] getInputDataTypes() {
		return new GateDataType[] {NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER, NUMBER};
	}

	@Override
	public GateDataType[] getOutputDataTypes() {
		return new GateDataType[] {NUMBER};
	}

	@Override
	public void logic(TileEntityChip chip) {
		int output = 0;
		
		for (Object obj : chip.inputs) {
			if (obj != null && obj instanceof Integer) {
				output += (int)obj;
			}
		}
		
		chip.outputs[0] = output;
	}

}
