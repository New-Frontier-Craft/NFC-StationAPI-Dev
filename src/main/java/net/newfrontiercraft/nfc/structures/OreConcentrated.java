package net.newfrontiercraft.nfc.structures;

import net.minecraft.block.BlockBase;
import net.minecraft.level.Level;
import net.minecraft.level.structure.Ore;
import net.minecraft.level.structure.Structure;

import java.util.Random;

public class OreConcentrated extends Structure {

    public OreConcentrated(int oreID, int radius, int veinAmount, int oreAmount, int generateIn) {
        this.oreID = oreID;
        this.radius = radius;
        this.veinAmount = veinAmount;
        this.oreAmount = oreAmount;
        this.generateIn = generateIn;
    }

    public OreConcentrated(int oreId, int radius, int veinAmount, int oreAmount) {
        this.oreID = oreId;
        this.radius = radius;
        this.veinAmount = veinAmount;
        this.oreAmount = oreAmount;
        this.generateIn = BlockBase.STONE.id;
    }

    public boolean generate(Level level, Random random, int x, int y, int z) {
        double deg = 6.2831853/(double) veinAmount;
        for(int p = veinAmount; p >= 0; p--){
            double length = radius;
            length  = length*random.nextFloat();
            int xx = (int) (length*Math.cos(deg*p))+radius;
            int zz = (int) (length*Math.sin(deg*p))+radius;
            (new Ore(oreID, oreAmount)).generate(level,
                    random, x+xx, y+random.nextInt(3), z+zz);
        }

        return false;
    }

    int oreID, radius, veinAmount, oreAmount, generateIn;

}
