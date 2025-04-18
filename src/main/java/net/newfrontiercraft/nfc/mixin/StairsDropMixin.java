package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(StairsBlock.class)
public abstract class StairsDropMixin extends Block {

    public StairsDropMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(at = @At("HEAD"), method = "dropStacks", cancellable = true)
    private void makeDropStacksNotAnnoying(World world, int x, int y, int z, int meta, float luck, CallbackInfo ci) {
        if (!world.isRemote) {
            int dropCount = this.getDroppedItemCount(world.random);
            for (int i = 0; i < dropCount; i++) {
                if (!(world.random.nextFloat() > luck)) {
                    int id = this.getDroppedItemId(meta, world.random);
                    if (id > 0) {
                        this.dropStack(world, x, y, z, new ItemStack(id, 1, this.getDroppedItemMeta(meta)));
                    }
                }
            }
        }
        ci.cancel();
    }

    @Inject(at = @At("HEAD"), method = "getDroppedItemId", cancellable = true)
    private void makeItemIdNotAnnoying(int blockMeta, Random random, CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(this.id);
    }
}
