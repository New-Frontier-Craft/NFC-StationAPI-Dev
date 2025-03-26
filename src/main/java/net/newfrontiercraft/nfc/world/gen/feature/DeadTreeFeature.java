package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class DeadTreeFeature extends Feature {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int l = random.nextInt(3) + 5;
        if(y >= 1 && y + l + 1 <= 128) {
            boolean flag = true;

            int x0;
            int j1;
            int maxFallenLeaves;
            for(x0 = y; x0 <= y + 1 + l; ++x0) {
                byte z0 = 1;
                if(x0 == y) {
                    z0 = 0;
                }

                if(x0 >= y + 1 + l - 2) {
                    z0 = 2;
                }

                for(j1 = x - z0; j1 <= x + z0 && flag; ++j1) {
                    for(maxFallenLeaves = z - z0; maxFallenLeaves <= z + z0 && flag; ++maxFallenLeaves) {
                        if(x0 >= 0 && x0 < 128) {
                            int falling = world.getBlockId(j1, x0, maxFallenLeaves);
                            if(falling != 0) {
                                flag = false;
                            }
                        } else {
                            flag = false;
                        }
                    }
                }
            }

            x0 = x;
            int i22 = z;
            j1 = world.getBlockId(x, y - 1, z);
            if((j1 == Block.GRASS.id || j1 == Block.DIRT.id || j1 == Block.SOUL_SAND.id) && y < 128 - l - 1) {
                maxFallenLeaves = random.nextInt(3) > 0 ? random.nextInt(24) + 8 : 0;
                boolean z23 = false;
                int swayHeight = -1;
                if(random.nextBoolean()) {
                    swayHeight = l - (random.nextInt(3) + 3);
                    switch(random.nextInt(3)) {
                        case 1:
                            x0 = x - 1;
                            break;
                        case 2:
                            i22 = z + 1;
                            break;
                        case 3:
                            i22 = z - 1;
                            break;
                        default:
                            x0 = x + 1;
                    }
                }

                int l1;
                int k2;
                for(l1 = y - 3 + l; l1 <= y + l; ++l1) {
                    k2 = l1 - (y + l);
                    int i3 = 1 - k2 / 2;

                    for(int k3 = x0 - i3; k3 <= x0 + i3; ++k3) {
                        int l3 = k3 - x0;

                        for(int i4 = i22 - i3; i4 <= i22 + i3; ++i4) {
                            int j4 = i4 - i22;
                            if((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && k2 != 0) && !Block.BLOCKS_OPAQUE[world.getBlockId(k3, l1, i4)]) {
                                if(maxFallenLeaves > 0 && !z23 && random.nextInt(3) == 0) {
                                    z23 = true;
                                }

                                int i1 = 0;
                                if(z23 && random.nextBoolean()) {
                                    for(i1 = 8; i1 >= 0; --i1) {
                                        if(world.shouldSuffocate(k3, l1 - i1, i4) && world.isAir(k3, l1 - i1 + 1, i4)) {
                                            if(maxFallenLeaves > 0) {
                                                --maxFallenLeaves;
                                            } else {
                                                z23 = false;
                                            }
                                            break;
                                        }
                                    }
                                }

                                if(i1 > -1) {
                                    world.setBlock(k3, l1 - i1 + 1, i4, BlockListener.petrifiedLeaves.id);
                                }
                            }
                        }
                    }
                }

                for(l1 = 0; l1 < l; ++l1) {
                    k2 = world.getBlockId(x, y + l1, z);
                    if(k2 == 0 || k2 == BlockListener.petrifiedLeaves.id) {
                        if(swayHeight > -1 && l1 >= swayHeight) {
                            world.setBlock(x0, y + l1, i22, BlockListener.petrifiedLog.id);
                        } else {
                            world.setBlock(x, y + l1, z, BlockListener.petrifiedLog.id);
                        }
                    }
                }

                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
