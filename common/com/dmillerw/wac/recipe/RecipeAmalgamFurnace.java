package com.dmillerw.wac.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidStack;

public class RecipeAmalgamFurnace {

	public ItemStack input1;
	public ItemStack input2;
	public ItemStack itemOutput;
	
	public LiquidStack liquidOutput;
	
	public int powerUsage;
	public int cookTime;
	
	public RecipeAmalgamFurnace(ItemStack input1, ItemStack input2, ItemStack itemOutput, LiquidStack liquidOutput, int powerUsage, int cookTime) {
		this.input1 = input1;
		this.input2 = input2;
		this.itemOutput = itemOutput;
		this.liquidOutput = liquidOutput;
		this.powerUsage = powerUsage;
		this.cookTime = cookTime / 5;
	}
	
	public RecipeAmalgamFurnace(ItemStack input1, ItemStack input2, ItemStack itemOutput, int powerUsage, int cookTime) {
		this(input1, input2, itemOutput, null, powerUsage, cookTime);
	}
	
	public RecipeAmalgamFurnace(ItemStack input1, ItemStack input2, LiquidStack liquidOutput, int powerUsage, int cookTime) {
		this(input1, input2, null, liquidOutput, powerUsage, cookTime);
	}
	
	public boolean matchesRecipe(ItemStack item1, ItemStack item2) {
		if (item1 == null || item2 == null) {
			return false;
		}
		
		if (item1.isItemEqual(input1) && item2.isItemEqual(item2)) {
			if (item1.stackSize >= input1.stackSize && item2.stackSize >= input2.stackSize) {
				return true;
			}
		}
		
		return false;
	}
	
}
