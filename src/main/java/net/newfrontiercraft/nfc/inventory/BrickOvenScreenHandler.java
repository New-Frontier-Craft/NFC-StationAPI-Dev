package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;

public class BrickOvenScreenHandler extends ScreenHandler {

    public BrickOvenScreenHandler(PlayerInventory inventoryplayer, BrickOvenBlockEntity tileentityfurnace) {
        cookTime = 0;
        burnTime = 0;
        itemBurnTime = 0;
        furnace = tileentityfurnace;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                addSlot(new Slot(tileentityfurnace,  k + i*3, 38 + k*18, 17 + i * 18));
            }
        }
        addSlot(new Slot(tileentityfurnace, 9, 56, 89));
        addSlot(new FurnaceOutputSlot(inventoryplayer.player, tileentityfurnace, 10, 116, 71));
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 9; k++) {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18,
                        120 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++) {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 178));
        }

    }

    public void sendContentUpdates() {
        super.sendContentUpdates();
        for (int i = 0; i < this.listeners.size(); i++) {
            ScreenHandlerListener screenHandlerListener = (ScreenHandlerListener) this.listeners.get(i);
            if (cookTime != furnace.furnaceCookTime) {
                screenHandlerListener.onPropertyUpdate(this, 0, furnace.furnaceCookTime);
            }
            if (burnTime != furnace.furnaceBurnTime) {
                screenHandlerListener.onPropertyUpdate(this, 1, furnace.furnaceBurnTime);
            }
            if (itemBurnTime != furnace.currentItemBurnTime) {
                screenHandlerListener.onPropertyUpdate(this, 2, furnace.currentItemBurnTime);
            }
            if (requiredTime != furnace.requiredTime) {
                screenHandlerListener.onPropertyUpdate(this, 3, furnace.requiredTime);
            }
        }

        cookTime = furnace.furnaceCookTime;
        burnTime = furnace.furnaceBurnTime;
        itemBurnTime = furnace.currentItemBurnTime;
        requiredTime = furnace.requiredTime;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int i, int j) {
        if (i == 0) {
            furnace.furnaceCookTime = j;
        }
        if (i == 1) {
            furnace.furnaceBurnTime = j;
        }
        if (i == 2) {
            furnace.currentItemBurnTime = j;
        }
        if (i == 3) {
            furnace.requiredTime = j;
        }
    }

    @Override
    public boolean canUse(PlayerEntity entityplayer) {
        return furnace.canPlayerUse(entityplayer);
    }

    @Override
    public ItemStack quickMove(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) slots.get(i);
        if (slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (i == 10) {
                insertItem(itemstack1, 11, 47, true);
            } else if (i >= 11 && i < 38) {
                insertItem(itemstack1, 38, 39, false);
            } else if (i >= 38 && i < 47) {
                insertItem(itemstack1, 11, 38, false);
            } else {
                insertItem(itemstack1, 11, 47, false);
            }
            if (itemstack1.count == 0) {
                slot.setStack(null);
            } else {
                slot.markDirty();
            }
            if (itemstack1.count != itemstack.count) {
                slot.onTakeItem(itemstack1);
            } else {
                return null;
            }
        }
        return itemstack;
    }

    private BrickOvenBlockEntity furnace;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;
    private int requiredTime;
}
