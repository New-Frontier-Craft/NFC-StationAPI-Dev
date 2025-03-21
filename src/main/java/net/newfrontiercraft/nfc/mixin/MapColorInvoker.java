package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.MapColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(MapColor.class)
public interface MapColorInvoker {
    @Invoker("<init>")
    static MapColor invokeMapColor(int id, int color) {
        return null;
    }
}
