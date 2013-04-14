package com.dmillerw.wac.gates;

public class GateManager {

	public static Gate[] gates = new Gate[256];
	
	public static Gate getGate(int id) {
		return gates[id];
	}
	
	public static int getInputCount(int id) {
		return gates[id].getInputDataTypes().length;
	}
	
	public static int getOutputCount(int id) {
		return gates[id].getOutputDataTypes().length;
	}
	
}