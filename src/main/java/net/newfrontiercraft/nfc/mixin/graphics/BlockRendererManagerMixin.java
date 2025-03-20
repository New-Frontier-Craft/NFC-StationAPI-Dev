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

    @Inject(method = "renderLadder", at = @At("HEAD"), cancellable = true)
    public void renderLadder(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        Tessellator var5 = Tessellator.INSTANCE;
        int var6 = block.getTexture(0);
        if (this.textureOverride >= 0) {
            var6 = this.textureOverride;
        }

        float var7 = block.getLuminance(this.blockView, x, y, z);
        var5.color(var7, var7, var7);

        Atlas.Sprite sprite = block.getAtlas().getTexture(var6);

        int var8 = (var6 & 15) << 4;
        int var9 = var6 & 240;
        double var10 = sprite.getStartU();
        double var12 = sprite.getStartV();
        double var14 = sprite.getEndU();
        double var16 = sprite.getEndV();
        int var18 = this.blockView.getBlockMeta(x, y, z);
        float var19 = 0.0F;
        float var20 = 0.05F;

        if (var18 == 5) {
            var5.vertex((double)((float)x + var20), (double)((float)(y + 1) + var19), (double)((float)(z + 1) + var19), var10, var14);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 0) - var19), (double)((float)(z + 1) + var19), var10, var16);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 0) - var19), (double)((float)(z + 0) - var19), var12, var16);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 1) + var19), (double)((float)(z + 0) - var19), var12, var14);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var19), var12, var16);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var19), var12, var14);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 1) + var19), (double)((float)(z + 0) + var19), var10, var14);
            var5.vertex((double)((float)x + var20), (double)((float)(y + 0) - var19), (double)((float)(z + 0) + var19), var10, var16);
        }

        if (var18 == 4) {
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 0) - var19), (double)((float)(z + 1) + var19), var12, var16);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 1) + var19), (double)((float)(z + 1) + var19), var12, var14);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 1) + var19), (double)((float)(z + 0) - var19), var10, var14);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 0) - var19), (double)((float)(z + 0) - var19), var10, var16);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 0) - var19), (double)((float)(z + 0) + var19), var12, var16);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 1) + var19), (double)((float)(z + 0) + var19), var12, var14);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var19), var10, var14);
            var5.vertex((double)((float)(x + 1) - var20), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var19), var10, var16);
        }

        if (var18 == 3) {
            var5.vertex((double)((float)(x + 1) + var19), (double)((float)(y + 0) - var19), (double)((float)z + var20), var12, var16);
            var5.vertex((double)((float)(x + 1) + var19), (double)((float)(y + 1) + var19), (double)((float)z + var20), var12, var14);
            var5.vertex((double)((float)(x + 0) - var19), (double)((float)(y + 1) + var19), (double)((float)z + var20), var10, var14);
            var5.vertex((double)((float)(x + 0) - var19), (double)((float)(y + 0) - var19), (double)((float)z + var20), var10, var16);
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 0) - var19), (double)((float)z + var20), var12, var16);
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 1) + var19), (double)((float)z + var20), var12, var14);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 1) + var19), (double)((float)z + var20), var10, var14);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 0) - var19), (double)((float)z + var20), var10, var16);
        }

        if (var18 == 2) {
            var5.vertex((double)((float)(x + 1) + var19), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var20), var10, var14);
            var5.vertex((double)((float)(x + 1) + var19), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var20), var10, var16);
            var5.vertex((double)((float)(x + 0) - var19), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var20), var12, var16);
            var5.vertex((double)((float)(x + 0) - var19), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var20), var12, var14);
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var20), var10, var14);
            var5.vertex((double)((float)(x + 0) + var19), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var20), var10, var16);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 0) - var19), (double)((float)(z + 1) - var20), var12, var16);
            var5.vertex((double)((float)(x + 1) - var19), (double)((float)(y + 1) + var19), (double)((float)(z + 1) - var20), var12, var14);
        }

        cir.setReturnValue(true);
    }
}
