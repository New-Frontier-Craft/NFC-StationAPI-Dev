package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.particle.ParticleManager;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.block.CreativeBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleManager.class)
public class ParticleClearingMixin {
    @Inject(at = @At("HEAD"), method = "setWorld", require = 0)
    private void clearCreativeParticles(World world, CallbackInfo ci) {
        CreativeBlock.creativeBlockParticles.clear();
    }
}
