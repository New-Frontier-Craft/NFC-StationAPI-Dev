package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;

public class TextureListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        String decorativeBlocks = "block/decorative_blocks/";
        String oreBlocks = "block/ores/";
        String oreStorage = "block/ore_storage/";
        String machines = "block/machines/";
        String woldGeneration = "block/world_generation/";

        String foodItems = "item/food/";
        String ingotItems = "item/ingots/";
        String pickaxes = "item/tools/pickaxes/";
        String oreDrops = "item/ore_drops/";

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

        BlockListener.brickOven.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front")).index, Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front_active")).index, Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_side")).index);
        BlockListener.brickOvenActive.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front")).index, Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_front_active")).index, Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, machines + "brick_oven_side")).index);

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

        BlockListener.pebble.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble")).index);
        BlockListener.pebbleSmall.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_small")).index);
        BlockListener.pebbleMedium.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_medium")).index);
        BlockListener.pebbleLarge.specifyTextures(Atlases.getTerrain().addTexture(Identifier.of(MOD_ID, woldGeneration + "pebble_large")).index);


        ItemListener.bronzePickaxe.setTexture(Identifier.of(MOD_ID, pickaxes + "bronze_pickaxe"));

        ItemListener.cookedEgg.setTexture(Identifier.of(MOD_ID, foodItems + "cooked_egg"));

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

        ItemListener.anthracite.setTexture(Identifier.of(MOD_ID, oreDrops + "anthracite"));
        ItemListener.netherAsh.setTexture(Identifier.of(MOD_ID, oreDrops + "nether_ash"));
        ItemListener.onyx.setTexture(Identifier.of(MOD_ID, oreDrops + "onyx"));
        ItemListener.sapphire.setTexture(Identifier.of(MOD_ID, oreDrops + "sapphire"));
        ItemListener.ruby.setTexture(Identifier.of(MOD_ID, oreDrops + "ruby"));
        ItemListener.emerald.setTexture(Identifier.of(MOD_ID, oreDrops + "emerald"));
    }
}
