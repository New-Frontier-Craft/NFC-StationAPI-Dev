package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.LiquidBlock;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LiquidBlock.class)
public interface LiquidBlockInvoker {
    @Invoker
    Vec3d invokeGetFlow(BlockView world, int x, int y, int z);
}
