package com.dmillerw.wac.gates;

import java.util.HashMap;
import java.util.Map;

import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.gates.arithmatic.GateAbsolute;
import com.dmillerw.wac.gates.arithmatic.GateAddition;
import com.dmillerw.wac.gates.arithmatic.GateAndAdd;
import com.dmillerw.wac.gates.arithmatic.GateAverage;
import com.dmillerw.wac.gates.arithmatic.GateCeiling;
import com.dmillerw.wac.gates.arithmatic.GateDivide;

public class GateManager {

	public static Map<Integer, Class<? extends Gate>> gates = new HashMap<Integer, Class<? extends Gate>>();
	
	public static void registerGate(int id, Class<? extends Gate> gate) {
		gates.put(id, gate);
	}
	
	public static Class<? extends Gate> getGateClass(int id) {
		return gates.get(id);
	}
	
	public static Gate createGate(int id) {
		try {
			return gates.get(id).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			LogHelper.warn("Something tried to create a gate with the id of "+id+" but it doesn't exist.");
			return null;
		}
	}
	
	public static void initializeDefaultGates() {
		registerGate(0, GateAbsolute.class);
		registerGate(1, GateAddition.class);
		registerGate(2, GateAndAdd.class);
		registerGate(3, GateAverage.class);
		registerGate(4, GateCeiling.class);
		registerGate(5, GateDivide.class);
	}
	
}