package net.newfrontiercraft.nfc.block;

import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

public class GlowingMushroomBlock extends LazyMushroomTemplate {
    public GlowingMushroomBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, hardness, blockSounds, lightImmune);
        setLuminance(0.795F);
    }
}
