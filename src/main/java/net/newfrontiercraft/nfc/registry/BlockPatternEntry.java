package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.block.BlockState;

public record BlockPatternEntry(char pattern, BlockState blockstate, int meta, ItemStack item){}
