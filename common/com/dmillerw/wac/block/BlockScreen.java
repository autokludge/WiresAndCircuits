package com.dmillerw.wac.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.dmillerw.wac.WACMain;

public class BlockScreen extends BlockContainer {

	public BlockScreen(int id) {
		super(id, Material.iron);
		setHardness(2F);
		setResistance(1F);
		setCreativeTab(WACMain.wacCreativeTabBlocks);
	}

	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
