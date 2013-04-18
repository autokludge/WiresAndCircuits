package com.dmillerw.wac.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAmalgamFurnace extends Container {

	private TileEntityAmalgamFurnace tile;
	
	private static final int ITEM_BURN_TIME = 0;
	private static final int CURRENT_BURN_TIME = 1;
	private static final int ENERGY_AMOUNT = 2;
	
	private int lastItemBurnTime = 0;
	private int lastCurrentBurnTime = 0;
	private int lastEnergyAmount = 0;
	
	public ContainerAmalgamFurnace(EntityPlayer player, TileEntityAmalgamFurnace tile) {
		this.tile = tile;
		
		/* INPUT/OUTPUT SLOTS */
		addSlotToContainer(new Slot(tile, 0, 56, 17));
		addSlotToContainer(new Slot(tile, 1, 56, 53));
		addSlotToContainer(new SlotOutput(tile, 2, 105, 35));
		
		/* PLAYER INVENTORY */
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
		}
	}
	
	public void addCraftingToCrafters(ICrafting crafter) {
      super.addCraftingToCrafters(crafter);
      crafter.sendProgressBarUpdate(this, ITEM_BURN_TIME, tile.itemBurnTime);
      crafter.sendProgressBarUpdate(this, CURRENT_BURN_TIME, tile.currentBurnTime);
      crafter.sendProgressBarUpdate(this, ENERGY_AMOUNT, (int) tile.power.getEnergyStored());
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int j = 0; j < this.crafters.size(); ++j) {
			ICrafting crafter = (ICrafting) crafters.get(j);
			
			if (this.lastItemBurnTime != tile.itemBurnTime) {
				crafter.sendProgressBarUpdate(this, ITEM_BURN_TIME, tile.itemBurnTime);
			}
			
			if (this.lastCurrentBurnTime != tile.currentBurnTime) {
				crafter.sendProgressBarUpdate(this, CURRENT_BURN_TIME, tile.currentBurnTime);
			}
			
			if (this.lastEnergyAmount != tile.power.getEnergyStored()) {
				crafter.sendProgressBarUpdate(this, ENERGY_AMOUNT, (int) tile.power.getEnergyStored());
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
		super.updateProgressBar(id, value);
		
		switch(id) {
			case ITEM_BURN_TIME: tile.itemBurnTime = value;
			case CURRENT_BURN_TIME: tile.currentBurnTime = value;
			case ENERGY_AMOUNT: tile.fakePowerAmount = value;
		}
		
		tile.worldObj.markBlockForUpdate(tile.xCoord, tile.yCoord, tile.zCoord);
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile.isUseableByPlayer(entityplayer);
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotNum) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotNum);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotNum == 2) {
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (slotNum != 1 && slotNum != 0) {
            	if (!this.mergeItemStack(itemstack1, 0, 1, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.stackSize == 0) {
                slot.putStack((ItemStack)null);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }
	
}
