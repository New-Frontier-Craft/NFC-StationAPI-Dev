package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlabBlock.class)
public class SlabMergingMixin {

    @Inject(at = @At("HEAD"), method = "onPlaced", cancellable = true)
    private void changeMergingLogic(World world, int x, int y, int z, CallbackInfo ci) {
        ci.cancel();
    }
}
