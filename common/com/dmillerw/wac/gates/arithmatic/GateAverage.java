package com.dmillerw.wac.gates.arithmatic;

import static com.dmillerw.wac.gates.GateDataType.NUMBER;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.GateCategory;
import com.dmillerw.wac.gates.GateDataType;
import com.dmillerw.wac.tileentity.TileEntityGate;

public class GateAverage extends Gate {

	@Override
	public String getName() {
		return "Average";
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
	public void logic(TileEntityGate chip) {
		double[] inputs = new double[8];
		double output = 0;
		int count = 0;
		
		if (chip.inputs[0] != null) inputs[0] = (double) chip.inputs[0];
		if (chip.inputs[1] != null) inputs[1] = (double) chip.inputs[1];
		if (chip.inputs[2] != null) inputs[2] = (double) chip.inputs[2];
		if (chip.inputs[3] != null) inputs[3] = (double) chip.inputs[3];
		if (chip.inputs[4] != null) inputs[4] = (double) chip.inputs[4];
		if (chip.inputs[5] != null) inputs[5] = (double) chip.inputs[5];
		if (chip.inputs[6] != null) inputs[6] = (double) chip.inputs[6];
		if (chip.inputs[7] != null) inputs[7] = (double) chip.inputs[7];
		
		for (double num : inputs) {
			if (num > 0) {
				count++;
				output += num;
			}
		}
		
		chip.outputs[0] = output / count;
	}
	
}
