package com.dmillerw.wac.item.block;

import com.dmillerw.wac.block.BlockCleanroom;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockCleanroom extends ItemBlock {

	public ItemBlockCleanroom(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + BlockCleanroom.blockSubNames[itemstack.getItemDamage()];
	}
	
}
