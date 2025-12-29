package net.newfrontiercraft.nfc.compat.bnb;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.registry.BrickOvenManager;
import net.newfrontiercraft.nfc.registry.TreeFarmHarvestingRegistry;
import net.newfrontiercraft.nfc.registry.TreeFarmPlantingRegistry;
import net.newfrontiercraft.nfc.utils.*;
import paulevs.bnb.block.BNBBlocks;
import paulevs.bnb.item.BNBItems;

public class BNBRecipes {
    public static void addBnbSmeltingRecipes() {
        BrickOvenManager.getInstance().addShapelessOvenRecipe(new ItemStack(ItemListener.siliconIngot, 2), new Object[] {
                new ItemStack(Block.SAND), new ItemStack(Block.SAND),
                new ItemStack(BNBItems.PURE_QUARTZ), new ItemStack(BNBItems.PURE_QUARTZ)},
                1000, FuelLevelEnum.HOT);
    }

    public static void addBnbTreeFarmRecipes() {
        // Generic blocks
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(BNBBlocks.TREE_LANTERN.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(BNBBlocks.TREE_LANTERN.asItem(), 0), 1),
        });
        // Chlorophate
        addRgbTreeRecipes(BNBBlocks.CHLOROPHATE_SAPLING, BNBBlocks.CHLOROPHATE_LEAVES, BNBBlocks.CHLOROPHATE_WEEPING_VINE, BNBBlocks.CHLOROPHATE_LOG, BNBBlocks.CHLOROPHATE_BRANCH, BNBBlocks.CHLOROPHATE_STEM);
        // Pirozen
        addRgbTreeRecipes(BNBBlocks.PIROZEN_SAPLING, BNBBlocks.PIROZEN_LEAVES, BNBBlocks.PIROZEN_WEEPING_VINE, BNBBlocks.PIROZEN_LOG, BNBBlocks.PIROZEN_BRANCH, BNBBlocks.PIROZEN_STEM);
        // Falurian
        addRgbTreeRecipes(BNBBlocks.FALURIAN_SAPLING, BNBBlocks.FALURIAN_LEAVES, BNBBlocks.FALURIAN_WEEPING_VINE, BNBBlocks.FALURIAN_LOG, BNBBlocks.FALURIAN_BRANCH, BNBBlocks.FALURIAN_STEM);
    }

    private static void addRgbTreeRecipes(Block sapling, Block leaves, Block weepingVine, Block log, Block branch, Block stem) {
        TreeFarmPlantingRegistry.getInstance().addRecipe(new ItemMeta(sapling.asItem(), 0), new PlantingRequirement(new BlockAndMetaRange[]{
                new BlockAndMetaRange(Block.NETHERRACK, new int[] {0}),
                new BlockAndMetaRange(BNBBlocks.NETHERRACK_MYCORRUM, new int[] {0})},
                sapling, 0)
        );
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(leaves.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(sapling.asItem(), 0), 0.25F),
        });
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(weepingVine.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(weepingVine.asItem(), 0), 1),
        });
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(log.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(log.asItem(), 0), 1),
        });
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(branch.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(stem.asItem(), 0), 1),
        });
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(stem.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(stem.asItem(), 0), 1),
        });
    }
}
