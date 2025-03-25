package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class LiquidBlockMixin extends Block {
    public LiquidBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "getTickRate", at = @At("HEAD"), cancellable = true)
    public void nfcGetTickRate(CallbackInfoReturnable<Integer> cir){
        if(this.material == Materials.oil){
            cir.setReturnValue(10);
        }
    }
}
