package com.dmillerw.wac.gates;

public class IOData {

	/*
	 * Just used to simplify the displaying of data inputs/outputs
	 */
	
	public char indexChar;
	
	/** In = 0 | Out = 1 */
	public byte inOrOut;
	
	public DataType type;
	
	public Object value;
	
	public IOData(char indexChar, byte inOrOut, DataType type) {
		this(indexChar, inOrOut, type, null);
	}
	
	public IOData(char indexChar, byte inOrOut, DataType type, Object value) {
		this.indexChar = indexChar;
		this.inOrOut = inOrOut;
		this.type = type;
		this.value = value;
	}
	
}
