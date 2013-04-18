package com.dmillerw.wac.tileentity;

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
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerFramework;
import buildcraft.core.IMachine;

import com.dmillerw.wac.interfaces.IRotatable;
import com.dmillerw.wac.tank.OutputTank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityAmalgamFurnace extends TileEntity implements IRotatable, IInventory, IPowerReceptor, IMachine, ITankContainer {

	private ForgeDirection rotation;
	
	private ItemStack[] inv = new ItemStack[3];
	
	public OutputTank recipeResultTank = new OutputTank(MAX_LIQUID);
	
	private static final int MAX_LIQUID = LiquidContainerRegistry.BUCKET_VOLUME * 10;
	private static final int MAX_ENERGY = 5000;
	
	public int currentBurnTime = 0;
	public int itemBurnTime = 0;
	
	@SideOnly(Side.CLIENT)
	public int fakePowerAmount = 0;
	
	public IPowerProvider power;
	
	public TileEntityAmalgamFurnace() {
		power = PowerFramework.currentFramework.createPowerProvider();
		power.configure(0, 50, 50, 50, MAX_ENERGY);
	}
	
	/* VANILLA */
	@Override
	public void updateEntity() {
		//TODO Do
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		currentBurnTime = nbt.getInteger("currentBurnTime");
		itemBurnTime = nbt.getInteger("itemBurnTime");
		PowerFramework.currentFramework.loadPowerProvider(this, nbt);
		
		/* ITEMS */
		NBTTagList nbttaglist = nbt.getTagList("Items");
        this.inv = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.inv.length) {
                this.inv[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }
        
        /* LIQUID */
        if (nbt.hasKey("liquid")) {
        	NBTTagCompound liquid = nbt.getCompoundTag("liquid");
        	
        	int id = liquid.getInteger("itemID");
        	int meta = liquid.getInteger("itemMeta");
        	int amount = liquid.getInteger("amount");
        	
        	LiquidStack liquidStack = new LiquidStack(id, amount, meta);
        	if (liquid.hasKey("tags")) {
        		liquidStack.extra = liquid.getCompoundTag("tags");
        	}
        	
        	recipeResultTank.setLiquid(liquidStack);
        }
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("rotation", (byte) rotation.ordinal());
		PowerFramework.currentFramework.savePowerProvider(this, nbt);
		nbt.setInteger("currentBurnTime", currentBurnTime);
		nbt.setInteger("itemBurnTime", itemBurnTime);
		
		/* ITEMS */
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.inv.length; ++i)
        {
            if (this.inv[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.inv[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
		nbt.setTag("Items", nbttaglist);
		
		/* LIQUID */
		if (recipeResultTank.getLiquid() != null) {
			NBTTagCompound liquid = new NBTTagCompound();
			
			liquid.setInteger("itemID", recipeResultTank.getLiquid().itemID);
			liquid.setInteger("itemMeta", recipeResultTank.getLiquid().itemMeta);
			liquid.setInteger("amount", recipeResultTank.getLiquid().amount);
			if (recipeResultTank.getLiquid().extra != null) {
				liquid.setTag("tags", recipeResultTank.getLiquid().extra);
			}
			
			nbt.setTag("liquid", liquid);
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
	
	/* MACHINE SPECIFIC */
	public int getScaledBurnTime(int i) {
		return currentBurnTime != 0 ? (int) (((float) currentBurnTime / (float) (itemBurnTime)) * i) : 0;
	}
	
	public int getScaledEnergy(int i) {
		return fakePowerAmount != 0 ? (int) (((float) fakePowerAmount / (float) (MAX_ENERGY)) * i) : 0;
	}

	public int getScaledLiquid(int i) {
		return getLiquidAmount() != 0 ? (int) (((float) getLiquidAmount() / (float) (MAX_LIQUID)) * i) : 0;
	}
	
	public int getLiquidAmount() {
		if (recipeResultTank.getLiquid() == null) {
			return 0;
		}
		
		return recipeResultTank.getLiquid().amount;
	}
	
	@Override
	public boolean isActive() {
		return currentBurnTime > 0;
	}
	
	/* IROTATABLE */
	@Override
	public ForgeDirection getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(ForgeDirection rotation) {
		this.rotation = rotation;
	}

	/* IINVENTORY */
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

	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
	}
	
	/* IPOWERRECEPTOR */
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

	/* IMACHINE */
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

	/* ITANKCONTAINER */
	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return drain(0, maxDrain, doDrain);
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return recipeResultTank.drain(maxDrain, doDrain);
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] {recipeResultTank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return recipeResultTank;
	}
	
}
