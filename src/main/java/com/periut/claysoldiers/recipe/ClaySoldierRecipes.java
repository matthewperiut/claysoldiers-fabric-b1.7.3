package com.periut.claysoldiers.recipe;

import com.periut.claysoldiers.item.ClaySoldierItems;
import com.periut.retroapi.register.recipe.RetroRecipes;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ClaySoldierRecipes {
    public static void registerRecipes() {
		RetroRecipes.addShaped(new ItemStack(ClaySoldierItems.horseDoll, 2), "#$#", "# #", '#', new ItemStack(Block.DIRT), '$', new ItemStack(Block.SOUL_SAND));
		RetroRecipes.addShaped(new ItemStack(ClaySoldierItems.clayDisruptor, 1), "#$#", "#@#", '$', new ItemStack(Item.STICK), '@', new ItemStack(Item.REDSTONE), '#', new ItemStack(Block.CLAY));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.greyDoll, 4), new ItemStack(Block.CLAY), new ItemStack(Block.SOUL_SAND));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.redDoll, 1), new ItemStack(ClaySoldierItems.greyDoll), new ItemStack(Item.DYE, 1, 1));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.yellowDoll, 1), new ItemStack(ClaySoldierItems.greyDoll), new ItemStack(Item.DYE, 1, 11));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.greenDoll, 1), new ItemStack(ClaySoldierItems.greyDoll), new ItemStack(Item.DYE, 1, 2));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.blueDoll, 1), new ItemStack(ClaySoldierItems.greyDoll), new ItemStack(Item.DYE, 1, 4));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.orangeDoll, 1), new ItemStack(ClaySoldierItems.greyDoll), new ItemStack(Item.DYE, 1, 14));
		RetroRecipes.addShapeless(new ItemStack(ClaySoldierItems.purpleDoll, 1), new ItemStack(ClaySoldierItems.greyDoll), new ItemStack(Item.DYE, 1, 5));
	}
}
