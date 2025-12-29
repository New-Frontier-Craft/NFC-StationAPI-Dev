package net.newfrontiercraft.nfc.utils;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public record ItemMeta(Item item, int meta) {
    public ItemStack toItemStack() {
        return new ItemStack(item, 1, meta);
    }
}
