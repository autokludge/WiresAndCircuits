package com.dmillerw.wac.block;

import java.util.List;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCleanroom extends Block {

	public Icon[] textures;
	
	public static String[] blockSubNames = new String[] {"cleanroomBlock", "cleanroomGlass", "cleanroomLightOff", "cleanroomLightOn"};
	public static String[] blockNames = new String[] {"Sterile Block", "Sterile Glass", "Sterile Light", "Sterile Light"};
	
	public BlockCleanroom(int id) {
		super(id, Material.iron);
		setCreativeTab(WACMain.wacCreativeTabBlocks);
	}
	
	public void onBlockAdded(World world, int x, int y, int z) {
       checkPoweredState(world, x, y, z);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int par5) {
        checkPoweredState(world, x, y, z);
    }
	
    public void checkPoweredState(World world, int x, int y, int z) {
    	if (!world.isRemote){
    		int meta = world.getBlockMetadata(x, y, z);
    		
            if (meta == 2 || meta == 3) {
            	if (this.isPowered(world, x, y, z) && !world.isBlockIndirectlyGettingPowered(x, y, z)){
                	world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                }
                else if (!this.isPowered(world, x, y, z) && world.isBlockIndirectlyGettingPowered(x, y, z)){
                    world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                }
            }
        }
    }
    
    public boolean isPowered(World world, int x, int y, int z) {
    	return world.getBlockMetadata(x, y, z) == 3;
    }
    
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return textures[meta];
	}
	
	public boolean isOpaqueCube()
    {
        return false;
    }
	
    public int damageDropped(int damage) {
    	return damage;
    }
    
	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 1 ? 1F : 5F;
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 1 ? 1F : 5F;
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 3 ? 15 : 0;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockSubNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[blockSubNames.length];
		
		for (int i=0; i<blockSubNames.length; i++) {
			textures[i] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cleanroom/"+blockSubNames[i]);
		}
	}
	
}
