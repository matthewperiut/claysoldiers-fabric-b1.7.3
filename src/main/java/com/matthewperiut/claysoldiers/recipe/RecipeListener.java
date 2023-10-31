package com.matthewperiut.claysoldiers.recipe;

import com.matthewperiut.claysoldiers.item.ItemListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;
        // Output <- Input
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            //ModLoader.AddRecipe(new iz(horseDoll, 2, 0), new Object[] { "#$#", "# #", Character.valueOf('$'), uu.bd, Character.valueOf('#'), uu.w });
            CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.horseDoll, 2), "#$#", "# #", '#', new ItemStack(Block.DIRT), '$', new ItemStack(Block.SOUL_SAND));

            //ModLoader.AddRecipe(new iz(clayDisruptor, 1, 0), new Object[] { "#$#", "#@#", Character.valueOf('$'), gm.B, Character.valueOf('#'), gm.aG, Character.valueOf('@'), gm.aA });
            CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.clayDisruptor, 1), "#$#", "#@#", '$', new ItemStack(Item.STICK), '@', new ItemStack(Item.REDSTONE_DUST), '#', new ItemStack(Block.CLAY));
        }
        // Output <- Input
        else if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.greyDoll, 4), new ItemStack(Block.CLAY), new ItemStack(Block.SOUL_SAND));

            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.redDoll, 1), new ItemStack(ItemListener.greyDoll), new ItemStack(Item.DYE_POWDER, 1, 1));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.yellowDoll, 1), new ItemStack(ItemListener.greyDoll), new ItemStack(Item.DYE_POWDER, 1, 11));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.greenDoll, 1), new ItemStack(ItemListener.greyDoll), new ItemStack(Item.DYE_POWDER, 1, 2));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.blueDoll, 1), new ItemStack(ItemListener.greyDoll), new ItemStack(Item.DYE_POWDER, 1, 4));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.orangeDoll, 1), new ItemStack(ItemListener.greyDoll), new ItemStack(Item.DYE_POWDER, 1, 14));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.purpleDoll, 1), new ItemStack(ItemListener.greyDoll), new ItemStack(Item.DYE_POWDER, 1, 5));

        }
    }
}
