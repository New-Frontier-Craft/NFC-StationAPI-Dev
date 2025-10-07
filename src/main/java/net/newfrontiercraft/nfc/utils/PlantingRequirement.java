package net.newfrontiercraft.nfc.utils;

import net.minecraft.block.Block;

public record PlantingRequirement(BlockAndMetaRange[] validOptions, Block placedBlock, int placedBlockMeta) {
}
