package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.Materials;
import net.newfrontiercraft.nfc.utils.CoilDamageCooldown;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements CoilDamageCooldown {
    @Unique int coilCooldown = 0;

    public LivingEntityMixin(World world) {
        super(world);
    }

    @Inject(at = @At("TAIL"), method = "isOnLadder", cancellable = true)
    private void scaffoldingMovement(CallbackInfoReturnable<Boolean> cir){
        int x = MathHelper.floor(this.x);
        int y = MathHelper.floor(this.boundingBox.minY);
        int z = MathHelper.floor(this.z);

        int id = this.world.getBlockId(x + 1, y, z);
        if(id == BlockListener.scaffoldBlock.id || id == Block.LADDER.id && this.world.getBlockMeta(x + 1, y, z) == 5){
            cir.setReturnValue(true);
        }
        id = this.world.getBlockId(x - 1, y, z);
        if(id == BlockListener.scaffoldBlock.id || id == Block.LADDER.id && this.world.getBlockMeta(x - 1, y, z) == 4){
            cir.setReturnValue(true);
        }
        id = this.world.getBlockId(x, y, z + 1);
        if(id == BlockListener.scaffoldBlock.id || id == Block.LADDER.id && this.world.getBlockMeta(x, y, z + 1) == 3){
            cir.setReturnValue(true);
        }
        id = this.world.getBlockId(x, y, z - 1);
        if(id == BlockListener.scaffoldBlock.id || id == Block.LADDER.id && this.world.getBlockMeta(x, y, z - 1) == 2){
            cir.setReturnValue(true);
        }
    }

    @ModifyExpressionValue(method = "baseTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;isInFluid(Lnet/minecraft/block/material/Material;)Z"))
    private boolean nfcBaseTick(boolean original){
        if(LivingEntity.class.cast(this).isInFluid(Materials.oil)){
            return true;
        }
        return original;
    }

    @Inject(method = "baseTick", at = @At("HEAD"))
    public void nfcBaseTick(CallbackInfo ci){
        if(this.coilCooldown > 0){
            this.coilCooldown--;
        }
    }

    @Override
    public int getCoilDamageCooldown() {
        return coilCooldown;
    }

    @Override
    public void setCoilDamageCooldown(int cooldown) {
        this.coilCooldown = cooldown;
    }
}
