package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockRenderManager.class)
public class BlockRendererManagerMixin{
    @Shadow private int textureOverride;

    @Shadow private BlockView blockView;

    @Inject(method = "renderLadder", at = @At("TAIL"), cancellable = true)
    public void renderLadder(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 6) int var18, @Local(ordinal = 0) double var10, @Local(ordinal = 1) double var12, @Local(ordinal = 2) double var14, @Local(ordinal = 3) double var16) {
        Tessellator var5 = Tessellator.INSTANCE;

        float var19 = 0.0F;
        float var20 = 0.05F;

        if (var18 == 5) {
            var5.vertex((double)((float)x + var20), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var19), var12, var16);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var19), var12, var14);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 1) + var19), (double)((float)(z + 0) + var19), var10, var14);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 0) - var19), (double)((float)(z + 0) + var19), var10, var16);
        }
        if (var18 == 4) {
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 0) - var19), (double)((float)(z + 0) + var19), var12, var16);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 1) + var19), (double)((float)(z + 0) + var19), var12, var14);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var19), var10, var14);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var19), var10, var16);
        }
        if (var18 == 3) {
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 0) - var19), (double)((float)z + var20), var12, var16);
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 1) + var19), (double)((float)z + var20), var12, var14);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 1) + var19), (double)((float)z + var20), var10, var14);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 0) - var19), (double)((float)z + var20), var10, var16);
        }
        if (var18 == 2) {
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var20), var10, var14);
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var20), var10, var16);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var20), var12, var16);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var20), var12, var14);
        }

        cir.setReturnValue(true);
    }
}
