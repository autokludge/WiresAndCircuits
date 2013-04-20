package com.dmillerw.wac.gates;

import java.util.List;

import com.dmillerw.wac.interfaces.IDataHandler;
import com.dmillerw.wac.tileentity.TileEntityGate;
import com.dmillerw.wac.util.DataConnection;

public abstract class Gate implements IDataHandler {

	public Object[] inputs;
	public Object[] outputs;
	
	public List<DataConnection>[] connectedOutputs;
	
	public abstract String getName();
	
	public abstract GateCategory getCategory();
	
	public abstract void logic(TileEntityGate chip);
	
}
