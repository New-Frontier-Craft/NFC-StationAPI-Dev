package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class AlphaTreeFeature extends Feature {
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        int treeHeight = random.nextInt(3) + 5;
        boolean canGrow = true;
        if (y >= 1 && y + treeHeight + 1 <= 128) {
            for(int currentY = y; currentY <= y + 1 + treeHeight; ++currentY) {
                int radius = 1;
                if (currentY == y) {
                    radius = 0;
                }

                if (currentY >= y + 1 + treeHeight - 2) {
                    radius = 2;
                }

                for(int xPosition = x - radius; xPosition <= x + radius && canGrow; ++xPosition) {
                    for(int zPosition = z - radius; zPosition <= z + radius && canGrow; ++zPosition) {
                        if (currentY >= world.getBottomY() && currentY < world.getTopY()) {
                            int blockId = world.getBlockId(xPosition, currentY, zPosition);
                            if (blockId != 0 && blockId != BlockListener.alphaLeaves.id) {
                                canGrow = false;
                            }
                        } else {
                            canGrow = false;
                        }
                    }
                }
            }

            if (!canGrow) {
                return false;
            } else {
                int groundBlock = world.getBlockId(x, y - 1, z);
                if ((groundBlock == Block.GRASS_BLOCK.id || groundBlock == Block.DIRT.id) && y < 128 - treeHeight - 1) {
                    world.setBlockWithoutNotifyingNeighbors(x, y - 1, z, Block.DIRT.id);

                    for(int xOffset = -2; xOffset <= 2; ++xOffset) {
                        for(int yOffset = -2; yOffset <= 0; ++yOffset) {
                            for(int zOffset = -2; zOffset <= 2; ++zOffset) {
                                if(world.getBlockId(x + xOffset, y + yOffset, z + zOffset) == Block.GRASS_BLOCK.id) {
                                    boolean placeGrass = true;
                                    if((xOffset < -1 || xOffset > 1 || zOffset < -1 || zOffset > 1) && random.nextInt(2) == 0) {
                                        placeGrass = false;
                                    }

                                    if(placeGrass) {
                                        world.setBlock(x + xOffset, y + yOffset, z + zOffset, BlockListener.alphaGrass.id);
                                    }
                                }
                            }
                        }
                    }

                    for(int leafY = y - 3 + treeHeight; leafY <= y + treeHeight; ++leafY) {
                        int distanceFromTop = leafY - (y + treeHeight);
                        int radius = 1 - distanceFromTop / 2;

                        for(int leafX = x - radius; leafX <= x + radius; ++leafX) {
                            int var13 = leafX - x;

                            for(int leafZ = z - radius; leafZ <= z + radius; ++leafZ) {
                                int var15 = leafZ - z;
                                if ((Math.abs(var13) != radius || Math.abs(var15) != radius || random.nextInt(2) != 0 && distanceFromTop != 0) && !Block.BLOCKS_OPAQUE[world.getBlockId(leafX, leafY, leafZ)]) {
                                    world.setBlockWithoutNotifyingNeighbors(leafX, leafY, leafZ, BlockListener.alphaLeaves.id);
                                }
                            }
                        }
                    }

                    for(int trunkY = 0; trunkY < treeHeight; ++trunkY) {
                        int blockAtTrunk = world.getBlockId(x, y + trunkY, z);
                        if (blockAtTrunk == 0 || blockAtTrunk == BlockListener.alphaLeaves.id) {
                            world.setBlockWithoutNotifyingNeighbors(x, y + trunkY, z, Block.LOG.id);
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
