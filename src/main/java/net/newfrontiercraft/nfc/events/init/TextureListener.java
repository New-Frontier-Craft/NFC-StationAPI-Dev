package net.newfrontiercraft.nfc.events.init;

import net.fabricmc.loader.api.FabricLoader;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

public class TextureListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    public static int oakSaplingTexture;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        // Block strings
        String convenienceBlocks = "block/convenience_blocks/";
        String decorativeBlocks = "block/decorative_blocks/";
        String netherBlocks = decorativeBlocks + "nether/";
        String wood = decorativeBlocks + "wood/";
        String planks = wood + "planks/";
        String laminated = wood + "laminated/";
        String stainedPlanks = planks + "stained/";
        String glass = decorativeBlocks + "glass/";
        String walls = decorativeBlocks + "walls/";
        String mudBlocks = decorativeBlocks + "mud/";
        String firedMudBlocks = mudBlocks + "fired/";
        String oreBlocks = "block/ores/";
        String oreStorage = "block/ore_storage/";
        String machines = "block/machines/";
        String woldGeneration = "block/world_generation/";
        String vanillaBlocks = "block/vanilla/";
        String scorchedSandstone = decorativeBlocks + "scorched_sandstone/";
        String mushrooms = woldGeneration + "mushrooms/";
        String creative = "block/creative/";
        String farming = "block/farming/";
        String planter = farming + "planter/";
        String unfiredPlanter = planter + "unfired/";
        String bigMushrooms = mushrooms + "big/";

        // Item strings
        String items = "item/";
        String ingotItems = items + "ingots/";
        String pickaxes = items + "tools/pickaxes/";
        String swords = items + "tools/swords/";
        String shovels = items + "tools/shovels/";
        String axes = items + "tools/axes/";
        String hoes = items + "tools/hoes/";
        String helmets = items + "armor/helmets/";
        String chestplates = items + "armor/chestplates/";
        String leggings = items + "armor/leggings/";
        String boots = items + "armor/boots/";
        String oreDrops = items + "ore_drops/";
        String foodItems = items + "food/";
        String otherDrops = items + "other_drops/";
        String parts = items + "parts/";

        // Convenience blocks
        int scaffoldTopTexture = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, convenienceBlocks + "scaffold_block_top")).index;
        BlockListener.scaffoldBlock.specifyTextures(scaffoldTopTexture, Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, convenienceBlocks + "scaffold_block_side")).index, scaffoldTopTexture);

        // Decorative blocks
        BlockListener.platedStone.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "plated_stone")).index);
        BlockListener.platedStoneVerticalCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "plated_stone_vertical_cut")).index);
        BlockListener.platedStoneHorizontalCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "plated_stone_horizontal_cut")).index);
        BlockListener.platedStoneCrossCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "plated_stone_cross_cut")).index);

        BlockListener.workedStone.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "worked_stone")).index);
        BlockListener.workedStoneVerticalCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "worked_stone_vertical_cut")).index);
        BlockListener.workedStoneHorizontalCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "worked_stone_horizontal_cut")).index);
        BlockListener.workedStoneCrossCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "worked_stone_cross_cut")).index);
        BlockListener.stoneBricks.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "stone_bricks")).index);
        BlockListener.stoneBricksLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "stone_bricks_large")).index);
        BlockListener.stoneCheckers.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "stone_checkers")).index);
        BlockListener.stoneTiling.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "stone_tiling")).index);
        BlockListener.stoneTilingLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "stone_tiling_large")).index);

        BlockListener.netherWorkedStone.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "worked_stone")).index);
        BlockListener.netherWorkedStoneVerticalCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "worked_stone_vertical_cut")).index);
        BlockListener.netherWorkedStoneHorizontalCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "worked_stone_horizontal_cut")).index);
        BlockListener.netherWorkedStoneCrossCut.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "worked_stone_cross_cut")).index);
        BlockListener.netherStoneBricks.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "stone_bricks")).index);
        BlockListener.netherStoneBricksLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "stone_bricks_large")).index);
        BlockListener.netherStoneCheckers.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "stone_checkers")).index);
        BlockListener.netherStoneTiling.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "stone_tiling")).index);
        BlockListener.netherStoneTilingLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, netherBlocks + "stone_tiling_large")).index);

        BlockListener.firedBricks.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "fired_bricks")).index);
        BlockListener.osmiumBricks.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "osmium_bricks")).index);
        logSide = getTextureIndex(wood + "log_side");
        BlockListener.decorativeWood.specifyTextures(
                new int[] {
                        getTextureIndex(wood + "rounded_log"),
                        getTextureIndex(wood + "small_logs"),
                        getTextureIndex(planks + "parquet"),
                        getTextureIndex(planks + "smooth"),
                        getTextureIndex(planks + "vertical"),
                        getTextureIndex(planks + "crossed"),
                        getTextureIndex(laminated + "bordered"),
                        getTextureIndex(laminated + "borderless"),
                        getTextureIndex(laminated + "horizontal"),
                        getTextureIndex(laminated + "vertical")
                }
        );
        BlockListener.stainedPlanks.specifyTextures(
                new int[] {
                        getTextureIndex(stainedPlanks + "white"),
                        getTextureIndex(stainedPlanks + "orange"),
                        getTextureIndex(stainedPlanks + "magenta"),
                        getTextureIndex(stainedPlanks + "light_blue"),
                        getTextureIndex(stainedPlanks + "yellow"),
                        getTextureIndex(stainedPlanks + "lime"),
                        getTextureIndex(stainedPlanks + "pink"),
                        getTextureIndex(stainedPlanks + "grey"),
                        getTextureIndex(stainedPlanks + "light_grey"),
                        getTextureIndex(stainedPlanks + "cyan"),
                        getTextureIndex(stainedPlanks + "purple"),
                        getTextureIndex(stainedPlanks + "blue"),
                        getTextureIndex(stainedPlanks + "brown"),
                        getTextureIndex(stainedPlanks + "green"),
                        getTextureIndex(stainedPlanks + "red"),
                        getTextureIndex(stainedPlanks + "black")
                }
        );
        BlockListener.window.specifyTextures(
                new int[] {
                        getTextureIndex(glass + "window"),
                        getTextureIndex(glass + "vertical_double_window"),
                        getTextureIndex(glass + "small_window"),
                        getTextureIndex(glass + "horizontal_double_window"),
                        getTextureIndex(glass + "mini_window"),
                        getTextureIndex(glass + "quadruple_window"),
                        getTextureIndex(glass + "gothic_window")
                }
        );
        BlockListener.stainedGlass.specifyTextures(
                new int[] {
                        getTextureIndex(glass + "white_stained_glass"),
                        getTextureIndex(glass + "orange_stained_glass"),
                        getTextureIndex(glass + "magenta_stained_glass"),
                        getTextureIndex(glass + "light_blue_stained_glass"),
                        getTextureIndex(glass + "yellow_stained_glass"),
                        getTextureIndex(glass + "lime_stained_glass"),
                        getTextureIndex(glass + "pink_stained_glass"),
                        getTextureIndex(glass + "gray_stained_glass"),
                        getTextureIndex(glass + "light_gray_stained_glass"),
                        getTextureIndex(glass + "cyan_stained_glass"),
                        getTextureIndex(glass + "purple_stained_glass"),
                        getTextureIndex(glass + "blue_stained_glass"),
                        getTextureIndex(glass + "brown_stained_glass"),
                        getTextureIndex(glass + "green_stained_glass"),
                        getTextureIndex(glass + "red_stained_glass"),
                        getTextureIndex(glass + "black_stained_glass"),
                }
        );
        BlockListener.tintedGlass.specifyTextures(getTextureIndex(glass + "tinted_glass"));
        BlockListener.snowBricks.specifyTextures(getTextureIndex(decorativeBlocks + "snow_bricks"));
        BlockListener.hardWall.specifyTextures(
                new int[] {
                        getTextureIndex(walls + "cobblestone"),
                        getTextureIndex(walls + "mossy_cobblestone"),
                        getTextureIndex(walls + "cobblestone"),
                        getTextureIndex(walls + "mossy_cobblestone")
                }
        );
        BlockListener.hardWall.asItem().setTextureId(getTextureIndex(walls + "wall_icon"));
        BlockListener.copperDoor.specifyTextures(getTextureIndex(convenienceBlocks + "copper_door_top"), getTextureIndex(convenienceBlocks + "copper_door_bottom"));
        BlockListener.blueGlowstone.specifyTextures(getTextureIndex(woldGeneration + "blue_glowstone"));
        BlockListener.alphaGrass.specifyTextures(
                getTextureIndex(decorativeBlocks + "alpha_grass_top"),
                getTextureIndex(decorativeBlocks + "alpha_grass_side"),
                getTextureIndex(decorativeBlocks + "alpha_grass_side_snow"),
                getTextureIndex(decorativeBlocks + "alpha_grass_bottom")
        );
        BlockListener.alphaLeaves.specifyTextures(
                getTextureIndex(decorativeBlocks + "alpha_leaves_fast"),
                getTextureIndex(decorativeBlocks + "alpha_leaves")
        );
        BlockListener.scorchedSand.specifyTextures(getTextureIndex(woldGeneration + "scorched_sand"));
        BlockListener.scorchedSandstone.specifyTextures(getTextureIndex(scorchedSandstone + "top"), getTextureIndex(scorchedSandstone + "side"), getTextureIndex(scorchedSandstone + "bottom"));

        // Mud Blocks
        BlockListener.mud.specifyTextures(
                new int[] {
                        getTextureIndex(mudBlocks + "plain"),
                        getTextureIndex(mudBlocks + "bricks"),
                        getTextureIndex(mudBlocks + "long_bricks"),
                        getTextureIndex(mudBlocks + "random_bricks"),
                        getTextureIndex(mudBlocks + "large_bricks")
                }
        );
        BlockListener.firedMud.specifyTextures(
                new int[] {
                        getTextureIndex(firedMudBlocks + "plain"),
                        getTextureIndex(firedMudBlocks + "bricks"),
                        getTextureIndex(firedMudBlocks + "long_bricks"),
                        getTextureIndex(firedMudBlocks + "random_bricks"),
                        getTextureIndex(firedMudBlocks + "large_bricks")
                }
        );

        // Ores
        BlockListener.cobaltOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "cobalt_ore")).index);
        BlockListener.aluminiumOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "aluminium_ore")).index);
        BlockListener.copperOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "copper_ore")).index);
        BlockListener.tinOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "tin_ore")).index);
        BlockListener.zincOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "zinc_ore")).index);
        BlockListener.nickelOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "nickel_ore")).index);
        BlockListener.bismuthOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "bismuth_ore")).index);
        BlockListener.osmiumOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "osmium_ore")).index);
        BlockListener.tungstenOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "tungsten_ore")).index);
        BlockListener.magnetiteOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "magnetite_ore")).index);
        BlockListener.silverOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "silver_ore")).index);
        BlockListener.leadOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "lead_ore")).index);
        BlockListener.siliconOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "silicon_ore")).index);
        BlockListener.chromeOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "chrome_ore")).index);
        BlockListener.anthraciteOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "anthracite_ore")).index);
        BlockListener.titaniumOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "titanium_ore")).index);
        BlockListener.uraniniteOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "uraninite_ore")).index);
        BlockListener.rubyOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "ruby_ore")).index);
        BlockListener.sapphireOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "sapphire_ore")).index);
        BlockListener.emeraldOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "emerald_ore")).index);
        BlockListener.boronOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "boron_ore")).index);
        BlockListener.platinumOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "platinum_ore")).index);
        BlockListener.mysteryOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "mystery_ore")).index);

        // Machines
        BlockListener.brickOven.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front_active")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_side")).index);
        BlockListener.brickOvenActive.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front_active")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_side")).index);
        BlockListener.carpentryWorkstation.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "carpentry_workstation_front")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "carpentry_workstation_side")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "carpentry_workstation_bottom")).index,
                Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "carpentry_workstation_top")).index);
        BlockListener.heatCoil.specifyTextures(
                getTextureIndex(machines + "heat_coil_0"),
                getTextureIndex(machines + "heat_coil_1"),
                getTextureIndex(machines + "heat_coil_2"),
                getTextureIndex(machines + "heat_coil_3")
        );
        BlockListener.combustionHeater.specifyTextures(getTextureIndex(machines + "combustion_heater_top"),
                getTextureIndex(machines + "combustion_heater_side"),
                getTextureIndex(machines + "combustion_heater_bottom"),
                getTextureIndex(machines + "combustion_heater_front"));
        BlockListener.combustionHeaterActive.specifyTextures(getTextureIndex(machines + "combustion_heater_top"),
                getTextureIndex(machines + "combustion_heater_side"),
                getTextureIndex(machines + "combustion_heater_bottom"),
                getTextureIndex(machines + "combustion_heater_front_active"));
        BlockListener.basicItemChute.specifyTextures(getTextureIndex(machines + "basic_item_chute_top"), getTextureIndex(machines + "basic_item_chute_side"), getTextureIndex(machines + "basic_item_chute_bottom"));
        BlockListener.itemChuteExtender.specifyTextures(getTextureIndex(machines + "item_chute_extender_top"), getTextureIndex(machines + "item_chute_extender_side"), getTextureIndex(machines + "item_chute_extender_bottom"));
        BlockListener.filteringItemChute.specifyTextures(getTextureIndex(machines + "filtering_item_chute_top"), getTextureIndex(machines + "filtering_item_chute_side"), getTextureIndex(machines + "filtering_item_chute_bottom"));
        BlockListener.preciseItemChute.specifyTextures(getTextureIndex(machines + "precise_item_chute_top"), getTextureIndex(machines + "precise_item_chute_side"), getTextureIndex(machines + "precise_item_chute_bottom"));
        BlockListener.machineFrame.specifyTextures(getTextureIndex(machines + "machine_frame"));
        BlockListener.creativeGenerator.specifyTextures(getTextureIndex(machines + "generator_output"), getTextureIndex(machines + "creative_generator_side"), getTextureIndex(machines + "generator_input"));
        BlockListener.stirlingGenerator.specifyTextures(getTextureIndex(machines + "generator_output"), getTextureIndex(machines + "stirling_generator_side"), getTextureIndex(machines + "generator_input"));
        BlockListener.machineGearBox.specifyTextures(getTextureIndex(machines + "machine_gear_box"));
        BlockListener.automaticCraftingTable.specifyTextures(getTextureIndex(machines + "automatic_crafting_table_top"),
                getTextureIndex(machines + "automatic_crafting_table_front"), getTextureIndex(machines + "automatic_crafting_table_front_active"),
                getTextureIndex(machines + "automatic_crafting_table_side"), getTextureIndex(machines + "automatic_crafting_table_bottom"));

        // Ore storage blocks
        BlockListener.coalBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "coal")).index);
        BlockListener.onyxBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "onyx")).index);
        BlockListener.cobaltBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "cobalt")).index);
        BlockListener.aluminiumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "aluminium")).index);
        BlockListener.copperBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "copper")).index);
        BlockListener.tinBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "tin")).index);
        BlockListener.zincBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "zinc")).index);
        BlockListener.nickelBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "nickel")).index);
        BlockListener.bismuthBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "bismuth")).index);
        BlockListener.osmiumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "osmium")).index);
        BlockListener.tungstenBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "tungsten")).index);
        BlockListener.magnetiteBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "magnetite")).index);
        BlockListener.silverBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "silver")).index);
        BlockListener.leadBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "lead")).index);
        BlockListener.siliconBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "silicon")).index);
        BlockListener.chromeBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "chrome")).index);
        BlockListener.titaniumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "titanium")).index);
        BlockListener.uraniumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "uranium")).index);
        BlockListener.rubyBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "ruby")).index);
        BlockListener.sapphireBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "sapphire")).index);
        BlockListener.emeraldBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "emerald")).index);
        BlockListener.boronBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "boron")).index);
        BlockListener.platinumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "platinum")).index);
        BlockListener.bronzeBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "bronze")).index);
        BlockListener.brassBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "brass")).index);
        BlockListener.steelBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "steel")).index);
        BlockListener.cupronickelBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "cupronickel")).index);

        // Pebble variants
        BlockListener.pebble.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble")).index);
        BlockListener.pebbleSmall.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_small")).index);
        BlockListener.pebbleMedium.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_medium")).index);
        BlockListener.pebbleLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_large")).index);

        // Saplings
        int alphaSaplingTexture = getTextureIndex(decorativeBlocks + "alpha_sapling");
        BlockListener.alphaSaplingBlock.specifyTextures(alphaSaplingTexture);
        BlockListener.alphaSaplingBlock.asItem().setTextureId(alphaSaplingTexture);

        // Mushrooms
        BlockListener.bioluminescentMushroom.specifyTextures(
                new int[] {
                        getTextureIndex(mushrooms + "blue"),
                        getTextureIndex(mushrooms + "purple")
                }
        );
        BlockListener.bioluminescentMushroom.asItem().setTextureId(getTextureIndex(mushrooms + "blue"));
        BlockListener.glowingMushroom.specifyTextures(getTextureIndex(mushrooms + "glowing"));
        BlockListener.glowingMushroom.asItem().setTextureId(getTextureIndex(mushrooms + "glowing"));
        BlockListener.fieryMushroom.specifyTextures(getTextureIndex(mushrooms + "fiery"));
        BlockListener.fieryMushroom.asItem().setTextureId(getTextureIndex(mushrooms + "fiery"));

        // Petrified blocks
        int petrifiedTopTexture = getTextureIndex(wood + "petrified_log_top");
        BlockListener.petrifiedLog.specifyTextures(petrifiedTopTexture, getTextureIndex(wood + "petrified_log_side"), petrifiedTopTexture);
        BlockListener.petrifiedPlanks.specifyTextures(getTextureIndex(planks + "petrified_wooden_planks"));
        BlockListener.petrifiedLeaves.specifyTextures(
                getTextureIndex(decorativeBlocks + "petrified_leaves_fast"),
                getTextureIndex(decorativeBlocks + "petrified_leaves_fancy")
        );

        // Farming
        BlockListener.unfiredPlanter.specifyTextures(getTextureIndex(unfiredPlanter + "top"), getTextureIndex(unfiredPlanter + "side"), getTextureIndex(unfiredPlanter + "side"));

        planterSide = getTextureIndex(planter + "side");
        planterEmpty = getTextureIndex(planter + "empty");
        planterFilled = getTextureIndex(planter + "filled");
        planterIrrigated = getTextureIndex(planter + "irrigated");

        // Pickaxes
        ItemListener.aluminiumPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "aluminium"));
        ItemListener.bismuthPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "bismuth"));
        ItemListener.copperPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "copper"));
        ItemListener.leadPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "lead"));
        ItemListener.tinPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "tin"));
        ItemListener.zincPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "zinc"));
        ItemListener.boronPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "boron"));
        ItemListener.brassPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "brass"));
        ItemListener.bronzePickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "bronze"));
        ItemListener.nickelPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "nickel"));
        ItemListener.platinumPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "platinum"));
        ItemListener.silverPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "silver"));
        ItemListener.chromePickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "chrome"));
        ItemListener.cobaltPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "cobalt"));
        ItemListener.siliconPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "silicon"));
        ItemListener.magnetPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "magnet"));
        ItemListener.steelPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "steel"));
        ItemListener.titaniumPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "titanium"));
        ItemListener.tungstenPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "tungsten"));
        ItemListener.onyxPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "onyx"));
        ItemListener.sapphirePickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "sapphire"));
        ItemListener.rubyPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "ruby"));
        ItemListener.emeraldPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "emerald"));
        ItemListener.osmiumPickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "osmium"));

        // Swords
        ItemListener.aluminiumSword.setTexture(Identifier.of(MOD_ID, swords + "aluminium"));
        ItemListener.bismuthSword.setTexture(Identifier.of(MOD_ID, swords + "bismuth"));
        ItemListener.copperSword.setTexture(Identifier.of(MOD_ID, swords + "copper"));
        ItemListener.leadSword.setTexture(Identifier.of(MOD_ID, swords + "lead"));
        ItemListener.tinSword.setTexture(Identifier.of(MOD_ID, swords + "tin"));
        ItemListener.zincSword.setTexture(Identifier.of(MOD_ID, swords + "zinc"));
        ItemListener.boronSword.setTexture(Identifier.of(MOD_ID, swords + "boron"));
        ItemListener.brassSword.setTexture(Identifier.of(MOD_ID, swords + "brass"));
        ItemListener.bronzeSword.setTexture(Identifier.of(MOD_ID, swords + "bronze"));
        ItemListener.nickelSword.setTexture(Identifier.of(MOD_ID, swords + "nickel"));
        ItemListener.platinumSword.setTexture(Identifier.of(MOD_ID, swords + "platinum"));
        ItemListener.silverSword.setTexture(Identifier.of(MOD_ID, swords + "silver"));
        ItemListener.chromeSword.setTexture(Identifier.of(MOD_ID, swords + "chrome"));
        ItemListener.cobaltSword.setTexture(Identifier.of(MOD_ID, swords + "cobalt"));
        ItemListener.siliconSword.setTexture(Identifier.of(MOD_ID, swords + "silicon"));
        ItemListener.magnetSword.setTexture(Identifier.of(MOD_ID, swords + "magnet"));
        ItemListener.steelSword.setTexture(Identifier.of(MOD_ID, swords + "steel"));
        ItemListener.titaniumSword.setTexture(Identifier.of(MOD_ID, swords + "titanium"));
        ItemListener.tungstenSword.setTexture(Identifier.of(MOD_ID, swords + "tungsten"));
        ItemListener.onyxSword.setTexture(Identifier.of(MOD_ID, swords + "onyx"));
        ItemListener.sapphireSword.setTexture(Identifier.of(MOD_ID, swords + "sapphire"));
        ItemListener.rubySword.setTexture(Identifier.of(MOD_ID, swords + "ruby"));
        ItemListener.emeraldSword.setTexture(Identifier.of(MOD_ID, swords + "emerald"));
        ItemListener.osmiumSword.setTexture(Identifier.of(MOD_ID, swords + "osmium"));

        // Shovels
        ItemListener.aluminiumShovel.setTexture(Identifier.of(MOD_ID, shovels + "aluminium"));
        ItemListener.bismuthShovel.setTexture(Identifier.of(MOD_ID, shovels + "bismuth"));
        ItemListener.copperShovel.setTexture(Identifier.of(MOD_ID, shovels + "copper"));
        ItemListener.leadShovel.setTexture(Identifier.of(MOD_ID, shovels + "lead"));
        ItemListener.tinShovel.setTexture(Identifier.of(MOD_ID, shovels + "tin"));
        ItemListener.zincShovel.setTexture(Identifier.of(MOD_ID, shovels + "zinc"));
        ItemListener.boronShovel.setTexture(Identifier.of(MOD_ID, shovels + "boron"));
        ItemListener.brassShovel.setTexture(Identifier.of(MOD_ID, shovels + "brass"));
        ItemListener.bronzeShovel.setTexture(Identifier.of(MOD_ID, shovels + "bronze"));
        ItemListener.nickelShovel.setTexture(Identifier.of(MOD_ID, shovels + "nickel"));
        ItemListener.platinumShovel.setTexture(Identifier.of(MOD_ID, shovels + "platinum"));
        ItemListener.silverShovel.setTexture(Identifier.of(MOD_ID, shovels + "silver"));
        ItemListener.chromeShovel.setTexture(Identifier.of(MOD_ID, shovels + "chrome"));
        ItemListener.cobaltShovel.setTexture(Identifier.of(MOD_ID, shovels + "cobalt"));
        ItemListener.siliconShovel.setTexture(Identifier.of(MOD_ID, shovels + "silicon"));
        ItemListener.magnetShovel.setTexture(Identifier.of(MOD_ID, shovels + "magnet"));
        ItemListener.steelShovel.setTexture(Identifier.of(MOD_ID, shovels + "steel"));
        ItemListener.titaniumShovel.setTexture(Identifier.of(MOD_ID, shovels + "titanium"));
        ItemListener.tungstenShovel.setTexture(Identifier.of(MOD_ID, shovels + "tungsten"));
        ItemListener.onyxShovel.setTexture(Identifier.of(MOD_ID, shovels + "onyx"));
        ItemListener.sapphireShovel.setTexture(Identifier.of(MOD_ID, shovels + "sapphire"));
        ItemListener.rubyShovel.setTexture(Identifier.of(MOD_ID, shovels + "ruby"));
        ItemListener.emeraldShovel.setTexture(Identifier.of(MOD_ID, shovels + "emerald"));
        ItemListener.osmiumShovel.setTexture(Identifier.of(MOD_ID, shovels + "osmium"));

        // Axes
        ItemListener.aluminiumAxe.setTexture(Identifier.of(MOD_ID, axes + "aluminium"));
        ItemListener.bismuthAxe.setTexture(Identifier.of(MOD_ID, axes + "bismuth"));
        ItemListener.copperAxe.setTexture(Identifier.of(MOD_ID, axes + "copper"));
        ItemListener.leadAxe.setTexture(Identifier.of(MOD_ID, axes + "lead"));
        ItemListener.tinAxe.setTexture(Identifier.of(MOD_ID, axes + "tin"));
        ItemListener.zincAxe.setTexture(Identifier.of(MOD_ID, axes + "zinc"));
        ItemListener.boronAxe.setTexture(Identifier.of(MOD_ID, axes + "boron"));
        ItemListener.brassAxe.setTexture(Identifier.of(MOD_ID, axes + "brass"));
        ItemListener.bronzeAxe.setTexture(Identifier.of(MOD_ID, axes + "bronze"));
        ItemListener.nickelAxe.setTexture(Identifier.of(MOD_ID, axes + "nickel"));
        ItemListener.platinumAxe.setTexture(Identifier.of(MOD_ID, axes + "platinum"));
        ItemListener.silverAxe.setTexture(Identifier.of(MOD_ID, axes + "silver"));
        ItemListener.chromeAxe.setTexture(Identifier.of(MOD_ID, axes + "chrome"));
        ItemListener.cobaltAxe.setTexture(Identifier.of(MOD_ID, axes + "cobalt"));
        ItemListener.siliconAxe.setTexture(Identifier.of(MOD_ID, axes + "silicon"));
        ItemListener.magnetAxe.setTexture(Identifier.of(MOD_ID, axes + "magnet"));
        ItemListener.steelAxe.setTexture(Identifier.of(MOD_ID, axes + "steel"));
        ItemListener.titaniumAxe.setTexture(Identifier.of(MOD_ID, axes + "titanium"));
        ItemListener.tungstenAxe.setTexture(Identifier.of(MOD_ID, axes + "tungsten"));
        ItemListener.onyxAxe.setTexture(Identifier.of(MOD_ID, axes + "onyx"));
        ItemListener.sapphireAxe.setTexture(Identifier.of(MOD_ID, axes + "sapphire"));
        ItemListener.rubyAxe.setTexture(Identifier.of(MOD_ID, axes + "ruby"));
        ItemListener.emeraldAxe.setTexture(Identifier.of(MOD_ID, axes + "emerald"));
        ItemListener.osmiumAxe.setTexture(Identifier.of(MOD_ID, axes + "osmium"));

        // Hoes
        ItemListener.aluminiumHoe.setTexture(Identifier.of(MOD_ID, hoes + "aluminium"));
        ItemListener.bismuthHoe.setTexture(Identifier.of(MOD_ID, hoes + "bismuth"));
        ItemListener.copperHoe.setTexture(Identifier.of(MOD_ID, hoes + "copper"));
        ItemListener.leadHoe.setTexture(Identifier.of(MOD_ID, hoes + "lead"));
        ItemListener.tinHoe.setTexture(Identifier.of(MOD_ID, hoes + "tin"));
        ItemListener.zincHoe.setTexture(Identifier.of(MOD_ID, hoes + "zinc"));
        ItemListener.boronHoe.setTexture(Identifier.of(MOD_ID, hoes + "boron"));
        ItemListener.brassHoe.setTexture(Identifier.of(MOD_ID, hoes + "brass"));
        ItemListener.bronzeHoe.setTexture(Identifier.of(MOD_ID, hoes + "bronze"));
        ItemListener.nickelHoe.setTexture(Identifier.of(MOD_ID, hoes + "nickel"));
        ItemListener.platinumHoe.setTexture(Identifier.of(MOD_ID, hoes + "platinum"));
        ItemListener.silverHoe.setTexture(Identifier.of(MOD_ID, hoes + "silver"));
        ItemListener.chromeHoe.setTexture(Identifier.of(MOD_ID, hoes + "chrome"));
        ItemListener.cobaltHoe.setTexture(Identifier.of(MOD_ID, hoes + "cobalt"));
        ItemListener.siliconHoe.setTexture(Identifier.of(MOD_ID, hoes + "silicon"));
        ItemListener.magnetHoe.setTexture(Identifier.of(MOD_ID, hoes + "magnet"));
        ItemListener.steelHoe.setTexture(Identifier.of(MOD_ID, hoes + "steel"));
        ItemListener.titaniumHoe.setTexture(Identifier.of(MOD_ID, hoes + "titanium"));
        ItemListener.tungstenHoe.setTexture(Identifier.of(MOD_ID, hoes + "tungsten"));
        ItemListener.onyxHoe.setTexture(Identifier.of(MOD_ID, hoes + "onyx"));
        ItemListener.sapphireHoe.setTexture(Identifier.of(MOD_ID, hoes + "sapphire"));
        ItemListener.rubyHoe.setTexture(Identifier.of(MOD_ID, hoes + "ruby"));
        ItemListener.emeraldHoe.setTexture(Identifier.of(MOD_ID, hoes + "emerald"));
        ItemListener.osmiumHoe.setTexture(Identifier.of(MOD_ID, hoes + "osmium"));

        // Helmets
        ItemListener.aluminiumHelmet.setTexture(Identifier.of(MOD_ID, helmets + "aluminium"));
        ItemListener.bismuthHelmet.setTexture(Identifier.of(MOD_ID, helmets + "bismuth"));
        ItemListener.copperHelmet.setTexture(Identifier.of(MOD_ID, helmets + "copper"));
        ItemListener.tinHelmet.setTexture(Identifier.of(MOD_ID, helmets + "tin"));
        ItemListener.zincHelmet.setTexture(Identifier.of(MOD_ID, helmets + "zinc"));
        ItemListener.boronHelmet.setTexture(Identifier.of(MOD_ID, helmets + "boron"));
        ItemListener.brassHelmet.setTexture(Identifier.of(MOD_ID, helmets + "brass"));
        ItemListener.bronzeHelmet.setTexture(Identifier.of(MOD_ID, helmets + "bronze"));
        ItemListener.nickelHelmet.setTexture(Identifier.of(MOD_ID, helmets + "nickel"));
        ItemListener.platinumHelmet.setTexture(Identifier.of(MOD_ID, helmets + "platinum"));
        ItemListener.silverHelmet.setTexture(Identifier.of(MOD_ID, helmets + "silver"));
        ItemListener.chromeHelmet.setTexture(Identifier.of(MOD_ID, helmets + "chrome"));
        ItemListener.cobaltHelmet.setTexture(Identifier.of(MOD_ID, helmets + "cobalt"));
        ItemListener.siliconHelmet.setTexture(Identifier.of(MOD_ID, helmets + "silicon"));
        ItemListener.steelHelmet.setTexture(Identifier.of(MOD_ID, helmets + "steel"));
        ItemListener.titaniumHelmet.setTexture(Identifier.of(MOD_ID, helmets + "titanium"));
        ItemListener.tungstenHelmet.setTexture(Identifier.of(MOD_ID, helmets + "tungsten"));
        ItemListener.onyxHelmet.setTexture(Identifier.of(MOD_ID, helmets + "onyx"));
        ItemListener.sapphireHelmet.setTexture(Identifier.of(MOD_ID, helmets + "sapphire"));
        ItemListener.rubyHelmet.setTexture(Identifier.of(MOD_ID, helmets + "ruby"));
        ItemListener.emeraldHelmet.setTexture(Identifier.of(MOD_ID, helmets + "emerald"));
        ItemListener.osmiumHelmet.setTexture(Identifier.of(MOD_ID, helmets + "osmium"));

        // Chestplates
        ItemListener.aluminiumChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "aluminium"));
        ItemListener.bismuthChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "bismuth"));
        ItemListener.copperChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "copper"));
        ItemListener.tinChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "tin"));
        ItemListener.zincChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "zinc"));
        ItemListener.boronChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "boron"));
        ItemListener.brassChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "brass"));
        ItemListener.bronzeChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "bronze"));
        ItemListener.nickelChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "nickel"));
        ItemListener.platinumChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "platinum"));
        ItemListener.silverChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "silver"));
        ItemListener.chromeChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "chrome"));
        ItemListener.cobaltChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "cobalt"));
        ItemListener.siliconChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "silicon"));
        ItemListener.steelChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "steel"));
        ItemListener.titaniumChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "titanium"));
        ItemListener.tungstenChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "tungsten"));
        ItemListener.onyxChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "onyx"));
        ItemListener.sapphireChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "sapphire"));
        ItemListener.rubyChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "ruby"));
        ItemListener.emeraldChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "emerald"));
        ItemListener.osmiumChestplate.setTexture(Identifier.of(MOD_ID, chestplates + "osmium"));

        // Leggings
        ItemListener.aluminiumLeggings.setTexture(Identifier.of(MOD_ID, leggings + "aluminium"));
        ItemListener.bismuthLeggings.setTexture(Identifier.of(MOD_ID, leggings + "bismuth"));
        ItemListener.copperLeggings.setTexture(Identifier.of(MOD_ID, leggings + "copper"));
        ItemListener.tinLeggings.setTexture(Identifier.of(MOD_ID, leggings + "tin"));
        ItemListener.zincLeggings.setTexture(Identifier.of(MOD_ID, leggings + "zinc"));
        ItemListener.boronLeggings.setTexture(Identifier.of(MOD_ID, leggings + "boron"));
        ItemListener.brassLeggings.setTexture(Identifier.of(MOD_ID, leggings + "brass"));
        ItemListener.bronzeLeggings.setTexture(Identifier.of(MOD_ID, leggings + "bronze"));
        ItemListener.nickelLeggings.setTexture(Identifier.of(MOD_ID, leggings + "nickel"));
        ItemListener.platinumLeggings.setTexture(Identifier.of(MOD_ID, leggings + "platinum"));
        ItemListener.silverLeggings.setTexture(Identifier.of(MOD_ID, leggings + "silver"));
        ItemListener.chromeLeggings.setTexture(Identifier.of(MOD_ID, leggings + "chrome"));
        ItemListener.cobaltLeggings.setTexture(Identifier.of(MOD_ID, leggings + "cobalt"));
        ItemListener.siliconLeggings.setTexture(Identifier.of(MOD_ID, leggings + "silicon"));
        ItemListener.steelLeggings.setTexture(Identifier.of(MOD_ID, leggings + "steel"));
        ItemListener.titaniumLeggings.setTexture(Identifier.of(MOD_ID, leggings + "titanium"));
        ItemListener.tungstenLeggings.setTexture(Identifier.of(MOD_ID, leggings + "tungsten"));
        ItemListener.onyxLeggings.setTexture(Identifier.of(MOD_ID, leggings + "onyx"));
        ItemListener.sapphireLeggings.setTexture(Identifier.of(MOD_ID, leggings + "sapphire"));
        ItemListener.rubyLeggings.setTexture(Identifier.of(MOD_ID, leggings + "ruby"));
        ItemListener.emeraldLeggings.setTexture(Identifier.of(MOD_ID, leggings + "emerald"));
        ItemListener.osmiumLeggings.setTexture(Identifier.of(MOD_ID, leggings + "osmium"));

        // Boots
        ItemListener.aluminiumBoots.setTexture(Identifier.of(MOD_ID, boots + "aluminium"));
        ItemListener.bismuthBoots.setTexture(Identifier.of(MOD_ID, boots + "bismuth"));
        ItemListener.copperBoots.setTexture(Identifier.of(MOD_ID, boots + "copper"));
        ItemListener.tinBoots.setTexture(Identifier.of(MOD_ID, boots + "tin"));
        ItemListener.zincBoots.setTexture(Identifier.of(MOD_ID, boots + "zinc"));
        ItemListener.boronBoots.setTexture(Identifier.of(MOD_ID, boots + "boron"));
        ItemListener.brassBoots.setTexture(Identifier.of(MOD_ID, boots + "brass"));
        ItemListener.bronzeBoots.setTexture(Identifier.of(MOD_ID, boots + "bronze"));
        ItemListener.nickelBoots.setTexture(Identifier.of(MOD_ID, boots + "nickel"));
        ItemListener.platinumBoots.setTexture(Identifier.of(MOD_ID, boots + "platinum"));
        ItemListener.silverBoots.setTexture(Identifier.of(MOD_ID, boots + "silver"));
        ItemListener.chromeBoots.setTexture(Identifier.of(MOD_ID, boots + "chrome"));
        ItemListener.cobaltBoots.setTexture(Identifier.of(MOD_ID, boots + "cobalt"));
        ItemListener.siliconBoots.setTexture(Identifier.of(MOD_ID, boots + "silicon"));
        ItemListener.steelBoots.setTexture(Identifier.of(MOD_ID, boots + "steel"));
        ItemListener.titaniumBoots.setTexture(Identifier.of(MOD_ID, boots + "titanium"));
        ItemListener.tungstenBoots.setTexture(Identifier.of(MOD_ID, boots + "tungsten"));
        ItemListener.onyxBoots.setTexture(Identifier.of(MOD_ID, boots + "onyx"));
        ItemListener.sapphireBoots.setTexture(Identifier.of(MOD_ID, boots + "sapphire"));
        ItemListener.rubyBoots.setTexture(Identifier.of(MOD_ID, boots + "ruby"));
        ItemListener.emeraldBoots.setTexture(Identifier.of(MOD_ID, boots + "emerald"));
        ItemListener.osmiumBoots.setTexture(Identifier.of(MOD_ID, boots + "osmium"));

        // Ingots
        ItemListener.aluminiumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "aluminium"));
        ItemListener.copperIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "copper"));
        ItemListener.tinIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "tin"));
        ItemListener.bismuthIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "bismuth"));
        ItemListener.zincIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "zinc"));
        ItemListener.nickelIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "nickel"));
        ItemListener.cobaltIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "cobalt"));
        ItemListener.tungstenIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "tungsten"));
        ItemListener.magnetiteIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "magnetite"));
        ItemListener.silverIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "silver"));
        ItemListener.leadIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "lead"));
        ItemListener.siliconIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "silicon"));
        ItemListener.chromeIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "chrome"));
        ItemListener.titaniumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "titanium"));
        ItemListener.uraniumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "uranium"));
        ItemListener.platinumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "platinum"));
        ItemListener.boronIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "boron"));
        ItemListener.brassIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "brass"));
        ItemListener.bronzeIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "bronze"));
        ItemListener.steelIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "steel"));
        ItemListener.osmiumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "osmium"));
        ItemListener.cupronickelIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "cupronickel"));

        // Ore drops
        ItemListener.anthracite.setTexture(Identifier.of(MOD_ID, oreDrops + "anthracite"));
        ItemListener.netherAsh.setTexture(Identifier.of(MOD_ID, oreDrops + "nether_ash"));
        ItemListener.onyx.setTexture(Identifier.of(MOD_ID, oreDrops + "onyx"));
        ItemListener.sapphire.setTexture(Identifier.of(MOD_ID, oreDrops + "sapphire"));
        ItemListener.ruby.setTexture(Identifier.of(MOD_ID, oreDrops + "ruby"));
        ItemListener.emerald.setTexture(Identifier.of(MOD_ID, oreDrops + "emerald"));

        // Food
        ItemListener.cookedEgg.setTexture(Identifier.of(MOD_ID, foodItems + "cooked_egg"));

        // Oil bucket
        ItemListener.oilBucket.setTexture(Identifier.of(MOD_ID, items + "oil_bucket"));
      
        // Doors
        ItemListener.copperDoor.setTexture(Identifier.of(MOD_ID, items + "copper_door"));

        // Telescope
        ItemListener.telescopeItem.setTexture(Identifier.of(MOD_ID, items + "tools/telescope"));

        // Other drops
        ItemListener.blueGlowstoneDust.setTexture(Identifier.of(MOD_ID, otherDrops + "blue_glowstone_dust"));

        // Intermediate items
        ItemListener.aluminiumGear.setTexture(Identifier.of(MOD_ID, parts + "aluminium_gear"));
        ItemListener.redstoneCircuit.setTexture(Identifier.of(MOD_ID, parts + "redstone_circuit"));

        // Particles
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft minecraft) {
            barrier = minecraft.textureManager.getTextureId("/assets/nfc/stationapi/textures/particle/barrier.png");
            support = minecraft.textureManager.getTextureId("/assets/nfc/stationapi/textures/particle/support.png");
            lightSource = minecraft.textureManager.getTextureId("/assets/nfc/stationapi/textures/particle/light_source.png");
        }
        BlockListener.barrier.specifyTextures(
                new int[] {
                        getTextureIndex(creative + "barrier"),
                        getTextureIndex(creative + "support")
                }
        );
        BlockListener.barrier.setParticleTextures(new int[] {
                barrier,
                support
        });
        BlockListener.lightSource.specifyTextures(
                new int[] {
                        getTextureIndex(creative + "light_source")
                }
        );
        BlockListener.lightSource.setParticleTextures(new int[] {
                lightSource
        });

        // Slabs
        BlockListener.vanillaSlabs.specifyTextures(
                new int[] {
                        Block.SLAB.textureId,
                        Block.SANDSTONE.textureId,
                        Block.PLANKS.textureId,
                        getTextureIndex(vanillaBlocks + "cobblestone")
                }
        );
        BlockListener.nonDyedSlabs.specifyTextures(
                new int[] {
                        getTextureIndex(vanillaBlocks + "bricks"),
                        getTextureIndex(decorativeBlocks + "worked_stone"),
                        getTextureIndex(decorativeBlocks + "stone_bricks_large"),
                        getTextureIndex(decorativeBlocks + "stone_bricks"),
                        getTextureIndex(mudBlocks + "bricks"),
                        getTextureIndex(firedMudBlocks + "bricks"),
                        getTextureIndex(planks + "petrified_wooden_planks")
                }
        );
        BlockListener.stainedPlanksSlabs.specifyTextures(
                new int[] {
                        getTextureIndex(stainedPlanks + "white"),
                        getTextureIndex(stainedPlanks + "orange"),
                        getTextureIndex(stainedPlanks + "magenta"),
                        getTextureIndex(stainedPlanks + "light_blue"),
                        getTextureIndex(stainedPlanks + "yellow"),
                        getTextureIndex(stainedPlanks + "lime"),
                        getTextureIndex(stainedPlanks + "pink"),
                        getTextureIndex(stainedPlanks + "grey"),
                        getTextureIndex(stainedPlanks + "light_grey"),
                        getTextureIndex(stainedPlanks + "cyan"),
                        getTextureIndex(stainedPlanks + "purple"),
                        getTextureIndex(stainedPlanks + "blue"),
                        getTextureIndex(stainedPlanks + "brown"),
                        getTextureIndex(stainedPlanks + "green"),
                        getTextureIndex(stainedPlanks + "red"),
                        getTextureIndex(stainedPlanks + "black")
                }
        );

        // Stairs
        BlockListener.woodenVanillaStairs.specifyTextures(
                new int[] {
                        Block.PLANKS.textureId
                }
        );
        BlockListener.stoneVanillaStairs.specifyTextures(
                new int[] {
                        getTextureIndex(vanillaBlocks + "cobblestone"),
                        Block.SANDSTONE.textureId
                }
        );
        BlockListener.nonDyedStairs.specifyTextures(
                new int[] {
                        getTextureIndex(vanillaBlocks + "bricks"),
                        getTextureIndex(decorativeBlocks + "worked_stone"),
                        getTextureIndex(decorativeBlocks + "stone_bricks_large"),
                        getTextureIndex(decorativeBlocks + "stone_bricks"),
                        getTextureIndex(mudBlocks + "bricks"),
                        getTextureIndex(firedMudBlocks + "bricks"),
                        getTextureIndex(planks + "petrified_wooden_planks")
                }
        );
        BlockListener.stainedPlanksStairs.specifyTextures(
                new int[] {
                        getTextureIndex(stainedPlanks + "white"),
                        getTextureIndex(stainedPlanks + "orange"),
                        getTextureIndex(stainedPlanks + "magenta"),
                        getTextureIndex(stainedPlanks + "light_blue"),
                        getTextureIndex(stainedPlanks + "yellow"),
                        getTextureIndex(stainedPlanks + "lime"),
                        getTextureIndex(stainedPlanks + "pink"),
                        getTextureIndex(stainedPlanks + "grey"),
                        getTextureIndex(stainedPlanks + "light_grey"),
                        getTextureIndex(stainedPlanks + "cyan"),
                        getTextureIndex(stainedPlanks + "purple"),
                        getTextureIndex(stainedPlanks + "blue"),
                        getTextureIndex(stainedPlanks + "brown"),
                        getTextureIndex(stainedPlanks + "green"),
                        getTextureIndex(stainedPlanks + "red"),
                        getTextureIndex(stainedPlanks + "black")
                }
        );

        // Big mushrooms
        BlockListener.fieryMushroomCap.specifyTextures(getTextureIndex(bigMushrooms + "fiery_cap"));
        BlockListener.fieryMushroomStem.specifyTextures(getTextureIndex(bigMushrooms + "fiery_stem"));
        BlockListener.glowingMushroomCap.specifyTextures(getTextureIndex(bigMushrooms + "glowing_cap"));
        BlockListener.glowingMushroomStem.specifyTextures(getTextureIndex(bigMushrooms + "glowing_stem"));
        BlockListener.purpleMushroomCap.specifyTextures(getTextureIndex(bigMushrooms + "purple_cap"));
        BlockListener.purpleMushroomStem.specifyTextures(getTextureIndex(bigMushrooms + "purple_stem"));
        BlockListener.blueMushroomCap.specifyTextures(getTextureIndex(bigMushrooms + "blue_cap"));
        BlockListener.blueMushroomStem.specifyTextures(getTextureIndex(bigMushrooms + "blue_stem"));
        BlockListener.blueShroomlight.specifyTextures(getTextureIndex(bigMushrooms + "blue_shroomlight"));

        // Vanilla texture changes
        grassBlockSide = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "grass_block_side")).index;
        grassBlockSideSnowy = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "grass_block_side_snowy")).index;
        grassBlockSideOverlay = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "grass_block_side_overlay")).index;
        cobblestone = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "cobblestone")).index;
        Block.COBBLESTONE.textureId = cobblestone;
        poweredRail = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "powered_rail")).index;
        poweredRailActive = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "powered_rail_active")).index;
        Block.POWERED_RAIL.asItem().setTextureId(poweredRail);
        Block.DIAMOND_BLOCK.textureId = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "diamond_block")).index;
        bricks = Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, vanillaBlocks + "bricks")).index;
        Block.BRICKS.textureId = bricks;
        oakSaplingTexture = getTextureIndex(vanillaBlocks + "oak_sapling");
    }

    public static int
            grassBlockSide,
            grassBlockSideSnowy,
            grassBlockSideOverlay,
            cobblestone,
            poweredRail,
            poweredRailActive,
            bricks,
            logSide,

            barrier,
            support,
            lightSource,

            planterSide,
            planterEmpty,
            planterFilled,
            planterIrrigated;

    private static int getTextureIndex(String path) {
        return Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, path)).index;
    }
}
