package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;

public class LightSourceBlock extends CreativeBlock {
    public LightSourceBlock(Identifier identifier, Material material, BlockSoundGroup blockSounds, boolean[] canCollide) {
        super(identifier, material, blockSounds, canCollide);
        setLuminance(1);
    }
}
