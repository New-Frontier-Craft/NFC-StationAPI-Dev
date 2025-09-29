package net.newfrontiercraft.nfc.compat.bnb;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.registry.BrickOvenManager;
import net.newfrontiercraft.nfc.utils.FuelLevelEnum;
import paulevs.bnb.item.BNBItems;

public class BNBRecipes {
    public static void addBnbSmeltingRecipes(RecipeRegisterEvent event) {
        BrickOvenManager.getInstance().addShapelessOvenRecipe(new ItemStack(ItemListener.siliconIngot, 2), new Object[] {
                new ItemStack(Block.SAND), new ItemStack(Block.SAND),
                new ItemStack(BNBItems.PURE_QUARTZ), new ItemStack(BNBItems.PURE_QUARTZ)},
                1000, FuelLevelEnum.HOT);
    }
}
