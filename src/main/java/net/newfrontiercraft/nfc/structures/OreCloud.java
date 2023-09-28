package net.newfrontiercraft.nfc.structures;

import net.minecraft.block.BlockBase;
import net.minecraft.level.Level;
import net.minecraft.level.structure.Structure;

import java.util.Random;

public class OreCloud extends Structure {


    public OreCloud(int oreID, int radius, int density, int amount, int generateIn) {
        this.oreID = oreID;
        this.radius = radius;
        this.density = density;
        this.amount = amount;
        this.generateIn = generateIn;
    }

    public OreCloud(int oreID, int radius, int density, int amount) {
        this.oreID = oreID;
        this.radius = radius;
        this.density = density;
        this.amount = amount;
        generateIn = BlockBase.STONE.id;
    }

    public boolean generate(Level level, Random random, int x, int y, int z) {
        int p = (int) (amount - (amount*(random.nextFloat()*0.2)));
        boolean pregen[][][] = new boolean [radius*2][radius*2][radius*2];
        double deg = 6.2831853/(double)p;
        for(int o = p-1; o >= 0; o--){
            double length = (double)radius;
            for(int w = 0; w <= density; w++){
                length  = length*random.nextFloat();
            }
            int xx = (int) (length*Math.cos(deg*o))+radius;
            int yy = (int) (length*Math.sin(deg*o))+radius;
            int zz = (int) (length*Math.sin(6.2831853*random.nextFloat()))+radius;
            if (pregen[xx][yy][zz]){
                o++;
            } else { pregen[xx][yy][zz] = true; }

        }

        for(int o = 0; o < radius*2; o++) for(int l = 0; l < radius*2; l++) for(int f = 0; f < radius*2; f++) {
            if(pregen[o][l][f] && level.getTileId(o+x, l+y, z+f) == generateIn){
                level.setTileInChunk(o+x, l+y, z+f, oreID);
            }
        }

        return false;
    }

    int oreID, radius, density, amount, generateIn;

}

