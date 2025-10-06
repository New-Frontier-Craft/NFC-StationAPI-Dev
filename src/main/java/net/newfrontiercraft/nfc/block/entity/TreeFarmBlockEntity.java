package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class TreeFarmBlockEntity extends BlockEntity implements Inventory {

    public static final int RECIPE_TIME = 1000;
    private static final int SAPLING_START = 0;
    private static final int SAPLING_END = 2;
    private static final int FERTILIZER_START = 3;
    private static final int FERTILIZER_END = 5;
    private static final int OUTPUT_START = 6;
    private static final int OUTPUT_END = 14;

    private ItemStack[] treeFarmItemStacks;
    public int craftingProgress;
    private int torque;
    private int checkTimer;
    public boolean isMultiBlock;

    public TreeFarmBlockEntity() {
        // First slots 0 to 8 are inputs, 9 is output
        treeFarmItemStacks = new ItemStack[15];
        craftingProgress = 0;
        torque = 0;
        isMultiBlock = false;
    }

    @Override
    public int size() {
        return treeFarmItemStacks.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        return treeFarmItemStacks[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (treeFarmItemStacks[slot] != null) {
            ItemStack itemStack;
            if (treeFarmItemStacks[slot].count <= amount) {
                itemStack = treeFarmItemStacks[slot];
                treeFarmItemStacks[slot] = null;
                return itemStack;
            }
            itemStack = treeFarmItemStacks[slot].split(amount);
            if (treeFarmItemStacks[slot].count == 0) {
                treeFarmItemStacks[slot] = null;
            }
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        treeFarmItemStacks[slot] = stack;
        if (stack != null && stack.count > getMaxCountPerStack()) {
            stack.count = getMaxCountPerStack();
        }
    }

    @Override
    public String getName() {
        return "Tree Farm";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (world.getBlockEntity(x, y, z) != this) {
            return false;
        }
        return player.getSquaredDistance((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D) <= 64D;
    }

    @Override
    public void readNbt(NbtCompound nbtCompound) {
        super.readNbt(nbtCompound);
        NbtList nbtList = nbtCompound.getList("Items");
        treeFarmItemStacks = new ItemStack[size()];
        for (int i = 0; i < nbtList.size(); i++) {
            NbtCompound itemCompound = (NbtCompound) nbtList.get(i);
            byte slotIndex = itemCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < treeFarmItemStacks.length) {
                treeFarmItemStacks[slotIndex] = new ItemStack(itemCompound);
            }
        }

        craftingProgress = nbtCompound.getShort("CraftingProgress");
        isMultiBlock = nbtCompound.getBoolean("IsMultiBlock");
        checkTimer = nbtCompound.getInt("CheckTimer");
        torque = nbtCompound.getInt("Torque");
    }

    @Override
    public void writeNbt(NbtCompound nbtCompound) {
        super.writeNbt(nbtCompound);
        nbtCompound.putShort("CraftingProgress", (short) craftingProgress);
        NbtList nbtList = new NbtList();
        for (int i = 0; i < treeFarmItemStacks.length; i++) {
            if (treeFarmItemStacks[i] != null) {
                NbtCompound itemCompound = new NbtCompound();
                itemCompound.putByte("Slot", (byte) i);
                treeFarmItemStacks[i].writeNbt(itemCompound);
                nbtList.add(itemCompound);
            }
        }

        nbtCompound.put("Items", nbtList);
        nbtCompound.putBoolean("IsMultiBlock", isMultiBlock);
        nbtCompound.putInt("CheckTimer", checkTimer);
        nbtCompound.putInt("Torque", torque);
    }

    public int getScaledCraftingProgress(int scale) {
        return (craftingProgress * scale) / RECIPE_TIME;
    }
}
