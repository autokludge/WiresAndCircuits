package com.dmillerw.wac.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerAmalgamFurnace extends Container {

	private TileEntityAmalgamFurnace tile;
	
	private int BURN_TIME_ID = 0;
	private int ENERGY_AMOUNT_ID = 1;
//	private int LIQUID_AMOUNT_ID = 2;
	
	private int lastBurnTime = 0;
	private int lastEnergyAmount = 0;
//	private int lastLiquidAmount = 0;
	
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
	
	public void addCraftingToCrafters(ICrafting par1ICrafting) {
        super.addCraftingToCrafters(par1ICrafting);
        par1ICrafting.sendProgressBarUpdate(this, BURN_TIME_ID, tile.currentBurnTime);
        par1ICrafting.sendProgressBarUpdate(this, ENERGY_AMOUNT_ID, (int) tile.power.getEnergyStored());
//      par1ICrafting.sendProgressBarUpdate(this, LIQUID_AMOUNT_ID, );
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int j = 0; j < this.crafters.size(); ++j) {
			if (lastBurnTime != tile.currentBurnTime) {
				((ICrafting) this.crafters.get(j)).sendProgressBarUpdate(this, BURN_TIME_ID, tile.currentBurnTime);
			}
			
			if (lastEnergyAmount != tile.power.getEnergyStored()) {
				((ICrafting) this.crafters.get(j)).sendProgressBarUpdate(this, ENERGY_AMOUNT_ID, (int) tile.power.getEnergyStored());
			}
		}
		
		this.lastBurnTime = tile.currentBurnTime;
		this.lastEnergyAmount = (int) tile.power.getEnergyStored();
	}
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
		if (id == BURN_TIME_ID) {
			this.tile.currentBurnTime = value;
		}

		if (id == ENERGY_AMOUNT_ID) {
			tile.fakePowerAmount = value;
		}

//		if (id == LIQUID_AMOUNT_ID) {
//			
//		}
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile.isUseableByPlayer(entityplayer);
	}

}
