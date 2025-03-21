package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyGlassBlockTemplate extends LazyBlockTemplate{
    boolean translucent;
    public LazyGlassBlockTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, boolean translucent) {
        super(identifier, material, hardness, blockSounds);
        this.translucent = translucent;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        int blockId = blockView.getBlockId(x, y, z);
        return blockId != this.id && super.isSideVisible(blockView, x, y, z, side);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public int getRenderLayer() {
        return translucent ? 1 : 0;
    }
}
