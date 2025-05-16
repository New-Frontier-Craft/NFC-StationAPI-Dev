package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.world.gen.feature.GlowingMushroomFeature;

import java.util.Random;

public class GlowingMushroomBlock extends LazyMushroomTemplate {
    public GlowingMushroomBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, hardness, blockSounds, lightImmune);
        setLuminance(0.795F);
    }

    @Override
    public boolean onBonemealUse(World world, int x, int y, int z, BlockState state) {
        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
        GlowingMushroomFeature feature = new GlowingMushroomFeature();
        if (!feature.generate(world, new Random(), x, y, z)) {
            world.setBlockWithoutNotifyingNeighbors(x, y, z, BlockListener.glowingMushroom.id);
            return false;
        }
        return true;
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z) {
        int var5 = world.getBlockId(x, y, z);
        boolean canPlaceHere = var5 == 0 || BLOCKS[var5].material.isReplaceable();
        if (!canPlaceHere) {
            return false;
        }
        int belowId = world.getBlockId(x, y - 1, z);
        return belowId == Block.NETHERRACK.id;
    }
}
