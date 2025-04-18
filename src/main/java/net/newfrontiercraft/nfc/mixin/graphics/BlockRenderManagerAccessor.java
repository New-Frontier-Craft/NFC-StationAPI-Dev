package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.render.block.BlockRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(BlockRenderManager.class)
public interface BlockRenderManagerAccessor {
    @Accessor
    void setEastFaceRotation(int rotation);
    @Accessor
    void setWestFaceRotation(int rotation);
    @Accessor
    void setSouthFaceRotation(int rotation);
    @Accessor
    void setNorthFaceRotation(int rotation);
    @Accessor
    void setTopFaceRotation(int rotation);
    @Accessor
    void setBottomFaceRotation(int rotation);
}
