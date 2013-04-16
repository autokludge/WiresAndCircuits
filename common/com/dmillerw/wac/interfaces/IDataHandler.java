package com.dmillerw.wac.interfaces;

import com.dmillerw.wac.gates.DataType;

public interface IDataHandler {

	public abstract DataType[] getInputDataTypes();
	
	public abstract DataType[] getOutputDataTypes();
	
}
