package net.newfrontiercraft.nfc.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.inventory.crafting.CraftingResultInventoryMatrix;
import net.newfrontiercraft.nfc.registry.CarpentryRecipes;

public class CarpentryWorkstationScreenHandler extends ScreenHandler {

    public CarpentryWorkstationScreenHandler(PlayerInventory inventoryplayer, World world, int i, int j, int k) {
        craftMatrix = new CraftingInventory(this, 1, 1);
        craftResult = new CraftingResultInventoryMatrix(12);
        this.world = world;
        localX = i;
        localY = j;
        localZ = k;

        for(int i1 = 0; i1 < 3; i1++) {
            for(int l1 = 0; l1 < 9; l1++) {
                addSlot(new Slot(inventoryplayer, l1 + i1 * 9 + 9, 8 + l1 * 18, 84 + i1 * 18));
            }
        }

        for(int j1 = 0; j1 < 9; j1++) {
            addSlot(new Slot(inventoryplayer, j1, 8 + j1 * 18, 142));
        }

        for (int xRow = 0; xRow < 4; xRow++) {
            for (int yRow = 0; yRow < 3; yRow++) {
                addSlot(new CraftingResultSlot(inventoryplayer.player, craftMatrix, craftResult, xRow + yRow * 4, 98 + xRow * 18, 17 + yRow * 18));
            }
        }

        addSlot(new Slot(craftMatrix, 0, 36, 35));
    }

    @Override
    public void onSlotUpdate(Inventory inventory) {
        ItemStack inputStack = craftMatrix.getStack(0);
        if (inputStack == null) {
            wipeOutputs();
            return;
        }
        ItemStack[] outputItems;
        outputItems = CarpentryRecipes.carpentry().getCarpentryResult(inputStack.itemId, inputStack.getDamage());
        if (outputItems == null) {
            wipeOutputs();
            return;
        }
        wipeOutputs();
        for (int index = 0; index < outputItems.length; index++) {
            craftResult.setStack(index, outputItems[index].copy());
        }
    }

    private void wipeOutputs() {
        for (int index = 0; index < 12; index++) {
            craftResult.setStack(index, null);
        }
    }

    @Override
    public void onClosed(PlayerEntity playerEntity) {
        super.onClosed(playerEntity);
        if(world.isRemote) {
            return;
        }
        ItemStack itemstack = craftMatrix.getStack(0);
        if (playerEntity == null) return;
        if(itemstack != null) {
            playerEntity.dropItem(itemstack);
        }
    }

    @Override
    public boolean canUse(PlayerEntity playerEntity) {
        return playerEntity.getSquaredDistance((double) localX + 0.5D, (double) localY + 0.5D, (double) localZ + 0.5D) <= 64D;
    }

    @Override
    public ItemStack quickMove(int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)slots.get(i);
        if(slot != null && slot.hasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if(i >= 36) {
                insertItem(itemstack1, 0, 36, true);
            } else {
                insertItem(itemstack1, 48, 49, false);
            }
            if(itemstack1.count == 0) {
                slot.setStack(null);
            } else {
                slot.markDirty();
            }
            if(itemstack1.count != itemstack.count) {
                slot.onTakeItem(itemstack1);
            } else {
                return null;
            }
        }
        return itemstack;
    }

    public CraftingInventory craftMatrix;
    public Inventory craftResult;
    private World world;
    private int localX;
    private int localY;
    private int localZ;
}
