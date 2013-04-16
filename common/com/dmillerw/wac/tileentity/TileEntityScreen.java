package com.dmillerw.wac.tileentity;

import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.interfaces.IConnectable;
import com.dmillerw.wac.interfaces.IDataHandler;
import com.dmillerw.wac.interfaces.ISideAttachment;
import com.dmillerw.wac.util.DataConnection;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityScreen extends TileEntity implements ISideAttachment, IDataHandler, IConnectable {

	//TODO this isn't getting loaded from nbt
	private ForgeDirection attached;
	
	public String input = "0";
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("attached", (byte) attached.ordinal());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
	}
	
	@Override
    public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
    	readFromNBT(pkt.customParam1);
    	worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public Packet getDescriptionPacket() {
		NBTTagCompound tag = new NBTTagCompound();
		writeToNBT(tag);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 0, tag);
    }
	
	@Override
	public void setSideAttached(ForgeDirection side) {
		attached = side;
	}

	@Override
	public ForgeDirection getSideAttached() {
		return attached;
	}

	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {DataType.STRING};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return null;
	}

	@Override
	public void linkOutput(int index, DataConnection end) {}

	@Override
	public void receiveInput(int index, Object value) {
		input = String.valueOf(value);
	}

}
