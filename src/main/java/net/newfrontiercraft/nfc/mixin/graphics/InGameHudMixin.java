package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.util.ScreenScaler;
import net.newfrontiercraft.nfc.events.init.Materials;
import net.newfrontiercraft.nfc.item.TelescopeItem;
import net.newfrontiercraft.nfc.utils.InGameHudAdditions;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin implements InGameHudAdditions {
    @Shadow private Minecraft minecraft;

    @ModifyExpressionValue(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ClientPlayerEntity;isInFluid(Lnet/minecraft/block/material/Material;)Z"))
    private boolean nfcBaseTick(boolean original){
        if(minecraft.player.isInFluid(Materials.oil)){
            return true;
        }
        return original;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerInventory;getArmorStack(I)Lnet/minecraft/item/ItemStack;", ordinal = 0, shift = At.Shift.BEFORE))
    private void nfcRenderTelescopeVision(float screenOpen, boolean mouseX, int mouseY, int par4, CallbackInfo ci){
        if(!TelescopeItem.isScoping()){
            return;
        }
        if(!TelescopeItem.hasTelescope()){
            TelescopeItem.setScoping(false);
            return;
        }
        ScreenScaler var5 = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        renderTelescopeVision(var6, var7);
    }

    private void renderTelescopeVision(int i, int j) {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/textures/misc/black.png"));
        Tessellator tessellator = Tessellator.INSTANCE;
        tessellator.startQuads();
        tessellator.vertex(0.0D, (double)j, -90.0D, 0.0D, 1.0D);
        tessellator.vertex((double)(i / 2 - j / 2), (double)j, -90.0D, 1.0D, 1.0D);
        tessellator.vertex((double)(i / 2 - j / 2), 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.vertex(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        tessellator.startQuads();
        tessellator.vertex((double)(i / 2 + j / 2), (double)j, -90.0D, 0.0D, 1.0D);
        tessellator.vertex((double)i, (double)j, -90.0D, 1.0D, 1.0D);
        tessellator.vertex((double)i, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.vertex((double)(i / 2 + j / 2), 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.minecraft.textureManager.getTextureId("/assets/nfc/stationapi/textures/misc/telescopeoverlay.png"));
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.startQuads();
        tessellator.vertex((double)(i / 2 - j / 2), (double)j, -90.0D, 0.0D, 1.0D);
        tessellator.vertex((double)(i / 2 + j / 2), (double)j, -90.0D, 1.0D, 1.0D);
        tessellator.vertex((double)(i / 2 + j / 2), 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.vertex((double)(i / 2 - j / 2), 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void renderTelescopeBypass(float tickDelta) {
        if(!TelescopeItem.isScoping()){
            return;
        }
        if(!TelescopeItem.hasTelescope()){
            TelescopeItem.setScoping(false);
            return;
        }
        ScreenScaler var5 = new ScreenScaler(this.minecraft.options, this.minecraft.displayWidth, this.minecraft.displayHeight);
        int var6 = var5.getScaledWidth();
        int var7 = var5.getScaledHeight();
        renderTelescopeVision(var6, var7);
        this.minecraft.gameRenderer.setupHudRender();
        GL11.glEnable(GL11.GL_BLEND);
        this.renderTelescopeVision(var6, var7);
        GL11.glDisable(GL11.GL_BLEND);
    }
}
