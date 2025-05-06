package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ItemDroppingBlock extends LazyBlockTemplate {
    private int dropId;

    public ItemDroppingBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public void setDropId(int dropId) {
        this.dropId = dropId;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return dropId;
    }
}
