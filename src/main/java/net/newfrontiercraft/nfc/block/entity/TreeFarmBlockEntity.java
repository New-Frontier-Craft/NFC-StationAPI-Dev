package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.newfrontiercraft.nfc.block.AutomaticCraftingTableBlock;
import net.newfrontiercraft.nfc.block.TreeFarmBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;

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
    public void tick() {
        boolean previouslyActive = craftingProgress > 0;
        if (checkTimer < 40) {
            checkTimer++;
        } else {
            isMultiBlock = checkMultiBlockStructure();
            checkTimer = 0;
        }
        if (!world.isRemote && isMultiBlock) {
            if (torque > 0) {
                craftingProgress += torque;
                if (craftingProgress >= RECIPE_TIME) {
                    craftingProgress = 0;
                    performTreeFarmLogic();
                }
            } else {
                craftingProgress = 0;
            }
        }
        boolean currentlyActive = craftingProgress > 0;
        if (previouslyActive != currentlyActive) {
            TreeFarmBlock.updateTreeFarmBlockState(currentlyActive, world, x, y, z);
        }
    }

    private void performTreeFarmLogic() {

    }

    private boolean checkMultiBlockStructure() {
        // Calculate central coordinates
        int meta = world.getBlockMeta(x, y, z) % 6;
        int xCentered = x;
        int zCentered = z;
        switch (meta) {
            case 2:
                zCentered++;
                break;
            case 3:
                zCentered--;
                break;
            case 4:
                xCentered++;
                break;
            case 5:
                xCentered--;
                break;
        }
        // Check structural integrity
        if (world.getBlockId(xCentered, y - 1, zCentered) != BlockListener.machineGearBox.id) {
            torque = 0;
            return false;
        }
        BlockEntity blockEntity = world.getBlockEntity(xCentered, y - 1, zCentered);
        if (blockEntity instanceof MachineGearBoxBlockEntity machineGearBoxBlockEntity) {
            torque = machineGearBoxBlockEntity.getTorque();
        }
        int frameCount = 0;
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                for (int yOffset = -1; yOffset <= 1; yOffset++) {
                    int blockId = world.getBlockId(xCentered + xOffset, y + yOffset, zCentered + zOffset);
                    if (blockId == BlockListener.machineFrame.id || blockId == BlockListener.plantBus.id || blockId == BlockListener.fertilizerBus.id) {
                        frameCount++;
                    }
                }
            }
        }
        if (frameCount < 25) {
            return false;
        }
        // Meta values to coordinates
        // 2 -> z+
        // 3 -> z-
        // 4 -> x+
        // 5 -> x-
        // Frame calculation
        // TODO: Calculate frame
        // Automatic input
        for (int zOffset = 1; zOffset >= -1; zOffset--) {
            for (int xOffset = 1; xOffset >= -1; xOffset--) {
                int ceilingBlockId = world.getBlockId(xCentered + xOffset, y + 1, zCentered + zOffset);
                boolean plantMode = ceilingBlockId == BlockListener.plantBus.id;
                boolean fertilizerMode = ceilingBlockId == BlockListener.fertilizerBus.id;
                if (!plantMode && !fertilizerMode) {
                    continue;
                }
                int lowerIndex;
                int upperIndex;
                if (plantMode) {
                    lowerIndex = SAPLING_START;
                    upperIndex = SAPLING_END;
                } else {
                    lowerIndex = FERTILIZER_START;
                    upperIndex = FERTILIZER_END;
                }
                if (world.getBlockEntity(xCentered + xOffset, y + 2, zCentered + zOffset) instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                    for (int slot = lowerIndex; slot <= upperIndex; slot++) {
                        if (basicItemChuteBlockEntity.storedItem == null) {
                            break;
                        }
                        if (treeFarmItemStacks[slot] == null) {
                            treeFarmItemStacks[slot] = basicItemChuteBlockEntity.storedItem;
                            basicItemChuteBlockEntity.storedItem = null;
                        } else if (treeFarmItemStacks[slot].isItemEqual(basicItemChuteBlockEntity.storedItem)) {
                            int totalCount = treeFarmItemStacks[slot].count + basicItemChuteBlockEntity.storedItem.count;
                            if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                                treeFarmItemStacks[slot].count = totalCount;
                                basicItemChuteBlockEntity.storedItem = null;
                            } else {
                                int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                                treeFarmItemStacks[slot].count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                                basicItemChuteBlockEntity.storedItem.count = leftovers;
                            }
                        }
                    }
                }
            }
        }
        // Automatic output
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                // Standard output from output slot
                BlockEntity target = world.getBlockEntity(xCentered + xOffset, y - 2, zCentered + zOffset);
                for (int slot = OUTPUT_START; slot <= OUTPUT_END; slot++) {
                    if (treeFarmItemStacks[slot] != null
                            && target instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                        if (basicItemChuteBlockEntity.storedItem == null) {
                            basicItemChuteBlockEntity.storedItem = treeFarmItemStacks[slot];
                            treeFarmItemStacks[slot] = null;
                        } else if (basicItemChuteBlockEntity.storedItem.isItemEqual(treeFarmItemStacks[slot])) {
                            int totalCount = treeFarmItemStacks[slot].count + basicItemChuteBlockEntity.storedItem.count;
                            if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                                basicItemChuteBlockEntity.storedItem.count = totalCount;
                                treeFarmItemStacks[slot] = null;
                            } else {
                                int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                                basicItemChuteBlockEntity.storedItem.count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                                treeFarmItemStacks[slot].count = leftovers;
                            }
                        }
                    }
                }
            }
        }
        return true;
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
