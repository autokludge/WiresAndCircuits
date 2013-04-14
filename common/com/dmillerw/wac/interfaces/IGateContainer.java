package com.dmillerw.wac.interfaces;

import com.dmillerw.wac.gates.Gate;

public interface IGateContainer {

	public void setGate(int id);
	
	public int getGateID();
	
	public Gate getGate();
	
}
