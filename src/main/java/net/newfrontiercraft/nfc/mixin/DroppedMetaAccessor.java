package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Block.class)
public interface DroppedMetaAccessor {

    @Invoker("getDroppedItemMeta")
    int invokeDroppedItemMeta(int meta);
}
