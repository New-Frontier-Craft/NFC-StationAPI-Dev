package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.TextureListener;

public class DecorativeWoodBlock extends LazyMultivariantBlockTemplate {
    public DecorativeWoodBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side > 1 && meta < 2) {
            return TextureListener.logSide;
        }
        return super.getTexture(side, meta);
    }
}
