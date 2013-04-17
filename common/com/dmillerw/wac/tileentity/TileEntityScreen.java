package com.dmillerw.wac.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.core.options.Option;
import com.dmillerw.wac.core.options.OptionType;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.interfaces.IConfigurable;
import com.dmillerw.wac.interfaces.IConnectable;
import com.dmillerw.wac.interfaces.IDataHandler;
import com.dmillerw.wac.interfaces.ISideAttachment;
import com.dmillerw.wac.util.DataConnection;

public class TileEntityScreen extends TileEntity implements ISideAttachment, IDataHandler, IConnectable, IConfigurable {

	private ForgeDirection attached;
	
	/**  0 = no round
	 *  -1 = round down
	 *   1 = round up
	 */
	public int roundType = 0;
	
	public double input = 0;
	
	/* VANILLA */
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("attached", (byte) attached.ordinal());
		saveSettings(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setSideAttached(ForgeDirection.getOrientation(nbt.getByte("attached")));
		loadSettings(nbt);
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

	/* ICONNECTABLE */
	@Override
	public void linkOutput(int index, DataConnection end) {}

	@Override
	public void receiveInput(int index, Object value) {
		input = ((Double)value).doubleValue();
	}

	/* ICONFIGURABLE */
	@Override
	public void onSettingsChanged() {
		this.receiveInput(0, input);
	}
	
	@Override
	public void saveSettings(NBTTagCompound nbt) {
		NBTTagCompound options = new NBTTagCompound();
		options.setInteger("roundType", roundType);
		nbt.setTag("options", options);
	}

	@Override
	public void loadSettings(NBTTagCompound nbt) {
		if (nbt.hasKey("options")) {
			NBTTagCompound options = nbt.getCompoundTag("options");
			this.roundType = options.getInteger("roundType");
		}
	}

	@Override
	public void saveOption(String id, OptionType type, Object value) {
		if (id.equals("roundType")) {
			this.roundType = ((Integer)value).intValue();
		}
	}

	@Override
	public Object loadOption(String id) {
		if (id.equals("roundType")) {
			return this.roundType;
		}
		
		return null;
	}

	@Override
	public OptionType getOptionType(String id) {
		if (id.equals("roundType")) {
			return OptionType.CHECKBOXES;
		}
		
		return OptionType.UNKNOWN;
	}

	public String getOptionName(String id) {
		if (id.equals("roundType")) {
			return "Round Type";
		}
		
		return "";
	}
	
	@Override
	public String getOptionComment(String id) {
		if (id.equals("roundType")) {
			return "0 = don't round | -1 = round down | 1 = round up";
		}
		
		return "";
	}
	
	@Override
	public Option[] getAllOptions() {
		return new Option[] {new Option("roundType", OptionType.INTEGER, roundType)};
	}

}
