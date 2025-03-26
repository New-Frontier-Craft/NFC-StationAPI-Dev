package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface BrickOvenRecipe {

    boolean matches(ItemStack[] craftingItems);

    ItemStack craft(ItemStack[] craftingItems);

    int getSize();

    int getTime();

    ItemStack getOutput();
    List getInput();

}
