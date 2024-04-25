package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.feature.OreFeature;
import net.modificationstation.stationapi.api.event.world.gen.WorldGenEvent;
import net.newfrontiercraft.nfc.structures.OreCloud;
import net.newfrontiercraft.nfc.structures.OreConcentrated;

public class ChunkListener {
    @EventListener
    public void populate(WorldGenEvent.ChunkDecoration event) {
        if (event.world.dimension instanceof OverworldDimension) populateOverworld(event);
    }

    public void populateOverworld (WorldGenEvent.ChunkDecoration event) {
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

        if (event.random.nextInt(64) == 1) {
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
            (new OreConcentrated(BlockListener.silverOre.id, 12, 6, 8)).generate( event.world, event.random, k4, l7, i11);
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
            (new OreFeature(BlockListener.rubyOre.id, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.emeraldOre.id, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(7) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(16);
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(BlockListener.sapphireOre.id, 1)).generate( event.world, event.random, k4, l7, i11);
        }

        if (event.random.nextInt(16) == 1) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(24) + 4;
            int i11 = event.z + event.random.nextInt(16);
            (new OreConcentrated(BlockListener.anthraciteOre.id, 9, 3, 10)).generate(event.world, event.random, k4, l7, i11);
        }

        {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(82);
            int i11 = event.z + event.random.nextInt(16);
            (new OreConcentrated(Block.COAL_ORE.id, 5, 2, 16)).generate(event.world, event.random, k4, l7, i11);
        }

        for (int k2 = 0; k2 < (1 + event.random.nextInt(2)); k2++) {
            int k4 = event.x + event.random.nextInt(16);
            int l7 = event.random.nextInt(48) + 48;
            int i11 = event.z + event.random.nextInt(16);
            (new OreFeature(Block.COAL_ORE.id, 12)).generate(event.world, event.random, k4, l7, i11);
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
    }
}
