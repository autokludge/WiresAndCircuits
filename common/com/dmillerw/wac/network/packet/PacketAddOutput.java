package com.dmillerw.wac.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.dmillerw.wac.interfaces.IWireConnector;
import com.dmillerw.wac.util.Vector;
import com.dmillerw.wac.util.WireConnection;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketAddOutput extends PacketVector {

	public WireConnection input;
	
	public void write(ByteArrayDataOutput out) {
		super.write(out);
		out.writeDouble(input.block.x);
		out.writeDouble(input.block.y);
		out.writeDouble(input.block.z);
		out.writeInt(input.index);
	}
	
	public void read(ByteArrayDataInput in) {
		super.read(in);
		double x = in.readDouble();
		double y = in.readDouble();
		double z = in.readDouble();
		int i = in.readInt();
		
		input = new WireConnection(new Vector(x, y, z), i);
	}
	
	public void execute(EntityPlayer player, Side side) {
		World world = player.worldObj;
		IWireConnector connector = (IWireConnector) world.getBlockTileEntity((int)point.x, (int)point.y, (int)point.z);
		connector.addOutputConnection(input.index, input.block);
	}
	
}
