package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Shadow public PlayerInventory inventory;

    @Inject(method = "getBlockBreakingSpeed", at = @At("HEAD"), cancellable = true)
    public void nfcGetBlockBreakingSpeed(Block block, CallbackInfoReturnable<Float> cir) {
        if(PlayerEntity.class.cast(this).isInFluid(Materials.oil)){
            float strenght = this.inventory.getStrengthOnBlock(block);
            cir.setReturnValue(strenght / 5.0F);
        }
    }

    @ModifyExpressionValue(method = "updateMovementStats", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;isInFluid(Lnet/minecraft/block/material/Material;)Z"))
    private boolean nfcUpdateMovementStats(boolean original){
        if(LivingEntity.class.cast(this).isInFluid(Materials.oil)){
            return true;
        }
        return original;
    }
}
