package com.dmillerw.wac.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.dmillerw.wac.client.gui.GuiAmalgamFurnace;
import com.dmillerw.wac.client.gui.GuiIndexSelection;
import com.dmillerw.wac.gates.DataType;
import com.dmillerw.wac.inventory.ContainerAmalgamFurnace;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.tileentity.TileEntityAmalgamFurnace;

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
		return side == Side.CLIENT ? new GuiIndexSelection(new DataType[] {DataType.NUMBER, DataType.NUMBER, DataType.STRING}, (byte) 0) : null;
		
//		return null;
	}
	
}
