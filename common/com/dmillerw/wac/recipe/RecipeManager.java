package com.dmillerw.wac.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmillerw.wac.item.ItemHandler;

public class RecipeManager {

	public static List<RecipeAmalgamFurnace> amalgamFurnaceRecipes = new ArrayList<RecipeAmalgamFurnace>();
	
	public static void registerRecipe(RecipeAmalgamFurnace recipe) {
		amalgamFurnaceRecipes.add(recipe);
	}
	
	public static void initializeAmalgamRecipes() {
		//TEST RECIPE
		registerRecipe(new RecipeAmalgamFurnace(new ItemStack(Item.ingotIron), new ItemStack(Item.ingotGold), new ItemStack(ItemHandler.itemIngot, 1, 0), 25, 100));
	}
	
}
