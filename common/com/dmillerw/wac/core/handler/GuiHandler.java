package com.dmillerw.wac.core.handler;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.dmillerw.wac.client.gui.GuiConfigurable;
import com.dmillerw.wac.core.options.Option;
import com.dmillerw.wac.core.options.OptionString;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return handleGuiRequest(ID, player, world, x, y, z, Side.SERVER);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return handleGuiRequest(ID, player, world, x, y, z, Side.CLIENT);
	}

	public Object handleGuiRequest(int ID, EntityPlayer player, World world, int x, int y, int z, Side side) {
//		TileEntity tile = world.getBlockTileEntity(x, y, z);
//		
//		if (ID == ModInfo.AMALGAM_FURNACE_ID) {
//			if (tile instanceof TileEntityAmalgamFurnace) {
//				return side == Side.SERVER ? new ContainerAmalgamFurnace(player, (TileEntityAmalgamFurnace) tile) : new GuiAmalgamFurnace(player, (TileEntityAmalgamFurnace) tile);
//			}
//		}
//		
//		return null;
		
		ArrayList<Option> options = new ArrayList<Option>();
		options.add(new OptionString("HELLO"));
		Option test = new OptionString("POTATO");
		test.category = "Test 2";
		options.add(test);
		return side == Side.CLIENT ? new GuiConfigurable(options) : null;
	}
	
}
