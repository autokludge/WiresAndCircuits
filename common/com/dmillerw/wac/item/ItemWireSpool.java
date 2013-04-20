package com.dmillerw.wac.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

public class ItemWireSpool extends Item {

	private Icon[] textures;
	
	private static String[] itemSubNames = new String[] {"emptySpool", "wireSpool"};
	public static String[] itemNames = new String[] {"Empty Spool", "Wire Spool"};
	
	public ItemWireSpool(int id) {
		super(id);
		setMaxStackSize(1);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(WACMain.wacCreativeTabItems);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float f1, float f2, float f3) {
		if (world.isRemote) return false;
		if (stack.getItemDamage() != 1) return false;
		
		return true;
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
		return textures[damage];
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[2];
		
		textures[0] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":spoolEmpty");
		textures[1] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wireSpool");
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i=0; i<itemSubNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return getUnlocalizedName() + "." + itemSubNames[stack.getItemDamage()];
	}
	
}
