package com.dmillerw.wac.gates.datatypes;

public class Angle {

	/** UP TO DOWN */
	public double pitch;
	/** LEFT TO RIGHT */
	public double yaw;
	/** DO A BARREL ROLL */
	public double roll;
	
	public Angle() {
		this(0,0,0);
	}
	
	public Angle(double pitch, double yaw, double roll) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.roll = roll;
	}
	
}
