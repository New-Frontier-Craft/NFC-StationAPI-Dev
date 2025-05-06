package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class GlowingMushroomFeature extends Feature {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (world.getBlockId(x, y - 1, z) != Block.NETHERRACK.id) {
            return false;
        }
        if (world.getBlockId(x, y, z) != 0) {
            return false;
        }
        int stemHeight = 6 + random.nextInt(7);
        for (int i = 0; i <= stemHeight; i++) {
            for (int j = -1; j <= 1; j++) {
                for (int k = -1; k <= 1; k++) {
                    if (world.getBlockId(x + j, y + i + 1, z + k) != 0) {
                        stemHeight = i;
                        break;
                    }
                }
            }
        }
        if (stemHeight < 3) {
            return false;
        }
        int mushroomCapLength = 2 + random.nextInt(stemHeight / 2);
        for (int i = 0; i < stemHeight; i++) {
            if (i >= stemHeight - mushroomCapLength) {
                world.setBlock(x + 1, y + i, z, BlockListener.glowingMushroomCap.id);
                world.setBlock(x - 1, y + i, z, BlockListener.glowingMushroomCap.id);
                world.setBlock(x, y + i, z + 1, BlockListener.glowingMushroomCap.id);
                world.setBlock(x, y + i, z - 1, BlockListener.glowingMushroomCap.id);
            }
            world.setBlock(x, y + i, z, BlockListener.glowingMushroomStem.id);
        }
        world.setBlock(x, y + stemHeight, z, BlockListener.glowingMushroomCap.id);
        return true;
    }
}
