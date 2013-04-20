package com.dmillerw.wac.interfaces;

import com.dmillerw.wac.gates.DataType;

public interface IDataHandler {

	public DataType[] getInputDataTypes();
	
	public DataType[] getOutputDataTypes();
	
}
