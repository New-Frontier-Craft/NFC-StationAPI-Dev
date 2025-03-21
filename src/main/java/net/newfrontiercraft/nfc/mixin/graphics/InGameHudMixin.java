package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    @Shadow private Minecraft minecraft;

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;isInFluid(Lnet/minecraft/block/material/Material;)Z"))
    private boolean nfcBaseTick(boolean original){
        if(minecraft.player.isInFluid(Materials.oil)){
            return true;
        }
        return original;
    }
}
