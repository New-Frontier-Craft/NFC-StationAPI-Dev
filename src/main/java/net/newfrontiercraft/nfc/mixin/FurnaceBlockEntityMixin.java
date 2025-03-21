package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FurnaceBlockEntity.class)
public class FurnaceBlockEntityMixin {
    @WrapOperation(
            method = "tick",
            at = @At(value = "FIELD", opcode = Opcodes.PUTFIELD, target = "Lnet/minecraft/item/ItemStack;count:I"
            )
    )
    public void nfcBucket(ItemStack stack, int value, Operation<Void> original){
        if(stack.getItem() instanceof BucketItem){
            stack.itemId = Item.BUCKET.id;
            stack.count = 1;
        }
        else {
            stack.count = value;
        }
    }
}
