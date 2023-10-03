package net.newfrontiercraft.nfc.containers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.ContainerBase;
import net.minecraft.container.ContainerListener;
import net.minecraft.container.slot.CraftingResult;
import net.minecraft.container.slot.FurnaceOutput;
import net.minecraft.container.slot.Slot;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemInstance;
import net.newfrontiercraft.nfc.tileentities.TileEntityBrickOven;

import java.awt.*;

public class ContainerBrickOven extends ContainerBase {

    public ContainerBrickOven(PlayerInventory inventoryplayer, TileEntityBrickOven tileentityfurnace) {
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
        addSlot(new FurnaceOutput(inventoryplayer.player, tileentityfurnace, 10, 116, 71));
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

    public void tick() {
        super.tick();
        for (int i = 0; i < this.listeners.size(); i++) {
            ContainerListener icrafting = (ContainerListener) this.listeners.get(i);
            if (cookTime != furnace.furnaceCookTime) {
                icrafting.updateProperty(this, 0, furnace.furnaceCookTime);
            }
            if (burnTime != furnace.furnaceBurnTime) {
                icrafting.updateProperty(this, 1, furnace.furnaceBurnTime);
            }
            if (itemBurnTime != furnace.currentItemBurnTime) {
                icrafting.updateProperty(this, 2, furnace.currentItemBurnTime);
            }
            if (requiredTime != furnace.requiredTime) {
                icrafting.updateProperty(this, 3, furnace.requiredTime);
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
    public boolean canUse(PlayerBase entityplayer) {
        return furnace.canPlayerUse(entityplayer);
    }

    @Override
    public ItemInstance transferSlot(int i) {
        ItemInstance itemstack = null;
        Slot slot = (Slot) slots.get(i);
        if (slot != null && slot.hasItem()) {
            ItemInstance itemstack1 = slot.getItem();
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
                slot.onCrafted(itemstack1);
            } else {
                return null;
            }
        }
        return itemstack;
    }

    private TileEntityBrickOven furnace;
    private int cookTime;
    private int burnTime;
    private int itemBurnTime;
    private int requiredTime;
}
