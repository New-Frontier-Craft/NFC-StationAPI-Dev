package net.newfrontiercraft.nfc.events.init;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.NetherDimension;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.feature.LakeFeature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.PlantPatchFeature;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.newfrontiercraft.nfc.utils.RandomUtil;
import net.newfrontiercraft.nfc.world.gen.feature.*;

import java.util.Random;

public class ChunkListener {
    public static boolean isBnbPresent = FabricLoader.getInstance().isModLoaded("bnb");
    public static boolean isSnoPresent = FabricLoader.getInstance().isModLoaded("simplenetherores");

    @EventListener
    public void populate(WorldGenEvent.ChunkDecoration event) {
        if (event.world.dimension instanceof OverworldDimension) populateOverworld(event);
        if (event.world.dimension instanceof NetherDimension) populateNether(event);
    }

    public void populateOverworld(WorldGenEvent.ChunkDecoration event) {
        Random random = event.random;
        World world = event.world;
        Biome biome = event.biome;
        RandomUtil randomUtil = new RandomUtil(random);
        
        if (random.nextInt(32) == 0) {
            int j14 = event.x + random.nextInt(16) + 8;
            int j16 = random.nextInt(56);
            int j19 = event.z + random.nextInt(16) + 8;
            (new PlantPatchFeature(BlockListener.bioluminescentMushroom.id)).generate(world, random, j14, j16, j19);
        }
        if (biome == Biome.SEASONAL_FOREST) {
            if (random.nextInt(48) == 0) {
                int j14 = event.x + random.nextInt(16) + 8;
                int j16 = random.nextInt(64) + 64;
                int j19 = event.z + random.nextInt(16) + 8;
                (new PlantPatchFeature(BlockListener.bioluminescentMushroom.id)).generate(world, random, j14, j16, j19);
            }
        }
        if (biome == Biome.RAINFOREST) {
            for (int i = 0; i < 2; i++) {
                int localX = event.x + random.nextInt(16) + 8;
                int localY = random.nextInt(64) + 64;
                int localZ = event.z + random.nextInt(16) + 8;
                int mushroomX = localX;
                int mushroomY = localY;
                int mushroomZ = localZ;
                for (int j = 0; j < random.nextInt(16); j++) {
                    mushroomX += randomUtil.bidirectionalRandom(12);
                    mushroomY += randomUtil.bidirectionalRandom(4);
                    mushroomZ += randomUtil.bidirectionalRandom(12);
                    if (new BlueMushroomFeature().generate(world, random, mushroomX, mushroomY, mushroomZ)) {
                        (new VariablePlantPatchFeature(BlockListener.bioluminescentMushroom.id, 16)).generate(world, random, mushroomX, mushroomY, mushroomZ);
                    }
                }
            }
        }
        if (biome == Biome.TAIGA || biome == Biome.TUNDRA) {
            if (random.nextInt(32) == 0) {
                int j14 = event.x + random.nextInt(16) + 8;
                int j16 = random.nextInt(16) + 45;
                int j19 = event.z + random.nextInt(16) + 8;
                (new PlantPatchFeature(BlockListener.bioluminescentMushroom.id)).generate(world, random, j14, j16, j19);
            }
        }
        
        if (event.biome == Biome.SAVANNA || event.biome == Biome.SHRUBLAND) {
            double humidity = event.world.method_1781().downfallMap[0];
            humidity *= 100;
            humidity = Math.floor(humidity);
            for (int k = 0; k < humidity; k++) {
                int x = event.x + event.random.nextInt(16);
                int y = event.random.nextInt(32) + 64;
                int z = event.z + event.random.nextInt(16);
                (new ShrubFeature()).generate(event.world, event.random, x, y, z);
            }
        }

        if (random.nextInt(16) == 1) {
            int i1 = event.x + random.nextInt(16) + 8;
            int l4 = random.nextInt(random.nextInt(random.nextInt(32) + 1) + 1) + random.nextInt(32) + 4;
            int i8 = event.z + random.nextInt(16) + 8;
            (new LakeFeature(BlockListener.oilStill.id)).generate(world, random, i1, l4, i8);
        }

        if (event.random.nextInt(32) == 0) {
            int x = event.x + event.random.nextInt(16) + 8;
            int y = event.random.nextInt(64) + 24;
            int z = event.z + event.random.nextInt(16) + 8;
            (new StalactiteCaveFeature()).generate(event.world, event.random, x, y, z);
        }

        if (event.random.nextInt(64) == 0) {
            int x = event.x + event.random.nextInt(16) + 8;
            int y = event.random.nextInt(32) + 40;
            int z = event.z + event.random.nextInt(16) + 8;
            //(new RavineFeature()).generate(event.world, event.random, x, y, z);
        }

        {
            int i6 = event.x + event.random.nextInt(16);
            int j9 = event.random.nextInt(20) + 90;
            int i12 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.mysteryOre.id, 1)).generate( event.world, event.random, i6, j9, i12);
        }

        if (event.random.nextInt(50) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(12);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.osmiumOre.id, 9)).generate( event.world, event.random, k4, l7, i11);
        }

        {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(50);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.zincOre.id, 10)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(32) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(60);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(Block.GOLD_ORE.id, 10, 0, 32)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(5) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(40);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.boronOre.id, 4, 0, 10)).generate( event.world, event.random, k4, l7, i11);
        }
        
        if (event.random.nextInt(16) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(50);
            int i11 = event.z + event.random.nextInt(16);
            (new ConcentratedOreVein(BlockListener.silverOre.id, 12, 6, 8)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(20) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(40);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.platinumOre.id, 18)).generate( event.world, event.random, k4, l7, i11);
        }
        
        if (event.random.nextInt(42) <= 2) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(24) + event.random.nextInt(8) + 2;
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(Block.IRON_ORE.id, 16, 2, 200)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(16) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(32);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.cobaltOre.id, 16)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(5) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(18) + 2;
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.chromeOre.id, 10)).generate( event.world, event.random, k4, l7, i11);
        }
        
        if (event.random.nextInt(25) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.titaniumOre.id, 8, 3, 25)).generate( event.world, event.random, k4, l7, i11);
        }

        {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(6) + 3;
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.tungstenOre.id, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        for (int k2 = 0; k2 < 2; k2++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(128);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.pebble.id, 10, 2, 80)).generate(event.world, event.random, k4, l7, i11);
        }

        {
            int i6 = event.x + event.random.nextInt(16);
            int j9 = event.random.nextInt(128);
            int i12 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.pebble.id, 32)).generate(event.world, event.random, i6, j9, i12);
        }

        /*
        {
            int i3 = event.x + event.random.nextInt(16);
            int j6 = event.random.nextInt(3) + 2;
            int k9 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.FakeBedrock.id, 1)).generate( event.world, event.random, i3, j6, k9);
        }
         */

        if (event.random.nextInt(32) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(32);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.uraniniteOre.id, 16)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(10) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(32);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.magnetiteOre.id, 5)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new ScatteredOrePieces(BlockListener.rubyOre.id, 0, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new ScatteredOrePieces(BlockListener.emeraldOre.id, 0, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new ScatteredOrePieces(BlockListener.sapphireOre.id, 0, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(42) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new ScatteredOrePieces(BlockListener.rubyOre.id, 10, 4)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(42) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new ScatteredOrePieces(BlockListener.emeraldOre.id, 10, 4)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(42) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new ScatteredOrePieces(BlockListener.sapphireOre.id, 10, 4)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(16) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(24) + 4;
            int i11 = event.z + event.random.nextInt(16);
            (new ConcentratedOreVein(BlockListener.anthraciteOre.id, 9, 3, 10)).generate(event.world, event.random, k4, l7, i11);
        }

        {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(82);
            int i11 = event.z + event.random.nextInt(16);
            (new ConcentratedOreVein(Block.COAL_ORE.id, 5, 2, 16)).generate(event.world, event.random, k4, l7, i11);
        }

        for (int k2 = 0; k2 < (1 + event.random.nextInt(2)); k2++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(48) + 48;
            int i11 = event.z + event.random.nextInt(16);
            (new UnrestrictedOreFeature(Block.COAL_ORE.id, 12)).generate(event.world, event.random, k4, l7, i11);
        }

        {
            for (int k2 = 0; k2 < (3 + event.random.nextInt(4)); k2++) {
                int k4 = event.x + event.random.nextInt(16);
                int l7 = event.random.nextInt(44) + 38;
                int i11 = event.z + event.random.nextInt(16);
                (new OreCloud(BlockListener.copperOre.id, 4, 1, 16)).generate(event.world, event.random, k4, l7, i11);
            }
        }

        if (event.random.nextInt(3) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(70);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.copperOre.id, 5, 1, 40)).generate(event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(50);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.aluminiumOre.id, 8, 2, 90)).generate(event.world, event.random, k4, l7, i11);
        }

        for (int k2 = 0; k2 < (3 + event.random.nextInt(5)); k2++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(52) + 30;
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.tinOre.id, 7)).generate(event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(24) + event.random.nextInt(24);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.nickelOre.id, 6, 4, 24)).generate(event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(24);
            int i11 = event.z + event.random.nextInt(16);
            (new OreCloud(BlockListener.siliconOre.id, 5, 4, 15)).generate(event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(5) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(64);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.bismuthOre.id, 15)).generate(event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(4) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(48);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.leadOre.id, 12)).generate(event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(25) == 1) {
            int i4 = event.x + event.random.nextInt(16);
            int j7 = event.random.nextInt(16);
            int k10 = event.z + event.random.nextInt(16);
            (new UnrestrictedOreFeature(Block.DIAMOND_ORE.id, 8)).generate(event.world, event.random, i4, j7, k10);
        }
    }

    public void populateNether(WorldGenEvent.ChunkDecoration event) {
        Random random = event.random;
        World world = event.world;

        int heightOffset = 0;
        int heightRangeExtension = 0;
        int frequencyMultiplier = 1;
        if (isBnbPresent) {
            heightOffset = 96;
            heightRangeExtension = 32;
            frequencyMultiplier = 8;
        }

        if (random.nextInt(4) < frequencyMultiplier) {
            int l2 = event.x + random.nextInt(16) + 8;
            int j4 = random.nextInt(128 + heightRangeExtension) + heightOffset;
            int l5 = event.z + random.nextInt(16) + 8;
            (new CrystalClusterFeature(BlockListener.blueGlowstone.id, Block.NETHERRACK.id)).generate(world, random, l2, j4, l5);
        }

        for (int i = 0; i < frequencyMultiplier; i++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(120 + heightRangeExtension) + 4 + heightOffset;
            int i11 = event.z + event.random.nextInt(16);
            (new ConcentratedOreVein(BlockListener.netherAshOre.id, 10, 2, 9, Block.NETHERRACK.id)).generate(event.world, event.random, k4, l7, i11);
        }

        for (int i = 0; i < frequencyMultiplier; i++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(120 + heightRangeExtension) + 4 + heightOffset;
            int i11 = event.z + event.random.nextInt(16);
            (new UnrestrictedOreFeature(BlockListener.netherOnyxOre.id, 3, Block.NETHERRACK.id)).generate(event.world, event.random, k4, l7, i11);
        }

        if (!isSnoPresent) {
            if (event.random.nextInt(24) < frequencyMultiplier) {
                int k4 = event.x + event.random.nextInt(16);
                int l7 = event.random.nextInt(120 + heightRangeExtension) + 4 + heightOffset;
                int i11 = event.z + event.random.nextInt(16);
                (new OreCloud(BlockListener.netherGoldOre.id, 10, 0, 48, Block.NETHERRACK.id)).generate(event.world, event.random, k4, l7, i11);
            }
        }

        for (int j2 = 0; j2 < event.random.nextInt(3 * frequencyMultiplier) + 1; j2++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(120 + heightRangeExtension) + 4 + heightOffset;
            int i11 = event.z + event.random.nextInt(16);
            (new UnrestrictedOreFeature(BlockListener.netherUraniniteOre.id, 16, Block.NETHERRACK.id)).generate(event.world, event.random, k4, l7, i11);
        }
    }
}
