package com.dmillerw.wac.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.interfaces.IWireConnector;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.network.packet.PacketAddRenderPoint;
import com.dmillerw.wac.util.Vector;
import com.dmillerw.wac.util.WireConnection;

import cpw.mods.fml.common.network.PacketDispatcher;

public class ItemWireSpool extends Item {

	private Icon texture;
	
	public WireConnection start;
	public WireConnection end;
	
	public ItemWireSpool(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(0);
		setCreativeTab(WACMain.wacCreativeTabItems);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) return false;
		
		if (world.getBlockTileEntity(x, y, z) instanceof IWireConnector ) {
			if (start == null) {
				//TODO Wire index stuff and input/output checking
				player.addChatMessage("Wiring started");
				start = new WireConnection(new Vector(x, y, z), 0);
				return true;
			} else if (end == null) {
				//TODO Wire index stuff and input/output checking
				player.addChatMessage("Wiring ended");
				end = new WireConnection(new Vector(x, y, z), 0);
				
				start = null;
				end = null;
				
				return true;
			} else {
				//Packet sending
				return true;
			}
		}
		
		if (start != null) {
			player.addChatMessage("Added point");
			PacketAddRenderPoint packet = new PacketAddRenderPoint();
			packet.point = new Vector(start.block.x, start.block.y, start.block.z);
			packet.input = new WireConnection(new Vector(x, y, z), 0);
			PacketDispatcher.sendPacketToServer(packet.makePacket());
			return true;
		}
		
		return false;
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
