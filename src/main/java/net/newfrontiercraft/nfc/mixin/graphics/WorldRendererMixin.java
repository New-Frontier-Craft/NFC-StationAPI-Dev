package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.WorldRenderer;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow private Minecraft client;

    @Inject(method = "reload", at = @At("HEAD"))
    void nfcReload(CallbackInfo ci){
        BlockListener.alphaLeaves.setFancyGraphics(this.client.options.fancyGraphics);
    }
}
