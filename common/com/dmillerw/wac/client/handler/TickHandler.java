package com.dmillerw.wac.client.handler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import org.lwjgl.input.Mouse;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.interfaces.IGateContainer;
import com.dmillerw.wac.item.ItemHandler;
import com.dmillerw.wac.util.BlockCoord;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {

	public static int selectedIndex = 0;
	public static int outOrIn = 0;
	
	public static BlockCoord gateCoords = null;
	
	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		if (type.equals(EnumSet.of(TickType.RENDER))) {
			if (Minecraft.getMinecraft().currentScreen == null) {
				EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
				World world = player.worldObj;
				MovingObjectPosition mop = getMOPFromPlayer(world, player, false);
				
				if (player.inventory.getCurrentItem() != null) {
					if (player.inventory.getCurrentItem().getItem() == ItemHandler.wireSpool) {
						if (mop != null) {
							BlockCoord tmpCoords = new BlockCoord(mop.blockX, mop.blockY, mop.blockZ);
							if (!tmpCoords.equals(gateCoords)) {
								gateCoords = tmpCoords;
								selectedIndex = 0;
							}
		
							if (world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ) instanceof IGateContainer) {
								modifyIndex(Mouse.getDWheel());
								render((IGateContainer) world.getBlockTileEntity(mop.blockX, mop.blockY, mop.blockZ));
							}
						}
					}
				}
			}
		}
	}

	private void render(IGateContainer container) {
		List<String> toDisplay = new ArrayList<String>();
		
		if (outOrIn == 0) {
			if (container.getOutputs() != null) {
				toDisplay.add("OUTPUT:");
				for (Object obj : container.getOutputs()) {
					toDisplay.add(String.valueOf(obj));
				}
			}
		} else if (outOrIn == 1) {
			if (container.getInputs() != null) {
				toDisplay.add("INPUT:");
				for (Object obj : container.getInputs()) {
					toDisplay.add(String.valueOf(obj));
				}
			}
		}
		
		WACMain.instance.gui.render(selectedIndex, toDisplay);
	}
	
	private void modifyIndex(int wheelPos) {
		EntityPlayer player = FMLClientHandler.instance().getClient().thePlayer;
		
		if (wheelPos > 0) {
			selectedIndex++;
			player.inventory.changeCurrentItem(-wheelPos);
		}
		if (wheelPos < 0) {
			selectedIndex--;
			player.inventory.changeCurrentItem(-wheelPos);
		}
	}
	
	private MovingObjectPosition getMOPFromPlayer(World world, EntityPlayer player, boolean bool) {
		float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.62D - (double)player.yOffset;
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
        Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        if (player instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.rayTraceBlocks_do_do(vec3, vec31, bool, !bool);
	}
	
	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		return "Index GUI";
	}

}
