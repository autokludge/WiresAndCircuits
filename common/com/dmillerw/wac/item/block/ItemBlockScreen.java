package com.dmillerw.wac.item.block;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.block.BlockIDs;
import com.dmillerw.wac.interfaces.ISideAttachment;
import com.dmillerw.wac.tileentity.TileEntityScreen;

public class ItemBlockScreen extends ItemBlock {

	public ItemBlockScreen(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (sideForge != ForgeDirection.UP && sideForge != ForgeDirection.DOWN) {
			super.onItemUse(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
			
			//Getting actual block coords based on side hit of targeted block
			x += sideForge.offsetX;
			y += sideForge.offsetY;
			z += sideForge.offsetZ;
		
		
			TileEntityScreen screen = (TileEntityScreen) world.getBlockTileEntity(x, y, z);
			if (screen == null) return false;
			((ISideAttachment)screen).setSideAttached(ForgeDirection.getOrientation(side).getOpposite());
			return true;
		}
		
		return false;
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
		
		if (!world.setBlock(x, y, z, BlockIDs.getID("blockScreen"), metadata, 3)) {
			return false;
		}

		if (world.getBlockId(x, y, z) == BlockIDs.getID("blockScreen")) {
			Block.blocksList[BlockIDs.getID("blockScreen")].onBlockPlacedBy(world, x, y, z, player, stack);
			Block.blocksList[BlockIDs.getID("blockScreen")].onPostBlockPlaced(world, x, y, z, metadata);
		}

       return true;
    }
	
}
