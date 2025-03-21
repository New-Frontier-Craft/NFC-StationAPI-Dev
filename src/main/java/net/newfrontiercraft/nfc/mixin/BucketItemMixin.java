package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.events.init.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BucketItem.class)
public class BucketItemMixin {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getMaterial(III)Lnet/minecraft/block/material/Material;", ordinal = 0), cancellable = true)
    public void nfcUse(ItemStack stack, World world, PlayerEntity user, CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 0) int hitX, @Local(ordinal = 1) int hitY, @Local(ordinal = 2) int hitZ) {
        if(world.getMaterial(hitX, hitY, hitZ) == Materials.oil && world.getBlockMeta(hitX, hitY, hitZ) == 0) {
            world.setBlock(hitX, hitY, hitZ, 0);
            cir.setReturnValue(new ItemStack(ItemListener.oilBucket));
        }
    }
}
