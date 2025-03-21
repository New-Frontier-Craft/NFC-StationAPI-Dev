package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRenderManager.class)
public class BlockRendererManagerMixin{
    @Inject(method = "renderLadder", at = @At("TAIL"), cancellable = true)
    public void nfcRenderLadderBack(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 6) int meta, @Local(ordinal = 0) double var10, @Local(ordinal = 1) double var12, @Local(ordinal = 2) double var14, @Local(ordinal = 3) double var16) {
        Tessellator tesselator = Tessellator.INSTANCE;

        float var19 = 0.0F;
        float offset = 0.05F;

        if (meta == 5) {
            tesselator.vertex(x + offset, y - var19, z + 1 - var19, var12, var16);
            tesselator.vertex(x + offset, y + 1 + var19, z + 1 - var19, var12, var14);
            tesselator.vertex(x + offset, y + 1 + var19, z + var19, var10, var14);
            tesselator.vertex(x + offset, y - var19, z + var19, var10, var16);
        }
        if (meta == 4) {
            tesselator.vertex(x + 1 - offset, y - var19, z + var19, var12, var16);
            tesselator.vertex(x + 1 - offset, y + 1 + var19, z + var19, var12, var14);
            tesselator.vertex(x + 1 - offset, y + 1 + var19, z + 1 - var19, var10, var14);
            tesselator.vertex(x + 1 - offset, y - var19, z + 1 - var19, var10, var16);
        }
        if (meta == 3) {
            tesselator.vertex(x + var19, y - var19, z + offset, var12, var16);
            tesselator.vertex(x + var19, y + 1 + var19, z + offset, var12, var14);
            tesselator.vertex(x + 1 - var19, y + 1 + var19, z + offset, var10, var14);
            tesselator.vertex(x + 1 - var19, y - var19, z + offset, var10, var16);
        }
        if (meta == 2) {
            tesselator.vertex(x + var19, y + 1 + var19, z + 1 - offset, var10, var14);
            tesselator.vertex(x + var19, y - var19, z + 1 - offset, var10, var16);
            tesselator.vertex(x + 1 - var19, y - var19, z + 1 - offset, var12, var16);
            tesselator.vertex(x + 1 - var19, y + 1 + var19, z + 1 - offset, var12, var14);
        }

        cir.setReturnValue(true);
    }
}
