package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class WoodenVanillaStairs extends LazyStairsTemplate {
    public WoodenVanillaStairs(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return Block.WOODEN_STAIRS.id;
    }
}
