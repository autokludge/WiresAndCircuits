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
      
    }
	
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int j = 0; j < this.crafters.size(); ++j) {
			
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value) {
		super.updateProgressBar(id, value);
		
    }
	
	@Override
	public boolean canInteractWith(EntityPlayer entityplayer) {
		return tile.isUseableByPlayer(entityplayer);
	}

}
