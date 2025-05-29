package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class ShrubFeature extends Feature {

    int meta;
    boolean alphaMode;

    public ShrubFeature(int meta) {
        super();
        this.meta = meta;
    }

    public ShrubFeature(boolean alphaMode) {
        super();
        this.alphaMode = alphaMode;
    }

    public ShrubFeature() {
        super();
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        if (!(world.getBlockId(x, y, z) == 0 && world.getLightLevel(x, y, z) > 0.9F)) return false;
        int ground = world.getBlockId(x, y - 1, z);
        if (ground != Block.GRASS_BLOCK.id && ground != Block.FARMLAND.id && ground != BlockListener.planter.id && ground != Block.DIRT.id) return false;
        int randomHeight = random.nextInt(3) + 2;
        for (int sphereCount = 0; sphereCount < random.nextInt(8) + 4; sphereCount++) {
            generateFoliageSphere(world, x - random.nextInt(3) + 1, y + random.nextInt(randomHeight), z - random.nextInt(3) + 1);
        }
        for (int sphereCount = 0; sphereCount < random.nextInt(4) + 2; sphereCount++) {
            generateFoliageSphere(world, x - random.nextInt(7) + 3, y + random.nextInt(randomHeight), z - random.nextInt(7) + 3);
        }
        generateFoliageSphere(world, x, y + randomHeight, z);
        for (int logHeight = 0; logHeight < randomHeight - 1; logHeight++) {
            world.setBlock(x, y + logHeight, z, Block.LOG.id, meta);
        }
        if (ground == Block.GRASS_BLOCK.id || ground == Block.FARMLAND.id) {
            world.setBlock(x, y - 1, z, Block.DIRT.id);
        }
        if(alphaMode) {
            for(int i2 = -2; i2 <= 2; i2++) {
                for(int j2 = -2; j2 <= 0; j2++) {
                    for(int k2 = -2; k2 <= 2; k2++) {
                        if(world.getBlockId(x + i2, y + j2, z + k2) == Block.GRASS_BLOCK.id) {
                            boolean flag2 = true;
                            if (((i2 < -1 || i2 > 1) || (k2 < -1 || k2 > 1)) && random.nextInt(2) == 0)
                                flag2 = false;

                            if (flag2)
                                world.setBlock(x + i2, y + j2, z + k2, BlockListener.alphaGrass.id);
                        }
                    }
                }
            }
        }
        if (ground != BlockListener.planter.id) {
            world.setBlock(x, y - 1, z, Block.DIRT.id);
        }
        return true;
    }

    private void generateFoliageSphere(World world, int x, int y, int z) {
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    if ((xOffset == 1 || xOffset == -1) && (yOffset == 1 || yOffset == -1) && (zOffset == 1 || zOffset == -1)) continue;
                    if (world.getBlockId(x + xOffset, y + yOffset, z + zOffset) != 0) continue;
                    world.setBlockWithoutNotifyingNeighbors(x + xOffset, y + yOffset, z + zOffset, alphaMode ? BlockListener.alphaLeaves.id : Block.LEAVES.id, meta);
                }
            }
        }
    }
}
