package com.dmillerw.wac.gates;

import java.util.HashMap;
import java.util.Map;

import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.gates.arithmatic.GateAbsolute;
import com.dmillerw.wac.gates.arithmatic.GateAddition;
import com.dmillerw.wac.gates.arithmatic.GateAndAdd;
import com.dmillerw.wac.gates.arithmatic.GateAverage;
import com.dmillerw.wac.gates.arithmatic.GateCeiling;
import com.dmillerw.wac.gates.arithmatic.GateDelta;
import com.dmillerw.wac.gates.arithmatic.GateDivide;
import com.dmillerw.wac.gates.arithmatic.GateExponentialPowers;
import com.dmillerw.wac.gates.arithmatic.GateFloor;
import com.dmillerw.wac.gates.arithmatic.GateIdentity;
import com.dmillerw.wac.gates.arithmatic.GateIncDec;
import com.dmillerw.wac.gates.arithmatic.GateIncrement;
import com.dmillerw.wac.gates.arithmatic.GateInverse;
import com.dmillerw.wac.gates.arithmatic.GateModulo;
import com.dmillerw.wac.gates.arithmatic.GateMultiply;
import com.dmillerw.wac.gates.arithmatic.GateNegate;
import com.dmillerw.wac.gates.arithmatic.GatePi;
import com.dmillerw.wac.gates.arithmatic.GateRandom;
import com.dmillerw.wac.gates.arithmatic.GateRound;
import com.dmillerw.wac.gates.arithmatic.GateSign;
import com.dmillerw.wac.gates.arithmatic.GateSquareRoot;
import com.dmillerw.wac.gates.arithmatic.GateSubtract;

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
		} catch (InstantiationException | IllegalAccessException | NullPointerException e) {
			LogHelper.warn("Something tried to create a gate with the id of "+id+" but it doesn't exist.");
			return null;
		}
	}
	
	public static void initializeDefaultGates() {
		//Any commented gates still need to be implemented
		
		/* ARITHMATIC */
		registerGate(0, GateAbsolute.class);
		registerGate(1, GateAddition.class);
		registerGate(2, GateAndAdd.class);
		registerGate(3, GateAverage.class);
		registerGate(4, GateCeiling.class);
		registerGate(5, GateDivide.class);
		registerGate(6, GateDelta.class);
//		registerGate(7, GateDeltaRec.class);
		registerGate(8, GateDivide.class);
//		registerGate(9, GateExp.class);
		registerGate(10, GateExponentialPowers.class);
		registerGate(11, GateFloor.class);
		registerGate(12, GateIdentity.class);
		registerGate(13, GateIncrement.class);
		registerGate(14, GateIncDec.class);
		registerGate(15, GateInverse.class);
//		registerGate(16, GateLog.class);
//		registerGate(17, GateLog10.class);
		registerGate(18, GateModulo.class);
		registerGate(19, GateMultiply.class);
		registerGate(20, GateNegate.class);
		registerGate(21, GatePi.class);
//		registerGate(22, GatePercent.class);
		registerGate(23, GateRandom.class);
		registerGate(24, GateRound.class);
		registerGate(25, GateSign.class);
		registerGate(26, GateSquareRoot.class);
		registerGate(27, GateSubtract.class);
	}
	
}