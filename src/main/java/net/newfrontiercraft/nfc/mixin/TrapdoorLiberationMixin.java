package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TrapdoorBlock.class)
public class TrapdoorLiberationMixin {

    @Inject(at = @At("HEAD"), method = "canPlaceAt", cancellable = true)
    private void removePlacementRestriction(World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }

    @Inject(at = @At("HEAD"), method = "neighborUpdate", cancellable = true)
    private void reduceUpdates(World world, int x, int y, int z, int id, CallbackInfo ci) {
        if (!(id > 0 && Block.BLOCKS[id].canEmitRedstonePower())) {
            ci.cancel();
        }
    }
}
