package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow private Minecraft client;

    @ModifyVariable(method = "getFov", at = @At("STORE"), ordinal = 1)
    float nfcGetFov(float original){
        if(this.client.camera.isInFluid(Materials.oil)){
            return 55.0F;
        }
        return original;
    }

}
