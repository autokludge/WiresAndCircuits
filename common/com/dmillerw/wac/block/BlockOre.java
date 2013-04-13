package com.dmillerw.wac.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

public class BlockOre extends Block {

	public Icon[] textures;
	
	public static String[] blockSubNames = new String[] {"oreCopper", "oreTin", "oreSilver"};
	public static String[] blockNames = new String[] {"Copper Ore", "Tin Ore", "Silver Ore"};
	
	public BlockOre(int id) {
		super(id, Material.rock);
		setHardness(2F);
		setResistance(1F);
		setStepSound(soundStoneFootstep);
		setCreativeTab(WACMain.wacCreativeTabMaterials);
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		return textures[meta];
	}
	
	public int damageDropped(int meta) {
        return meta;
    }

    public int idDropped(int id, Random random, int par3) {
        return BlockIDs.getID("blockOre");
    }
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockSubNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
    
    public void registerIcons(IconRegister register) {
    	textures = new Icon[blockSubNames.length];
    	
    	for (int i=0; i<blockSubNames.length; i++) {
    		textures[i] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":ore/"+blockSubNames[i]);
    	}
    }
    
}
