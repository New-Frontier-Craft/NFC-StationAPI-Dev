package net.newfrontiercraft.nfc.structures;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeature;

import java.util.Random;

public class ConcentratedOreVein extends Feature {

    public ConcentratedOreVein(int oreId, int radius, int veinAmount, int oreAmount, int generateIn) {
        this.oreID = oreId;
        this.radius = radius;
        this.veinAmount = veinAmount;
        this.oreAmount = oreAmount;
        this.generateIn = generateIn;
    }

    public ConcentratedOreVein(int oreId, int radius, int veinAmount, int oreAmount) {
        this.oreID = oreId;
        this.radius = radius;
        this.veinAmount = veinAmount;
        this.oreAmount = oreAmount;
        this.generateIn = Block.STONE.id;
    }

    @Override
    public boolean generate(World level, Random random, int x, int y, int z) {
        double deg = 6.2831853/(double) veinAmount;
        for(int p = veinAmount; p >= 0; p--){
            double length = radius;
            length  = length*random.nextFloat();
            int xx = (int) (length*Math.cos(deg*p))+radius;
            int zz = (int) (length*Math.sin(deg*p))+radius;
            (new OreFeature(oreID, oreAmount)).generate(level,
                    random, x+xx, y+random.nextInt(3), z+zz);
        }

        return false;
    }

    int oreID, radius, veinAmount, oreAmount, generateIn;

}
