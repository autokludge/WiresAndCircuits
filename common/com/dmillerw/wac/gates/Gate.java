package com.dmillerw.wac.gates;

import com.dmillerw.wac.tileentity.TileEntityGate;

public abstract class Gate {

	public abstract String getName();
	
	public abstract GateCategory getCategory();
	
	public abstract GateDataType[] getInputDataTypes();
	
	public abstract GateDataType[] getOutputDataTypes();
	
	public abstract void logic(TileEntityGate chip);
	
}
