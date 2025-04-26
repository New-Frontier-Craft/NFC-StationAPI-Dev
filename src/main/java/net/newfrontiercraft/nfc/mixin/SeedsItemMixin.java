package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SeedsItem;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(SeedsItem.class)
public class SeedsItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    void nfcGenerate(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir){
        if (side == 1) {
            int var8 = world.getBlockId(x, y, z);
            if (var8 == BlockListener.planter.id && world.isAir(x, y + 1, z)) {
                world.setBlock(x, y + 1, z, Block.WHEAT.id);
                --stack.count;
                cir.setReturnValue(true);
            }
        }
    }
}
