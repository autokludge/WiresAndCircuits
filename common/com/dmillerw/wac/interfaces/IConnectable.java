package com.dmillerw.wac.interfaces;

import com.dmillerw.wac.util.DataConnection;

public interface IConnectable {

	public void linkOutput(int index, DataConnection end);
	
	public void receiveInput(int index, Object value);
	
}
