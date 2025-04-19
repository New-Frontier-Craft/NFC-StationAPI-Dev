package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.utils.VanillaStairPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {
    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    void nfcUseOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side, CallbackInfoReturnable<Boolean> cir){
        if(stack.getItem() instanceof BlockItem blockItem && (blockItem.getBlock().id == Block.COBBLESTONE_STAIRS.id || blockItem.getBlock().id == Block.WOODEN_STAIRS.id)){
            boolean isStone = blockItem.getBlock().id == Block.COBBLESTONE_STAIRS.id;
            boolean placed = VanillaStairPlacer.INSTANCE.useOnBlock(stack, user, world, x, y, z, side, isStone);
            cir.setReturnValue(placed);
        }
    }
}
