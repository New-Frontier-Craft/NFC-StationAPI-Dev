package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.TreeFarmBlockEntity;

public class TreeFarmScreenHandler extends ScreenHandler {

    private final TreeFarmBlockEntity treeFarm;
    private int cookTime;

    public TreeFarmScreenHandler(PlayerInventory playerInventory, TreeFarmBlockEntity treeFarmBlockEntity) {
        cookTime = 0;
        treeFarm = treeFarmBlockEntity;
        // Sapling slots
        for (int yIndex = 0; yIndex < 3; yIndex++) {
            addSlot(new Slot(treeFarmBlockEntity, yIndex, 8, 16 + yIndex * 18));
        }
        // Fertilizer slots
        for (int yIndex = 0; yIndex < 3; yIndex++) {
            addSlot(new Slot(treeFarmBlockEntity, yIndex + 3, 44, 16 + yIndex * 18));
        }
        // Output slots
        for (int yIndex = 0; yIndex < 3; yIndex++) {
            for (int xIndex = 0; xIndex < 3; xIndex++) {
                addSlot(new Slot(treeFarmBlockEntity,  6 + xIndex + yIndex*3, 116 + xIndex*18, 16 + yIndex * 18));
            }
        }
        // Player inventory
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(playerInventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(playerInventory, j, 8 + j * 18, 142));
        }

    }

    public void sendContentUpdates() {
        super.sendContentUpdates();
        for (Object listener : this.listeners) {
            ScreenHandlerListener screenHandlerListener = (ScreenHandlerListener) listener;
            if (cookTime != treeFarm.craftingProgress) {
                screenHandlerListener.onPropertyUpdate(this, 0, treeFarm.craftingProgress);
            }
        }

        cookTime = treeFarm.craftingProgress;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int i, int j) {
        if (i == 0) {
            treeFarm.craftingProgress = j;
        }
    }

    @Override
    public boolean canUse(PlayerEntity entityplayer) {
        return treeFarm.canPlayerUse(entityplayer);
    }

//    TODO: Deal with quick moving when I am more energetic
//    @Override
//    public ItemStack quickMove(int i) {
//        ItemStack itemstack = null;
//        Slot slot = (Slot) slots.get(i);
//        if (slot != null && slot.hasStack()) {
//            ItemStack itemstack1 = slot.getStack();
//            itemstack = itemstack1.copy();
//            if (i == 9) {
//                insertItem(itemstack1, 10, 46, true);
//            } else if (i >= 10 && i < 37) {
//                insertItem(itemstack1, 37, 38, false);
//            } else if (i >= 37 && i < 46) {
//                insertItem(itemstack1, 10, 37, false);
//            } else {
//                insertItem(itemstack1, 10, 46, false);
//            }
//            if (itemstack1.count == 0) {
//                slot.setStack(null);
//            } else {
//                slot.markDirty();
//            }
//            if (itemstack1.count != itemstack.count) {
//                slot.onTakeItem(itemstack1);
//            } else {
//                return null;
//            }
//        }
//        return itemstack;
//    }
}
