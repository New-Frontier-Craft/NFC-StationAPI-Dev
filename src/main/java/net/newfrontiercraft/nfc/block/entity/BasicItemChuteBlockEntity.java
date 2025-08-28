package net.newfrontiercraft.nfc.block.entity;

import net.danygames2014.nyalib.item.ItemHandler;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.MinecartEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.Box;
import net.modificationstation.stationapi.api.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BasicItemChuteBlockEntity extends BlockEntity implements Inventory, ItemHandler {
    protected ItemStack storedItem;
    protected int tickTimer;
    protected boolean awaitingMinecart;
    public boolean chuteExtender;

    public BasicItemChuteBlockEntity(boolean chuteExtender) {
        this.chuteExtender = chuteExtender;
    }

    public BasicItemChuteBlockEntity() {
        this.chuteExtender = false;
    }

    @Override
    public void tick() {
        if (tickTimer < 20) {
            tickTimer++;
            return;
        }
        if (awaitingMinecart) {
            if (storedItem != null) {
                List minecarts = world.getEntities(null, Box.createCached(x, y - 1, z, x + 1, y, z + 1));
                for (Object minecart : minecarts) {
                    if (minecart instanceof MinecartEntity minecartEntity) {
                        if (minecartEntity.type == 1) {
                            insertIntoMinecart(minecartEntity);
                        }
                    }
                }
            }
            List minecarts = world.getEntities(null, Box.createCached(x, y + 1, z, x + 1, y + 2, z + 1));
            for (Object minecart : minecarts) {
                if (minecart instanceof MinecartEntity minecartEntity) {
                    if (minecartEntity.type == 1) {
                        extractFromMinecart(minecartEntity);
                    }
                }
            }
        }
        tickTimer = 0;
        int meta = world.getBlockMeta(x, y, z);
        BlockEntity inputTarget = world.getBlockEntity(x, y - 1, z);
        if (inputTarget != null && storedItem != null) {
            pushOutOfChute(inputTarget);
        }
        if (chuteExtender) {
            return;
        }
        BlockEntity outputTarget = world.getBlockEntity(x, y + 1, z);
        if (outputTarget != null && meta != 1) {
            pullIntoChute(outputTarget);
        }
    }

    protected void insertIntoMinecart(MinecartEntity entityMinecart) {
        int slotCount = entityMinecart.size();
        int remainingItemCount;
        for (int i = 0; i < slotCount; i++) {
            ItemStack minecartItem = entityMinecart.getStack(i);
            if (minecartItem == null) {
                entityMinecart.setStack(i, storedItem);
                remainingItemCount = 0;
            } else if (!minecartItem.isItemEqual(storedItem)) {
                continue;
            } else {
                int totalItemCount = minecartItem.count + storedItem.count;
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
            if (remainingItemCount <= 0) {
                storedItem = null;
                break;
            } else {
                storedItem.count = remainingItemCount;
            }
        }
    }

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
        if (!(inputTarget instanceof ItemHandler)) {
            return;
        }
        if (!((ItemHandler)inputTarget).canInsertItem(Direction.DOWN)) {
            return;
        }
        int slotCount = ((ItemHandler)inputTarget).getItemSlots(Direction.DOWN);
        for (int i = 0; i < slotCount; i++) {
            storedItem = ((ItemHandler)inputTarget).insertItem(storedItem, i, Direction.DOWN);
            if (storedItem == null) {
                break;
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
            ItemStack outputItem = ((ItemHandler)outputTarget).getItem(i, Direction.UP);
            if (outputItem == null) {
                continue;
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
    public int size() {
        return 1;
    }

    @Override
    public ItemStack getStack(int i) {
        return storedItem;
    }

    @Override
    public ItemStack removeStack(int i, int j) {
        if(storedItem != null) {
            if(storedItem.count <= j) {
                ItemStack itemstack = storedItem;
                storedItem = null;
                return itemstack;
            }
            ItemStack itemstack1 = storedItem.split(j);
            if(storedItem.count == 0) {
                storedItem = null;
            }
            return itemstack1;
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int i, ItemStack itemstack) {
        storedItem = itemstack;
    }

    @Override
    public String getName() {
        return "Basic Chute";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity entityplayer) {
        return true;
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound) {
        super.readNbt(nbttagcompound);
        tickTimer = nbttagcompound.getInt("TickTimer");
        awaitingMinecart = nbttagcompound.getBoolean("AwaitingMinecart");
        chuteExtender = nbttagcompound.getBoolean("ChuteExtender");
        NbtList nbttaglist = nbttagcompound.getList("Item");
        if (nbttaglist.size() == 0) {
            return;
        }
        NbtCompound nbttagcompound1 = (NbtCompound)nbttaglist.get(0);
        storedItem = new ItemStack(nbttagcompound1);
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound) {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putInt("TickTimer", tickTimer);
        nbttagcompound.putBoolean("AwaitingMinecart", awaitingMinecart);
        nbttagcompound.putBoolean("ChuteExtender", chuteExtender);
        NbtList nbttaglist = new NbtList();
        if (storedItem != null) {
            NbtCompound nbttagcompound1 = new NbtCompound();
            storedItem.writeNbt(nbttagcompound1);
            nbttaglist.add(nbttagcompound1);
        }
        nbttagcompound.put("Item", nbttaglist);
    }

//    @Override
//    public boolean isInput(int side) {
//        return side == 1;
//    }
//
//    @Override
//    public int numberOfInputSlots(int side) {
//        if (side == 1) return 1;
//        return 0;
//    }
//
//    @Override
//    public int insertItem(ItemStack itemStack, int slot, int side) {
//        if (side == 1) {
//            if (slot == 0) {
//                if (storedItem == null) {
//                    storedItem = itemStack;
//                    return 0;
//                } else if (storedItem.isItemEqual(itemStack)) {
//                    int totalItemCount = storedItem.count + itemStack.count;
//                    if (totalItemCount <= storedItem.getMaxCount()) {
//                        storedItem.count = totalItemCount;
//                        return 0;
//                    } else {
//                        storedItem.count = storedItem.getMaxCount();
//                        return totalItemCount - storedItem.getMaxCount();
//                    }
//                }
//            }
//        }
//        return itemStack.count;
//    }
//
//    @Override
//    public int getTotalCountOfItem(ItemStack itemStack, int side) {
//        if (storedItem == null) {
//            return 0;
//        } else if (!storedItem.isItemEqual(itemStack)) {
//            return 0;
//        } else {
//            return storedItem.count;
//        }
//    }
//
//    @Override
//    public ItemStack itemAtSlot(int side, int slot) {
//        return storedItem;
//    }

    public int handleGroundItem(ItemStack itemStack) {
        if (storedItem == null) {
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

    public void activateMinecartSearch() {
        awaitingMinecart = true;
        tickTimer = 20;
    }

    public void deactivateMinecartSearch() {
        awaitingMinecart = false;
    }

    @Override
    public boolean canExtractItem(@Nullable Direction direction) {
        return false;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, @Nullable Direction direction) {
        return null;
    }

    @Override
    public boolean canInsertItem(@Nullable Direction direction) {
        return direction == Direction.DOWN || direction == Direction.UP;
    }

    @Override
    public ItemStack insertItem(ItemStack itemStack, int slot, @Nullable Direction direction) {
        if (itemStack == null) {
            return null;
        }
        ItemStack itemToInsert = itemStack.copy();
        if (storedItem == null) {
            storedItem = itemToInsert;
            return null;
        } else if (storedItem.isItemEqual(itemToInsert)) {
            int totalItemCount = storedItem.count + itemStack.count;
            if (totalItemCount <= storedItem.getMaxCount()) {
                storedItem.count = totalItemCount;
                return null;
            } else {
                storedItem.count = storedItem.getMaxCount();
                itemToInsert.count = totalItemCount - storedItem.getMaxCount();
                return itemToInsert;
            }
        }
        return itemToInsert;
    }

    @Override
    public ItemStack insertItem(ItemStack itemStack, @Nullable Direction direction) {
        return this.insertItem(itemStack, 0, direction);
    }

    @Override
    public ItemStack getItem(int i, @Nullable Direction direction) {
        return storedItem;
    }

    @Override
    public boolean setItem(ItemStack itemStack, int i, @Nullable Direction direction) {
        storedItem = itemStack;
        return true;
    }

    @Override
    public int getItemSlots(@Nullable Direction direction) {
        return 1;
    }

    @Override
    public ItemStack[] getInventory(@Nullable Direction direction) {
        return new ItemStack[]{storedItem};
    }

    @Override
    public boolean canConnectItem(Direction direction) {
        return direction == Direction.UP || direction == Direction.DOWN;
    }
}
