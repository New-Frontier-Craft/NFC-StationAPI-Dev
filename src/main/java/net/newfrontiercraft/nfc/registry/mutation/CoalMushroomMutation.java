package net.newfrontiercraft.nfc.registry.mutation;

import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class CoalMushroomMutation implements BlockMutation {
    @Override
    public void mutateBlock(World world, int x, int y, int z, Random random) {
        if (random.nextInt(4) != 0) {
            return;
        }
        if (world.getBlockId(x, y - 1, z) != BlockListener.coalBlock.id) {
            return;
        }
        if (world.getBlockId(x + 1, y - 1, z) == BlockListener.oilStill.id
        || world.getBlockId(x - 1, y - 1, z) == BlockListener.oilStill.id
        || world.getBlockId(x, y - 1, z + 1) == BlockListener.oilStill.id
        || world.getBlockId(x, y - 1, z - 1) == BlockListener.oilStill.id) {
            world.setBlock(x, y, z, BlockListener.coalMushroom.id);
        }
    }
}
