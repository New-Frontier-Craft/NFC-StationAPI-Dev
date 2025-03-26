package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SlabBlock.class)
public class SlabMergingMixin {

    @Inject(at = @At("HEAD"), method = "onPlaced", cancellable = true)
    private void changeMergingLogic(World world, int x, int y, int z, CallbackInfo ci) {
        int blockId = world.getBlockId(x, y - 1, z);
        int selfMeta = world.getBlockMeta(x, y, z);
        if (selfMeta == 0) {
            return;
        }
        int belowMeta = world.getBlockMeta(x, y - 1, z);
        if (selfMeta == belowMeta) {
            if (blockId == Block.SLAB.id) {
                world.setBlock(x, y, z, 0);
                switch (selfMeta) {
                    case 1:
                        world.setBlock(x, y - 1, z, Block.SANDSTONE.id, selfMeta);
                        break;
                    case 2:
                        world.setBlock(x, y - 1, z, Block.PLANKS.id, selfMeta);
                        break;
                    case 3:
                        world.setBlock(x, y - 1, z, Block.COBBLESTONE.id, selfMeta);
                        break;
                }
                ci.cancel();
            }
        }
    }
}
