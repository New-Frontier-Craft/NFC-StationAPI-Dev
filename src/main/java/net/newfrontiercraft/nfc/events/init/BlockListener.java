package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.newfrontiercraft.nfc.block.*;

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

            netherWorkedStone,
            netherWorkedStoneVerticalCut,
            netherWorkedStoneHorizontalCut,
            netherWorkedStoneCrossCut,
            netherStoneBricks,
            netherStoneBricksLarge,
            netherStoneCheckers,
            netherStoneTiling,
            netherStoneTilingLarge,

            firedBricks,
            osmiumBricks,
            snowBricks,
            blueGlowstone,
            scorchedSandstone,
            petrifiedLog,
            petrifiedPlanks,

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
            steelBlock,

            unfiredPlanter;

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

    public static BrickOvenBlock
            brickOven,
            brickOvenActive;

    public static LazySandTemplate
            pebble,
            pebbleSmall,
            pebbleMedium,
            pebbleLarge,

            scorchedSand;

    public static LazyMultivariantBlockTemplate
            mud,
            firedMud,
            decorativeWood,
            stainedPlanks;

    public static LazyMultivariantGlassBlockTemplate
            window,
            stainedGlass;

    public static LazyGlassBlockTemplate tintedGlass;

    public static CarpentryWorkstationBlock carpentryWorkstation;

    public static ScaffoldBlock scaffoldBlock;
    public static OilFlowingBlock oilFlowing;
    public static OilStillBlock oilStill;

    public static WallBlock hardWall;
    public static FenceGateBlock fenceGate;
    public static AlphaGrassBlock alphaGrass;
    public static AlphaLeavesBlock alphaLeaves;
    public static AlphaSapling alphaSapling;

    public static PetrifiedLeavesBlock petrifiedLeaves;

    public static DoorBlock copperDoor;

    public static BioluminescentMushroomBlock bioluminescentMushroom;
    public static GlowingMushroomBlock glowingMushroom;
    public static FieryMushroomBlock fieryMushroom;

    public static UnrestrictedNetherPortalBlock unrestrictedNetherPortal;

    public static CreativeBlock
            barrier,
            lightSource;

    public static LazySlabTemplate
            vanillaSlabs,
            nonDyedSlabs,
            stainedPlanksSlabs;

    public static LazyStairsTemplate
            woodenVanillaStairs,
            stoneVanillaStairs,
            nonDyedStairs,
            stainedPlanksStairs;

    public static DoubleStoneSlabBlock doubleStoneSlab;

    public static PlanterBlock planter;

    public static ItemDroppingBlock
            fieryMushroomCap,
            fieryMushroomStem,
            glowingMushroomCap,
            glowingMushroomStem,
            purpleMushroomCap,
            purpleMushroomStem,
            blueMushroomCap,
            blueMushroomStem;

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

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

        netherWorkedStone = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_worked_stone"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherWorkedStoneVerticalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_worked_stone_vertical_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherWorkedStoneHorizontalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_worked_stone_horizontal_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherWorkedStoneCrossCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_worked_stone_cross_cut"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherStoneBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_stone_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherStoneBricksLarge = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_stone_bricks_large"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherStoneCheckers = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_stone_checkers"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherStoneTiling = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_stone_tiling"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        netherStoneTilingLarge = new LazyBlockTemplate(Identifier.of(MOD_ID, "nether_stone_tiling_large"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

        firedBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "fired_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        osmiumBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "osmium_bricks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        decorativeWood = new DecorativeWoodBlock(Identifier.of(MOD_ID, "decorative_wood"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        stainedPlanks = new LazyMultivariantBlockTemplate(Identifier.of(MOD_ID, "stained_planks"), Material.WOOD, 1.5F, Block.WOOD_SOUND_GROUP);
        window = new LazyMultivariantGlassBlockTemplate(Identifier.of(MOD_ID, "window"), Material.GLASS, 0.3F, Block.GLASS_SOUND_GROUP, false);
        stainedGlass = new LazyMultivariantGlassBlockTemplate(Identifier.of(MOD_ID, "stained_glass"), Material.GLASS, 0.3F, Block.GLASS_SOUND_GROUP, true);
        tintedGlass = (LazyGlassBlockTemplate) new LazyGlassBlockTemplate(Identifier.of(MOD_ID, "tinted_glass"), Material.GLASS, 0.6F, Block.GLASS_SOUND_GROUP, true).setResistance(12.0F).setOpacity(255);
        snowBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "snow_bricks"), Material.SNOW_BLOCK, 1.5F, Block.WOOL_SOUND_GROUP);
        hardWall = new WallBlock(Identifier.of(MOD_ID, "hard_wall"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        fenceGate = (FenceGateBlock) new FenceGateBlock(Identifier.of(MOD_ID, "fence_gate"), Material.WOOD).setHardness(2.0F).setResistance(5.0F).setSoundGroup(Block.WOOD_SOUND_GROUP).setTranslationKey(Identifier.of(MOD_ID, "fence_gate"));
        blueGlowstone = new BlueGlowstoneBlock(Identifier.of(MOD_ID, "blue_glowstone"), Material.STONE, 0.3F, Block.GLASS_SOUND_GROUP);
        alphaGrass = (AlphaGrassBlock) new AlphaGrassBlock(Identifier.of(MOD_ID, "alpha_grass")).setHardness(1.2F).setSoundGroup(Block.DIRT_SOUND_GROUP).setTranslationKey(Identifier.of(MOD_ID, "alpha_grass"));
        alphaLeaves = (AlphaLeavesBlock) new AlphaLeavesBlock(Identifier.of(MOD_ID, "alpha_leaves")).setHardness(0.2F).setOpacity(1).setSoundGroup(Block.DIRT_SOUND_GROUP).setTranslationKey(Identifier.of(MOD_ID, "alpha_leaves"));
        alphaSapling = (AlphaSapling) new AlphaSapling(Identifier.of(MOD_ID, "alpha_sapling"), 0).setHardness(0.0F).ignoreMetaUpdates().setSoundGroup(Block.DIRT_SOUND_GROUP).setTranslationKey(Identifier.of(MOD_ID, "alpha_sapling"));

        copperDoor = (DoorBlock) new CopperDoorBlock(Identifier.of(MOD_ID, "copper_door_block"), Materials.copper, Identifier.of(MOD_ID, "copper_door")).setHardness(3.0F).setSoundGroup(Block.METAL_SOUND_GROUP).setTranslationKey(Identifier.of(MOD_ID, "copper_door_block"));

        mud = new LazyMultivariantBlockTemplate(Identifier.of(MOD_ID, "mud"), Material.SOIL, 1.5F, Block.GRAVEL_SOUND_GROUP);
        firedMud = new LazyMultivariantBlockTemplate(Identifier.of(MOD_ID, "fired_mud"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);

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

        pebble = new LazySandTemplate(Identifier.of(MOD_ID, "pebble"), 3F, Block.GRAVEL_SOUND_GROUP, 8, Material.SOIL);
        pebbleSmall = new LazySandTemplate(Identifier.of(MOD_ID, "pebble_small"), 3F, Block.GRAVEL_SOUND_GROUP, 8, Material.SOIL);
        pebbleMedium = new LazySandTemplate(Identifier.of(MOD_ID, "pebble_medium"), 3F, Block.GRAVEL_SOUND_GROUP, 8, Material.SOIL);
        pebbleLarge = new LazySandTemplate(Identifier.of(MOD_ID, "pebble_large"), 3F, Block.GRAVEL_SOUND_GROUP, 8, Material.SOIL);

        brickOven = new BrickOvenBlock(Identifier.of(MOD_ID, "brick_oven"), Material.STONE, false, 0F, 1.5F);
        brickOvenActive = new BrickOvenBlock(Identifier.of(MOD_ID, "brick_oven_active"), Material.STONE, true, 0.875F, 1.5F);

        carpentryWorkstation = new CarpentryWorkstationBlock(Identifier.of(MOD_ID, "carpentry_workstation"), Material.WOOD, 1.0F);

        scaffoldBlock = new ScaffoldBlock(Identifier.of(MOD_ID, "scaffold_block"), Material.WOOD, 0.2F, Block.WOOD_SOUND_GROUP);

        oilFlowing = (OilFlowingBlock) new OilFlowingBlock(Identifier.of(MOD_ID, "oil_flowing"), Materials.oil).setHardness(100.0F).disableTrackingStatistics().setOpacity(3).ignoreMetaUpdates();
        oilStill = (OilStillBlock) new OilStillBlock(Identifier.of(MOD_ID, "oil"), Materials.oil).setHardness(100.0F).disableTrackingStatistics().setOpacity(3).ignoreMetaUpdates();

        scorchedSand = new ScorchedSandBlock(Identifier.of(MOD_ID, "scorched_sand"), 0.5F, Block.SAND_SOUND_GROUP, 1);
        scorchedSandstone = new LazyBlockTemplate(Identifier.of(MOD_ID, "scorched_sandstone"), Material.STONE, 0.8F, Block.STONE_SOUND_GROUP);

        bioluminescentMushroom = new BioluminescentMushroomBlock(Identifier.of(MOD_ID, "bioluminescent_mushroom"), 0, Block.WOOD_SOUND_GROUP, true);
        glowingMushroom = new GlowingMushroomBlock(Identifier.of(MOD_ID, "glowing_mushroom"), 0, Block.DIRT_SOUND_GROUP, false);
        fieryMushroom = new FieryMushroomBlock(Identifier.of(MOD_ID, "fiery_mushroom"), 0, Block.DIRT_SOUND_GROUP, false);

        petrifiedLog = new LazyBlockTemplate(Identifier.of(MOD_ID, "petrified_log"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        petrifiedPlanks = new LazyBlockTemplate(Identifier.of(MOD_ID, "petrified_planks"), Material.STONE, 1.5F, Block.STONE_SOUND_GROUP);
        petrifiedLeaves = (PetrifiedLeavesBlock) new PetrifiedLeavesBlock(Identifier.of(MOD_ID, "petrified_leaves")).setOpacity(1).setHardness(0.2F).setSoundGroup(Block.DIRT_SOUND_GROUP).setTranslationKey(Identifier.of(MOD_ID, "petrified_leaves"));
        unrestrictedNetherPortal = new UnrestrictedNetherPortalBlock(Identifier.of(MOD_ID, "unrestricted_nether_portal"));

        barrier = new CreativeBlock(Identifier.of(MOD_ID, "barrier"), Material.STONE, Block.STONE_SOUND_GROUP, new boolean[] {true, false});
        lightSource = new LightSourceBlock(Identifier.of(MOD_ID, "light_source"), Material.STONE, Block.STONE_SOUND_GROUP, new boolean[] {false});

        vanillaSlabs = new VanillaSlabBlock(Identifier.of(MOD_ID, "vanilla_slabs"), Material.STONE, 1.0F, Block.STONE_SOUND_GROUP,
                new int[] {
                        Block.DOUBLE_SLAB.id, Block.SANDSTONE.id, Block.PLANKS.id, Block.COBBLESTONE.id, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                },
                new float[] {
                        0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F
                },
                Block.SLAB
        );
        nonDyedSlabs = new LazySlabTemplate(Identifier.of(MOD_ID, "non_dyed_slabs"), Material.STONE, 1.0F, Block.STONE_SOUND_GROUP,
                new int[] {
                        Block.BRICKS.id, workedStone.id, stoneBricksLarge.id, stoneBricks.id, mud.id, firedMud.id, petrifiedPlanks.id, 0, 0, 0, 0, 0, 0, 0, 0, 0
                },
                new int[] {
                        0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                },
                null
        );
        stainedPlanksSlabs = new LazySlabTemplate(Identifier.of(MOD_ID, "stained_planks_slabs"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP,
                new int[] {
                        stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id, stainedPlanks.id
                },
                new int[] {
                        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
                },
                null
        );

        woodenVanillaStairs = new WoodenVanillaStairs(Identifier.of(MOD_ID, "wooden_vanilla_stairs"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        stoneVanillaStairs = new StoneVanillaStairs(Identifier.of(MOD_ID, "stone_vanilla_stairs"), Material.STONE, 1.0F, Block.STONE_SOUND_GROUP);
        nonDyedStairs = new LazyStairsTemplate(Identifier.of(MOD_ID, "non_dyed_stairs"), Material.STONE, 1.0F, Block.STONE_SOUND_GROUP);
        stainedPlanksStairs = new LazyStairsTemplate(Identifier.of(MOD_ID, "stained_planks_stairs"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        doubleStoneSlab = new DoubleStoneSlabBlock(Identifier.of(MOD_ID, "double_stone_slab"), Material.STONE, 1.0F, Block.STONE_SOUND_GROUP);

        unfiredPlanter = new LazyBlockTemplate(Identifier.of(MOD_ID, "unfired_planter"), Material.SOIL, 1.0F, Block.DIRT_SOUND_GROUP);
        planter = new PlanterBlock(Identifier.of(MOD_ID, "planter"), Material.STONE, 1.0F, Block.STONE_SOUND_GROUP);

        fieryMushroomCap = new ItemDroppingBlock(Identifier.of(MOD_ID, "fiery_mushroom_cap"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        fieryMushroomStem = new ItemDroppingBlock(Identifier.of(MOD_ID, "fiery_mushroom_stem"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        glowingMushroomCap = new ItemDroppingBlock(Identifier.of(MOD_ID, "glowing_mushroom_cap"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        glowingMushroomStem = new ItemDroppingBlock(Identifier.of(MOD_ID, "glowing_mushroom_stem"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        purpleMushroomCap = new ItemDroppingBlock(Identifier.of(MOD_ID, "purple_mushroom_cap"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        purpleMushroomStem = new ItemDroppingBlock(Identifier.of(MOD_ID, "purple_mushroom_stem"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        blueMushroomCap = new ItemDroppingBlock(Identifier.of(MOD_ID, "blue_mushroom_cap"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);
        blueMushroomStem = new ItemDroppingBlock(Identifier.of(MOD_ID, "blue_mushroom_stem"), Material.WOOD, 1.0F, Block.WOOD_SOUND_GROUP);

        fieryMushroomCap.setLuminance(0.695F);
        fieryMushroomStem.setLuminance(0.695F);
        glowingMushroomCap.setLuminance(1.0F);
        glowingMushroomStem.setLuminance(1.0F);
        purpleMushroomCap.setLuminance(1.0F);
        purpleMushroomStem.setLuminance(1.0F);
        blueMushroomCap.setLuminance(1.0F);
        blueMushroomStem.setLuminance(1.0F);

        fieryMushroomCap.setDropId(fieryMushroom.id);
        fieryMushroomStem.setDropId(fieryMushroom.id);
        glowingMushroomCap.setDropId(glowingMushroom.id);
        glowingMushroomStem.setDropId(glowingMushroom.id);
        purpleMushroomCap.setDropId(bioluminescentMushroom.id);
        purpleMushroomCap.setDropMeta(1);
        purpleMushroomStem.setDropId(bioluminescentMushroom.id);
        purpleMushroomStem.setDropMeta(1);
        blueMushroomCap.setDropId(bioluminescentMushroom.id);
        blueMushroomStem.setDropId(bioluminescentMushroom.id);

        // Changes to vanilla blast resistance
        Block.COAL_ORE.setResistance(500F);
        Block.IRON_ORE.setResistance(500F);
        Block.GOLD_ORE.setResistance(500F);
        Block.LAPIS_ORE.setResistance(500F);
        Block.REDSTONE_ORE.setResistance(500F);
        Block.DIAMOND_ORE.setResistance(500F);
        Block.STONE.setResistance(8F);
        Block.COBBLESTONE.setResistance(10F);

        // Changes to vanilla hardness
        Block.GRASS_BLOCK.setHardness(1.2F);
        Block.DIRT.setHardness(1.0F);
        Block.LOG.setHardness(2.5F);
        Block.GOLD_BLOCK.setHardness(2.0F);
        Block.IRON_BLOCK.setHardness(3.0F);
        Block.BRICKS.setHardness(4.0F);
        Block.OBSIDIAN.setHardness(50.0F);
        Block.DIAMOND_ORE.setHardness(10.0F);
        Block.REDSTONE_ORE.setHardness(4.0F);
        Block.LIT_REDSTONE_ORE.setHardness(4.0F);

        // Set fuel burn time
        FuelRegistry.addFuelItem(scaffoldBlock.asItem(), 200);
        FuelRegistry.addFuelItem(alphaSapling.asItem(), 100);
        FuelRegistry.addFuelItem(coalBlock.asItem(), 6400);
        FuelRegistry.addFuelItem(fieryMushroom.asItem(), 600);

    }
}
