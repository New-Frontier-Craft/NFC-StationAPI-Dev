package net.newfrontiercraft.nfc.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.DungeonFeature;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(DungeonFeature.class)
public class DungeonLootMixin {
    
    @Inject(method = "getRandomChestItem", at = @At("HEAD"), cancellable = true)
    public void replaceDungeonLoot(Random random, CallbackInfoReturnable<ItemStack> cir) {
        int i = random.nextInt(19);
        if (i == 0) {
            int j = random.nextInt(3);
            if(j == 0)
                cir.setReturnValue(new ItemStack(ItemListener.telescopeItem));
            if(j == 1)
                cir.setReturnValue(new ItemStack(Item.SADDLE));
            // TODO: Reactivate with wrench addition
//            if(j == 2)
//                cir.setReturnValue(new ItemStack(mod_NFC.Wrench));
        } else if (i == 1 && random.nextInt(2) == 0) {
            cir.setReturnValue(new ItemStack( ItemListener.boronIngot, random.nextInt(4) + 1));
        } else if (i == 2) {
            cir.setReturnValue(new ItemStack(Item.BREAD));
        } else if (i == 3) {
            cir.setReturnValue(new ItemStack(Item.WHEAT, random.nextInt(4) + 1));
        } else if (i == 4) {
            cir.setReturnValue(new ItemStack(Item.GUNPOWDER, random.nextInt(4) + 1));
        } else if (i == 5) {
            cir.setReturnValue(new ItemStack(Item.STRING, random.nextInt(10) + 1));
        } else if (i == 6) {
            cir.setReturnValue(new ItemStack(Item.BUCKET));
        } else if (i == 7 && random.nextInt(100) == 0) {
            cir.setReturnValue(new ItemStack(Item.GOLDEN_APPLE));
        } else if (i == 8 && random.nextInt(2) == 0) {
            cir.setReturnValue(new ItemStack(Item.REDSTONE, random.nextInt(10) + 1));
        } else if (i == 9 && random.nextInt(8) == 0) {
            cir.setReturnValue(random.nextBoolean() ? new ItemStack(Item.RECORD_CAT) : new ItemStack(Item.RECORD_THIRTEEN));
        } else if (i == 10) {
            cir.setReturnValue(new ItemStack(Item.DYE, random.nextInt(16) + 1, 3));
        } else if (i == 11) {
            int j = random.nextInt(6);
            if(j == 0)
                cir.setReturnValue(new ItemStack(ItemListener.emerald, 1));
            else if(j == 1)
                cir.setReturnValue(new ItemStack(ItemListener.sapphire, 1));
            else if(j == 2)
                cir.setReturnValue(new ItemStack(ItemListener.ruby, 1));
        } else if (i == 12) {
            cir.setReturnValue(new ItemStack(Item.ARROW, random.nextInt(32) + 16));
        } else if (i == 13) {
            cir.setReturnValue(new ItemStack(Item.IRON_SWORD, 1, random.nextInt(200) + 40));
        } else if (i == 14) {
            int j = random.nextInt(4);
            if(j == 0)
                cir.setReturnValue(new ItemStack(Item.CHAIN_HELMET, 1));
            else if(j == 1)
                cir.setReturnValue(new ItemStack(Item.CHAIN_CHESTPLATE, 1));
            else if(j == 2)
                cir.setReturnValue(new ItemStack(Item.CHAIN_LEGGINGS, 1));
            else if(j == 3)
                cir.setReturnValue(new ItemStack(Item.CHAIN_BOOTS, 1));
        } else if (i == 15) {
            cir.setReturnValue(new ItemStack(BlockListener.alphaSaplingBlock, random.nextInt(2) + 1, 3));
        } else if (i > 15) {
            cir.setReturnValue(new ItemStack(Item.BONE, random.nextInt(3) + 1));
        } else {
            cir.setReturnValue(null);
        }
    }
}
