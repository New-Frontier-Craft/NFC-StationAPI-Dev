package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(World world) {
        super(world);
    }

    @Inject(at = @At("TAIL"), method = "isOnLadder", cancellable = true)
    private void scaffoldingMovement(CallbackInfoReturnable<Boolean> cir){
        int x = MathHelper.floor(this.x);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z);

        int id = this.world.getBlockId(x + 1, y, z);
        if(id == BlockListener.scaffoldBlock.id){
            cir.setReturnValue(true);
        }
        id = this.world.getBlockId(x - 1, y, z);
        if(id == BlockListener.scaffoldBlock.id){
            cir.setReturnValue(true);
        }
        id = this.world.getBlockId(x, y, z + 1);
        if(id == BlockListener.scaffoldBlock.id){
            cir.setReturnValue(true);
        }
        id = this.world.getBlockId(x, y, z - 1);
        if(id == BlockListener.scaffoldBlock.id){
            cir.setReturnValue(true);
        }
    }
}
