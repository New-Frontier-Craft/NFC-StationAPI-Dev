package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class SiliconLayerFeature extends Feature {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int height = y;
        boolean foundSandstone = false;
        for (; height < 100; height += 4) {
            if (world.getBlockId(x, height + 4, z) == Block.SANDSTONE.id) {
                foundSandstone = true;
                break;
            }
        }
        if (!foundSandstone) {
            return false;
        }
        for (int i = 0; i < 256; i++) {
            int randomX = x + random.nextInt(16) - 8;
            int randomY = height + random.nextInt(9) - 4;
            int randomZ = z + random.nextInt(16) - 8;
            if (world.getBlockId(randomX, randomY, randomZ) == Block.STONE.id) {
                world.setBlock(randomX, randomY, randomZ, BlockListener.siliconOre.id);
            }
        }
        return true;
    }
}
