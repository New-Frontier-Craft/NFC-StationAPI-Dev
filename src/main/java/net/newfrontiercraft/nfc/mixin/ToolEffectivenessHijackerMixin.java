package net.newfrontiercraft.nfc.mixin;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.impl.item.ToolEffectivenessImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ToolEffectivenessImpl.class)
public class ToolEffectivenessHijackerMixin {
    @Inject(at = @At("HEAD"), method = "shouldApplyCustomLogic", cancellable = true)
    private static void hijackMethod(ItemStack item, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
