package net.newfrontiercraft.nfc.world.gen.carver;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.Generator;

public class RavineWorldCarver extends Generator {
    @Override
    protected void place(World world, int startChunkX, int startChunkZ, int chunkX, int chunkZ, byte[] blocks) {
        if(this.random.nextInt(15) != 0) {
            return;
        }
        int x = (startChunkX * 16 + this.random.nextInt(16));
        int y = this.random.nextInt(this.random.nextInt(128 - 8) + 8);
        int z = (startChunkZ * 16 + this.random.nextInt(16));

        int fillingId;
        int curvature = random.nextInt(50) + 1;
        int smoothness = random.nextInt(5) * random.nextInt(3) + 1;
        int sideBias = random.nextInt(100);
        int width = random.nextInt(12) + 8;
        int depth = random.nextInt(12) + 4;
        int randomSideOffset = 0;
        int randomHeightOffset = 0;
        int flatness = random.nextInt(4) + 2;
        int ceilingCurve = random.nextInt(4) + 2;
        int steepness = random.nextInt(8) + 4;
        int inverter = 1;
        int length = random.nextInt(96) + 32;
        if (random.nextBoolean()) {
            inverter = 0;
        }

        int xOff = length * inverter + (width + 16) * (1 - inverter);
        int zOff = (width + 16) * inverter + length * (1 - inverter);
        int minCX = (x - xOff) >> 4;
        int maxCX = (x + xOff + 16) >> 4;
        int minCZ = (z - zOff) >> 4;
        int maxCZ = (z + zOff + 16) >> 4;

        if (chunkX < minCX || chunkX > maxCX || chunkZ < minCZ || chunkZ > maxCZ) {
            return;
        }

        for (int rx = -length; rx <= length; rx++) {
            for (int rz = -width; rz <= width; rz++) {
                int ryLimit = (-random.nextInt(steepness) / (random.nextInt(smoothness) + 1) - depth + (((rz + random.nextInt(2)) * (rz + random.nextInt(2))) / flatness) * steepness - steepness * 2) + Math.abs(rx * 16 / length);
                for (int ry = -ryLimit * ceilingCurve / 10; ry >= ryLimit; ry--) {
                    int bx = x + (rx * inverter) + ((rz + randomSideOffset) * (1 - inverter));
                    int by = y + ry + randomHeightOffset;
                    int bz = z + ((rz + randomSideOffset) * inverter) + (rx * (1 - inverter));
                    if (bx >> 4 != chunkX || bz >> 4 != chunkZ || by < 0) {
                        continue;
                    }
                    bx &= 15;
                    bz &= 15;

                    if (getBlockMaterial(blocks, bx, by + 1, bz) == Material.PLANT) {
                        setBlock(blocks, bx, by + 1, bz, 0);
                    }

                    if (by < 12) {
                        fillingId = Block.LAVA.id;
                    } else if (getBlockId(blocks, bx, by + 1, bz) == Block.WATER.id) {
                        fillingId = Block.WATER.id;
                    } else {
                        fillingId = 0;
                    }

                    Block oreBlock = Block.BLOCKS[getBlockId(blocks, bx, by, bz)];
                    int oreBlockId = oreBlock == null ? 0 : oreBlock.id;
                    if (oreBlockId == Block.FLOWING_LAVA.id
                            || oreBlockId == Block.LAVA.id
                            || oreBlockId == Block.GRASS_BLOCK.id
                            || oreBlockId == Block.STONE.id
                            || oreBlockId == Block.DIRT.id) {
                        setBlock(blocks, bx, by, bz, fillingId);
                    }
                }
            }

            if (random.nextInt(smoothness) == 0) {
                if (random.nextInt(100) - sideBias < 0) {
                    randomSideOffset += 1;
                } else {
                    randomSideOffset -= 1;
                }
                randomHeightOffset += (random.nextInt(3) - 1) * random.nextInt(steepness);
                if (steepness - random.nextInt(steepness) > random.nextInt(steepness) && steepness > 1) {
                    steepness -= 1;
                } else {
                    steepness += 1;
                }
                if (flatness - random.nextInt(flatness) > random.nextInt(flatness) && flatness > 1) {
                    flatness -= 1;
                } else {
                    flatness += 1;
                }
                if (curvature - random.nextInt(curvature) > random.nextInt(curvature) && curvature > smoothness) {
                    curvature -= smoothness;
                } else {
                    curvature += steepness;
                }
                if (ceilingCurve - random.nextInt(ceilingCurve) > random.nextInt(ceilingCurve) * 2 / 3 && ceilingCurve > 1) {
                    ceilingCurve -= 1;
                } else {
                    ceilingCurve += 1;
                }
            }
            if (random.nextInt(100) - sideBias < 0) {
                sideBias -= curvature;
            } else {
                sideBias += curvature;
            }
            if (smoothness - random.nextInt(smoothness) > random.nextInt(smoothness) && smoothness > 1) {
                smoothness -= 1;
            } else {
                smoothness += 1;
            }
        }
    }

    private static int getBlockId(byte[] blocks, int x, int y, int z) {
        return blocks[x << 11 | z << 7 | y];
    }

    private static Material getBlockMaterial(byte[] blocks, int x, int y, int z) {
        Block block = Block.BLOCKS[getBlockId(blocks, x, y, z)];
        if (block == null) {
            return Material.AIR;
        }
        return block.material;
    }

    private static void setBlock(byte[] blocks, int x, int y, int z, int id) {
        blocks[x << 11 | z << 7 | y] = (byte)id;
    }
}
