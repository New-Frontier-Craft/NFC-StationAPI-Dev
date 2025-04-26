package net.newfrontiercraft.nfc.feature;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CrystalClusterFeature extends Feature {
    private final int blockId;
    private final int sourceBlockId;

    public CrystalClusterFeature(int blockId, int sourceBlockId) {
        this.blockId = blockId;
        this.sourceBlockId = sourceBlockId;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        if (!world.isAir(x, y, z)) {
            return false;
        } else if (world.getBlockId(x, y + 1, z) != sourceBlockId) {
            return false;
        } else {
            world.setBlock(x, y, z, blockId);

            for(int var6 = 0; var6 < 1500; ++var6) {
                int var7 = x + random.nextInt(8) - random.nextInt(8);
                int var8 = y - random.nextInt(12);
                int var9 = z + random.nextInt(8) - random.nextInt(8);
                if (world.getBlockId(var7, var8, var9) == 0) {
                    int var10 = 0;

                    for(int var11 = 0; var11 < 6; ++var11) {
                        int var12 = 0;
                        if (var11 == 0) {
                            var12 = world.getBlockId(var7 - 1, var8, var9);
                        }

                        if (var11 == 1) {
                            var12 = world.getBlockId(var7 + 1, var8, var9);
                        }

                        if (var11 == 2) {
                            var12 = world.getBlockId(var7, var8 - 1, var9);
                        }

                        if (var11 == 3) {
                            var12 = world.getBlockId(var7, var8 + 1, var9);
                        }

                        if (var11 == 4) {
                            var12 = world.getBlockId(var7, var8, var9 - 1);
                        }

                        if (var11 == 5) {
                            var12 = world.getBlockId(var7, var8, var9 + 1);
                        }

                        if (var12 == blockId) {
                            ++var10;
                        }
                    }

                    if (var10 == 1) {
                        world.setBlock(var7, var8, var9, blockId);
                    }
                }
            }

            return true;
        }
    }
}
