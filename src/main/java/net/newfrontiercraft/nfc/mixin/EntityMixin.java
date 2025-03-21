package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow public World world;

    @Shadow @Final public Box boundingBox;

    @Shadow private boolean firstTick;
    @Shadow public double velocityX;
    @Shadow public double velocityY;
    @Shadow public double velocityZ;
    @Shadow protected Random random;
    @Shadow protected float fallDistance;
    @Shadow public int fireTicks;
    boolean inOil = false;

    @Inject(method = "baseTick", at = @At("HEAD"))
    public void nfcBaseTick(CallbackInfo ci){
        boolean handleOilMovement = world.updateMovementInFluid(boundingBox.expand(0.0, -0.4F, 0.0).contract(0.001, 0.001, 0.001), Materials.oil, Entity.class.cast(this));
        if(handleOilMovement){
            if(!inOil && !firstTick){
                float f = MathHelper.sqrt(velocityX * velocityX * 0.2F + velocityY * velocityY + velocityZ * velocityZ * 0.2F) * 0.2F;
                if(f > 1.0F){
                    f = 1.0F;
                }
                world.playSound(Entity.class.cast(this), "random.splash", f, 1.0F + (random.nextFloat() - random.nextFloat()) * 0.4F);
            }
            fallDistance = 0.0F;
            this.inOil = true;
            fireTicks = 0;
        } else {
            this.inOil = false;
        }
    }

    @Inject(method = "isWet", at = @At("HEAD"), cancellable = true)
    public void nfcIsWet(CallbackInfoReturnable<Boolean> cir){
        if(inOil){
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isSubmergedInWater", at = @At("HEAD"), cancellable = true)
    public void nfcIsSubmergedInWater(CallbackInfoReturnable<Boolean> cir){
        if(inOil){
            cir.setReturnValue(true);
        }
    }

}
