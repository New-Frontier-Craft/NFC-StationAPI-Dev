package net.newfrontiercraft.nfc.mixin;

import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.StorageBlockRecipes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(StorageBlockRecipes.class)
public class StorageBlockRecipeObliteratorMixin {
    @Inject(at = @At("HEAD"), method = "add", cancellable = true)
    private void suppressOres(CraftingRecipeManager craftingRecipeManager, CallbackInfo ci) {
        ci.cancel();
    }
}
