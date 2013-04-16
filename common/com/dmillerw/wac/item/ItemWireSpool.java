package com.dmillerw.wac.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.interfaces.IConnectable;
import com.dmillerw.wac.interfaces.IGateContainer;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.util.BlockCoord;
import com.dmillerw.wac.util.GateConnection;

public class ItemWireSpool extends Item {

	private Icon texture;
	
	public BlockCoord startGate;
	public BlockCoord endGate;
	
	public int startIndex;
	public int endIndex;
	
	public ItemWireSpool(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(0);
		setCreativeTab(WACMain.wacCreativeTabItems);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {
		if (world.isRemote) return false;
		if (!(world.getBlockTileEntity(x, y, z) instanceof IGateContainer)) return false;
		
		//TODO so much type checking
		if (startGate == null) {
			startGate = new BlockCoord(x, y, z);
			//Temp
			startIndex = 0;
			player.addChatMessage("Link started");
			return true;
		} else if (endGate == null) {
			endGate = new BlockCoord(x, y, z);
			if (startGate == endGate) {
				endGate = null;
				return false;
			}
			//Temp
			endIndex = 0;
			
			//Might be temp
			IConnectable gate = (IConnectable) world.getBlockTileEntity(startGate.x, startGate.y, startGate.z);
			gate.linkOutput(startIndex, new GateConnection(endGate, endIndex));
			
			//End
			startGate = null;
			endGate = null;
			
			player.addChatMessage("Link ended");
			return true;
		}
		
		return true;
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return texture;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wireSpool");
	}
	
}
