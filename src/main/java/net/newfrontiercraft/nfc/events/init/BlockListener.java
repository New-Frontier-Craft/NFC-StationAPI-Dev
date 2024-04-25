package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.blocks.*;

public class BlockListener {

    public static LazyBlockTemplate
            platedStone,
            platedStoneVerticalCut,
            platedStoneHorizontalCut,
            platedStoneCrossCut,
            workedStone,
            workedStoneVerticalCut,
            workedStoneHorizontalCut,
            workedStoneCrossCut,
            stoneBricks,
            stoneBricksLarge,
            stoneCheckers,
            stoneTiling,
            stoneTilingLarge,
            firedBricks,
            osmiumBricks,

            coalBlock,
            onyxBlock,
            cobaltBlock,
            aluminiumBlock,
            copperBlock,
            tinBlock,
            zincBlock,
            nickelBlock,
            bismuthBlock,
            osmiumBlock,
            tungstenBlock,
            magnetiteBlock,
            silverBlock,
            leadBlock,
            siliconBlock,
            chromeBlock,
            titaniumBlock,
            uraniumBlock,
            rubyBlock,
            sapphireBlock,
            emeraldBlock,
            boronBlock,
            platinumBlock,
            bronzeBlock,
            brassBlock,
            steelBlock;

    public static LazyOreTemplate
            netherAshOre,
            netherUraniniteOre,
            netherGoldOre,
            netherOnyxOre,
            cobaltOre,
            aluminiumOre,
            copperOre,
            tinOre,
            zincOre,
            nickelOre,
            bismuthOre,
            osmiumOre,
            tungstenOre,
            magnetiteOre,
            silverOre,
            leadOre,
            siliconOre,
            chromeOre,
            anthraciteOre,
            titaniumOre,
            uraniniteOre,
            rubyOre,
            sapphireOre,
            emeraldOre,
            boronOre,
            platinumOre,
            mysteryOre;

    public static BrickOven
            brickOven,
            brickOvenActive;

    public static LazySandTemplate
            pebble,
            pebbleSmall,
            pebbleMedium,
            pebbleLarge;

    public static CarpentryWorkstation carpentryWorkstation;

    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        platedStone = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        platedStoneVerticalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone_vertical_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        platedStoneHorizontalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone_horizontal_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        platedStoneCrossCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone_cross_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        workedStone = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        workedStoneVerticalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone_vertical_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        workedStoneHorizontalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone_horizontal_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        workedStoneCrossCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone_cross_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        stoneBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        stoneBricksLarge = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_bricks_large"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        stoneCheckers = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_checkers"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        stoneTiling = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_tiling"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        stoneTilingLarge = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_tiling_large"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        firedBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "fired_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        osmiumBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "osmium_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        netherAshOre = new LazyOreTemplate(Identifier.of(MOD_ID, "nether_ash_ore"), 3F);
        netherUraniniteOre = new LazyOreTemplate(Identifier.of(MOD_ID, "nether_uraninite_ore"), 3F);
        netherGoldOre = new LazyOreTemplate(Identifier.of(MOD_ID, "nether_gold_ore"), 3F);
        netherOnyxOre = new LazyOreTemplate(Identifier.of(MOD_ID, "nether_onyx_ore"), 4F);
        cobaltOre = new LazyOreTemplate(Identifier.of(MOD_ID, "cobalt_ore"), 4F);
        aluminiumOre = new LazyOreTemplate(Identifier.of(MOD_ID, "aluminium_ore"), 3F);
        copperOre = new LazyOreTemplate(Identifier.of(MOD_ID, "copper_ore"), 3F);
        tinOre = new LazyOreTemplate(Identifier.of(MOD_ID, "tin_ore"), 3F);
        zincOre = new LazyOreTemplate(Identifier.of(MOD_ID, "zinc_ore"), 3F);
        nickelOre = new LazyOreTemplate(Identifier.of(MOD_ID, "nickel_ore"), 3.5F);
        bismuthOre = new LazyOreTemplate(Identifier.of(MOD_ID, "bismuth_ore"), 3F);
        osmiumOre = new LazyOreTemplate(Identifier.of(MOD_ID, "osmium_ore"), 10F);
        tungstenOre = new LazyOreTemplate(Identifier.of(MOD_ID, "tungsten_ore"), 6F);
        magnetiteOre = new LazyOreTemplate(Identifier.of(MOD_ID, "magnetite_ore"), 4F);
        silverOre = new LazyOreTemplate(Identifier.of(MOD_ID, "silver_ore"), 3.5F);
        leadOre = new LazyOreTemplate(Identifier.of(MOD_ID, "lead_ore"), 3F);
        siliconOre = new LazyOreTemplate(Identifier.of(MOD_ID, "silicon_ore"), 4F);
        chromeOre = new LazyOreTemplate(Identifier.of(MOD_ID, "chrome_ore"), 4F);
        anthraciteOre = new LazyOreTemplate(Identifier.of(MOD_ID, "anthracite_ore"), 4F);
        titaniumOre = new LazyOreTemplate(Identifier.of(MOD_ID, "titanium_ore"), 6F);
        uraniniteOre = new LazyOreTemplate(Identifier.of(MOD_ID, "uraninite_ore"), 8F);
        rubyOre = new LazyOreTemplate(Identifier.of(MOD_ID, "ruby_ore"), 8F);
        sapphireOre = new LazyOreTemplate(Identifier.of(MOD_ID, "sapphire_ore"), 8F);
        emeraldOre = new LazyOreTemplate(Identifier.of(MOD_ID, "emerald_ore"), 8F);
        boronOre = new LazyOreTemplate(Identifier.of(MOD_ID, "boron_ore"), 3.5F);
        platinumOre = new LazyOreTemplate(Identifier.of(MOD_ID, "platinum_ore"), 3.5F);
        mysteryOre = new LazyOreTemplate(Identifier.of(MOD_ID, "mystery_ore"), 2F);

        coalBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "coal_block"), Material.STONE, 3F, Block.STONE_SOUND_GROUP);
        onyxBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "onyx_block"), Material.METAL, 4F, Block.METAL_SOUND_GROUP);
        cobaltBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "cobalt_block"), Material.METAL, 3F, Block.METAL_SOUND_GROUP);
        aluminiumBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "aluminium_block"), Material.METAL, 2F, Block.METAL_SOUND_GROUP);
        copperBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "copper_block"), Material.METAL, 2F, Block.METAL_SOUND_GROUP);
        tinBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "tin_block"), Material.METAL, 2F, Block.METAL_SOUND_GROUP);
        zincBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "zinc_block"), Material.METAL, 2F, Block.METAL_SOUND_GROUP);
        nickelBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "nickel_block"), Material.METAL, 2.5F, Block.METAL_SOUND_GROUP);
        bismuthBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "bismuth_block"), Material.METAL, 2F, Block.METAL_SOUND_GROUP);
        osmiumBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "osmium_block"), Material.METAL, 10F, Block.METAL_SOUND_GROUP);
        tungstenBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "tungsten_block"), Material.METAL, 3.5F, Block.METAL_SOUND_GROUP);
        magnetiteBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "magnetite_block"), Material.METAL, 3F, Block.METAL_SOUND_GROUP);
        silverBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "silver_block"), Material.METAL, 2.5F, Block.METAL_SOUND_GROUP);
        leadBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "lead_block"), Material.METAL, 2F, Block.METAL_SOUND_GROUP);
        siliconBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "silicon_block"), Material.METAL, 3F, Block.METAL_SOUND_GROUP);
        chromeBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "chrome_block"), Material.METAL, 3F, Block.METAL_SOUND_GROUP);
        titaniumBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "titanium_block"), Material.METAL, 3.5F, Block.METAL_SOUND_GROUP);
        uraniumBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "uranium_block"), Material.METAL, 3.5F, Block.METAL_SOUND_GROUP);
        rubyBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "ruby_block"), Material.STONE, 4F, Block.STONE_SOUND_GROUP);
        sapphireBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "sapphire_block"), Material.STONE, 4F, Block.STONE_SOUND_GROUP);
        emeraldBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "emerald_block"), Material.STONE, 4F, Block.STONE_SOUND_GROUP);
        boronBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "boron_block"), Material.METAL, 2.5F, Block.METAL_SOUND_GROUP);
        platinumBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "platinum_block"), Material.METAL, 2.5F, Block.METAL_SOUND_GROUP);
        bronzeBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "bronze_block"), Material.METAL, 2.5F, Block.METAL_SOUND_GROUP);
        brassBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "brass_block"), Material.METAL, 2.5F, Block.METAL_SOUND_GROUP);
        steelBlock = new LazyBlockTemplate(Identifier.of(MOD_ID, "steel_block"), Material.METAL, 3.5F, Block.METAL_SOUND_GROUP);

        pebble = new LazySandTemplate(Identifier.of(MOD_ID, "pebble"), 3F, Block.GRAVEL_SOUND_GROUP, 8);
        pebbleSmall = new LazySandTemplate(Identifier.of(MOD_ID, "pebble_small"), 3F, Block.GRAVEL_SOUND_GROUP, 8);
        pebbleMedium = new LazySandTemplate(Identifier.of(MOD_ID, "pebble_medium"), 3F, Block.GRAVEL_SOUND_GROUP, 8);
        pebbleLarge = new LazySandTemplate(Identifier.of(MOD_ID, "pebble_large"), 3F, Block.GRAVEL_SOUND_GROUP, 8);

        brickOven = new BrickOven(Identifier.of(MOD_ID, "brick_oven"), Material.STONE, false, 0F, 1.5F);
        brickOvenActive = new BrickOven(Identifier.of(MOD_ID, "brick_oven_active"), Material.STONE, true, 0.875F, 1.5F);

        carpentryWorkstation = new CarpentryWorkstation(Identifier.of(MOD_ID, "carpentry_workstation"), Material.METAL, 1.0F);
    }
}
