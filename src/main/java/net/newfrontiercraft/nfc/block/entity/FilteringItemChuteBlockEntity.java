package net.newfrontiercraft.nfc.block.entity;

import net.danygames2014.nyalib.item.ItemHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
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

    @Override
    protected void insertIntoMinecart(MinecartEntity entityMinecart) {
        int slotCount = entityMinecart.size();
        int remainingItemCount = storedItem.count;
        for (int i = 0; i < slotCount; i++) {
            int totalCountInInput = countItemsInMinecart(entityMinecart);
            ItemStack minecartItem = entityMinecart.getStack(i);
            if (minecartItem == null) {
                if (precise && filter != null) {
                    if (totalCountInInput >= filter.count) {
                        break;
                    } else if (totalCountInInput + storedItem.count <= filter.count) {
                        entityMinecart.setStack(i, storedItem);
                        remainingItemCount = 0;
                    } else {
                        ItemStack stackForInsertion = storedItem.copy();
                        stackForInsertion.count = filter.count - totalCountInInput;
                        entityMinecart.setStack(i, stackForInsertion);
                        remainingItemCount -= (filter.count - totalCountInInput);
                    }
                } else {
                    entityMinecart.setStack(i, storedItem);
                    remainingItemCount = 0;
                }
            } else if (!minecartItem.isItemEqual(storedItem)) {
                continue;
            } else {
                int totalItemCount = minecartItem.count + storedItem.count;
                if (precise && filter != null) {
                    if (totalCountInInput >= filter.count) {
                        break;
                    } else if (totalItemCount + totalCountInInput <= filter.count) {
                        minecartItem.count = totalItemCount;
                        entityMinecart.setStack(i, minecartItem);
                        remainingItemCount = 0;
                    } else {
                        minecartItem.count += filter.count - totalCountInInput;
                        entityMinecart.setStack(i, minecartItem);
                        remainingItemCount -= filter.count - totalCountInInput;
                    }
                } else {
                    if (totalItemCount < storedItem.getMaxCount()) {
                        minecartItem.count = totalItemCount;
                        entityMinecart.setStack(i, minecartItem);
                        remainingItemCount = 0;
                    } else {
                        minecartItem.count = minecartItem.getMaxCount();
                        entityMinecart.setStack(i, minecartItem);
                        remainingItemCount = totalItemCount - storedItem.getMaxCount();
                    }
                }
            }
            if (remainingItemCount <= 0) {
                storedItem = null;
                break;
            } else {
                storedItem.count = remainingItemCount;
            }
        }
    }

    protected int countItemsInMinecart(MinecartEntity entityMinecart) {
        if (filter == null) {
            return 0;
        }
        int totalCount = 0;
        for (int i = 0; i < entityMinecart.size(); i++) {
            ItemStack slotItem = entityMinecart.getStack(i);
            if (slotItem == null) {
                continue;
            }
            if (filter.isItemEqual(slotItem)) {
                totalCount += slotItem.count;
            }
        }
        return totalCount;
    }

    @Override
    protected void extractFromMinecart(MinecartEntity entityMinecart) {
        if (storedItem != null && storedItem.count >= storedItem.getMaxCount()) {
            return;
        }
        int slotCount = entityMinecart.size();
        for (int i = 0; i < slotCount; i++) {
            ItemStack minecartItem = entityMinecart.getStack(i);
            if (minecartItem == null) {
                continue;
            }
            if (filter != null && !filter.isItemEqual(minecartItem)) {
                continue;
            }
            if (storedItem == null) {
                storedItem = minecartItem;
                entityMinecart.setStack(i, null);
                continue;
            }
            if (!storedItem.isItemEqual(minecartItem)) {
                continue;
            }
            if (storedItem.count + minecartItem.count <= storedItem.getMaxCount()) {
                storedItem.count += minecartItem.count;
                entityMinecart.setStack(i, null);
            } else {
                int removedCount = minecartItem.count - (storedItem.count + minecartItem.count - storedItem.getMaxCount());
                storedItem.count = storedItem.getMaxCount();
                minecartItem.count -= removedCount;
                entityMinecart.setStack(i, minecartItem);
                break;
            }
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
