package com.dmillerw.wac.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.core.options.Configurable;
import com.dmillerw.wac.core.options.OptionNumber;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.interfaces.IConfigurable;
import com.dmillerw.wac.interfaces.IDataHandler;
import com.dmillerw.wac.interfaces.ISideAttachment;

public class TileEntityScreen extends TileEntity implements ISideAttachment, IDataHandler, IConfigurable {

	private ForgeDirection attached;
	
	public Configurable options;
	
	public double input = 0;
	
	/* VANILLA */
	public TileEntityScreen() {
		options = new Configurable("Screen");
		options.setOption("roundType", new OptionNumber(0));
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("attached", (byte) attached.ordinal());
		options.saveSettings(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
		options.loadSettings(nbt);
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
	
    /* ISIDEATTACHMENT */
	@Override
	public void setSideAttached(ForgeDirection side) {
		attached = side;
	}

	@Override
	public ForgeDirection getSideAttached() {
		return attached;
	}

	/* IDATAHANDLER */
	@Override
	public DataType[] getInputDataTypes() {
		return new DataType[] {DataType.NUMBER};
	}

	@Override
	public DataType[] getOutputDataTypes() {
		return null;
	}

	@Override
	public Configurable getConfiguration() {
		return options;
	}

	@Override
	public void setConfiguration(Configurable config) {
		this.options = config;
	}

}
