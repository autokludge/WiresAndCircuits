package com.dmillerw.wac.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.interfaces.IRotatable;
import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;
import com.dmillerw.wac.util.PlayerUtil;

public class BlockMachine extends BlockContainer {

	@SuppressWarnings("unchecked")
	public static Class<? extends TileEntity>[] machineTileEntities = new Class[] {TileEntityAmalgamFurnace.class};
	public static String[] machineTENames = new String[] {"amalgamFurnace"};
	public static String[] machineNames = new String[] {"Amalgam Furnace"};
	
	public BlockMachine(int id) {
		super(id, Material.iron);
		setResistance(4F);
		setHardness(5F);
		setCreativeTab(WACMain.wacCreativeTabBlocks);
	}
	
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entity, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, entity, stack);
		IRotatable rotate = (IRotatable) world.getBlockTileEntity(x, y, z);
		rotate.setRotation(ForgeDirection.getOrientation(PlayerUtil.determineOrientation(world, x, y, z, entity)));
	}
	
	public TileEntity createTileEntity(World world, int meta) {
		try {
			return machineTileEntities[meta].newInstance();
		} catch (InstantiationException e) {
			LogHelper.warn("BlockMachine tried to create a tile entity that doesn't exist! ["+e.getMessage()+"]");
		} catch (IllegalAccessException e) {
			LogHelper.warn("BlockMachine tried to create a tile entity that doesn't exist! ["+e.getMessage()+"]");
		}
		
		return null;
	}
	
	/* IGNORE */
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
