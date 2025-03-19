package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.SlabBlock;
import net.newfrontiercraft.nfc.events.init.TextureListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SlabBlock.class)
public class SlabTextureMixin {
    @Inject(at = @At("HEAD"), method = "getTexture(II)I", remap = false, cancellable = true, require = 0)
    private void replaceTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        if (meta == 3) {
            cir.setReturnValue(TextureListener.cobblestone);
        }
    }
}
