package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.player.PlayerInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlayerInventory.class)
public interface PlayerInventoryAccessor {
    @Invoker
    int invokeIndexOf(int itemId);
}
