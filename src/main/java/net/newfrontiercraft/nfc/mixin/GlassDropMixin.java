package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.GlassBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(GlassBlock.class)
public class GlassDropMixin {
    @Inject(at = @At("HEAD"), method = "getDroppedItemCount", cancellable = true)
    private void changeDropCount(Random random, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(1);
    }
}
