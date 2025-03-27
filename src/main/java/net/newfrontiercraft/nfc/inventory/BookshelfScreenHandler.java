package net.newfrontiercraft.nfc.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.BookshelfBlockEntity;

public class BookshelfScreenHandler extends ScreenHandler {
    private Inventory bookshelfInventory;
    private int rows;

    public BookshelfScreenHandler(Inventory inventory, BookshelfBlockEntity blockEntity) {
        this.bookshelfInventory = blockEntity;
        this.rows = blockEntity.size() / 5;
        int i = (this.rows - 4) * 18;

        int l;
        int j1;
        for(l = 0; l < 2; ++l) {
            for(j1 = 0; j1 < 5; ++j1) {
                this.addSlot(new Slot(blockEntity, j1 + l * 5, 8 + j1 * 18 + 36, 18 + l * 18));
            }
        }

        for(l = 0; l < 3; ++l) {
            for(j1 = 0; j1 < 9; ++j1) {
                this.addSlot(new Slot(inventory, j1 + l * 9 + 9, 8 + j1 * 18, 103 + l * 18 + i));
            }
        }

        for(l = 0; l < 9; ++l) {
            this.addSlot(new Slot(inventory, l, 8 + l * 18, 161 + i));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.bookshelfInventory.canPlayerUse(player);
    }

    @Override
    public ItemStack quickMove(int slot) {
        ItemStack itemstack = null;
        Slot selectedSlot = (Slot)this.slots.get(slot);
        if(selectedSlot != null && selectedSlot.hasStack()) {
            ItemStack stack = selectedSlot.getStack();
            itemstack = stack.copy();
            if(slot < this.rows * 5) {
                this.insertItem(stack, this.rows * 5, this.slots.size(), true);
            } else {
                this.insertItem(stack, 0, this.rows * 5, false);
            }

            if(stack.count == 0) {
                selectedSlot.setStack(null);
            } else {
                selectedSlot.markDirty();
            }
        }

        return itemstack;
    }
}
