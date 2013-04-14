package com.dmillerw.wac.item.block;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.dmillerw.wac.block.BlockMachine;

public class ItemBlockMachine extends ItemBlock {

	public ItemBlockMachine(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + BlockMachine.machineTENames[itemstack.getItemDamage()];
	}
	
}
