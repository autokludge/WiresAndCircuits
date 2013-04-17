package com.dmillerw.wac.client.gui;

import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

import com.dmillerw.wac.gates.IOData;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiIndexSelection extends GuiScreen {

	private int selectedIndex;
	
	private ArrayList<IOData> indexes = new ArrayList<IOData>();
	
	@Override
	public void initGui() {

	}
	
	@Override
	public void drawScreen(int x, int y, float f) {

	}
	
	public void selectIndex(int index) {
		selectedIndex = index;
	}
	
	public int getSelectedIndex() {
		return selectedIndex;
	}
	
	public boolean isSelected(int index) {
		return index == selectedIndex;
	}
	
}
