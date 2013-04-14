package com.dmillerw.wac.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.gates.GateManager;
import com.dmillerw.wac.interfaces.IAttachedToSide;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.tileentity.TileEntityGate;

public class BlockGate extends BlockContainer {

	public static float CHIP_THICKNESS = 0.10F;
	
	private Icon[] textures;
	
	public BlockGate(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(WACMain.wacCreativeTabGates);
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
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		IAttachedToSide attached = (IAttachedToSide) world.getBlockTileEntity(x, y, z);
		
		if (ForgeDirection.getOrientation(side) == attached.getSideAttached().getOpposite()) {
			return textures[1];
		}
		
		return textures[0];
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return side == 1 ? textures[1] : textures[0];
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[2];
		
		textures[0] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":chip/side");
		textures[1] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":chip/front");
	}
	
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		IAttachedToSide attached = (IAttachedToSide) world.getBlockTileEntity(x, y, z);
		
		if (attached.getSideAttached() == ForgeDirection.UP) {
			setBlockBounds(0.30F, 0.90F, 0.30F, 0.70F, 0.90F + CHIP_THICKNESS, 0.70F);
		} else if (attached.getSideAttached() == ForgeDirection.DOWN) {
			setBlockBounds(0.30F, 0F, 0.30F, 0.70F, 0F + CHIP_THICKNESS, 0.70F);
		} else if (attached.getSideAttached() == ForgeDirection.WEST) {
			setBlockBounds(0F, 0.30F, 0.30F, 0F + CHIP_THICKNESS, 0.70F, 0.70F);
		} else if (attached.getSideAttached() == ForgeDirection.EAST) {
			setBlockBounds(0.90F, 0.30F, 0.30F, 0.90F + CHIP_THICKNESS, 0.70F, 0.70F);
		} else if (attached.getSideAttached() == ForgeDirection.SOUTH) {
			setBlockBounds(0.30F, 0.30F, 0.90F, 0.70F, 0.70F, 0.90F + CHIP_THICKNESS);
		} else if (attached.getSideAttached() == ForgeDirection.NORTH) {
			setBlockBounds(0.30F, 0.30F, 0F, 0.70F, 0.70F, 0F + CHIP_THICKNESS);
		}
	}
	
	public void setBlockBoundsForItemRender() {
		setBlockBounds(0.30F, 0F, 0.30F, 0.70F, 0F + CHIP_THICKNESS, 0.70F);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<GateManager.gates.size(); i++) {
			if (GateManager.getGateClass(i) != null) {
				list.add(new ItemStack(id, 1, i));
			}
		}
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
		return new TileEntityGate();
	}
	
}
