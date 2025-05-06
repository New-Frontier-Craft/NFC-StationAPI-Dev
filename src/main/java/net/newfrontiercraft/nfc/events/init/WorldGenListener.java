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
import net.modificationstation.stationapi.api.worldgen.feature.HeightScatterFeature;
import net.modificationstation.stationapi.api.worldgen.feature.LeveledScatterFeature;
import net.modificationstation.stationapi.api.worldgen.surface.SurfaceBuilder;
import net.modificationstation.stationapi.api.worldgen.surface.SurfaceRule;
import net.newfrontiercraft.nfc.world.gen.feature.DeadTreeFeature;
import net.newfrontiercraft.nfc.world.gen.feature.MetaCapablePlantPatchFeature;

public class WorldGenListener {
    private Biome[] netherBiomes;

    @EventListener
    public void registerBiomes(BiomeRegisterEvent event) {
        netherBiomes = new Biome[4];

        BiomeBuilder biomeBuilder;

        // Scorched deposits
        SurfaceRule ashSurface = SurfaceBuilder.start(BlockListener.scorchedSand).replace(Block.NETHERRACK).build();
        biomeBuilder = BiomeBuilder.start("Scorched Deposits");
        netherBiomes[0] = biomeBuilder.height(0, 127).snow(false).surfaceRule(ashSurface).feature(new LeveledScatterFeature(new PlantPatchFeature(BlockListener.fieryMushroom.id), 1)).build();

        // Glowing Mushroom Forest
        SurfaceRule glowingMushroomSurface = SurfaceBuilder.start(Block.GOLD_BLOCK).replace(Block.NETHERRACK).build(); // TODO: Replace with proper surface block or remove
        biomeBuilder = BiomeBuilder.start("Glowing Mushroom Forest");
        netherBiomes[1] = biomeBuilder.height(0, 127).snow(false).surfaceRule(glowingMushroomSurface).feature(new LeveledScatterFeature(new PlantPatchFeature(BlockListener.glowingMushroom.id), 1)).build();

        // Purple Mushroom Forest
        SurfaceRule purpleMushroomSurface = SurfaceBuilder.start(Block.STONE).replace(Block.NETHERRACK).build(); // TODO: Replace with proper surface block or remove
        biomeBuilder = BiomeBuilder.start("Purple Mushroom Forest");
        netherBiomes[2] = biomeBuilder.height(0, 127).snow(false).surfaceRule(purpleMushroomSurface).feature(new LeveledScatterFeature(new MetaCapablePlantPatchFeature(BlockListener.bioluminescentMushroom.id, 1), 2)).build();

        // Dead Forest
        SurfaceRule deadForestSurface = SurfaceBuilder.start(Block.SOUL_SAND).replace(Block.NETHERRACK).build(); // TODO: Replace with proper surface block or remove
        biomeBuilder = BiomeBuilder.start("Dead Forest");
        netherBiomes[3] = biomeBuilder.height(0, 127).snow(false).feature(new LeveledScatterFeature(new DeadTreeFeature(), 1)).surfaceRule(deadForestSurface).build();
    }

    @EventListener
    public void registerRegions(BiomeProviderRegisterEvent event) {
        // Simple climate provider with biomes by temperature
        VoronoiBiomeProvider provider = new VoronoiBiomeProvider();
        provider.addBiome(netherBiomes[0]);
        provider.addBiome(netherBiomes[1]);
        provider.addBiome(netherBiomes[2]);
        provider.addBiome(netherBiomes[3]);

        BiomeAPI.addNetherBiomeProvider(StationAPI.NAMESPACE.id("nfc_nether_provider"), provider);
    }
}
