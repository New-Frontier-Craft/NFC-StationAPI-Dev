package net.newfrontiercraft.nfc.feature;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class StalactiteCaveFeature extends Feature {

    static class HeightFunction {
        int direction;
        int flatnessX;
        int flatnessZ;
        int centerOffsetX;
        int centerOffsetZ;
        int heightOffsetX;
        int heightOffsetZ;

        HeightFunction(int direction, int flatnessX, int flatnessZ, int centerOffsetX, int centerOffsetZ, int heightOffsetX, int heightOffsetZ) {
            this.direction = direction;
            this.flatnessX = flatnessX;
            this.flatnessZ = flatnessZ;
            this.centerOffsetX = centerOffsetX;
            this.centerOffsetZ = centerOffsetZ;
            this.heightOffsetX = heightOffsetX;
            this.heightOffsetZ = heightOffsetZ;
        }

        int getLocalHeight(int x, int z) {
            return (((x + centerOffsetX) * (x + centerOffsetX) + heightOffsetX) / flatnessX) * direction + (((z + centerOffsetZ) * (z + centerOffsetZ) + heightOffsetZ) / flatnessZ) * direction;
        }
    }

    public boolean generate(World world, Random rand, int x, int y, int z) {
        int xSize = 48;
        int zSize = 48;
        int flatness = 128 + rand.nextInt(384 + 1);
        int fillingId;
        HeightFunction[] lowerFunctions = new HeightFunction[(xSize + zSize)/8 + 1];
        for (int i = 0; i < lowerFunctions.length; i++) {
            lowerFunctions[i] = new HeightFunction(1 - 2 * rand.nextInt(2), flatness + rand.nextInt(flatness), flatness + rand.nextInt(flatness), xSize / 2 - rand.nextInt(xSize + 1), zSize / 2 - rand.nextInt(zSize + 1), -rand.nextInt(32), -rand.nextInt(32));
        }
        HeightFunction[] upperFunctions = new HeightFunction[(xSize + zSize)/8 + 1];
        for (int i = 0; i < upperFunctions.length; i++) {
            upperFunctions[i] = new HeightFunction(1 - 2 * rand.nextInt(2), flatness + rand.nextInt(flatness), flatness + rand.nextInt(flatness), xSize / 2 - rand.nextInt(xSize + 1), zSize / 2 - rand.nextInt(zSize + 1), rand.nextInt(32), rand.nextInt(32));
        }
        int[] stalactiteAtZ = new int[(xSize + zSize)/16 + 1];
        for (int xWidth = -xSize; xWidth <= xSize; xWidth++) {
            for (int iterator = 0; iterator < stalactiteAtZ.length; iterator++) {
                stalactiteAtZ[iterator] = rand.nextInt(120) - 60;
                for (int zWidth = -zSize; zWidth <= zSize; zWidth++) {
                    boolean generateStalactite = false;
                    for (int i : stalactiteAtZ) {
                        if (i == zWidth) {
                            generateStalactite = true;
                            break;
                        }
                    }
                    if (generateStalactite) continue;
                    for (int height = heightFromAllFunctions(upperFunctions, xWidth, zWidth) - (xWidth*xWidth + zWidth*zWidth)/((8192)/flatness); height > heightFromAllFunctions(lowerFunctions, xWidth, zWidth) + (xWidth*xWidth + zWidth*zWidth)/((8192)/flatness); height--) {
                        int compactY = y + height;
                        int compactX = x + xWidth;
                        int compactZ = z + zWidth;
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
            }
        }
        return true;
    }

    private int heightFromAllFunctions(HeightFunction[] heightFunctions, int x, int z) {
        int totalHeight = 0;
        for (HeightFunction heightFunction : heightFunctions) {
            totalHeight += heightFunction.getLocalHeight(x, z);
        }
        if (totalHeight > 24) {
            return 24;
        } else if (totalHeight < -24) {
            return -24;
        }
        return totalHeight;
    }
}
