package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.newfrontiercraft.nfc.block.BrickOvenBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.registry.CokeOvenRecipeRegistry;
import net.newfrontiercraft.nfc.utils.CokeOvenResult;
import net.newfrontiercraft.nfc.utils.FuelLevelEnum;
import net.newfrontiercraft.nfc.utils.ItemMeta;

public class CokeOvenBlockEntity extends BlockEntity implements Inventory {

    private static final int OUTPUT_SLOT = 1;
    private static final int TEMPERATURE_MARGIN = 50;

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
        if (!world.isRemote && isMultiBlock) {
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
        // Automatic input
        boolean inputFull = false;
        for (int zOffset = 1; zOffset >= -1; zOffset--) {
            for (int xOffset = 1; xOffset >= -1; xOffset--) {
                if (world.getBlockEntity(xCentered + xOffset, y + 3, zCentered + zOffset) instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity) {
                    if (basicItemChuteBlockEntity.storedItem == null) {
                        continue;
                    }
                    if (furnaceItemStacks[0] == null) {
                        furnaceItemStacks[0] = basicItemChuteBlockEntity.storedItem;
                        basicItemChuteBlockEntity.storedItem = null;
                    } else if (furnaceItemStacks[0].isItemEqual(basicItemChuteBlockEntity.storedItem)) {
                        int totalCount = furnaceItemStacks[0].count + basicItemChuteBlockEntity.storedItem.count;
                        if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                            furnaceItemStacks[0].count = totalCount;
                            basicItemChuteBlockEntity.storedItem = null;
                        } else {
                            int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                            furnaceItemStacks[0].count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                            basicItemChuteBlockEntity.storedItem.count = leftovers;
                            inputFull = true;
                            break;
                        }
                    }
                }
            }
            if (inputFull) {
                break;
            }
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
        if (furnaceItemStacks[0] == null) {
            return false;
        }
        CokeOvenResult result = CokeOvenRecipeRegistry.getInstance().getResult(new ItemMeta(furnaceItemStacks[0].getItem(), furnaceItemStacks[0].getDamage()));
        if (result == null) {
            return false;
        }
        ItemStack itemStack = result.result();
        minimumRequiredHeatLevel = result.minimum().getHeat() - TEMPERATURE_MARGIN;
        maximumRequiredHeatLevel = result.maximum().getHeat() + TEMPERATURE_MARGIN;
        if (furnaceItemStacks[OUTPUT_SLOT] == null) {
            return true;
        }
        if (!furnaceItemStacks[OUTPUT_SLOT].copy().isItemEqual(itemStack)) {
            return false;
        }
        if (furnaceItemStacks[OUTPUT_SLOT].copy().count < getMaxCountPerStack()
                && furnaceItemStacks[OUTPUT_SLOT].copy().count + itemStack.copy().count < furnaceItemStacks[OUTPUT_SLOT].copy().getMaxCount()) {
            return true;
        }
        return furnaceItemStacks[OUTPUT_SLOT].copy().count + itemStack.copy().count <= itemStack.copy().getMaxCount();
    }

    public void smeltItem() {
        if (!canSmelt()) {
            return;
        }
        CokeOvenResult result = CokeOvenRecipeRegistry.getInstance().getResult(new ItemMeta(furnaceItemStacks[0].getItem(), furnaceItemStacks[0].getDamage()));
        if (result == null) {
            return;
        }
        ItemStack itemStack = result.result();
        minimumRequiredHeatLevel = result.minimum().getHeat() - TEMPERATURE_MARGIN;
        maximumRequiredHeatLevel = result.maximum().getHeat() + TEMPERATURE_MARGIN;
        if (furnaceItemStacks[OUTPUT_SLOT] == null) {
            furnaceItemStacks[OUTPUT_SLOT] = itemStack.copy();
        } else if (furnaceItemStacks[OUTPUT_SLOT].itemId == itemStack.copy().itemId) {
            furnaceItemStacks[OUTPUT_SLOT].count += itemStack.copy().count;
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

    public int getMaximumRequiredHeatLevel() {
        return maximumRequiredHeatLevel;
    }

    public void setHeatLevel(int heatLevel) {
        this.heatLevel = heatLevel;
    }

    public void setMinimumRequiredHeatLevel(int minimumRequiredHeatLevel) {
        this.minimumRequiredHeatLevel = minimumRequiredHeatLevel;
    }

    public void setMaximumRequiredHeatLevel(int maximumRequiredHeatLevel) {
        this.maximumRequiredHeatLevel = maximumRequiredHeatLevel;
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

}
