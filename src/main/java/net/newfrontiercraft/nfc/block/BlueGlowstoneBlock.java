package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.ItemListener;

import java.util.Random;

public class BlueGlowstoneBlock extends LazyBlockTemplate {
    public BlueGlowstoneBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setLuminance(1.0F);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 4;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return ItemListener.blueGlowstoneDust.id;
    }
}
