package com.dmillerw.wac.gates;

import com.dmillerw.wac.tileentity.TileEntityGate;

public abstract class Gate {

	public abstract String getName();
	
	public abstract GateCategory getCategory();
	
	public abstract DataType[] getInputDataTypes();
	
	public abstract DataType[] getOutputDataTypes();
	
	public abstract void logic(TileEntityGate chip);
	
}
