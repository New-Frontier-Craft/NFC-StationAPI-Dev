package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LargeOakTreeFeature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class LargeAlphaTreeFeature extends Feature {
    static final byte[] MINOR_AXES = new byte[]{2, 0, 0, 1, 2, 1};
    Random random = new Random();
    World world;
    int[] origin = new int[]{0, 0, 0};
    int height = 0;
    int trunkHeight;
    double trunkScale = 0.618;
    double branchDensity = (double)1.0F;
    double branchSlope = 0.381;
    double branchLengthScale = (double)1.0F;
    double foliageDensity = (double)1.0F;
    int trunkWidth = 1;
    int maxTrunkHeight = 12;
    int foliageClusterHeight = 4;
    int[][] branches;

    void makeBranches() {
        this.trunkHeight = (int)((double)this.height * this.trunkScale);
        if (this.trunkHeight >= this.height) {
            this.trunkHeight = this.height - 1;
        }

        int var1 = (int)(1.382 + Math.pow(this.foliageDensity * (double)this.height / (double)13.0F, (double)2.0F));
        if (var1 < 1) {
            var1 = 1;
        }

        int[][] var2 = new int[var1 * this.height][4];
        int var3 = this.origin[1] + this.height - this.foliageClusterHeight;
        int var4 = 1;
        int var5 = this.origin[1] + this.trunkHeight;
        int var6 = var3 - this.origin[1];
        var2[0][0] = this.origin[0];
        var2[0][1] = var3;
        var2[0][2] = this.origin[2];
        var2[0][3] = var5;
        --var3;

        while(var6 >= 0) {
            int var7 = 0;
            float var8 = this.getTreeShape(var6);
            if (var8 < 0.0F) {
                --var3;
                --var6;
            } else {
                for(double var9 = (double)0.5F; var7 < var1; ++var7) {
                    double var11 = this.branchLengthScale * (double)var8 * ((double)this.random.nextFloat() + 0.328);
                    double var13 = (double)this.random.nextFloat() * (double)2.0F * 3.14159;
                    int var15 = MathHelper.floor(var11 * Math.sin(var13) + (double)this.origin[0] + var9);
                    int var16 = MathHelper.floor(var11 * Math.cos(var13) + (double)this.origin[2] + var9);
                    int[] var17 = new int[]{var15, var3, var16};
                    int[] var18 = new int[]{var15, var3 + this.foliageClusterHeight, var16};
                    if (this.tryBranch(var17, var18) == -1) {
                        int[] var19 = new int[]{this.origin[0], this.origin[1], this.origin[2]};
                        double var20 = Math.sqrt(Math.pow((double)Math.abs(this.origin[0] - var17[0]), (double)2.0F) + Math.pow((double)Math.abs(this.origin[2] - var17[2]), (double)2.0F));
                        double var22 = var20 * this.branchSlope;
                        if ((double)var17[1] - var22 > (double)var5) {
                            var19[1] = var5;
                        } else {
                            var19[1] = (int)((double)var17[1] - var22);
                        }

                        if (this.tryBranch(var19, var17) == -1) {
                            var2[var4][0] = var15;
                            var2[var4][1] = var3;
                            var2[var4][2] = var16;
                            var2[var4][3] = var19[1];
                            ++var4;
                        }
                    }
                }

                --var3;
                --var6;
            }
        }

        this.branches = new int[var4][4];
        System.arraycopy(var2, 0, this.branches, 0, var4);
    }

    void placeCluster(int x, int y, int z, float shape, byte majorAxis, int clusterBlock) {
        int var7 = (int)((double)shape + 0.618);
        byte var8 = MINOR_AXES[majorAxis];
        byte var9 = MINOR_AXES[majorAxis + 3];
        int[] var10 = new int[]{x, y, z};
        int[] var11 = new int[]{0, 0, 0};
        int var12 = -var7;
        int var13 = -var7;

        for(var11[majorAxis] = var10[majorAxis]; var12 <= var7; ++var12) {
            var11[var8] = var10[var8] + var12;
            var13 = -var7;

            while(var13 <= var7) {
                double var15 = Math.sqrt(Math.pow((double)Math.abs(var12) + (double)0.5F, (double)2.0F) + Math.pow((double)Math.abs(var13) + (double)0.5F, (double)2.0F));
                if (var15 > (double)shape) {
                    ++var13;
                } else {
                    var11[var9] = var10[var9] + var13;
                    int var14 = this.world.getBlockId(var11[0], var11[1], var11[2]);
                    if (var14 != 0 && var14 != 18) {
                        ++var13;
                    } else {
                        this.world.setBlockWithoutNotifyingNeighbors(var11[0], var11[1], var11[2], clusterBlock);
                        ++var13;
                    }
                }
            }
        }

    }

    float getTreeShape(int height) {
        if ((double)height < (double)((float)this.height) * 0.3) {
            return -1.618F;
        } else {
            float var2 = (float)this.height / 2.0F;
            float var3 = (float)this.height / 2.0F - (float)height;
            float var4;
            if (var3 == 0.0F) {
                var4 = var2;
            } else if (Math.abs(var3) >= var2) {
                var4 = 0.0F;
            } else {
                var4 = (float)Math.sqrt(Math.pow((double)Math.abs(var2), (double)2.0F) - Math.pow((double)Math.abs(var3), (double)2.0F));
            }

            var4 *= 0.5F;
            return var4;
        }
    }

    float getClusterShape(int layer) {
        if (layer >= 0 && layer < this.foliageClusterHeight) {
            return layer != 0 && layer != this.foliageClusterHeight - 1 ? 3.0F : 2.0F;
        } else {
            return -1.0F;
        }
    }

    void placeFoliageCluster(int x, int baseY, int z) {
        int var4 = baseY;

        for(int var5 = baseY + this.foliageClusterHeight; var4 < var5; ++var4) {
            float var6 = this.getClusterShape(var4 - baseY);
            this.placeCluster(x, var4, z, var6, (byte)1, BlockListener.alphaLeaves.id);
        }

    }

    void placeBranch(int[] from, int[] to, int log) {
        int[] var4 = new int[]{0, 0, 0};
        byte var5 = 0;

        byte var6;
        for(var6 = 0; var5 < 3; ++var5) {
            var4[var5] = to[var5] - from[var5];
            if (Math.abs(var4[var5]) > Math.abs(var4[var6])) {
                var6 = var5;
            }
        }

        if (var4[var6] != 0) {
            byte var7 = MINOR_AXES[var6];
            byte var8 = MINOR_AXES[var6 + 3];
            byte var9;
            if (var4[var6] > 0) {
                var9 = 1;
            } else {
                var9 = -1;
            }

            double var10 = (double)var4[var7] / (double)var4[var6];
            double var12 = (double)var4[var8] / (double)var4[var6];
            int[] var14 = new int[]{0, 0, 0};
            int var15 = 0;

            for(int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
                var14[var6] = MathHelper.floor((double)(from[var6] + var15) + (double)0.5F);
                var14[var7] = MathHelper.floor((double)from[var7] + (double)var15 * var10 + (double)0.5F);
                var14[var8] = MathHelper.floor((double)from[var8] + (double)var15 * var12 + (double)0.5F);
                this.world.setBlockWithoutNotifyingNeighbors(var14[0], var14[1], var14[2], log);
            }

        }
    }

    void placeFoliage() {
        int var1 = 0;

        for(int var2 = this.branches.length; var1 < var2; ++var1) {
            int var3 = this.branches[var1][0];
            int var4 = this.branches[var1][1];
            int var5 = this.branches[var1][2];
            this.placeFoliageCluster(var3, var4, var5);
        }

    }

    boolean shouldPlaceBranch(int height) {
        return !((double)height < (double)this.height * 0.2);
    }

    void PlaceTrunk() {
        int var1 = this.origin[0];
        int var2 = this.origin[1];
        int var3 = this.origin[1] + this.trunkHeight;
        int var4 = this.origin[2];
        int[] var5 = new int[]{var1, var2, var4};
        int[] var6 = new int[]{var1, var3, var4};
        this.placeBranch(var5, var6, 17);
        if (this.trunkWidth == 2) {
            int var10002 = var5[0]++;
            var10002 = var6[0]++;
            this.placeBranch(var5, var6, 17);
            var10002 = var5[2]++;
            var10002 = var6[2]++;
            this.placeBranch(var5, var6, 17);
            var5[0] += -1;
            var6[0] += -1;
            this.placeBranch(var5, var6, 17);
        }

    }

    void placeBranches() {
        int var1 = 0;
        int var2 = this.branches.length;

        for(int[] var3 = new int[]{this.origin[0], this.origin[1], this.origin[2]}; var1 < var2; ++var1) {
            int[] var4 = this.branches[var1];
            int[] var5 = new int[]{var4[0], var4[1], var4[2]};
            var3[1] = var4[3];
            int var6 = var3[1] - this.origin[1];
            if (this.shouldPlaceBranch(var6)) {
                this.placeBranch(var3, var5, 17);
            }
        }

    }

    int tryBranch(int[] from, int[] to) {
        int[] var3 = new int[]{0, 0, 0};
        byte var4 = 0;

        byte var5;
        for(var5 = 0; var4 < 3; ++var4) {
            var3[var4] = to[var4] - from[var4];
            if (Math.abs(var3[var4]) > Math.abs(var3[var5])) {
                var5 = var4;
            }
        }

        if (var3[var5] == 0) {
            return -1;
        } else {
            byte var6 = MINOR_AXES[var5];
            byte var7 = MINOR_AXES[var5 + 3];
            byte var8;
            if (var3[var5] > 0) {
                var8 = 1;
            } else {
                var8 = -1;
            }

            double var9 = (double)var3[var6] / (double)var3[var5];
            double var11 = (double)var3[var7] / (double)var3[var5];
            int[] var13 = new int[]{0, 0, 0};
            int var14 = 0;

            int var15;
            for(var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
                var13[var5] = from[var5] + var14;
                var13[var6] = MathHelper.floor((double)from[var6] + (double)var14 * var9);
                var13[var7] = MathHelper.floor((double)from[var7] + (double)var14 * var11);
                int var16 = this.world.getBlockId(var13[0], var13[1], var13[2]);
                if (var16 != 0 && var16 != 18) {
                    break;
                }
            }

            return var14 == var15 ? -1 : Math.abs(var14);
        }
    }

    boolean canPlace() {
        int[] var1 = new int[]{this.origin[0], this.origin[1], this.origin[2]};
        int[] var2 = new int[]{this.origin[0], this.origin[1] + this.height - 1, this.origin[2]};
        int var3 = this.world.getBlockId(this.origin[0], this.origin[1] - 1, this.origin[2]);
        if (var3 != 2 && var3 != 3) {
            return false;
        } else {
            int var4 = this.tryBranch(var1, var2);
            if (var4 == -1) {
                return true;
            } else if (var4 < 6) {
                return false;
            } else {
                this.height = var4;
                return true;
            }
        }
    }

    @Override
    public void prepare(double d0, double d1, double d2) {
        this.maxTrunkHeight = (int)(d0 * (double)12.0F);
        if (d0 > (double)0.5F) {
            this.foliageClusterHeight = 5;
        }

        this.branchLengthScale = d1;
        this.foliageDensity = d2;
    }

    void placeGrass(){
        Random j = new Random();

        for(int i2 = -3; i2 <= 3; ++i2) {
            for(int j2 = -2; j2 <= 0; ++j2) {
                for(int k2 = -3; k2 <= 3; ++k2) {
                    if(this.world.getBlockId(this.origin[0] + i2, this.origin[1] + j2, this.origin[2] + k2) == Block.GRASS_BLOCK.id) {
                        boolean flag2 = true;
                        if((i2 < -2 || i2 > 2 || k2 < -2 || k2 > 2) && j.nextInt(2) == 0) {
                            flag2 = false;
                        } else if((i2 < -1 || i2 > 1 || k2 < -1 || k2 > 1) && j.nextFloat() <= 0.65F) {
                            flag2 = false;
                        }

                        if(flag2) {
                            this.world.setBlock(this.origin[0] + i2, this.origin[1] + j2, this.origin[2] + k2, BlockListener.alphaGrass.id);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        this.world = world;
        long var6 = random.nextLong();
        this.random.setSeed(var6);
        this.origin[0] = x;
        this.origin[1] = y;
        this.origin[2] = z;
        if (this.height == 0) {
            this.height = 5 + this.random.nextInt(this.maxTrunkHeight);
        }

        if (!this.canPlace()) {
            return false;
        } else {
            this.makeBranches();
            this.placeFoliage();
            this.PlaceTrunk();
            this.placeBranches();
            this.placeGrass();
            return true;
        }
    }
}
