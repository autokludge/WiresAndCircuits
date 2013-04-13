package com.dmillerw.wac.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.interfaces.IAttachedToSide;
import com.dmillerw.wac.tileentity.TileEntityChip;

public class BlockChip extends BlockContainer {

	public static float CHIP_THICKNESS = 0.10F;
	
	public BlockChip(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(WACMain.wacCreativeTabBlocks);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {return null;}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		IAttachedToSide attached = (IAttachedToSide) world.getBlockTileEntity(x, y, z);
		
		int xOrig = x;
		int yOrig = y;
		int zOrig = z;
		
		if (attached == null) {
			this.dropBlockAsItem(world, xOrig, yOrig, zOrig, world.getBlockMetadata(xOrig, yOrig, zOrig), 0);
			world.setBlock(xOrig, yOrig, zOrig, 0);
			return;
		}
		
		x += attached.getSideAttached().offsetX;
		y += attached.getSideAttached().offsetY;
		z += attached.getSideAttached().offsetZ;
		
		if (Block.blocksList[world.getBlockId(x, y, z)] == null) {
			this.dropBlockAsItem(world, xOrig, yOrig, zOrig, world.getBlockMetadata(xOrig, yOrig, zOrig), 0);
			world.setBlock(xOrig, yOrig, zOrig, 0);
			return;
		}
		
		if (!Block.blocksList[world.getBlockId(x, y, z)].isBlockSolidOnSide(world, x, y, z, attached.getSideAttached())) {
			this.dropBlockAsItem(world, xOrig, yOrig, zOrig, world.getBlockMetadata(xOrig, yOrig, zOrig), 0);
			world.setBlock(xOrig, yOrig, zOrig, 0);
			return;
		}
	}
	
	public Icon getIcon(int side, int meta) {
		return Block.blockIron.getIcon(side, meta);
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		IAttachedToSide attached = (IAttachedToSide) world.getBlockTileEntity(x, y, z);
		
		if (attached.getSideAttached() == ForgeDirection.UP) {
			setBlockBounds(0.10F, 0.90F, 0.10F, 0.90F, 0.90F + CHIP_THICKNESS, 0.90F);
		} else if (attached.getSideAttached() == ForgeDirection.DOWN) {
			setBlockBounds(0.10F, 0F, 0.10F, 0.90F, 0F + CHIP_THICKNESS, 0.90F);
		} else if (attached.getSideAttached() == ForgeDirection.WEST) {
			setBlockBounds(0F, 0.10F, 0.10F, 0F + CHIP_THICKNESS, 0.90F, 0.90F);
		} else if (attached.getSideAttached() == ForgeDirection.EAST) {
			setBlockBounds(0.90F, 0.10F, 0.10F, 0.90F + CHIP_THICKNESS, 0.90F, 0.90F);
		} else if (attached.getSideAttached() == ForgeDirection.SOUTH) {
			setBlockBounds(0.10F, 0.10F, 0.90F, 0.90F, 0.90F, 0.90F + CHIP_THICKNESS);
		} else if (attached.getSideAttached() == ForgeDirection.NORTH) {
			setBlockBounds(0.10F, 0.10F, 0F, 0.90F, 0.90F, 0F + CHIP_THICKNESS);
		}
	}
	
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.10F, 0F, 0.10F, 0.90F, 0.10F, 0.90F);
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityChip();
	}
	
}
