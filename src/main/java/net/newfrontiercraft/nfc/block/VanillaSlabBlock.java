package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

public class VanillaSlabBlock extends LazySlabTemplate {
    public VanillaSlabBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, float[] boundingBoxValues, Block bottomSlabCounterpart) {
        super(identifier, material, hardness, blockSounds, fullBlocks, bottomSlabCounterpart);
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
