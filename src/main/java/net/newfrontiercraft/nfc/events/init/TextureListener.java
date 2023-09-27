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
        String foodItems = "item/food/";

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

        ItemListener.cookedEgg.setTexture(Identifier.of(MOD_ID, foodItems + "cooked_egg"));
    }
}
