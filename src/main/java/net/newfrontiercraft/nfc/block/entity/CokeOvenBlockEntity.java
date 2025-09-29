package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.newfrontiercraft.nfc.block.BrickOvenBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.utils.FuelLevelEnum;

public class CokeOvenBlockEntity extends BlockEntity implements Inventory {

    private static final int OUTPUT_SLOT = 1;

    private ItemStack[] furnaceItemStacks;
    public int furnaceCookTime;
    public int requiredTime = 200;
    public boolean isMultiBlock;
    private int checkTimer;
    private int heatLevel;
    private int minimumRequiredHeatLevel;
    private int maximumRequiredHeatLevel;
    private static final int MAXIMUM_ADDED_BURN_TIME = FuelLevelEnum.SUPERHEATED.getHeat();
    private boolean scheduledForRemoval = false;

    public CokeOvenBlockEntity() {
        // First slots 0 to 8 are inputs. 9 is fuel, 10 is output
        furnaceItemStacks = new ItemStack[2];
        furnaceCookTime = 0;
        isMultiBlock = false;
    }

    @Override
    public int size() {
        return furnaceItemStacks.length;
    }

    @Override
    public ItemStack getStack(int i) {
        return furnaceItemStacks[i];
    }

    @Override
    public ItemStack removeStack(int i, int j) {
        if (furnaceItemStacks[i] != null) {
            if (furnaceItemStacks[i].count <= j) {
                ItemStack itemstack = furnaceItemStacks[i];
                furnaceItemStacks[i] = null;
                return itemstack;
            }
            ItemStack itemstack1 = furnaceItemStacks[i].split(j);
            if (furnaceItemStacks[i].count == 0) {
                furnaceItemStacks[i] = null;
            }
            return itemstack1;
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int i, ItemStack itemstack) {
        furnaceItemStacks[i] = itemstack;
        if (itemstack != null && itemstack.count > getMaxCountPerStack()) {
            itemstack.count = getMaxCountPerStack();
        }
    }

    @Override
    public String getName() {
        return "Coke Oven";
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound) {
        super.readNbt(nbttagcompound);
        NbtList nbttaglist = nbttagcompound.getList("Items");
        furnaceItemStacks = new ItemStack[size()];
        for (int i = 0; i < nbttaglist.size(); i++) {
            NbtCompound nbttagcompound1 = (NbtCompound) nbttaglist
                    .get(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < furnaceItemStacks.length) {
                furnaceItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }

        furnaceCookTime = nbttagcompound.getShort("CookTime");
        isMultiBlock = nbttagcompound.getBoolean("IsMultiBlock");
        checkTimer = nbttagcompound.getInt("CheckTimer");
        heatLevel = nbttagcompound.getInt("HeatLevel");
        minimumRequiredHeatLevel = nbttagcompound.getInt("MinimumRequiredHeatLevel");
        maximumRequiredHeatLevel = nbttagcompound.getInt("MaximumRequiredHeatLevel");
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound) {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putShort("CookTime", (short) furnaceCookTime);
        NbtList nbttaglist = new NbtList();
        for (int i = 0; i < furnaceItemStacks.length; i++) {
            if (furnaceItemStacks[i] != null) {
                NbtCompound nbttagcompound1 = new NbtCompound();
                nbttagcompound1.putByte("Slot", (byte) i);
                furnaceItemStacks[i].writeNbt(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.put("Items", nbttaglist);
        nbttagcompound.putBoolean("IsMultiBlock", isMultiBlock);
        nbttagcompound.putInt("CheckTimer", checkTimer);
        nbttagcompound.putInt("HeatLevel", heatLevel);
        nbttagcompound.putInt("MinimumRequiredHeatLevel", minimumRequiredHeatLevel);
        nbttagcompound.putInt("MaximumRequiredHeatLevel", maximumRequiredHeatLevel);
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    public int getCookProgressScaled(int i) {
        return (furnaceCookTime * i) / requiredTime;
    }

    @Override
    public void tick() {
        boolean previouslyActive = heatLevel > 0;
        if (scheduledForRemoval) {
            return;
        }
        if (checkTimer < 40) {
            checkTimer++;
        } else {
            isMultiBlock = checkMultiBlockStructure();
            checkTimer = 0;
        }
        if (!world.isRemote) {
            if (canSmelt() && heatLevel >= minimumRequiredHeatLevel && heatLevel <= maximumRequiredHeatLevel) {
                furnaceCookTime++;
                if (furnaceCookTime >= requiredTime) {
                    furnaceCookTime = 0;
                    smeltItem();
                }
            } else {
                furnaceCookTime = 0;
            }
        }
        boolean currentlyActive = heatLevel > 0;
        if (previouslyActive != currentlyActive) {
            BrickOvenBlock.updateFurnaceBlockState(currentlyActive, world, x, y, z);
        }
    }

    private boolean checkMultiBlockStructure() {
        // Handle duplicate block entities
        if (scheduledForRemoval) {
            return false;
        }
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (blockEntity != this) {
            scheduledForRemoval = true;
            return false;
        }
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
        if (world.getBlockId(xCentered, y, zCentered) != 0) {
            return false;
        }
        if (world.getBlockId(xCentered, y + 1, zCentered) != 0) {
            return false;
        }
        if (world.getBlockId(xCentered, y - 1, zCentered) != BlockListener.heatCoil.id) {
            return false;
        }
        int brickCount = 0;
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 2; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    if (world.getBlockId(xCentered + xOffset, y + yOffset, zCentered + zOffset) == BlockListener.cokeOvenBricks.id) {
                        brickCount++;
                    }
                }
            }
        }
        if (brickCount != 32) {
            return false;
        }
        // Extract heat
        HeatCoilBlockEntity heatSource = (HeatCoilBlockEntity) world.getBlockEntity(xCentered, y - 1, zCentered);
        int heatSourceValue = heatSource.getHeatLevel();
        if (heatSourceValue > 0) {
            if (isMultiBlock) {
                heatLevel = Math.max(heatLevel - 40, 0);
            }
        }
        if (heatSourceValue > heatLevel) {
            int heatGap = heatSourceValue - heatLevel;
            int extractedHeat = Math.min(160, heatGap);
            heatLevel += extractedHeat;
            heatSource.changeHeatLevel(-extractedHeat);
        } else if (heatSourceValue < heatLevel) {
            heatLevel = heatSourceValue;
        }
        // Meta values to coordinates
        // 2 -> z+
        // 3 -> z-
        // 4 -> x+
        // 5 -> x-
        // Automatic input
        // TODO: Replace with more simple code without rotation and for only handling a single input slot
        switch (meta) {
            case 2:
                for (int zOffset = 1; zOffset >= -1; zOffset--) {
                    for (int xOffset = 1; xOffset >= -1; xOffset--) {
                        if (world.getBlockEntity(xCentered + xOffset, y + 2, zCentered + zOffset) instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                            if (basicItemChuteBlockEntity.storedItem == null) {
                                continue;
                            }
                            int ovenIndex = (-xOffset + 1) + (-zOffset + 1) * 3;
                            if (furnaceItemStacks[ovenIndex] == null) {
                                furnaceItemStacks[ovenIndex] = basicItemChuteBlockEntity.storedItem;
                                basicItemChuteBlockEntity.storedItem = null;
                            } else if (furnaceItemStacks[ovenIndex].isItemEqual(basicItemChuteBlockEntity.storedItem)) {
                                int totalCount = furnaceItemStacks[ovenIndex].count + basicItemChuteBlockEntity.storedItem.count;
                                if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                                    furnaceItemStacks[ovenIndex].count = totalCount;
                                    basicItemChuteBlockEntity.storedItem = null;
                                } else {
                                    int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    furnaceItemStacks[ovenIndex].count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    basicItemChuteBlockEntity.storedItem.count = leftovers;
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    for (int xOffset = -1; xOffset <= 1; xOffset++) {
                        if (world.getBlockEntity(xCentered + xOffset, y + 2, zCentered + zOffset) instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                            if (basicItemChuteBlockEntity.storedItem == null) {
                                continue;
                            }
                            int ovenIndex = (xOffset + 1) + (zOffset + 1) * 3;
                            if (furnaceItemStacks[ovenIndex] == null) {
                                furnaceItemStacks[ovenIndex] = basicItemChuteBlockEntity.storedItem;
                                basicItemChuteBlockEntity.storedItem = null;
                            } else if (furnaceItemStacks[ovenIndex].isItemEqual(basicItemChuteBlockEntity.storedItem)) {
                                int totalCount = furnaceItemStacks[ovenIndex].count + basicItemChuteBlockEntity.storedItem.count;
                                if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                                    furnaceItemStacks[ovenIndex].count = totalCount;
                                    basicItemChuteBlockEntity.storedItem = null;
                                } else {
                                    int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    furnaceItemStacks[ovenIndex].count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    basicItemChuteBlockEntity.storedItem.count = leftovers;
                                }
                            }
                        }
                    }
                }
                break;
            case 4:
                for (int xOffset = 1; xOffset >= -1; xOffset--) {
                    for (int zOffset = -1; zOffset <= 1; zOffset++) {
                        if (world.getBlockEntity(xCentered + xOffset, y + 2, zCentered + zOffset) instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                            if (basicItemChuteBlockEntity.storedItem == null) {
                                continue;
                            }
                            int ovenIndex = (zOffset + 1) + (-xOffset + 1) * 3;
                            if (furnaceItemStacks[ovenIndex] == null) {
                                furnaceItemStacks[ovenIndex] = basicItemChuteBlockEntity.storedItem;
                                basicItemChuteBlockEntity.storedItem = null;
                            } else if (furnaceItemStacks[ovenIndex].isItemEqual(basicItemChuteBlockEntity.storedItem)) {
                                int totalCount = furnaceItemStacks[ovenIndex].count + basicItemChuteBlockEntity.storedItem.count;
                                if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                                    furnaceItemStacks[ovenIndex].count = totalCount;
                                    basicItemChuteBlockEntity.storedItem = null;
                                } else {
                                    int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    furnaceItemStacks[ovenIndex].count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    basicItemChuteBlockEntity.storedItem.count = leftovers;
                                }
                            }
                        }
                    }
                }
                break;
            case 5:
                for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    for (int zOffset = 1; zOffset >= -1; zOffset--) {
                        if (world.getBlockEntity(xCentered + xOffset, y + 2, zCentered + zOffset) instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                            if (basicItemChuteBlockEntity.storedItem == null) {
                                continue;
                            }
                            int ovenIndex = (-zOffset + 1) + (xOffset + 1) * 3;
                            if (furnaceItemStacks[ovenIndex] == null) {
                                furnaceItemStacks[ovenIndex] = basicItemChuteBlockEntity.storedItem;
                                basicItemChuteBlockEntity.storedItem = null;
                            } else if (furnaceItemStacks[ovenIndex].isItemEqual(basicItemChuteBlockEntity.storedItem)) {
                                int totalCount = furnaceItemStacks[ovenIndex].count + basicItemChuteBlockEntity.storedItem.count;
                                if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                                    furnaceItemStacks[ovenIndex].count = totalCount;
                                    basicItemChuteBlockEntity.storedItem = null;
                                } else {
                                    int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    furnaceItemStacks[ovenIndex].count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                                    basicItemChuteBlockEntity.storedItem.count = leftovers;
                                }
                            }
                        }
                    }
                }
                break;
        }
        // Automatic output
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int zOffset = -1; zOffset <= 1; zOffset++) {
                // Standard output from output slot
                BlockEntity target = world.getBlockEntity(xCentered + xOffset, y - 2, zCentered + zOffset);
                if (furnaceItemStacks[OUTPUT_SLOT] != null
                        && target instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity
                        && !(target instanceof FilteringItemChuteBlockEntity)) {
                    if (basicItemChuteBlockEntity.storedItem == null) {
                        basicItemChuteBlockEntity.storedItem = furnaceItemStacks[OUTPUT_SLOT];
                        furnaceItemStacks[OUTPUT_SLOT] = null;
                        return true;
                    } else if (basicItemChuteBlockEntity.storedItem.isItemEqual(furnaceItemStacks[OUTPUT_SLOT])) {
                        int totalCount = furnaceItemStacks[OUTPUT_SLOT].count + basicItemChuteBlockEntity.storedItem.count;
                        if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                            basicItemChuteBlockEntity.storedItem.count = totalCount;
                            furnaceItemStacks[OUTPUT_SLOT] = null;
                        } else {
                            int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                            basicItemChuteBlockEntity.storedItem.count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                            furnaceItemStacks[OUTPUT_SLOT].count = leftovers;
                        }
                    }
                }
                // Input byproduct clearing through filtered item chutes
                for (int slot = 0; slot < OUTPUT_SLOT; slot++) {
                    ItemStack slotItem = furnaceItemStacks[slot];
                    if (slotItem == null) {
                        continue;
                    }
                    if (target instanceof FilteringItemChuteBlockEntity filteringItemChuteBlockEntity) {
                        if (filteringItemChuteBlockEntity.filter == null) {
                            break;
                        }
                        if (filteringItemChuteBlockEntity.storedItem != null) {
                            break;
                        }
                        if (!slotItem.isItemEqual(filteringItemChuteBlockEntity.filter)) {
                            continue;
                        }
                        filteringItemChuteBlockEntity.storedItem = slotItem;
                        furnaceItemStacks[slot] = null;
                    }
                }
            }
        }
        return true;
    }

    private boolean canSmelt() {
        boolean isGridEmpty = true;
        for (int i = 0; i < OUTPUT_SLOT; i++) {
            if (furnaceItemStacks[i] != null) {
                isGridEmpty = false;
                break;
            }
        }
        if (isGridEmpty) {
            return false;
        }
//        ItemStack itemstack = BrickOvenManager.getInstance().findMatchingRecipe(furnaceItemStacks, this);
        // TODO: Introduce recipe registry
        ItemStack itemstack = null;
        if (itemstack == null) {
            return false;
        }
        if (furnaceItemStacks[OUTPUT_SLOT] == null) {
            return true;
        }
        if (!furnaceItemStacks[OUTPUT_SLOT].copy().isItemEqual(itemstack)) {
            return false;
        }
        if (furnaceItemStacks[OUTPUT_SLOT].copy().count < getMaxCountPerStack()
                && furnaceItemStacks[OUTPUT_SLOT].copy().count + itemstack.copy().count < furnaceItemStacks[OUTPUT_SLOT].copy().getMaxCount()) {
            return true;
        }
        return furnaceItemStacks[OUTPUT_SLOT].copy().count + itemstack.copy().count <= itemstack.copy().getMaxCount();
    }

    public void smeltItem() {
        if (!canSmelt()) {
            return;
        }
//        ItemStack itemstack = BrickOvenManager.getInstance().findMatchingRecipe(furnaceItemStacks, this);
        // TODO: Introduce recipe registry
        ItemStack itemstack = null;
        if (furnaceItemStacks[OUTPUT_SLOT] == null) {
            furnaceItemStacks[OUTPUT_SLOT] = itemstack.copy();
        } else if (furnaceItemStacks[OUTPUT_SLOT].itemId == itemstack.copy().itemId) {
            furnaceItemStacks[OUTPUT_SLOT].count += itemstack.copy().count;
        }

        //Removed container item code
        for(int i = 0; i < OUTPUT_SLOT; i++)
        {
            if(furnaceItemStacks[i] != null){
                furnaceItemStacks[i].count--;
                if (furnaceItemStacks[i].copy().count <= 0) {
                    furnaceItemStacks[i] = null;
                }
            }
        }
    }

    public int getHeatLevel() {
        return heatLevel;
    }

    public int getMinimumRequiredHeatLevel() {
        return minimumRequiredHeatLevel;
    }

    public void resetHeatRequirement() {
        this.minimumRequiredHeatLevel = 0;
    }

    public boolean canPlayerUse(PlayerEntity entityplayer) {
        if (scheduledForRemoval) {
            return false;
        }
        if (world.getBlockEntity(x, y, z) != this) {
            return false;
        }
        return entityplayer.getSquaredDistance((double) x + 0.5D,
                (double) y + 0.5D, (double) z + 0.5D) <= 64D;
    }

    public void setTimeAndHeat(int i, int requiredHeatLevel){
        requiredTime = i;
        this.minimumRequiredHeatLevel = requiredHeatLevel;
    }

}
