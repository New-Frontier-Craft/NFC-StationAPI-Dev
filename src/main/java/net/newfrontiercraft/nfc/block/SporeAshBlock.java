package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class SporeAshBlock extends LazyBlockTemplate {
    public SporeAshBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        this.setTickRandomly(true);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        if (world.getBlockId(x, y - 1, z) != BlockListener.coalBlock.id) {
            return;
        }
        world.setBlock(x, y, z, BlockListener.coalMushroom.id);
    }
}
