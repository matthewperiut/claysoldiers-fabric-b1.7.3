package com.matthewperiut.claysoldiers.recipe;

import com.matthewperiut.claysoldiers.item.ItemListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {
    @EventListener
    public void registerRecipes(RecipeRegisterEvent event)
    {
        Identifier type = event.recipeId;
        // Output <- Input
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type())
        {
            //ModLoader.AddRecipe(new iz(horseDoll, 2, 0), new Object[] { "#$#", "# #", Character.valueOf('$'), uu.bd, Character.valueOf('#'), uu.w });
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.horseDoll, 2), "#$#", "# #", '#', new ItemInstance(BlockBase.DIRT), '$', new ItemInstance(BlockBase.SOUL_SAND));

            //ModLoader.AddRecipe(new iz(clayDisruptor, 1, 0), new Object[] { "#$#", "#@#", Character.valueOf('$'), gm.B, Character.valueOf('#'), gm.aG, Character.valueOf('@'), gm.aA });
            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.clayDisruptor, 1), "#$#", "#@#", '$', new ItemInstance(ItemBase.stick), '@', new ItemInstance(ItemBase.redstoneDust), '#', new ItemInstance(BlockBase.CLAY));
        }
        // Output <- Input
        else if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
        {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.greyDoll, 4), new ItemInstance(BlockBase.CLAY), new ItemInstance(BlockBase.SOUL_SAND));

            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.redDoll, 1), new ItemInstance(ItemListener.greyDoll), new ItemInstance(ItemBase.dyePowder,1,1));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.yellowDoll, 1), new ItemInstance(ItemListener.greyDoll), new ItemInstance(ItemBase.dyePowder,1,11));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.greenDoll, 1), new ItemInstance(ItemListener.greyDoll), new ItemInstance(ItemBase.dyePowder,1,2));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.blueDoll, 1), new ItemInstance(ItemListener.greyDoll), new ItemInstance(ItemBase.dyePowder,1,4));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.orangeDoll, 1), new ItemInstance(ItemListener.greyDoll), new ItemInstance(ItemBase.dyePowder,1,14));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.purpleDoll, 1), new ItemInstance(ItemListener.greyDoll), new ItemInstance(ItemBase.dyePowder,1,5));

        }
        // Input -> Output
        else if (type == RecipeRegisterEvent.Vanilla.SMELTING.type())
        {

        }
    }
}
