package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.util.Identifier;

@HasMetaNamedBlockItem
public class LazyMultivariantBlockTemplate extends LazyBlockTemplate {
    private int[] metaSpecificTextures;

    public LazyMultivariantBlockTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (meta >= metaSpecificTextures.length) {
            return super.getTexture(side, meta);
        }
        return metaSpecificTextures[meta];
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return blockMeta;
    }

    public void specifyTextures(int[] metaSpecificTextures) {
        this.metaSpecificTextures = metaSpecificTextures;
    }
}
