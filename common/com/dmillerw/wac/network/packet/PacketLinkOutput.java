package com.dmillerw.wac.network.packet;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.dmillerw.wac.interfaces.IConnectable;
import com.dmillerw.wac.util.BlockCoord;
import com.dmillerw.wac.util.DataConnection;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketLinkOutput extends PacketBlockCoord {

	public DataConnection connection;
	
	public int startIndex;
	
	@Override
	public void write(ByteArrayDataOutput out) {
		super.write(out);
		out.writeInt(connection.gateLocation.x);
		out.writeInt(connection.gateLocation.y);
		out.writeInt(connection.gateLocation.z);
		out.writeInt(connection.gateIndex);
		out.writeInt(startIndex);
	}

	@Override
	public void read(ByteArrayDataInput in) {
		super.read(in);
		int x = in.readInt();
		int y = in.readInt();
		int z = in.readInt();
		int endIndex = in.readInt();
		int startIndex = in.readInt();
		
		connection = new DataConnection(new BlockCoord(x, y, z), endIndex);
		this.startIndex = startIndex;
	}

	@Override
	public void execute(EntityPlayer player, Side side) {
		if (side == Side.SERVER) {
			World world = player.worldObj;
			TileEntity tile = world.getBlockTileEntity(coords.x, coords.y, coords.z);
		
			if (tile != null && tile instanceof IConnectable) {
				((IConnectable)tile).linkOutput(startIndex, connection);
				world.markBlockForUpdate(connection.gateLocation.x, connection.gateLocation.y, connection.gateLocation.z);
			}
		}
	}
	
}
