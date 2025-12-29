package net.newfrontiercraft.nfc.wrappers;

import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.utils.ChanceDrop;
import net.newfrontiercraft.nfc.utils.ItemMeta;

import java.util.ArrayList;

public class HarvestingRecipe {

    private final ItemStack input;
    private final ArrayList<ItemStack> outputs = new ArrayList<>();

    public HarvestingRecipe(ItemMeta blockToHarvest, ChanceDrop[] chanceDrops) {
        input = blockToHarvest.toItemStack();
        for (ChanceDrop chanceDrop : chanceDrops) {
            outputs.add(chanceDrop.drop().toItemStack());
        }
    }

    public ItemStack getInput() {
        return input;
    }

    public ArrayList<ItemStack> getOutputs() {
        return outputs;
    }
}
