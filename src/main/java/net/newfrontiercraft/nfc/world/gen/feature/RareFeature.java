package net.newfrontiercraft.nfc.world.gen.feature;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class RareFeature extends Feature {
    private final int rarity;
    private final Feature feature;

    public RareFeature(Feature feature, int rarity) {
        this.feature = feature;
        this.rarity = rarity;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        return random.nextInt(rarity) == 0 && feature.generate(world, random, x, y, z);
    }
}
