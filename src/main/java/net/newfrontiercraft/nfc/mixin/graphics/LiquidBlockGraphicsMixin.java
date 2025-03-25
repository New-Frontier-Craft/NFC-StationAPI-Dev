package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.Block;
import net.minecraft.block.LiquidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.Materials;
import net.newfrontiercraft.nfc.mixin.LiquidBlockInvoker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LiquidBlock.class)
public abstract class LiquidBlockGraphicsMixin extends Block {
    public LiquidBlockGraphicsMixin(int i, Material material) {
        super(i, material);
    }

    @Inject(method = "getFlowingAngle", at = @At("HEAD"), cancellable = true)
    private static void nfcGetFlowingAngle(BlockView view, int x, int y, int z, Material material, CallbackInfoReturnable<Double> cir) {
        if(material == Materials.oil){
            Vec3d var5 = ((LiquidBlockInvoker) BlockListener.oilFlowing).invokeGetFlow(view, x, y, z);
            cir.setReturnValue(var5.x == (double)0.0F && var5.z == (double)0.0F ? (double)-1000.0F : Math.atan2(var5.z, var5.x) - (Math.PI / 2D));
        }
    }

    @Inject(method = "getColorMultiplier", at = @At("HEAD"), cancellable = true)
    public void nfcGetColorMultiplier(BlockView x, int y, int z, int par4, CallbackInfoReturnable<Integer> cir) {
        if(this.material == Materials.oil){
            cir.setReturnValue(0x211508);
        }
    }
}
