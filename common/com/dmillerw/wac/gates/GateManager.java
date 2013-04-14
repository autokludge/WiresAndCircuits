package com.dmillerw.wac.gates;

import java.util.HashMap;
import java.util.Map;

import com.dmillerw.wac.core.helper.LogHelper;

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
	
}