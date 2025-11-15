package net.newfrontiercraft.nfc.block;

import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class CoalMushroomBlock extends LazyMushroomTemplate {
    public CoalMushroomBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, hardness, blockSounds, lightImmune);
    }

    @Override
    protected boolean canPlantOnTop(int id) {
        return id == BlockListener.coalBlock.id;
    }
}
