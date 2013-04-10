package com.dmillerw.wac;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;

import com.dmillerw.wac.block.BlockHandler;
import com.dmillerw.wac.block.BlockIDs;
import com.dmillerw.wac.core.CommonProxy;
import com.dmillerw.wac.core.CreativeTabWAC;
import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.item.ItemHandler;
import com.dmillerw.wac.item.ItemIDs;
import com.dmillerw.wac.lib.ModInfo;
import com.dmillerw.wac.network.PacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION)
@NetworkMod(channels = {ModInfo.MOD_CHANNEL}, serverSideRequired=false, clientSideRequired=true, packetHandler=PacketHandler.class)
public class WACMain {
	@Instance(ModInfo.MOD_ID)
	public static WACMain instance;
	@SidedProxy(serverSide=ModInfo.MOD_COMMON_PROXY, clientSide=ModInfo.MOD_CLIENT_PROXY)
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e) {
		LogHelper.init();
		
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		BlockIDs.handleConfig(config);
		ItemIDs.handleConfig(config);
	}
	
	@Init
	public void init(FMLInitializationEvent e) {
		BlockHandler.init();
		ItemHandler.init();
		
		BlockHandler.addSmeltingRecipes();
		
		//Temp
		LanguageRegistry.instance().addStringLocalization("itemGroup.gswm.materials", "WaC Materials");
		LanguageRegistry.instance().addStringLocalization("itemGroup.gswm.items", "WaC Items");
		LanguageRegistry.instance().addStringLocalization("itemGroup.gswm.blocks", "WaC Blocks");
	}

	public static CreativeTabs wacCreativeTabMaterials = new CreativeTabWAC("materials", "block", "blockOre");
	public static CreativeTabs wacCreativeTabItems = new CreativeTabWAC("items", "item", "cleanroomHat");
	public static CreativeTabs wacCreativeTabBlocks = new CreativeTabWAC("blocks", "block", "blockCleanroom");
	
}
