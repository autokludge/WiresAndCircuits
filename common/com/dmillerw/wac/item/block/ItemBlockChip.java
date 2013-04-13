package com.dmillerw.wac.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.block.BlockCleanroom;
import com.dmillerw.wac.block.BlockIDs;
import com.dmillerw.wac.interfaces.IAttachedToSide;

public class ItemBlockChip extends ItemBlock {

	public ItemBlockChip(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
		
		//Getting actual block coords based on side hit of targeted block
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		x += sideForge.offsetX;
		y += sideForge.offsetY;
		z += sideForge.offsetZ;
		
		IAttachedToSide attached = (IAttachedToSide) world.getBlockTileEntity(x, y, z);
		if (attached == null) return false;
		attached.setSideAttached(ForgeDirection.getOrientation(side).getOpposite());
		
		return true;
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		int xOrig = x - sideForge.offsetX;
		int yOrig = y - sideForge.offsetY;
		int zOrig = z - sideForge.offsetZ;
		
		if (!world.isBlockSolidOnSide(xOrig, yOrig, zOrig, sideForge)) {
			return false;
		}
		
		if (!world.setBlock(x, y, z, BlockIDs.getID("blockChip"), metadata, 3)) {
			return false;
		}

		if (world.getBlockId(x, y, z) == BlockIDs.getID("blockChip")) {
			Block.blocksList[BlockIDs.getID("blockChip")].onBlockPlacedBy(world, x, y, z, player, stack);
			Block.blocksList[BlockIDs.getID("blockChip")].onPostBlockPlaced(world, x, y, z, metadata);
		}

       return true;
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
