package com.dmillerw.wac.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.dmillerw.wac.lib.ModInfo;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {

	public static Logger gswmLog;
	
	public static void init() {
		gswmLog = Logger.getLogger(ModInfo.MOD_NAME);
		gswmLog.setParent(FMLLog.getLogger());
	}
	
	public static void info(String msg) {
		gswmLog.log(Level.INFO, msg);
	}
	
	public static void warn(String msg) {
		gswmLog.log(Level.WARNING, msg);
	}
	
	public static void log(Level lvl, String msg) {
		gswmLog.log(lvl, msg);
	}
	
}
