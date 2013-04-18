package com.dmillerw.wac.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.interfaces.IRotatable;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;
import com.dmillerw.wac.util.PlayerUtil;

public class BlockMachine extends BlockContainer {

	@SuppressWarnings("unchecked")
	public static Class<? extends TileEntity>[] machineTileEntities = new Class[] {TileEntityAmalgamFurnace.class};
	public static String[] machineTENames = new String[] {"amalgamFurnace"};
	public static String[] machineNames = new String[] {"Amalgam Furnace"};
	
	public Icon[][] textures;
	
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
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float f1, float f2, float f3) {
		if (player.isSneaking()) {
			return false;
		}
		
		player.openGui(WACMain.instance, ModInfo.AMALGAM_FURNACE_ID, world, x, y, z);
		return true;
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		IRotatable rotate = (IRotatable) world.getBlockTileEntity(x, y, z);
		
		switch(sideForge) {
		case UP: return textures[meta][1];
		case DOWN: return textures[meta][0];
		default: if (sideForge != rotate.getRotation()) return textures[meta][2];
		}
		
		switch(meta) {
			case 0: {
				TileEntityAmalgamFurnace furnace = (TileEntityAmalgamFurnace) world.getBlockTileEntity(x, y, z);
				
				if (!furnace.isActive() && furnace.fakePowerAmount == 0) {
					return textures[0][3];
				} else if (furnace.isActive() && furnace.fakePowerAmount == 0) {
					return textures[0][4];
				} else if (!furnace.isActive() && furnace.fakePowerAmount != 0) {
					return textures[0][5];
				} else if (furnace.isActive() && furnace.fakePowerAmount != 0) {
					return textures[0][6];
				}
			}
		}
		
		//Default if all else fails
		return textures[meta][2];
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		switch(sideForge) {
		case UP: return textures[meta][1];
		case DOWN: return textures[meta][0];
		case EAST: return textures[meta][3];
		default: return textures[meta][2];
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		final String MOD_PREFIX = ModInfo.MOD_ID.toLowerCase()+":";
		
		textures = new Icon[machineNames.length][16];
		
		for (int i=0; i<machineNames.length; i++) {
			switch(i) {
				case 0: 
					textures[i][0] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/bottom");
					textures[i][1] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/top");
					textures[i][2] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/side");
					
					//Front textures
					textures[i][3] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/front");
					textures[i][4] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/frontBurning");
					textures[i][5] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/frontPowered");
					textures[i][6] = register.registerIcon(MOD_PREFIX+"amalgamFurnace/frontPoweredBurning");
			}
		}
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
