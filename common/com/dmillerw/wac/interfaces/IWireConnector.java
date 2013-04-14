package com.dmillerw.wac.interfaces;

import java.util.List;

import com.dmillerw.wac.util.Vector;

public interface IWireConnector {

	public List<Vector> getOutputConnections(int index);
	
	public void addOutputConnection(int index, Vector connection);
	
	public void setOutputConnections(int index, List<Vector> connections);
	
}
