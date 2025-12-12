package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.PaintingEntityRenderer;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.modificationstation.stationapi.api.client.StationRenderAPI;
import net.modificationstation.stationapi.api.client.texture.SpriteAtlasTexture;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlas;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.newfrontiercraft.nfc.registry.PaintingRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PaintingEntityRenderer.class)
public abstract class PaintingEntityRendererMixin {
    @Shadow protected abstract void applyBrightness(PaintingEntity painting, float u, float v);

    @WrapOperation(method = "render(Lnet/minecraft/entity/decoration/painting/PaintingEntity;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PaintingEntityRenderer;bindTexture(Ljava/lang/String;)V"))
    void bindTexture(PaintingEntityRenderer instance, String s, Operation<Void> original, PaintingEntity paintingEntity, double d, double e, double f, float g, float h){
        if(PaintingRegistry.INSTANCE.isNfcPaining(paintingEntity.variant.id)){
            StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE).bindTexture();
        }
        else {
            original.call(instance, s);
        }
    }

    @WrapOperation(method = "render(Lnet/minecraft/entity/decoration/painting/PaintingEntity;DDDFF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/entity/PaintingEntityRenderer;renderPainting(Lnet/minecraft/entity/decoration/painting/PaintingEntity;IIII)V"))
    void renderPainting(PaintingEntityRenderer instance, PaintingEntity entity, int width, int height, int u, int v, Operation<Void> original){
        if(PaintingRegistry.INSTANCE.isNfcPaining(entity.variant.id)){
            renderNfcPainting(entity, width, height, PaintingRegistry.INSTANCE.sprites.get(entity.variant.id));
        } else {
            original.call(instance, entity, width, height, u, v);
        }
    }
    @Unique
    private void renderNfcPainting(PaintingEntity painting, int width, int height, Atlas.Sprite sprite) {

        SpriteAtlasTexture atlas = StationRenderAPI.getBakedModelManager().getAtlas(Atlases.GAME_ATLAS_TEXTURE);
        float uScale = (float) 1 / atlas.getWidth();
        float vScale = (float) 1 / atlas.getHeight();

        double frontSpriteStartU = sprite.getStartU();
        double frontSpriteEndU = sprite.getEndU();
        double frontSpriteStartV = sprite.getStartV();
        double frontSpriteEndV = sprite.getEndV();

        double backSpriteStartU = PaintingRegistry.INSTANCE.paintingBackSprite.getStartU();
        double backSpriteEndU = PaintingRegistry.INSTANCE.paintingBackSprite.getEndU();
        double backSpriteStartV = PaintingRegistry.INSTANCE.paintingBackSprite.getStartV();
        double backSpriteEndV = PaintingRegistry.INSTANCE.paintingBackSprite.getEndV();

        int sx = width / 16;
        int sy = height / 16;

        double segU = (frontSpriteEndU - frontSpriteStartU) / sx;
        double segV = (frontSpriteEndV - frontSpriteStartV) / sy;

        float var6 = (float)(-width) / 2.0F;
        float var7 = (float)(-height) / 2.0F;
        float var8 = -0.5F;
        float var9 = 0.5F;

        for(int sectionX = 0; sectionX < width / 16; ++sectionX) {
            for(int sectionY = 0; sectionY < height / 16; ++sectionY) {
                float var12 = var6 + (float)((sectionX + 1) * 16);
                float var13 = var6 + (float)(sectionX * 16);
                float var14 = var7 + (float)((sectionY + 1) * 16);
                float var15 = var7 + (float)(sectionY * 16);
                this.applyBrightness(painting, (var12 + var13) / 2.0F, (var14 + var15) / 2.0F);

                double frontStartU = frontSpriteEndU - segU * (sectionX + 1);
                double frontEndU = frontSpriteEndU - segU * sectionX;
                double frontStartV = frontSpriteEndV - segV * (sectionY + 1);
                double frontEndV = frontSpriteEndV - segV * sectionY;

                // painting back
                double var20 = backSpriteStartU;
                double var21 = backSpriteStartU + (16 * uScale);
                double var22 = backSpriteStartV;
                double var23 = backSpriteStartV + (16 * vScale);
                // axis 1
                double var24 = backSpriteStartU;
                double var25 = backSpriteStartU + (16 * uScale);
                double var26 = backSpriteStartV + (0.5F * vScale);
                double var27 = backSpriteStartV + (0.5F * vScale);
                // axis 2
                double var28 = backSpriteStartU + (0.5F * vScale);
                double var29 = backSpriteStartU + (0.5F * vScale);
                double var30 = backSpriteStartV;
                double var31 = backSpriteStartV + (16 * uScale);

                Tessellator var32 = Tessellator.INSTANCE;
                var32.startQuads();
                var32.normal(0.0F, 0.0F, -1.0F);
                var32.vertex(var12, var15, var8, frontStartU, frontEndV);
                var32.vertex(var13, var15, var8, frontEndU, frontEndV);
                var32.vertex(var13, var14, var8, frontEndU, frontStartV);
                var32.vertex(var12, var14, var8, frontStartU, frontStartV);
                var32.normal(0.0F, 0.0F, 1.0F);
                var32.vertex(var12, var14, var9, var20, var22);
                var32.vertex(var13, var14, var9, var21, var22);
                var32.vertex(var13, var15, var9, var21, var23);
                var32.vertex(var12, var15, var9, var20, var23);
                var32.normal(0.0F, -1.0F, 0.0F);
                var32.vertex(var12, var14, var8, var24, var26);
                var32.vertex(var13, var14, var8, var25, var26);
                var32.vertex(var13, var14, var9, var25, var27);
                var32.vertex(var12, var14, var9, var24, var27);
                var32.normal(0.0F, 1.0F, 0.0F);
                var32.vertex(var12, var15, var9, var24, var26);
                var32.vertex(var13, var15, var9, var25, var26);
                var32.vertex(var13, var15, var8, var25, var27);
                var32.vertex(var12, var15, var8, var24, var27);
                var32.normal(-1.0F, 0.0F, 0.0F);
                var32.vertex(var12, var14, var9, var29, var30);
                var32.vertex(var12, var15, var9, var29, var31);
                var32.vertex(var12, var15, var8, var28, var31);
                var32.vertex(var12, var14, var8, var28, var30);
                var32.normal(1.0F, 0.0F, 0.0F);
                var32.vertex(var13, var14, var8, var29, var30);
                var32.vertex(var13, var15, var8, var29, var31);
                var32.vertex(var13, var15, var9, var28, var31);
                var32.vertex(var13, var14, var9, var28, var30);
                var32.draw();
            }
        }

    }
}
