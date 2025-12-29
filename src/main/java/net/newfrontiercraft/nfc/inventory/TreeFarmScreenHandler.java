package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.TreeFarmBlockEntity;

public class TreeFarmScreenHandler extends ScreenHandler {

    private final TreeFarmBlockEntity treeFarm;
    private int cookTime;
    private boolean isMultiblock;
    private boolean hasFrame;

    public TreeFarmScreenHandler(PlayerInventory playerInventory, TreeFarmBlockEntity treeFarmBlockEntity) {
        cookTime = 0;
        treeFarm = treeFarmBlockEntity;
        hasFrame = false;
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
                addSlot(new Slot(treeFarmBlockEntity, 6 + xIndex + yIndex * 3, 116 + xIndex * 18, 16 + yIndex * 18));
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
            if (isMultiblock != treeFarm.isMultiBlock) {
                screenHandlerListener.onPropertyUpdate(this, 1, treeFarm.isMultiBlock ? 1 : 0);
            }
            if (hasFrame != treeFarm.hasFrame()) {
                screenHandlerListener.onPropertyUpdate(this, 2, treeFarm.hasFrame() ? 1 : 0);
            }
        }

        cookTime = treeFarm.craftingProgress;
        isMultiblock = treeFarm.isMultiBlock;
        hasFrame = treeFarm.hasFrame();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int id, int value) {
        if (id == 0) {
            treeFarm.craftingProgress = value;
        }
        if (id == 1) {
            treeFarm.isMultiBlock = value == 1;
        }
        if (id == 2) {
            treeFarm.setHasFrame(value == 1);
        }
    }

    @Override
    public boolean canUse(PlayerEntity entityplayer) {
        return treeFarm.canPlayerUse(entityplayer);
    }

    @Override
    public ItemStack quickMove(int slot) {
        ItemStack itemStack = null;
        Slot selectedSlot = (Slot) slots.get(slot);
        if (selectedSlot != null && selectedSlot.hasStack()) {
            ItemStack slotStack = selectedSlot.getStack();
            itemStack = slotStack.copy();
            if (slot >= 6 && slot < 16) {
                insertItem(slotStack, 16, 51, true);
            }
            if (slotStack.count == 0) {
                selectedSlot.setStack(null);
            } else {
                selectedSlot.markDirty();
            }
            if (slotStack.count != itemStack.count) {
                selectedSlot.onTakeItem(slotStack);
            } else {
                return null;
            }
        }
        return itemStack;
    }
}
