package net.newfrontiercraft.nfc.feature;

import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class MetaCapablePlantPatchFeature extends Feature {
    private final int plantBlockId;
    private final int plantMeta;

    public MetaCapablePlantPatchFeature(int plantBlockId, int plantMeta) {
        this.plantBlockId = plantBlockId;
        this.plantMeta = plantMeta;
    }

    public boolean generate(World world, Random random, int x, int y, int z) {
        for(int var6 = 0; var6 < 64; ++var6) {
            int var7 = x + random.nextInt(8) - random.nextInt(8);
            int var8 = y + random.nextInt(4) - random.nextInt(4);
            int var9 = z + random.nextInt(8) - random.nextInt(8);
            if (world.isAir(var7, var8, var9) && ((PlantBlock) Block.BLOCKS[this.plantBlockId]).canGrow(world, var7, var8, var9)) {
                world.setBlockWithoutNotifyingNeighbors(var7, var8, var9, this.plantBlockId, this.plantMeta);
            }
        }

        return true;
    }
}
