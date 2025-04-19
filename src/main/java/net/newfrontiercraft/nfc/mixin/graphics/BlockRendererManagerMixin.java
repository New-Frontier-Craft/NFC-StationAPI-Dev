package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldRegion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;
import net.newfrontiercraft.nfc.utils.BlockWithItemRenderBounds;
import net.newfrontiercraft.nfc.utils.FenceConnection;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockRenderManager.class)
public class BlockRendererManagerMixin{
    @Shadow private BlockView blockView;

    @Inject(method = "renderLadder", at = @At("TAIL"), cancellable = true)
    public void nfcRenderLadderBack(Block block, int x, int y, int z, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 6) int meta, @Local(ordinal = 0) double var10, @Local(ordinal = 1) double var12, @Local(ordinal = 2) double var14, @Local(ordinal = 3) double var16) {
        Tessellator tesselator = Tessellator.INSTANCE;

        float var19 = 0.0F;
        float offset = 0.05F;

        if (meta == 5) {
            tesselator.vertex(x + offset, y - var19, z + 1 - var19, var12, var16);
            tesselator.vertex(x + offset, y + 1 + var19, z + 1 - var19, var12, var14);
            tesselator.vertex(x + offset, y + 1 + var19, z + var19, var10, var14);
            tesselator.vertex(x + offset, y - var19, z + var19, var10, var16);
        }
        if (meta == 4) {
            tesselator.vertex(x + 1 - offset, y - var19, z + var19, var12, var16);
            tesselator.vertex(x + 1 - offset, y + 1 + var19, z + var19, var12, var14);
            tesselator.vertex(x + 1 - offset, y + 1 + var19, z + 1 - var19, var10, var14);
            tesselator.vertex(x + 1 - offset, y - var19, z + 1 - var19, var10, var16);
        }
        if (meta == 3) {
            tesselator.vertex(x + var19, y - var19, z + offset, var12, var16);
            tesselator.vertex(x + var19, y + 1 + var19, z + offset, var12, var14);
            tesselator.vertex(x + 1 - var19, y + 1 + var19, z + offset, var10, var14);
            tesselator.vertex(x + 1 - var19, y - var19, z + offset, var10, var16);
        }
        if (meta == 2) {
            tesselator.vertex(x + var19, y + 1 + var19, z + 1 - offset, var10, var14);
            tesselator.vertex(x + var19, y - var19, z + 1 - offset, var10, var16);
            tesselator.vertex(x + 1 - var19, y - var19, z + 1 - offset, var12, var16);
            tesselator.vertex(x + 1 - var19, y + 1 + var19, z + 1 - offset, var12, var14);
        }

        cir.setReturnValue(true);
    }

    @WrapOperation(
            method = "renderFence",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/BlockView;getBlockId(III)I")
    )
    public int nfcGetBlockId(BlockView instance, int x, int y, int z, Operation<Integer> original){
        boolean isConnectedState = instance.getBlockMeta(x, y, z) == 1;
        if(((FenceConnection)Block.FENCE).connectFenceAt(instance, x, y, z, isConnectedState)){
            return Block.FENCE.id;
        }
        return original.call(instance, x, y, z);
    }

    @Inject(method = "render(Lnet/minecraft/block/Block;IF)V", at = @At("HEAD"))
    public void nfcInventoryTranslucencyHead(Block block, int metadata, float brightness, CallbackInfo ci) {
        if(!block.isOpaque()){
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        }
    }

    @Inject(method = "render(Lnet/minecraft/block/Block;IF)V", at = @At("TAIL"))
    public void nfcInventoryTranslucencyTail(Block block, int metadata, float brightness, CallbackInfo ci) {
        if(!block.isOpaque()){
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    @Inject(method = "render(Lnet/minecraft/block/Block;IF)V", at = @At("HEAD"))
    public void nfcBlockWithItemRenderBounds(Block block, int metadata, float brightness, CallbackInfo ci) {
        if(block instanceof BlockWithItemRenderBounds blockWithItemRenderBounds){
            blockWithItemRenderBounds.setBlockBoundsForItemRender();
        }
    }

    @WrapOperation(
        method = "renderTorch",
        at = @At(
                value = "INVOKE",
                target = "Lnet/minecraft/client/render/block/BlockRenderManager;renderTiltedTorch(Lnet/minecraft/block/Block;DDDDD)V",
                ordinal = 4
        )
    )
    public void nfcRenderSlabTorch(BlockRenderManager instance, Block block, double x, double y, double z, double xTilt, double zTilt, Operation<Void> original, Block block2, int x2, int y2, int z2){
        double finalY = y;

        int belowId = blockView.getBlockId(x2, y2 - 1, z2);
        if (belowId == Block.SLAB.id) {
            finalY -= 0.5D;
        } else if (belowId != 0) {
            Block belowBlock = Block.BLOCKS[belowId];
            if (belowBlock instanceof LazySlabTemplate && blockView instanceof WorldRegion worldRegion) {
                BlockState slabBlockState = worldRegion.getBlockState(x2, y2 - 1, z2);
                if(slabBlockState.contains(LazySlabTemplate.ROTATIONS)){
                    int rotation = slabBlockState.get(LazySlabTemplate.ROTATIONS);
                    if (rotation == 0) {
                        finalY -= 0.5D;
                    }
                }
            }
        }
        original.call(instance, block, x, finalY, z, xTilt, zTilt);
    }
}
