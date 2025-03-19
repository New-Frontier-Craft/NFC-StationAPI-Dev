package net.newfrontiercraft.nfc.wrappers;

import net.minecraft.item.ItemStack;

import java.util.List;

public class CarpentryRecipe {
    private final ItemStack input;
    private final List outputs;

    public CarpentryRecipe(ItemStack input, List outputs) {
        this.input = input;
        this.outputs = outputs;
    }

    public List getOutputs() {
        return this.outputs;
    }

    public ItemStack getInput() {
        return input;
    }
}
