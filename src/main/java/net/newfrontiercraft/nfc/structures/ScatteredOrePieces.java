package net.newfrontiercraft.nfc.structures;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class ScatteredOrePieces extends Feature {

    public ScatteredOrePieces(int oreId, int radius, int oreAmount) {
        this.oreId = oreId;
        this.radius = radius;
        this.oreAmount = oreAmount;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        for (int amount = 0; amount < oreAmount; amount++) {
            int xOffset = x + random.nextInt(radius * 2 + 1) - radius;
            int yOffset = y + random.nextInt(radius * 2 + 1) - radius;
            int zOffset = z + random.nextInt(radius * 2 + 1) - radius;
            if (world.getBlockId(xOffset, yOffset, zOffset) == Block.STONE.id) {
                world.setBlock(xOffset, yOffset, zOffset, this.oreId);
            }
        }
        return true;
    }

    int oreId, radius, oreAmount;
}
