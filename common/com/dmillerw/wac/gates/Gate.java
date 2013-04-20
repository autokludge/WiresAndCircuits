package com.dmillerw.wac.gates;

import com.dmillerw.wac.interfaces.IDataHandler;
import com.dmillerw.wac.tileentity.TileEntityGate;

public abstract class Gate implements IDataHandler {

	public Object[] inputs;
	public Object[] outputs;
	
	public abstract String getName();
	
	public abstract GateCategory getCategory();
	
	public abstract void logic(TileEntityGate chip);
	
}
