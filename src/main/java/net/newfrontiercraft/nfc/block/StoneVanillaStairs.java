package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class StoneVanillaStairs extends LazyStairsTemplate {
    public StoneVanillaStairs(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        if (blockMeta == 0) {
            return Block.COBBLESTONE_STAIRS.id;
        }
        return super.getDroppedItemId(blockMeta, random);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 0 && meta == 1) {
            return Block.SANDSTONE.textureId + 16;
        }
        if (side == 1 && meta == 1) {
            return Block.SANDSTONE.textureId - 16;
        }
        return super.getTexture(side, meta);
    }
}
