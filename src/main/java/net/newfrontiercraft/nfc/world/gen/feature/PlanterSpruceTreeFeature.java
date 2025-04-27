package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class PlanterSpruceTreeFeature extends Feature {
    public PlanterSpruceTreeFeature() {
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int var6 = random.nextInt(4) + 6;
        int var7 = 1 + random.nextInt(2);
        int var8 = var6 - var7;
        int var9 = 2 + random.nextInt(2);
        boolean var10 = true;
        if (y >= 1 && y + var6 + 1 <= 128) {
            int var11;
            int var13;
            int var15;
            int var21;
            for(var11 = y; var11 <= y + 1 + var6 && var10; ++var11) {
                if (var11 - y < var7) {
                    var21 = 0;
                } else {
                    var21 = var9;
                }

                for(var13 = x - var21; var13 <= x + var21 && var10; ++var13) {
                    for(int var14 = z - var21; var14 <= z + var21 && var10; ++var14) {
                        if (var11 >= 0 && var11 < 128) {
                            var15 = world.getBlockId(var13, var11, var14);
                            if (var15 != 0 && var15 != Block.LEAVES.id) {
                                var10 = false;
                            }
                        } else {
                            var10 = false;
                        }
                    }
                }
            }

            if (!var10) {
                return false;
            } else {
                var11 = world.getBlockId(x, y - 1, z);
                if ((var11 == BlockListener.planter.id) && y < 128 - var6 - 1) {
                    var21 = random.nextInt(2);
                    var13 = 1;
                    byte var22 = 0;

                    int var16;
                    int var17;
                    for(var15 = 0; var15 <= var8; ++var15) {
                        var16 = y + var6 - var15;

                        for(var17 = x - var21; var17 <= x + var21; ++var17) {
                            int var18 = var17 - x;

                            for(int var19 = z - var21; var19 <= z + var21; ++var19) {
                                int var20 = var19 - z;
                                if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.BLOCKS_OPAQUE[world.getBlockId(var17, var16, var19)]) {
                                    world.setBlockWithoutNotifyingNeighbors(var17, var16, var19, Block.LEAVES.id, 1);
                                }
                            }
                        }

                        if (var21 >= var13) {
                            var21 = var22;
                            var22 = 1;
                            ++var13;
                            if (var13 > var9) {
                                var13 = var9;
                            }
                        } else {
                            ++var21;
                        }
                    }

                    var15 = random.nextInt(3);

                    for(var16 = 0; var16 < var6 - var15; ++var16) {
                        var17 = world.getBlockId(x, y + var16, z);
                        if (var17 == 0 || var17 == Block.LEAVES.id) {
                            world.setBlockWithoutNotifyingNeighbors(x, y + var16, z, Block.LOG.id, 1);
                        }
                    }

                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
