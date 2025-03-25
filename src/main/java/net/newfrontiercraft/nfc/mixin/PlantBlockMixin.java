package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.PlantBlock;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin {
    boolean isNfcSoil(int id){
        if(id == BlockListener.alphaGrass.id){
            return true;
        }
        return false;
    }

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    void nfcCanPlantOnTop(int id, CallbackInfoReturnable<Boolean> cir) {
        if(this.isNfcSoil(id)){
            cir.setReturnValue(true);
        }
    }
}
