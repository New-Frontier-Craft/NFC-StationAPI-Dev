package net.newfrontiercraft.nfc.compat.vbe;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.registry.CarpentryRecipes;
import paulevs.vbe.block.VBEBlocks;

public class VBERecipes {

    public static void addVbeRecipes(RecipeRegisterEvent event) {
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

}
