package com.dmillerw.wac.interfaces;

import com.dmillerw.wac.util.GateConnection;

public interface IConnectable {

	public void linkOutput(int index, GateConnection end);
	
	public void receiveInput(int index, Object value);
	
}
