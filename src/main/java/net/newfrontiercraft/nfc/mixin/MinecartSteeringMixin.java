package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.RailBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecartEntity.class)
public abstract class MinecartSteeringMixin extends Entity {

    public MinecartSteeringMixin(World world) {
        super(world);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void handleSteering(CallbackInfo ci) {
        if (this.world.isRemote) {
            return;
        }
        int flooredX = MathHelper.floor(this.x);
        int flooredY = MathHelper.floor(this.y);
        int flooredZ = MathHelper.floor(this.z);
        if (RailBlock.isRail(this.world, flooredX, flooredY - 1, flooredZ)) {
            --flooredY;
        }
        int currentBlockId = this.world.getBlockId(flooredX, flooredY, flooredZ);
        if (!RailBlock.isRail(currentBlockId)) {
            return;
        }
        if (passenger == null) {
            return;
        }
        if (passenger.isSneaking()) {
            velocityX *= 0.9;
            velocityZ *= 0.9;
        } else if (velocityX < 0.1 && velocityX > -0.1 && velocityZ < 0.1 && velocityZ > -0.1) {
            velocityX += passenger.velocityX * 0.6D;
            velocityZ += passenger.velocityZ * 0.6D;
        }
    }
}
