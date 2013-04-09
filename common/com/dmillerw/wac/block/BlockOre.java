package com.dmillerw.wac.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.item.ItemIDs;
import com.dmillerw.wac.lib.ModInfo;

public class BlockOre extends Block {

	//TODO Make array
	public Icon texture;
	
	public BlockOre(int id) {
		super(id, Material.rock);
		setHardness(2F);
		setResistance(1F);
		setStepSound(soundStoneFootstep);
		setCreativeTab(WACMain.wacCreativeTab);
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		return texture;
	}
	
	public int quantityDropped(Random random) {
        return random.nextInt(5);
    }

    public int idDropped(int id, Random random, int par3) {
        return ItemIDs.getShiftedID("itemDust");
    }
	
    public void registerIcons(IconRegister register) {
    	texture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":ore/oreGreenstone");
    	System.out.println(texture.getIconName());
    }
    
}