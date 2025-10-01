package net.newfrontiercraft.nfc.wrappers;

import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.utils.CokeOvenResult;

public record CokeOvenRecipe(ItemStack input, CokeOvenResult output) {
}
