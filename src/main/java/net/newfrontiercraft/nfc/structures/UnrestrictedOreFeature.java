package net.newfrontiercraft.nfc.structures;

import net.minecraft.block.Block;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

/**
 * Unrestricted version of the ore feature that does not get cancelled for some specific ores.
 */
public class UnrestrictedOreFeature extends Feature {
    private int oreBlockId;
    private int oreCount;

    public UnrestrictedOreFeature(int oreBlockId, int oreCount) {
        this.oreBlockId = oreBlockId;
        this.oreCount = oreCount;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        float var6 = random.nextFloat() * 3.1415927F;
        double var7 = (double)((float)(x + 8) + MathHelper.sin(var6) * (float)this.oreCount / 8.0F);
        double var9 = (double)((float)(x + 8) - MathHelper.sin(var6) * (float)this.oreCount / 8.0F);
        double var11 = (double)((float)(z + 8) + MathHelper.cos(var6) * (float)this.oreCount / 8.0F);
        double var13 = (double)((float)(z + 8) - MathHelper.cos(var6) * (float)this.oreCount / 8.0F);
        double var15 = (double)(y + random.nextInt(3) + 2);
        double var17 = (double)(y + random.nextInt(3) + 2);

        for(int var19 = 0; var19 <= this.oreCount; ++var19) {
            double var20 = var7 + (var9 - var7) * (double)var19 / (double)this.oreCount;
            double var22 = var15 + (var17 - var15) * (double)var19 / (double)this.oreCount;
            double var24 = var11 + (var13 - var11) * (double)var19 / (double)this.oreCount;
            double var26 = random.nextDouble() * (double)this.oreCount / 16.0;
            double var28 = (double)(MathHelper.sin((float)var19 * 3.1415927F / (float)this.oreCount) + 1.0F) * var26 + 1.0;
            double var30 = (double)(MathHelper.sin((float)var19 * 3.1415927F / (float)this.oreCount) + 1.0F) * var26 + 1.0;
            int var32 = MathHelper.floor(var20 - var28 / 2.0);
            int var33 = MathHelper.floor(var22 - var30 / 2.0);
            int var34 = MathHelper.floor(var24 - var28 / 2.0);
            int var35 = MathHelper.floor(var20 + var28 / 2.0);
            int var36 = MathHelper.floor(var22 + var30 / 2.0);
            int var37 = MathHelper.floor(var24 + var28 / 2.0);

            for(int var38 = var32; var38 <= var35; ++var38) {
                double var39 = ((double)var38 + 0.5 - var20) / (var28 / 2.0);
                if (var39 * var39 < 1.0) {
                    for(int var41 = var33; var41 <= var36; ++var41) {
                        double var42 = ((double)var41 + 0.5 - var22) / (var30 / 2.0);
                        if (var39 * var39 + var42 * var42 < 1.0) {
                            for(int var44 = var34; var44 <= var37; ++var44) {
                                double var45 = ((double)var44 + 0.5 - var24) / (var28 / 2.0);
                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0 && world.getBlockId(var38, var41, var44) == Block.STONE.id) {
                                    world.setBlockWithoutNotifyingNeighbors(var38, var41, var44, this.oreBlockId);
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
