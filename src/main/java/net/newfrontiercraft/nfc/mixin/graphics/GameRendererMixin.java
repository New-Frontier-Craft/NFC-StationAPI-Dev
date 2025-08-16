package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.newfrontiercraft.nfc.events.init.Materials;
import net.newfrontiercraft.nfc.item.TelescopeItem;
import net.newfrontiercraft.nfc.utils.InGameHudAdditions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow private Minecraft client;

    @Inject(method = "getFov", at = @At("HEAD"), cancellable = true)
    void nfcGetFov(float par1, CallbackInfoReturnable<Float> cir){
        if(TelescopeItem.isScoping()){
            cir.setReturnValue(10F);
        }
    }


    @ModifyVariable(method = "getFov", at = @At("STORE"), ordinal = 1)
    float nfcGetFovOil(float original){
        if(this.client.camera.isInFluid(Materials.oil)){
            return 55.0F;
        }
        return original;
    }

    @Inject(method = "onFrameUpdate", at = @At(value = "INVOKE", target = "Ljava/lang/System;nanoTime()J", ordinal = 1))
    void nfcRenderTelescopeBypass(float tickDelta, CallbackInfo ci){
        if(this.client.options.hideHud && this.client.currentScreen == null){
            ((InGameHudAdditions)this.client.inGameHud).renderTelescopeBypass(tickDelta);
        }
    }

    @ModifyVariable(method = "onFrameUpdate", at = @At(value = "STORE"), ordinal = 1)
    float changeMouseSensitivity(float original){
        if(TelescopeItem.isScoping()){
            return original * 0.4f;
        }
        return original;
    }
}
