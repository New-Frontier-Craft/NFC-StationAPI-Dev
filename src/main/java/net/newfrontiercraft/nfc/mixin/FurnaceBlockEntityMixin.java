package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.api.HeatConsumer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FurnaceBlockEntity.class)
public abstract class FurnaceBlockEntityMixin extends BlockEntity implements HeatConsumer {
    @Unique
    private static final int MAXIMUM_ADDED_BURN_TIME = 1000;

    @Shadow public int burnTime;

    @Shadow protected abstract int getFuelTime(ItemStack itemStack);

    @Shadow private ItemStack[] inventory;

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

    @Override
    @Unique
    public int addHeat(int heat) {
        if (getFuelTime(inventory[1]) > 0 || burnTime >= MAXIMUM_ADDED_BURN_TIME) {
            return 0;
        }
        int totalBurnTime = burnTime + heat;
        if (totalBurnTime <= MAXIMUM_ADDED_BURN_TIME) {
            burnTime = totalBurnTime;
            FurnaceBlock.updateLitState(burnTime > 0, world, x, y, z);
            return heat;
        }
        burnTime = MAXIMUM_ADDED_BURN_TIME;
        FurnaceBlock.updateLitState(true, world, x, y, z);
        return heat - (totalBurnTime - MAXIMUM_ADDED_BURN_TIME);
    }

    @Override
    @Unique
    public int getHeat() {
        return burnTime;
    }
}
