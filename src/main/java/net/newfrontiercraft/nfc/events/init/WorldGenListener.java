package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.PlantPatchFeature;
import net.modificationstation.stationapi.api.StationAPI;
import net.modificationstation.stationapi.api.event.world.biome.BiomeRegisterEvent;
import net.modificationstation.stationapi.api.event.worldgen.biome.BiomeProviderRegisterEvent;
import net.modificationstation.stationapi.api.worldgen.BiomeAPI;
import net.modificationstation.stationapi.api.worldgen.biome.BiomeBuilder;
import net.modificationstation.stationapi.api.worldgen.biome.VoronoiBiomeProvider;
import net.modificationstation.stationapi.api.worldgen.feature.LeveledScatterFeature;
import net.modificationstation.stationapi.api.worldgen.surface.SurfaceBuilder;
import net.modificationstation.stationapi.api.worldgen.surface.SurfaceRule;
import net.newfrontiercraft.nfc.world.gen.feature.*;

public class WorldGenListener {
    private Biome[] netherBiomes;

    @EventListener
    public void registerBiomes(BiomeRegisterEvent event) {
        netherBiomes = new Biome[4];

        BiomeBuilder biomeBuilder;

        // Scorched deposits
        SurfaceRule scorchedSurface = SurfaceBuilder.start(BlockListener.scorchedSand).ground(2).replace(Block.NETHERRACK).build();
        biomeBuilder = BiomeBuilder.start("Scorched Deposits");
        netherBiomes[0] = biomeBuilder.height(0, 127).snow(false).feature(new RareFeature(new LeveledScatterFeature(new PlantPatchFeature(BlockListener.fieryMushroom.id), 1), 4)).feature(new LeveledScatterFeature(new FieryMushroomFeature(), 1)).surfaceRule(scorchedSurface).build();

        // Glowing Mushroom Forest
        biomeBuilder = BiomeBuilder.start("Glowing Mushroom Forest");
        netherBiomes[1] = biomeBuilder.height(0, 127).snow(false).feature(new RareFeature(new LeveledScatterFeature(new PlantPatchFeature(BlockListener.glowingMushroom.id), 1), 4)).feature(new LeveledScatterFeature(new GlowingMushroomFeature(), 1)).build();

        // Purple Mushroom Forest
        SurfaceRule purpleMushroomSurface = SurfaceBuilder.start(Block.STONE).ground(1).replace(Block.NETHERRACK).build(); // TODO: Replace with proper surface block or remove
        biomeBuilder = BiomeBuilder.start("Purple Mushroom Forest");
        netherBiomes[2] = biomeBuilder.height(0, 127).snow(false).feature(new RareFeature(new LeveledScatterFeature(new MetaCapablePlantPatchFeature(BlockListener.bioluminescentMushroom.id, 1), 1), 4)).surfaceRule(purpleMushroomSurface).build();

        // Dead Forest
        SurfaceRule deadForestSurface = SurfaceBuilder.start(Block.SOUL_SAND).ground(4).replace(Block.NETHERRACK).build(); // TODO: Replace with proper surface block or remove
        biomeBuilder = BiomeBuilder.start("Dead Forest");
        netherBiomes[3] = biomeBuilder.height(0, 127).snow(false).feature(new LeveledScatterFeature(new DeadTreeFeature(), 1)).surfaceRule(deadForestSurface).build();
    }

    @EventListener
    public void registerRegions(BiomeProviderRegisterEvent event) {
        VoronoiBiomeProvider firstProvider = new VoronoiBiomeProvider();
        firstProvider.addBiome(netherBiomes[0]);
        firstProvider.addBiome(netherBiomes[1]);

        VoronoiBiomeProvider secondProvider = new VoronoiBiomeProvider();
        secondProvider.addBiome(netherBiomes[2]);
        secondProvider.addBiome(netherBiomes[3]);

        BiomeAPI.addNetherBiomeProvider(StationAPI.NAMESPACE.id("first_nether_provider"), firstProvider);
        BiomeAPI.addNetherBiomeProvider(StationAPI.NAMESPACE.id("second_nether_provider"), secondProvider);
    }
}
