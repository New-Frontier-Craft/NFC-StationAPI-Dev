package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.material.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Material.class)
public interface MaterialInvoker {
    @Invoker
    Material invokeSetDestroyPistonBehavior();
}
