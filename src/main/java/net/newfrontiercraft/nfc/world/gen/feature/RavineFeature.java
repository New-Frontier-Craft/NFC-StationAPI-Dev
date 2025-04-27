package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class RavineFeature extends Feature {
    public boolean generate(World world, Random rand, int x, int y, int z) {
        int fillingId;
        int curvature = rand.nextInt(25) + 1;
        int smoothness = rand.nextInt(5) * rand.nextInt(3) + 1;
        int sideBias = rand.nextInt(100);
        int width = rand.nextInt(16) + 4;
        int depth = rand.nextInt(16) + 4;
        int randomSideOffset = 0;
        int randomHeightOffset = 0;
        int flatness = rand.nextInt(4) + 2;
        int steepness = rand.nextInt(8) + 4;
        int inverter = 1;
        int length =  rand.nextInt(112) + 16;
        if (rand.nextBoolean()) {
            inverter = 0;
        }
        for (int xLength = -length; xLength <= length; xLength++) {
            for (int zLength = -width; zLength <= width; zLength++) {
                for (int height = 0; height >= (- rand.nextInt(steepness)/(rand.nextInt(smoothness) + 1) - depth + (((zLength + rand.nextInt(2))*(zLength + rand.nextInt(2)))/flatness)*steepness - steepness*2) + Math.abs(xLength * 16 / length); height--) {
                    int compactX = x + (xLength * inverter) + ((zLength + randomSideOffset) * (1 - inverter));
                    int compactY = y + height + randomHeightOffset;
                    int compactZ = z + ((zLength + randomSideOffset) * inverter) + (xLength * (1 - inverter));
                    if (world.getMaterial(compactX, compactY + 1, compactZ) == Material.PLANT) world.setBlockWithoutNotifyingNeighbors(compactX, compactY + 1, compactZ, 0);
                    if (compactY < 12) fillingId = Block.LAVA.id;
                    else if (world.getBlockId(compactX, compactY + 1, compactZ) == Block.WATER.id) fillingId = Block.WATER.id;
                    else fillingId = 0;
                    int blockId = world.getBlockId(compactX, compactY, compactZ);
                    Block oreBlock = Block.BLOCKS[blockId];
                    if (blockId == Block.FLOWING_LAVA.id ||
                            blockId == Block.LAVA.id ||
                            blockId == Block.GRASS_BLOCK.id ||
                            blockId == Block.STONE.id ||
                            blockId == Block.DIRT.id ||
                            blockId == Block.GRAVEL.id ||
                            blockId == BlockListener.pebble.id ||
                            oreBlock instanceof OreBlock) {
                        world.setBlockWithoutNotifyingNeighbors(compactX, compactY, compactZ, fillingId);
                    }
                }
            }
            if (rand.nextInt(smoothness) == 0) {
                if (rand.nextInt(100) - sideBias < 0) {
                    randomSideOffset += 1;
                } else {
                    randomSideOffset -= 1;
                }
                randomHeightOffset += (rand.nextInt(3) - 1) * rand.nextInt(steepness);
                if (steepness - rand.nextInt(steepness) > rand.nextInt(steepness) && steepness > 1) {
                    steepness -= 1;
                } else {
                    steepness += 1;
                }
                if (flatness - rand.nextInt(flatness) > rand.nextInt(flatness) && flatness > 1) {
                    flatness -= 1;
                } else {
                    flatness += 1;
                }
                if (curvature - rand.nextInt(curvature) > rand.nextInt(curvature) && curvature > smoothness) {
                    curvature -= smoothness;
                } else {
                    curvature += steepness;
                }
            }
            if (rand.nextInt(100) - sideBias < 0) {
                sideBias -= curvature;
            } else {
                sideBias += curvature;
            }
            if (smoothness - rand.nextInt(smoothness) > rand.nextInt(smoothness) && smoothness > 1) {
                smoothness -= 1;
            } else {
                smoothness += 1;
            }
        }
        return true;
    }
}
