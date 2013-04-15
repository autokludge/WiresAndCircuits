package com.dmillerw.wac.tileentity;

import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;
import buildcraft.core.IMachine;

import com.dmillerw.wac.interfaces.IRotatable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityAmalgamFurnace extends TileEntity implements IRotatable, IInventory, IPowerReceptor, IMachine {

	private ForgeDirection rotation;
	
	private ItemStack[] inv = new ItemStack[3];
	
//	private static final int MAX_LIQUID = LiquidContainerRegistry.BUCKET_VOLUME * 10;
	private static final int MAX_ENERGY = 5000;
	
	public int currentBurnTime = 0;
	public int itemBurnTime = 0;
	
	public IPowerProvider power;
	
	public TileEntityAmalgamFurnace() {
		System.out.println("Generating powerframework");
		power = PowerFramework.currentFramework.createPowerProvider();
		power.configure(0, 50, 50, 50, MAX_ENERGY);
	}
	
	@Override
	public void updateEntity() {
		if (worldObj.isRemote) return;
		
		System.out.println(power.getEnergyStored());
		
		if (getStackInSlot(0) != null) {
			itemBurnTime = 50;
		} else {
			itemBurnTime = 0;
		}
		
//		if (power.useEnergy(50, 50, true) == 50) {
//			System.out.println("Used 50");
//		}
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		currentBurnTime = nbt.getInteger("currentBurnTime");
		itemBurnTime = nbt.getInteger("itemBurnTime");
		PowerFramework.currentFramework.loadPowerProvider(this, nbt);
		
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.inv.length; ++i) {
			if (this.inv[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inv[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("rotation", (byte) rotation.ordinal());
		PowerFramework.currentFramework.savePowerProvider(this, nbt);
		nbt.setInteger("currentBurnTime", currentBurnTime);
		nbt.setInteger("itemBurnTime", itemBurnTime);
		
		NBTTagList nbttaglist = nbt.getTagList("Items");
		this.inv = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbt1 = (NBTTagCompound) nbttaglist.tagAt(i);
			byte slot = nbt1.getByte("Slot");

			if (slot >= 0 && slot < this.inv.length) {
				this.inv[slot] = ItemStack.loadItemStackFromNBT(nbt1);
			}
		}
	}
	
	@Override
	public Packet getDescriptionPacket() {
		Packet132TileEntityData packet = new Packet132TileEntityData();
		NBTTagCompound tag = new NBTTagCompound();

		this.writeToNBT(tag);

		packet.xPosition = this.xCoord;
		packet.yPosition = this.yCoord;
		packet.zPosition = this.zCoord;
		packet.customParam1 = tag;

		return packet;
	}

	@Override
	public void onDataPacket(INetworkManager network, Packet132TileEntityData packet) {
		if (packet.xPosition == this.xCoord && packet.yPosition == this.yCoord && packet.zPosition == this.zCoord) {
			readFromNBT(packet.customParam1);
		}
	}
	
	@Override
	public ForgeDirection getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(ForgeDirection rotation) {
		this.rotation = rotation;
	}

	@Override
	public int getSizeInventory() {
		return 3;
	}

	public ItemStack decrStackSize(int slot, int amount) {
		if (this.inv[slot] != null) {
			ItemStack itemstack;

			if (this.inv[slot].stackSize <= amount) {
				itemstack = this.inv[slot];
				this.inv[slot] = null;
				return itemstack;
			} else {
				itemstack = this.inv[slot].splitStack(amount);

				if (this.inv[slot].stackSize == 0) {
					this.inv[slot] = null;
				}

				return itemstack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inv[slot];
	}
	
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.inv[slot] != null) {
			ItemStack itemstack = this.inv[slot];
			this.inv[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int slot, ItemStack stack) {
		this.inv[slot] = stack;

		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName() {
		return null;
	}

	@Override
	public boolean isInvNameLocalized() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return true;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}

	@Override
	public boolean isStackValidForSlot(int slot, ItemStack itemstack) {
		return slot != 2;
	}

	@Override
	public void setPowerProvider(IPowerProvider provider) {
		power = provider;
	}

	@Override
	public IPowerProvider getPowerProvider() {
		return power;
	}

	@Override
	public void doWork() {}

	@Override
	public int powerRequest(ForgeDirection from) {
		return 50;
	}

	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}
	
	public int getScaledBurnTime(int i) {
		return currentBurnTime != 0 ? (int) (((float) currentBurnTime / (float) (itemBurnTime)) * i) : 0;
	}
	
	public int getScaledEnergy(int i) {
		return power.getEnergyStored() != 0 ? (int) (((float) power.getEnergyStored() / (float) (MAX_ENERGY)) * i) : 0;
	}

	@Override
	public boolean isActive() {
		return itemBurnTime > 0;
	}

	@Override
	public boolean manageLiquids() {
		return false;
	}

	@Override
	public boolean manageSolids() {
		return false;
	}

	@Override
	public boolean allowActions() {
		return false;
	}
	
}
