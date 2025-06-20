package net.newfrontiercraft.nfc.block.entity;

import net.danygames2014.nyalib.item.ItemHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.modificationstation.stationapi.api.util.math.Direction;

public class FilteringItemChuteBlockEntity extends BasicItemChuteBlockEntity {

    protected ItemStack filter;
    public boolean precise;

    public FilteringItemChuteBlockEntity(boolean precise) {
        this.precise = precise;
    }

    public FilteringItemChuteBlockEntity() {

    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public ItemStack getStack(int i) {
        if (i == 1) {
            return filter;
        }
        return storedItem;
    }

    @Override
    public ItemStack removeStack(int i, int j) {
        if (i == 0) {
            if (storedItem != null) {
                if (storedItem.count <= j) {
                    ItemStack itemstack = storedItem;
                    storedItem = null;
                    return itemstack;
                }
                ItemStack itemstack1 = storedItem.split(j);
                if (storedItem.count == 0) {
                    storedItem = null;
                }
                return itemstack1;
            } else {
                return null;
            }
        } else {
            if (filter != null) {
                if (filter.count <= j) {
                    ItemStack itemstack = filter;
                    filter = null;
                    return itemstack;
                }
                ItemStack itemstack1 = filter.split(j);
                if (filter.count == 0) {
                    filter = null;
                }
                return itemstack1;
            } else {
                return null;
            }
        }
    }

    @Override
    public void setStack(int i, ItemStack itemstack) {
        if (i == 0) {
            storedItem = itemstack;
        } else {
            filter = itemstack;
        }
    }

    protected void pushOutOfChute(BlockEntity inputTarget) {
        if (filter == null) {
            return;
        }
        if (!(inputTarget instanceof ItemHandler)) {
            return;
        }
        if (!((ItemHandler)inputTarget).canInsertItem(Direction.DOWN)) {
            return;
        }
        ItemStack insertedItem;
        int slotCount = ((ItemHandler)inputTarget).getItemSlots(Direction.DOWN);
        ItemStack[] allDestinationSlots = ((ItemHandler)inputTarget).getInventory(Direction.DOWN);
        int totalCountInDestination = 0;
        if (precise) {
            for (ItemStack destinationSlot : allDestinationSlots) {
                if (destinationSlot == null) {
                    continue;
                }
                if (destinationSlot.isItemEqual(filter)) {
                    totalCountInDestination += destinationSlot.count;
                }
            }
        }
        int countToInsert;
        for (int i = 0; i < slotCount; i++) {
            if (storedItem == null) {
                break;
            }
            insertedItem = storedItem.copy();
            if (precise) {
                if (totalCountInDestination >= filter.count) {
                    break;
                }
                if (totalCountInDestination + insertedItem.count > filter.count) {
                    countToInsert = filter.count - totalCountInDestination;
                    insertedItem.count = countToInsert;
                    ItemStack remainingCountStack = ((ItemHandler) inputTarget).insertItem(insertedItem, i, Direction.DOWN);
                    if (remainingCountStack == null) {
                        storedItem.count -= countToInsert;
                        break;
                    } else {
                        storedItem.count -= countToInsert - remainingCountStack.count;
                        insertedItem.count -= countToInsert - remainingCountStack.count;
                    }
                } else {
                    storedItem = ((ItemHandler) inputTarget).insertItem(storedItem, i, Direction.DOWN);
                }
            } else {
                storedItem = ((ItemHandler) inputTarget).insertItem(storedItem, i, Direction.DOWN);
            }
        }
    }

    protected void pullIntoChute(BlockEntity outputTarget) {
        if (!(outputTarget instanceof ItemHandler)) {
            return;
        }
        if (!((ItemHandler)outputTarget).canExtractItem(outputTarget instanceof FurnaceBlockEntity ? Direction.NORTH : Direction.UP)) { // Lie to the furnace in order to extract from it
            return;
        }
        if (storedItem != null && storedItem.count >= storedItem.getMaxCount()) {
            return;
        }
        int slotCount = ((ItemHandler)outputTarget).getItemSlots(Direction.UP);
        for (int i = 0; i < slotCount; i++) {
            ItemStack outputItem = ((ItemHandler)outputTarget).getItemInSlot(i, Direction.UP);
            if (outputItem == null) {
                continue;
            }
            if (filter != null) {
                if (!outputItem.isItemEqual(filter)) {
                    continue;
                }
            }
            if (storedItem == null) {
                storedItem = ((ItemHandler)outputTarget).extractItem(i, outputItem.count, Direction.UP);
                continue;
            }
            if (!storedItem.isItemEqual(outputItem)) {
                continue;
            }
            if (storedItem.count + outputItem.count <= storedItem.getMaxCount()) {
                storedItem.count += outputItem.count;
                ((ItemHandler)outputTarget).extractItem(i, outputItem.count, Direction.UP);
            } else {
                int removedCount = outputItem.count - (storedItem.count + outputItem.count - storedItem.getMaxCount());
                storedItem.count = storedItem.getMaxCount();
                ((ItemHandler)outputTarget).extractItem(i, removedCount, Direction.UP);
                break;
            }
        }
    }

    @Override
    public String getName() {
        return "Filtering Chute";
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound) {
        super.readNbt(nbttagcompound);
        NbtList nbttaglist = nbttagcompound.getList("Filter");
        if (nbttaglist.size() == 0) {
            return;
        }
        NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist.get(0);
        filter = new ItemStack(nbttagcompound1);
        precise = nbttagcompound.getBoolean("Precise");
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound) {
        super.writeNbt(nbttagcompound);
        NbtList nbttaglist = new NbtList();
        if (filter != null) {
            NbtCompound nbttagcompound1 = new NbtCompound();
            filter.writeNbt(nbttagcompound1);
            nbttaglist.add(nbttagcompound1);
        }
        nbttagcompound.put("Filter", nbttaglist);
        nbttagcompound.putBoolean("Precise", precise);
    }

    @Override
    public int handleGroundItem(ItemStack itemStack) {
        if (storedItem == null) {
            if (filter != null && !filter.isItemEqual(itemStack)) {
                return itemStack.count;
            }
            storedItem = itemStack;
            return 0;
        }
        if (!storedItem.isItemEqual(itemStack)) {
            return itemStack.count;
        }
        int totalItemCount = storedItem.count + itemStack.count;
        if (totalItemCount <= storedItem.getMaxCount()) {
            storedItem.count = totalItemCount;
            return 0;
        } else {
            storedItem.count = storedItem.getMaxCount();
            return totalItemCount - storedItem.getMaxCount();
        }
    }
}
