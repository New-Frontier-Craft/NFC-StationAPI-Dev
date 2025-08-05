package net.newfrontiercraft.nfc.gui;

import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;

public class InventoryBiomeSource extends BiomeSource {

    public InventoryBiomeSource(World world){
        super(world);
    }

    @Override
    public Biome[] getBiomesInArea(Biome[] biomes, int x, int z, int width, int depth) {
        biomes = new Biome[width * depth];
        this.temperatureMap = new double[width * depth];
        this.downfallMap = new double[width * depth];
        this.weirdnessMap = new double[width * depth];
        for(int i = 0; i < biomes.length; i++){
            biomes[i] = Biome.PLAINS;

            // Setting these values to something to avoid crashing
            temperatureMap[i] = 1f;
            downfallMap[i] = 1f;
            weirdnessMap[i] = 1f;
        }
        return biomes;
    }
}
