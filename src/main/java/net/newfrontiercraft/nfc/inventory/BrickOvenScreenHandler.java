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
    private final BrickOvenBlockEntity furnace;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;
    private int requiredTime;
    private boolean isMultiblock;
    private int heatLevel;
    private int maximumHeatLevel;
    private int requiredHeatLevel;

    public BrickOvenScreenHandler(PlayerInventory inventoryplayer, BrickOvenBlockEntity tileentityfurnace) {
        cookTime = 0;
        burnTime = 0;
        itemBurnTime = 0;
        isMultiblock = false;
        heatLevel = 0;
        maximumHeatLevel = 0;
        requiredHeatLevel = 0;
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
            if (isMultiblock != furnace.isMultiBlock) {
                screenHandlerListener.onPropertyUpdate(this, 4, furnace.isMultiBlock ? 1 : 0);
            }
            if (heatLevel != furnace.getHeatLevel()) {
                screenHandlerListener.onPropertyUpdate(this, 5, furnace.getHeatLevel());
            }
            if (maximumHeatLevel != furnace.getMaximumHeatLevel()) {
                screenHandlerListener.onPropertyUpdate(this, 6, furnace.getMaximumHeatLevel());
            }
            if (requiredHeatLevel != furnace.getRequiredHeatLevel()) {
                screenHandlerListener.onPropertyUpdate(this, 7, furnace.getRequiredHeatLevel());
            }
        }

        cookTime = furnace.furnaceCookTime;
        burnTime = furnace.furnaceBurnTime;
        itemBurnTime = furnace.currentItemBurnTime;
        requiredTime = furnace.requiredTime;
        isMultiblock = furnace.isMultiBlock;
        heatLevel = furnace.getHeatLevel();
        maximumHeatLevel = furnace.getMaximumHeatLevel();
        requiredHeatLevel = furnace.getRequiredHeatLevel();
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int id, int value) {
        if (id == 0) {
            furnace.furnaceCookTime = value;
        }
        if (id == 1) {
            furnace.furnaceBurnTime = value;
        }
        if (id == 2) {
            furnace.currentItemBurnTime = value;
        }
        if (id == 3) {
            furnace.requiredTime = value;
        }
        if (id == 4) {
            furnace.isMultiBlock = value == 1;
        }
        if (id == 5) {
            furnace.setHeatLevel(value);
        }
        if (id == 6) {
            furnace.setMaximumHeatLevel(value);
        }
        if (id == 7) {
            furnace.setRequiredHeatLevel(value);
        }
    }

    @Override
    public boolean canUse(PlayerEntity entityplayer) {
        return furnace.canPlayerUse(entityplayer);
    }

    @Override
    public ItemStack quickMove(int slot) {
        ItemStack itemStack = null;
        Slot selectedSlot = (Slot) slots.get(slot);
        if (selectedSlot != null && selectedSlot.hasStack()) {
            ItemStack slotStack = selectedSlot.getStack();
            itemStack = slotStack.copy();
            if (slot == 10) {
                insertItem(slotStack, 11, 47, true);
            } else if (slot >= 11 && slot < 38) {
                insertItem(slotStack, 38, 39, false);
            } else if (slot >= 38 && slot < 47) {
                insertItem(slotStack, 11, 38, false);
            } else {
                insertItem(slotStack, 11, 47, false);
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
