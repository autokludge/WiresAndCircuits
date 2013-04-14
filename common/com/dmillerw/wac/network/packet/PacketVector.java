package com.dmillerw.wac.network.packet;

import net.minecraft.entity.player.EntityPlayer;

import com.dmillerw.wac.util.Vector;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketVector extends PacketWAC {

	public Vector point;
	
	public void write(ByteArrayDataOutput out) {
		out.writeDouble(point.x);
		out.writeDouble(point.y);
		out.writeDouble(point.z);
	}
	
	public void read(ByteArrayDataInput in) {
		double x = in.readDouble();
		double y = in.readDouble();
		double z = in.readDouble();
		
		point = new Vector(x, y, z);
	}
	
	public void execute(EntityPlayer player, Side side) {
		
	}
	
}
