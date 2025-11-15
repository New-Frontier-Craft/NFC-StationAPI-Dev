package net.newfrontiercraft.nfc.registry.mutation;

import net.minecraft.world.World;

import java.util.Random;

public interface BlockMutation {
    void mutateBlock(World world, int x, int y, int z, Random random);
}
