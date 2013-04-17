package com.dmillerw.wac.core.options;

public class Option {

	public String id;
	public OptionType type;
	public Object value;
	
	public Option(String id, OptionType type, Object value) {
		this.id = id;
		this.type = type;
		this.value = value;
	}
	
}
