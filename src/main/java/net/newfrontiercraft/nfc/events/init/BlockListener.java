package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.blocks.LazyBlockTemplate;
import net.newfrontiercraft.nfc.blocks.LazyOreTemplate;

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
            stoneTilingLarge;

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

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerBlocks(BlockRegistryEvent event) {
        platedStone = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        platedStoneVerticalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone_vertical_cut"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        platedStoneHorizontalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone_horizontal_cut"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        platedStoneCrossCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "plated_stone_cross_cut"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        workedStone = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        workedStoneVerticalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone_vertical_cut"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        workedStoneHorizontalCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone_horizontal_cut"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        workedStoneCrossCut = new LazyBlockTemplate(Identifier.of(MOD_ID, "worked_stone_cross_cut"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        stoneBricks = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_bricks"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        stoneBricksLarge = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_bricks_large"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        stoneCheckers = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_checkers"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        stoneTiling = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_tiling"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);
        stoneTilingLarge = new LazyBlockTemplate(Identifier.of(MOD_ID, "stone_tiling_large"), Material.STONE, 1.5F, BlockBase.STONE_SOUNDS);

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
    }
}
