package com.dmillerw.wac;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;

import com.dmillerw.wac.block.BlockHandler;
import com.dmillerw.wac.block.BlockIDs;
import com.dmillerw.wac.core.CommonProxy;
import com.dmillerw.wac.core.CreativeTabWAC;
import com.dmillerw.wac.core.helper.LogHelper;
import com.dmillerw.wac.gates.Gate;
import com.dmillerw.wac.gates.arithmatic.GateAbsolute;
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

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION/*, dependencies="required-after:BuildCraft|Core"*/)
@NetworkMod(channels = {ModInfo.MOD_CHANNEL}, serverSideRequired=false, clientSideRequired=true, packetHandler=PacketHandler.class)
public class WACMain {
	@Instance(ModInfo.MOD_ID)
	public static WACMain instance;
	@SidedProxy(serverSide=ModInfo.MOD_COMMON_PROXY, clientSide=ModInfo.MOD_CLIENT_PROXY)
	public static CommonProxy proxy;
	
	public static CreativeTabs wacCreativeTabMaterials = new CreativeTabWAC("materials", "block", "blockOre", "WaC Materials");
	public static CreativeTabs wacCreativeTabItems = new CreativeTabWAC("items", "item", "cleanroomHat", "WaC Items");
	public static CreativeTabs wacCreativeTabBlocks = new CreativeTabWAC("blocks", "block", "blockCleanroom", "WaC Blocks");
	
	public static Gate gateAbsoluteValue = new GateAbsolute(0);
	
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
		
		proxy.registerTileEntities();
	}

}
