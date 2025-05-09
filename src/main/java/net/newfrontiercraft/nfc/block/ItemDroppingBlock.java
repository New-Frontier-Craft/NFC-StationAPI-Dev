package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class ItemDroppingBlock extends LazyBlockTemplate {
    private int dropId;
    private int dropMeta;

    public ItemDroppingBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public void setDropId(int dropId) {
        this.dropId = dropId;
    }

    public void setDropMeta(int dropMeta) {
        this.dropMeta = dropMeta;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return dropId;
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return dropMeta;
    }
}
