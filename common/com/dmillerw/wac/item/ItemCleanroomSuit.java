package com.dmillerw.wac.item;

import com.dmillerw.wac.WACMain;
import com.dmillerw.wac.lib.ModInfo;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.Icon;

public class ItemCleanroomSuit extends ItemArmor {

	public Icon texture;
	
	public String[] armorTypes = new String[] {"cleanroomHat", "cleanroomSuit", "cleanroomPants", "cleanroomShoes"};
	
	private int index;
	
	public ItemCleanroomSuit(int id, EnumArmorMaterial material, int index, int type) {
		super(id, material, type, index);
		this.index = index;
		setMaxDamage(30);
		setCreativeTab(WACMain.wacCreativeTabItems);
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return texture;
	}
	
//	@Override
//	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) {
//		String baseTextureFile = "/mods/"+ModInfo.MOD_ID.toLowerCase()+"/textures/items/cleanroomSuit/cleanroom_";
//		return index <= 2 ? baseTextureFile+"1.png" : baseTextureFile+"2.png";
//	}
	
	@Override
	public void registerIcons(IconRegister register) {
		texture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cleanroomSuit/"+armorTypes[index]);
	}
	
}
