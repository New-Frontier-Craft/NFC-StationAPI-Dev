package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;

public class TextureListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        // Block strings
        String decorativeBlocks = "block/decorative_blocks/";
        String mudBlocks = "block/decorative_blocks/mud/";
        String firedMudBlocks = "block/decorative_blocks/mud/fired/";
        String oreBlocks = "block/ores/";
        String oreStorage = "block/ore_storage/";
        String machines = "block/machines/";
        String woldGeneration = "block/world_generation/";
        String vanillaBlocks = "block/vanilla/";

        // Item strings
        String ingotItems = "item/ingots/";
        String pickaxes = "item/tools/pickaxes/";
        String swords = "item/tools/swords/";
        String shovels = "item/tools/shovels/";
        String axes = "item/tools/axes/";
        String hoes = "item/tools/hoes/";
        String helmets = "item/armor/helmets/";
        String chestplates = "item/armor/chestplates/";
        String leggings = "item/armor/leggings/";
        String boots = "item/armor/boots/";
        String oreDrops = "item/ore_drops/";
        String foodItems = "item/food/";

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
        BlockListener.firedBricks.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "fired_bricks")).index);
        BlockListener.osmiumBricks.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, decorativeBlocks + "osmium_bricks")).index);

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
        BlockListener.netherAshOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "nether_ash_ore")).index);
        BlockListener.netherUraniniteOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "nether_uraninite_ore")).index);
        BlockListener.netherGoldOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "nether_gold_ore")).index);
        BlockListener.netherOnyxOre.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreBlocks + "nether_onyx_ore")).index);
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

        // Ore storage blocks
        BlockListener.coalBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "coal_block")).index);
        BlockListener.onyxBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "onyx_block")).index);
        BlockListener.cobaltBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "cobalt_block")).index);
        BlockListener.aluminiumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "aluminium_block")).index);
        BlockListener.copperBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "copper_block")).index);
        BlockListener.tinBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "tin_block")).index);
        BlockListener.zincBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "zinc_block")).index);
        BlockListener.nickelBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "nickel_block")).index);
        BlockListener.bismuthBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "bismuth_block")).index);
        BlockListener.osmiumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "osmium_block")).index);
        BlockListener.tungstenBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "tungsten_block")).index);
        BlockListener.magnetiteBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "magnetite_block")).index);
        BlockListener.silverBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "silver_block")).index);
        BlockListener.leadBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "lead_block")).index);
        BlockListener.siliconBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "silicon_block")).index);
        BlockListener.chromeBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "chrome_block")).index);
        BlockListener.titaniumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "titanium_block")).index);
        BlockListener.uraniumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "uranium_block")).index);
        BlockListener.rubyBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "ruby_block")).index);
        BlockListener.sapphireBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "sapphire_block")).index);
        BlockListener.emeraldBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "emerald_block")).index);
        BlockListener.boronBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "boron_block")).index);
        BlockListener.platinumBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "platinum_block")).index);
        BlockListener.bronzeBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "bronze_block")).index);
        BlockListener.brassBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "brass_block")).index);
        BlockListener.steelBlock.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, oreStorage + "steel_block")).index);

        // Pebble variants
        BlockListener.pebble.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble")).index);
        BlockListener.pebbleSmall.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_small")).index);
        BlockListener.pebbleMedium.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_medium")).index);
        BlockListener.pebbleLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_large")).index);

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
        ItemListener.aluminiumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "aluminium_ingot"));
        ItemListener.copperIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "copper_ingot"));
        ItemListener.tinIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "tin_ingot"));
        ItemListener.bismuthIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "bismuth_ingot"));
        ItemListener.zincIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "zinc_ingot"));
        ItemListener.nickelIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "nickel_ingot"));
        ItemListener.cobaltIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "cobalt_ingot"));
        ItemListener.tungstenIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "tungsten_ingot"));
        ItemListener.magnetiteIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "magnetite_ingot"));
        ItemListener.silverIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "silver_ingot"));
        ItemListener.leadIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "lead_ingot"));
        ItemListener.siliconIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "silicon_ingot"));
        ItemListener.chromeIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "chrome_ingot"));
        ItemListener.titaniumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "titanium_ingot"));
        ItemListener.uraniumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "uranium_ingot"));
        ItemListener.platinumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "platinum_ingot"));
        ItemListener.boronIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "boron_ingot"));
        ItemListener.brassIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "brass_ingot"));
        ItemListener.bronzeIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "bronze_ingot"));
        ItemListener.steelIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "steel_ingot"));
        ItemListener.osmiumIngot.setTexture(Identifier.of(MOD_ID, ingotItems + "osmium_ingot"));

        // Ore drops
        ItemListener.anthracite.setTexture(Identifier.of(MOD_ID, oreDrops + "anthracite"));
        ItemListener.netherAsh.setTexture(Identifier.of(MOD_ID, oreDrops + "nether_ash"));
        ItemListener.onyx.setTexture(Identifier.of(MOD_ID, oreDrops + "onyx"));
        ItemListener.sapphire.setTexture(Identifier.of(MOD_ID, oreDrops + "sapphire"));
        ItemListener.ruby.setTexture(Identifier.of(MOD_ID, oreDrops + "ruby"));
        ItemListener.emerald.setTexture(Identifier.of(MOD_ID, oreDrops + "emerald"));

        // Food
        ItemListener.cookedEgg.setTexture(Identifier.of(MOD_ID, foodItems + "cooked_egg"));

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
    }

    public static int
            grassBlockSide,
            grassBlockSideSnowy,
            grassBlockSideOverlay,
            cobblestone,
            poweredRail,
            poweredRailActive,
            bricks;

    private static int getTextureIndex(String path) {
        return Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, path)).index;
    }
}
