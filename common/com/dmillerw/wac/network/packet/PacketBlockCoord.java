package com.dmillerw.wac.network.packet;

import net.minecraft.entity.player.EntityPlayer;

import com.dmillerw.wac.util.BlockCoord;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketBlockCoord extends PacketWAC {

	public BlockCoord coords;
	
	@Override
	public void write(ByteArrayDataOutput out) {
		out.write(coords.x);
		out.write(coords.y);
		out.write(coords.z);
	}

	@Override
	public void read(ByteArrayDataInput in) {
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		
		coords = new BlockCoord(x, y, z);
	}

	@Override
	public void execute(EntityPlayer player, Side side) {}

}
