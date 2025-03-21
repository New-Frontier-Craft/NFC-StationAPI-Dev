package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {
    @Shadow private Minecraft minecraft;

    @Shadow protected abstract void renderUnderwaterOverlay(float delta);

    @Inject(method = "renderScreenOverlays", at = @At("TAIL"), cancellable = true)
    void nfcRenderScreenOverlays(float delta, CallbackInfo ci){
        if(this.minecraft.player.isInFluid(Materials.oil)) {
            int textureId = this.minecraft.textureManager.getTextureId("assets/nfc/stationapi/textures/misc/oil.png");
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
            this.renderUnderwaterOverlay(delta);
        }
    }
}
