package net.newfrontiercraft.nfc.registries;

import net.minecraft.item.ItemInstance;

public interface IRecipeOven {

    boolean matches(ItemInstance[] itemstacks);

    ItemInstance getCraftingResult(ItemInstance[] itemstacks);

    int getRecipeSize();

    int getTime();

    ItemInstance getRecipeOutput();

}
