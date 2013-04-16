package com.dmillerw.wac.interfaces;

import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.util.GateConnection;

public interface IGateContainer {

	public void setGate(int id);
	
	public int getGateID();
	
	public Gate getGate();
	
	public void linkGate(int index, GateConnection end);
	
	public void receiveInput(int index, Object value);
	
}
