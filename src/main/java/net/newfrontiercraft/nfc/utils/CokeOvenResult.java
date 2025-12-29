package net.newfrontiercraft.nfc.utils;

import net.minecraft.item.ItemStack;

public record CokeOvenResult(ItemStack result, FuelLevelEnum minimum, FuelLevelEnum maximum) {
}
