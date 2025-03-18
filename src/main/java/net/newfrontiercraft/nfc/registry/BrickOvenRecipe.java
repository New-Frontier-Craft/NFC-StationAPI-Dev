package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;

public interface BrickOvenRecipe {

    boolean matches(ItemStack[] itemstacks);

    ItemStack getCraftingResult(ItemStack[] itemstacks);

    int getRecipeSize();

    int getTime();

    ItemStack getRecipeOutput();

}
