package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.blocks.LazyOreTemplate;

public class TextureListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerTextures(TextureRegisterEvent event) {
        String decorativeBlocks = "block/decorative_blocks/";
        String foodItems = "item/food/";
        String oreBlocks = "block/ores/";

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

        ItemListener.cookedEgg.setTexture(Identifier.of(MOD_ID, foodItems + "cooked_egg"));
    }
}
