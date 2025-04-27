package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class PlanterOakTreeFeature extends Feature {
    public PlanterOakTreeFeature() {
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        int var6 = random.nextInt(3) + 4;
        boolean var7 = true;
        if (y >= 1 && y + var6 + 1 <= 128) {
            int var8;
            int var9;
            int var10;
            int var11;
            int var12;
            for(var8 = y; var8 <= y + 1 + var6; ++var8) {
                var9 = 1;
                if (var8 == y) {
                    var9 = 0;
                }

                if (var8 >= y + 1 + var6 - 2) {
                    var9 = 2;
                }

                for(var10 = x - var9; var10 <= x + var9 && var7; ++var10) {
                    for(var11 = z - var9; var11 <= z + var9 && var7; ++var11) {
                        if (var8 >= 0 && var8 < 128) {
                            var12 = world.getBlockId(var10, var8, var11);
                            if (var12 != 0 && var12 != Block.LEAVES.id) {
                                var7 = false;
                            }
                        } else {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7) {
                return false;
            } else {
                var8 = world.getBlockId(x, y - 1, z);
                if ((var8 == BlockListener.planter.id) && y < 128 - var6 - 1) {

                    for(var9 = y - 3 + var6; var9 <= y + var6; ++var9) {
                        var10 = var9 - (y + var6);
                        var11 = 1 - var10 / 2;

                        for(var12 = x - var11; var12 <= x + var11; ++var12) {
                            int var13 = var12 - x;

                            for(int var14 = z - var11; var14 <= z + var11; ++var14) {
                                int var15 = var14 - z;
                                if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || random.nextInt(2) != 0 && var10 != 0) && !Block.BLOCKS_OPAQUE[world.getBlockId(var12, var9, var14)]) {
                                    world.setBlockWithoutNotifyingNeighbors(var12, var9, var14, Block.LEAVES.id);
                                }
                            }
                        }
                    }

                    for(var9 = 0; var9 < var6; ++var9) {
                        var10 = world.getBlockId(x, y + var9, z);
                        if (var10 == 0 || var10 == Block.LEAVES.id) {
                            world.setBlockWithoutNotifyingNeighbors(x, y + var9, z, Block.LOG.id);
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
