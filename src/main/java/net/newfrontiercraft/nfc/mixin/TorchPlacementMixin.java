package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.TorchBlock;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TorchBlock.class)
public class TorchPlacementMixin {

    @Inject(at = @At("HEAD"), method = "canPlaceAt", cancellable = true)
    private void allowSlabPlacement(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        int belowId = world.getBlockId(x, y - 1, z);
        if (belowId == Block.SLAB.id) {
            cir.setReturnValue(true);
        } else if (belowId != 0) {
            Block belowBlock = Block.BLOCKS[belowId];
            if (!(belowBlock instanceof LazySlabTemplate)) {
                return;
            }
            int rotation = world.getBlockState(x, y - 1, z).get(LazySlabTemplate.ROTATIONS);
            if (rotation < 2) {
                cir.setReturnValue(true);
            }
        }
    }
}
