package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.GrassBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import net.newfrontiercraft.nfc.events.init.TextureListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GrassBlock.class)
public class GrassBlockTextureMixin {
    @Inject(at = @At("HEAD"), method = "getTextureId", remap = false, cancellable = true, require = 0)
    private void replaceTexture(BlockView blockView, int x, int y, int z, int side, CallbackInfoReturnable<Integer> cir) {
        if (side > 1) {
            Material topMaterial = blockView.getMaterial(x, y + 1, z);
            if (topMaterial != Material.SNOW_LAYER && topMaterial != Material.SNOW_BLOCK) {
                cir.setReturnValue(TextureListener.grassBlockSide);
            } else {
                cir.setReturnValue(TextureListener.grassBlockSideSnowy);
            }
        }
    }
}
