package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.TorchBlock;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
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

    @Inject(at = @At("HEAD"), method = "onPlaced(Lnet/minecraft/world/World;III)V", cancellable = true)
    private void rotateTorchCorrectly(World world, int x, int y, int z, CallbackInfo ci) {
        int belowId = world.getBlockId(x, y - 1, z);
        if (belowId == Block.SLAB.id) {
            world.setBlockMeta(x, y, z, 5);
            ci.cancel();
        } else if (belowId != 0) {
            Block belowBlock = Block.BLOCKS[belowId];
            if (!(belowBlock instanceof LazySlabTemplate)) {
                return;
            }
            int rotation = world.getBlockState(x, y - 1, z).get(LazySlabTemplate.ROTATIONS);
            if (rotation < 2) {
                world.setBlockMeta(x, y, z, 5);
                ci.cancel();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "neighborUpdate", cancellable = true)
    private void rotateTorchCorrectly(World world, int x, int y, int z, int id, CallbackInfo ci) {
        if (world.getBlockMeta(x, y, z) != 5) {
            return;
        }
        int belowId = world.getBlockId(x, y - 1, z);
        if (belowId == Block.SLAB.id) {
            ci.cancel();
        } else if (belowId != 0) {
            Block belowBlock = Block.BLOCKS[belowId];
            if (!(belowBlock instanceof LazySlabTemplate)) {
                return;
            }
            int rotation = world.getBlockState(x, y - 1, z).get(LazySlabTemplate.ROTATIONS);
            if (rotation < 2) {
                ci.cancel();
            }
        }
    }

    @WrapOperation(
            method = "raycast",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/TorchBlock;setBoundingBox(FFFFFF)V",
                    ordinal = 4
            )
    )
    public void nfcSlabTorchSetBoundingBox(TorchBlock instance, float minX, float minY, float minZ, float maxX, float maxY, float maxZ, Operation<Void> original, World world, int x, int y, int z, Vec3d startPos, Vec3d endPos){
        int belowId = world.getBlockId(x, y - 1, z);
        float offset = 0;
        if (belowId == Block.SLAB.id) {
            offset = 0.5F;
        } else if (belowId != 0) {
            Block belowBlock = Block.BLOCKS[belowId];
            if (belowBlock instanceof LazySlabTemplate) {
                BlockState slabBlockState = world.getBlockState(x, y - 1, z);
                if(slabBlockState.contains(LazySlabTemplate.ROTATIONS)){
                    int rotation = slabBlockState.get(LazySlabTemplate.ROTATIONS);
                    if (rotation == 0) {
                        offset = 0.5F;
                    }
                }
            }
        }
        original.call(instance, minX, minY - offset, minZ, maxX, maxY - offset, maxZ);
    }
}
