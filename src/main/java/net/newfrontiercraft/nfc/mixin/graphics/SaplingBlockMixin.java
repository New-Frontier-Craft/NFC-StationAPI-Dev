package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.newfrontiercraft.nfc.events.init.TextureListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SaplingBlock.class)
public class SaplingBlockMixin {
    @Inject(method = "getTexture", at = @At("HEAD"), cancellable = true)
    void nfcGetTexture(int side, int meta, CallbackInfoReturnable<Integer> cir){
        int saplingMeta = meta & 3;
        if (saplingMeta == 0 && Block.class.cast(this).id == Block.SAPLING.id) {
            cir.setReturnValue(TextureListener.oakSaplingTexture);
        }
    }
}
