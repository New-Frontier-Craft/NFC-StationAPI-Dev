package net.newfrontiercraft.nfc.registries;

import net.minecraft.item.ItemStack;

public interface OvenRecipe {

    boolean matches(ItemStack[] itemstacks);

    ItemStack getCraftingResult(ItemStack[] itemstacks);

    int getRecipeSize();

    int getTime();

    ItemStack getRecipeOutput();

}
