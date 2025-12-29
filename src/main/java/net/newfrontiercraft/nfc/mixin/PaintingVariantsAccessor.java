package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.decoration.painting.PaintingVariants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PaintingVariants.class)
public interface PaintingVariantsAccessor {
    @Invoker("<init>")
    static PaintingVariants newPaintingVariants(String enumName, int enumOrdinal, String id, int width, int height, int textureOffsetX, int textureOffsetY) {
        throw new AssertionError();
    }
}
