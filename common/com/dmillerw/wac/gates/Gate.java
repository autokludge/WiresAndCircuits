package com.dmillerw.wac.gates;

import com.dmillerw.wac.tileentity.TileEntityChip;

public abstract class Gate {

	protected int id;
	protected GateCategory cat;
	
	public Gate(int id, GateCategory cat) {
		GateManager.gates[id] = this;
		this.id = id;
		this.cat = cat;
	}
	
	public int getID() {
		return id;
	}
	
	public GateCategory getCategory() {
		return cat;
	}
	
	public abstract GateDataType[] getInputDataTypes();
	
	public abstract GateDataType[] getOutputDataTypes();
	
	public abstract void logic(TileEntityChip chip);
	
}
