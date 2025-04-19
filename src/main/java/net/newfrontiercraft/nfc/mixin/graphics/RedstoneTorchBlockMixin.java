package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Random;

@Mixin(RedstoneTorchBlock.class)
public class RedstoneTorchBlockMixin {
    @WrapOperation(
            method = "randomDisplayTick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;addParticle(Ljava/lang/String;DDDDDD)V"
            )
    )
    public void nfcSlabTorchAddParticle(World world, String name, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Operation<Void> original, World world2, int x2, int y2, int z2, Random random){
        int belowId = world.getBlockId(x2, y2 - 1, z2);
        double finalY = y;
        if (belowId == Block.SLAB.id) {
            finalY -= 0.5D;
        } else if (belowId != 0) {
            Block belowBlock = Block.BLOCKS[belowId];
            if (belowBlock instanceof LazySlabTemplate) {
                BlockState slabBlockState = world.getBlockState(x2, y2 - 1, z2);
                if(slabBlockState.contains(LazySlabTemplate.ROTATIONS)){
                    int rotation = slabBlockState.get(LazySlabTemplate.ROTATIONS);
                    if (rotation == 0) {
                        finalY -= 0.5D;
                    }
                }
            }
        }
        original.call(world, name, x, finalY, z, velocityX, velocityY, velocityZ);
    }
}
