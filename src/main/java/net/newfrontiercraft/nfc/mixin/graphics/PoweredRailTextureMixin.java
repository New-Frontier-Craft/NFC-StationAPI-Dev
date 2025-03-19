package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.Block;
import net.minecraft.block.RailBlock;
import net.minecraft.block.material.Material;
import net.newfrontiercraft.nfc.events.init.TextureListener;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RailBlock.class)
public abstract class PoweredRailTextureMixin extends Block {
    @Shadow @Final private boolean alwaysStraight;

    public PoweredRailTextureMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(at = @At("HEAD"), method = "getTexture", cancellable = true, require = 0)
    private void replaceTexture(int side, int meta, CallbackInfoReturnable<Integer> cir) {
        if (alwaysStraight) {
            if (id == Block.POWERED_RAIL.id) {
                if ((meta & 8) == 0) {
                    cir.setReturnValue(TextureListener.poweredRail);
                } else {
                    cir.setReturnValue(TextureListener.poweredRailActive);
                }
            }
        }
    }
}
