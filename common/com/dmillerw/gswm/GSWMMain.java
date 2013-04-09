package com.dmillerw.gswm;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;

import com.dmillerw.gswm.block.BlockHandler;
import com.dmillerw.gswm.block.BlockIDs;
import com.dmillerw.gswm.core.CommonProxy;
import com.dmillerw.gswm.core.CreativeTabGSWM;
import com.dmillerw.gswm.core.helper.LogHelper;
import com.dmillerw.gswm.item.ItemHandler;
import com.dmillerw.gswm.item.ItemIDs;
import com.dmillerw.gswm.lib.ModInfo;
import com.dmillerw.gswm.network.PacketHandler;
import com.dmillerw.gswm.world.OreGenerator;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION)
@NetworkMod(channels = {ModInfo.MOD_ID}, serverSideRequired=false, clientSideRequired=true, packetHandler=PacketHandler.class)
public class GSWMMain {
	@Instance(ModInfo.MOD_ID)
	public static GSWMMain instance;
	@SidedProxy(serverSide=ModInfo.MOD_COMMON_PROXY, clientSide=ModInfo.MOD_CLIENT_PROXY)
	public static CommonProxy proxy;
	
	public static CreativeTabs gwsmCreativeTab = new CreativeTabGSWM();
	
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
		
		//Temp
		GameRegistry.registerWorldGenerator(new OreGenerator());
		LanguageRegistry.instance().addStringLocalization("itemGroup.gswm", "Greenstone Wire Mod");
	}
	
}
