package com.dmillerw.wac.interfaces;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IHandleActivation {

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player);
	
}
