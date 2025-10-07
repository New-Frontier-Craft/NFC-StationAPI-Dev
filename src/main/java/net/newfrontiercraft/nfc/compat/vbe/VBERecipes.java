package net.newfrontiercraft.nfc.compat.vbe;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.registry.CarpentryRecipes;
import net.newfrontiercraft.nfc.registry.TreeFarmHarvestingRegistry;
import net.newfrontiercraft.nfc.utils.ChanceDrop;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import paulevs.vbe.block.VBEBlocks;

public class VBERecipes {

    public static void addCarpentryRecipes(RecipeRegisterEvent event) {
        CarpentryRecipes.carpentry().addCarpentry(BlockRegistry.INSTANCE.getId(VBEBlocks.OAK_LOG), 0, 2, new ItemStack[] {
                new ItemStack(BlockListener.decorativeWood, 1, 0),
                new ItemStack(BlockListener.decorativeWood, 1, 1),
                new ItemStack(BlockListener.decorativeWood, 2, 6),
                new ItemStack(BlockListener.decorativeWood, 2, 7),
                new ItemStack(BlockListener.decorativeWood, 2, 8),
                new ItemStack(BlockListener.decorativeWood, 2, 9)});
        CarpentryRecipes.carpentry().addCarpentry(BlockRegistry.INSTANCE.getId(VBEBlocks.BIRCH_LOG), 0, 2, new ItemStack[] {
                new ItemStack(BlockListener.decorativeWood, 1, 0),
                new ItemStack(BlockListener.decorativeWood, 1, 1),
                new ItemStack(BlockListener.decorativeWood, 2, 6),
                new ItemStack(BlockListener.decorativeWood, 2, 7),
                new ItemStack(BlockListener.decorativeWood, 2, 8),
                new ItemStack(BlockListener.decorativeWood, 2, 9)});
        CarpentryRecipes.carpentry().addCarpentry(BlockRegistry.INSTANCE.getId(VBEBlocks.SPRUCE_LOG), 0, 2, new ItemStack[] {
                new ItemStack(BlockListener.decorativeWood, 1, 0),
                new ItemStack(BlockListener.decorativeWood, 1, 1),
                new ItemStack(BlockListener.decorativeWood, 2, 6),
                new ItemStack(BlockListener.decorativeWood, 2, 7),
                new ItemStack(BlockListener.decorativeWood, 2, 8),
                new ItemStack(BlockListener.decorativeWood, 2, 9)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("decorative_wood"), 0, 1, new ItemStack[] {
                new ItemStack(BlockListener.decorativeWood, 1, 0),
                new ItemStack(BlockListener.decorativeWood, 1, 1),
                new ItemStack(VBEBlocks.OAK_LOG)});
    }

    public static void addTreeFarmingRecipes() {
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(VBEBlocks.OAK_LOG.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(VBEBlocks.OAK_LOG.asItem(), 0), 1.0F)
        });
        TreeFarmHarvestingRegistry.getInstance().addRecipe(new ItemMeta(VBEBlocks.OAK_LEAVES.asItem(), 0), new ChanceDrop[]{
                new ChanceDrop(new ItemMeta(Block.SAPLING.asItem(), 0), 0.25F),
                new ChanceDrop(new ItemMeta(Item.APPLE, 0), 0.001F),
        });
    }

}
