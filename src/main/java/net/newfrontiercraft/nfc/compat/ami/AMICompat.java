package net.newfrontiercraft.nfc.compat.ami;

import net.glasslauncher.mods.alwaysmoreitems.api.*;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class AMICompat implements ModPluginProvider {

    @Override
    public String getName() {
        return "New Frontier Craft";
    }

    @Override
    public Identifier getId() {
        return BlockListener.MOD_ID.id("nfc");
    }

    @Override
    public void onAMIHelpersAvailable(AMIHelpers amiHelpers) {

    }

    @Override
    public void onItemRegistryAvailable(ItemRegistry itemRegistry) {

    }

    @Override
    public void register(ModRegistry registry) {
    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry) {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound recipe) {
        return null;
    }
}