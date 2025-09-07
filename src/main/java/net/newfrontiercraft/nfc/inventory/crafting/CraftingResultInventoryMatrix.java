package net.newfrontiercraft.nfc.inventory.crafting;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

public class CraftingResultInventoryMatrix implements Inventory {

    public CraftingResultInventoryMatrix(int size) {
        this.size = size;
        stackResult = new ItemStack[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ItemStack getStack(int i) {
        return stackResult[i];
    }

    @Override
    public ItemStack removeStack(int i, int j) {
        if(stackResult[i] != null) {
            ItemStack itemstack = stackResult[i];
            stackResult[i] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int i, ItemStack itemstack) {
        stackResult[i] = itemstack;
    }

    @Override
    public String getName() {
        return "ResultMatrix";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity entityplayer) {
        return false;
    }

    public int size;
    private ItemStack[] stackResult;
}
