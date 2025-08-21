package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.recipe.CraftingRecipeManager;
import net.newfrontiercraft.nfc.block.AutomaticCraftingTableBlock;
import net.newfrontiercraft.nfc.inventory.crafting.AutocraftingMatrix;
import net.newfrontiercraft.nfc.inventory.crafting.AutocraftingScreenHandler;

public class AutomaticCraftingTableBlockEntity extends BlockEntity implements Inventory {

    public static final int RECIPE_TIME = 1000;
    private static final int OUTPUT = 9;

    private ItemStack[] craftingTableItemStacks;
    public int craftingProgress;
    private int torque;
    private int checkTimer;
    public boolean isMultiBlock;

    public AutomaticCraftingTableBlockEntity() {
        // First slots 0 to 8 are inputs, 9 is output
        craftingTableItemStacks = new ItemStack[10];
        craftingProgress = 0;
        torque = 0;
        isMultiBlock = false;
    }

    @Override
    public void tick() {
        boolean previouslyActive = craftingProgress > 0;
        if (checkTimer < 40) {
            checkTimer++;
        } else {
            isMultiBlock = checkMultiBlockStructure();
            checkTimer = 0;
        }
        if (!world.isRemote) {
            if (torque > 0 && canCraft()) {
                if (isMultiBlock) {
                    craftingProgress += torque * 2;
                } else {
                    craftingProgress += torque;
                }
                if (craftingProgress >= RECIPE_TIME) {
                    craftingProgress = 0;
                    craftItem();
                }
            } else {
                craftingProgress = 0;
            }
        }
        boolean currentlyActive = craftingProgress > 0;
        if (previouslyActive != currentlyActive) {
            AutomaticCraftingTableBlock.updateCraftingTableBlockState(currentlyActive, world, x, y, z);
        }
    }

    private boolean checkMultiBlockStructure() {
        torque = 4;
        return false;
    }

    private boolean canCraft() {
        AutocraftingMatrix autocraftingMatrix = new AutocraftingMatrix();
        AutocraftingScreenHandler autocraftingScreenHandler = new AutocraftingScreenHandler(autocraftingMatrix);
        CraftingInventory craftingInventory = new CraftingInventory(autocraftingScreenHandler, 3, 3);
        for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
            if (craftingTableItemStacks[slotIndex] == null) {
                continue;
            }
            craftingInventory.setStack(slotIndex, craftingTableItemStacks[slotIndex]);
        }
        ItemStack itemstack = CraftingRecipeManager.getInstance().craft(craftingInventory);
        if (itemstack == null) {
            return false;
        }
        if (craftingTableItemStacks[OUTPUT] == null) {
            return true;
        }
        if (!craftingTableItemStacks[OUTPUT].copy().isItemEqual(itemstack)) {
            return false;
        }
        if (craftingTableItemStacks[OUTPUT].copy().count < getMaxCountPerStack()
                && craftingTableItemStacks[OUTPUT].copy().count + itemstack.copy().count < craftingTableItemStacks[OUTPUT].copy().getMaxCount()) {
            return true;
        }
        return craftingTableItemStacks[OUTPUT].copy().count + itemstack.copy().count <= itemstack.copy().getMaxCount();
    }

    public void craftItem() {
        if (!canCraft()) {
            return;
        }
        AutocraftingMatrix autocraftingMatrix = new AutocraftingMatrix();
        AutocraftingScreenHandler autocraftingScreenHandler = new AutocraftingScreenHandler(autocraftingMatrix);
        CraftingInventory craftingInventory = new CraftingInventory(autocraftingScreenHandler, 3, 3);
        for (int slotIndex = 0; slotIndex < 9; slotIndex++) {
            if (craftingTableItemStacks[slotIndex] == null) {
                continue;
            }
            craftingInventory.setStack(slotIndex, craftingTableItemStacks[slotIndex]);
        }
        ItemStack itemstack = CraftingRecipeManager.getInstance().craft(craftingInventory);
        if (craftingTableItemStacks[OUTPUT] == null) {
            craftingTableItemStacks[OUTPUT] = itemstack.copy();
        } else if (craftingTableItemStacks[OUTPUT].itemId == itemstack.copy().itemId) {
            craftingTableItemStacks[OUTPUT].count += itemstack.copy().count;
        }

        // Clear input slots
        for(int i = 0; i < 9; i++) {
            if(craftingTableItemStacks[i] != null){
                craftingTableItemStacks[i].count--;
                if (craftingTableItemStacks[i].copy().count <= 0) {
                    craftingTableItemStacks[i] = null;
                }
            }
        }
    }

    public int getScaledCraftingProgress(int scale) {
        return (craftingProgress * scale) / RECIPE_TIME;
    }

    @Override
    public int size() {
        return craftingTableItemStacks.length;
    }

    @Override
    public ItemStack getStack(int slot) {
        return craftingTableItemStacks[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if (craftingTableItemStacks[slot] != null) {
            ItemStack itemStack;
            if (craftingTableItemStacks[slot].count <= amount) {
                itemStack = craftingTableItemStacks[slot];
                craftingTableItemStacks[slot] = null;
                return itemStack;
            }
            itemStack = craftingTableItemStacks[slot].split(amount);
            if (craftingTableItemStacks[slot].count == 0) {
                craftingTableItemStacks[slot] = null;
            }
            return itemStack;
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        craftingTableItemStacks[slot] = stack;
        if (stack != null && stack.count > getMaxCountPerStack()) {
            stack.count = getMaxCountPerStack();
        }
    }

    @Override
    public String getName() {
        return "Automatic Crafting Table";
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
        craftingTableItemStacks = new ItemStack[size()];
        for (int i = 0; i < nbtList.size(); i++) {
            NbtCompound itemCompound = (NbtCompound) nbtList.get(i);
            byte slotIndex = itemCompound.getByte("Slot");
            if (slotIndex >= 0 && slotIndex < craftingTableItemStacks.length) {
                craftingTableItemStacks[slotIndex] = new ItemStack(itemCompound);
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
        for (int i = 0; i < craftingTableItemStacks.length; i++) {
            if (craftingTableItemStacks[i] != null) {
                NbtCompound itemCompound = new NbtCompound();
                itemCompound.putByte("Slot", (byte) i);
                craftingTableItemStacks[i].writeNbt(itemCompound);
                nbtList.add(itemCompound);
            }
        }

        nbtCompound.put("Items", nbtList);
        nbtCompound.putBoolean("IsMultiBlock", isMultiBlock);
        nbtCompound.putInt("CheckTimer", checkTimer);
        nbtCompound.putInt("Torque", torque);
    }
}
