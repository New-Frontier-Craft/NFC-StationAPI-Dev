package net.newfrontiercraft.nfc.mixin;

import net.minecraft.client.render.block.BlockRenderManager;
import net.newfrontiercraft.nfc.events.init.TextureListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BlockRenderManager.class)
public class GrassOverlayMixin {

    @ModifyConstant(method = "renderSmooth", constant = {
            @Constant(intValue = 38)
    }, require = 0)
    private int replaceOverlayIndex(int constant) {
        return TextureListener.grassBlockSideOverlay;
    }

    @ModifyConstant(method = "renderFlat", constant = {
            @Constant(intValue = 38)
    }, require = 0)
    private int replaceFlatOverlayIndex(int constant) {
        return TextureListener.grassBlockSideOverlay;
    }
}
