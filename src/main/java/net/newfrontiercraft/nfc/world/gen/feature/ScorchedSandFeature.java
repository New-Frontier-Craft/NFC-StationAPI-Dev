package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class ScorchedSandFeature extends Feature {

    private int numberOfBlocks = 32;

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if(world.getBlockId(x, y, z) == 0){
            return false;
        } else {

            float f = random.nextFloat() * (float)Math.PI;
            double d = ((float)(x + 8) + MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
            double d1 = ((float)(x + 8) - MathHelper.sin(f) * (float)this.numberOfBlocks / 8.0F);
            double d2 = ((float)(z + 8) + MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);
            double d3 = ((float)(z + 8) - MathHelper.cos(f) * (float)this.numberOfBlocks / 8.0F);

            for(int l = 0; l < this.numberOfBlocks; l++){
                double d6 = d + (d1 - d) * (double)l / (double)this.numberOfBlocks;
                double d8 = d2 + (d3 - d2) * (double)l / (double)this.numberOfBlocks;
                double d9 = random.nextDouble() * (double)this.numberOfBlocks / 16.0D;
                double d10 = (double)(MathHelper.sin((float)l * 3.141593F / (float)this.numberOfBlocks) + 1.0F) * d9 + 1.0D;
                int i1 = MathHelper.floor(d6 - d10 / 2.0D);
                int j1 = MathHelper.floor(d6 + d10 / 2.0D);
                int i2 = MathHelper.floor(d8 - d10 / 2.0D);
                int j2 = MathHelper.floor(d8 + d10 / 2.0D);

                for(int k2 = i1; k2 <= j1 + random.nextInt(4) + 8; ++k2) {
                    for(int l2 = 0; l2 <= random.nextInt(3) + 1; ++l2) {
                        for(int i3 = i2; i3 <= j2 + random.nextInt(4) + 8; ++i3) {
                            int k3 = k2 - random.nextInt(3);
                            int l3 = l2 - random.nextInt(3);
                            int i4 = i3 - random.nextInt(3);
                            int j3 = world.getBlockId(k3, l3 + y, i4);
                            int j4 = world.getBlockId(k3, l3 + y - 1, i4);
                            int j5 = world.getBlockId(k3, l3 + y + 4, i4);
                            if(j3 == Block.NETHERRACK.id && j4 != 0 && j5 == 0) {
                                world.setBlock(k3, l3 + y, i4, BlockListener.scorchedSand.id);
                            }
                        }
                    }
                }
            }

            return true;
        }
    }
}
