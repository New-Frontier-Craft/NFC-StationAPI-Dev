package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.newfrontiercraft.nfc.api.HeatConsumer;
import net.newfrontiercraft.nfc.block.BrickOvenBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.registry.BrickOvenManager;

public class BrickOvenBlockEntity extends BlockEntity implements Inventory, HeatConsumer {

    private ItemStack[] furnaceItemStacks;
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    public int requiredTime = 200;
    public boolean isMultiBlock;
    private int checkTimer;
    private static final int MAXIMUM_ADDED_BURN_TIME = 1000;
    private boolean scheduledForRemoval = false;

    public BrickOvenBlockEntity() {
        // First slots 0 to 8 are inputs. 9 is fuel, 10 is output
        furnaceItemStacks = new ItemStack[11];
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
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
        return "Brick Oven";
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

        furnaceBurnTime = nbttagcompound.getShort("BurnTime");
        furnaceCookTime = nbttagcompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(furnaceItemStacks[9]);
        isMultiBlock = nbttagcompound.getBoolean("IsMultiBlock");
        checkTimer = nbttagcompound.getInt("CheckTimer");
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound) {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putShort("BurnTime", (short) furnaceBurnTime);
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
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    public int getCookProgressScaled(int i) {
        return (furnaceCookTime * i) / requiredTime;
    }

    public int getBurnTimeRemainingScaled(int i) {
        if (currentItemBurnTime == 0) {
            currentItemBurnTime = 200;
        }
        return (furnaceBurnTime * i) / currentItemBurnTime;
    }

    public boolean isBurning() {
        return furnaceBurnTime > 0;
    }

    @Override
    public void tick() {
        if (scheduledForRemoval) {
            return;
        }
        boolean previouslyActive = furnaceBurnTime > 0;
        if (checkTimer < 40) {
            checkTimer++;
        } else {
            isMultiBlock = checkMultiBlockStructure();
            checkTimer = 0;
        }
        if (furnaceBurnTime > 0) {
            if (isMultiBlock) {
                furnaceBurnTime -= 4;
            } else {
                furnaceBurnTime--;
            }
        } else if (furnaceBurnTime < 0) {
            furnaceBurnTime = 0;
        }
        if (!world.isRemote) {
            if (furnaceBurnTime == 0 && canSmelt()) {
                currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[9]);
                if (furnaceBurnTime > 0) {
                    if (furnaceItemStacks[9] != null) {
                        furnaceItemStacks[9].count--;
                        if (furnaceItemStacks[9].getItem() instanceof BucketItem) {
                            furnaceItemStacks[9] = new ItemStack(Item.BUCKET);
                        } else
                        if (furnaceItemStacks[9].count == 0) {
                            furnaceItemStacks[9] = null;
                        }
                    }
                }
            }
            if (isBurning() && canSmelt()) {
                if (isMultiBlock) {
                    furnaceCookTime += 8;
                } else {
                    furnaceCookTime++;
                }
                if (furnaceCookTime >= requiredTime) {
                    furnaceCookTime = 0;
                    smeltItem();
                }
            } else {
                furnaceCookTime = 0;
            }
        }
        boolean currentlyActive = furnaceBurnTime > 0;
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
        if (world.getBlockId(xCentered, y - 1, zCentered) != BlockListener.heatCoil.id) {
            return false;
        }
        int brickCount = 0;
        for (int xOffset = -1; xOffset <= 1; xOffset++) {
            for (int yOffset = -1; yOffset <= 1; yOffset++) {
                for (int zOffset = -1; zOffset <= 1; zOffset++) {
                    if (world.getBlockId(xCentered + xOffset, y + yOffset, zCentered + zOffset) == BlockListener.firedBricks.id) {
                        brickCount++;
                    }
                }
            }
        }
        if (brickCount != 24) {
            return false;
        }
        // Extract heat
        HeatCoilBlockEntity heatSource = (HeatCoilBlockEntity) world.getBlockEntity(xCentered, y - 1, zCentered);
        int heatSourceValue = heatSource.getHeatLevel();
        if (heatSourceValue > furnaceBurnTime && furnaceBurnTime < MAXIMUM_ADDED_BURN_TIME && furnaceBurnTime >= 0) {
            int transferredHeat = (heatSourceValue - furnaceBurnTime)/2;
            furnaceBurnTime += transferredHeat;
            heatSource.changeHeatLevel(-transferredHeat);
        }
        // Meta values to coordinates
        // 2 -> z+
        // 3 -> z-
        // 4 -> x+
        // 5 -> x-
        // Automatic input
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
                if (furnaceItemStacks[10] != null
                        && target instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity
                        && !(target instanceof FilteringItemChuteBlockEntity)) {
                    if (basicItemChuteBlockEntity.storedItem == null) {
                        basicItemChuteBlockEntity.storedItem = furnaceItemStacks[10];
                        furnaceItemStacks[10] = null;
                        return true;
                    } else if (basicItemChuteBlockEntity.storedItem.isItemEqual(furnaceItemStacks[10])) {
                        int totalCount = furnaceItemStacks[10].count + basicItemChuteBlockEntity.storedItem.count;
                        if (totalCount <= basicItemChuteBlockEntity.storedItem.getMaxCount()) {
                            basicItemChuteBlockEntity.storedItem.count = totalCount;
                            furnaceItemStacks[10] = null;
                        } else {
                            int leftovers = totalCount - basicItemChuteBlockEntity.storedItem.getMaxCount();
                            basicItemChuteBlockEntity.storedItem.count = basicItemChuteBlockEntity.storedItem.getMaxCount();
                            furnaceItemStacks[10].count = leftovers;
                        }
                    }
                }
                // Input byproduct clearing through filtered item chutes
                for (int slot = 0; slot < 9; slot++) {
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
        ItemStack itemstack = BrickOvenManager.getInstance().findMatchingRecipe(furnaceItemStacks, this);
        if (itemstack == null) {
            return false;
        }
        if (furnaceItemStacks[10] == null) {
            return true;
        }
        if (!furnaceItemStacks[10].copy().isItemEqual(itemstack)) {
            return false;
        }
        if (furnaceItemStacks[10].copy().count < getMaxCountPerStack()
                && furnaceItemStacks[10].copy().count + itemstack.copy().count < furnaceItemStacks[10].copy().getMaxCount()) {
            return true;
        }
        return furnaceItemStacks[10].copy().count + itemstack.copy().count <= itemstack.copy().getMaxCount();
    }

    public void smeltItem() {
        if (!canSmelt()) {
            return;
        }
        ItemStack itemstack = BrickOvenManager.getInstance().findMatchingRecipe(furnaceItemStacks, this);
        if (furnaceItemStacks[10] == null) {
            furnaceItemStacks[10] = itemstack.copy();
        } else if (furnaceItemStacks[10].itemId == itemstack.copy().itemId) {
            furnaceItemStacks[10].count += itemstack.copy().count;
        }

        //Removed container item code
        for(int i = 0; i < 9; i++)
        {
            if(furnaceItemStacks[i] != null){
                furnaceItemStacks[i].count--;
                if (furnaceItemStacks[i].copy().count <= 0) {
                    furnaceItemStacks[i] = null;
                }
            }
        }
    }

    private int getItemBurnTime(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        int i = itemstack.itemId;
        int j = itemstack.getDamage();
        if(i == Item.STICK.id) {
            return 50;
        }
        if(i == Block.SAPLING.id) {
            return 50;
        }
        if(i < 256 && Block.BLOCKS[i].material == Material.WOOD) {
            return 100;
        }

        if(i == BlockListener.scaffoldBlock.id) {
            return 100;
        }
        if(i == BlockListener.fieryMushroom.id) {
            return 200;
        }

        if((i == Item.COAL.id  || i == ItemListener.netherAsh.id) && j == 0) {
            return 1600;
        } else if(i == Item.COAL.id) {
            return 800;
        }
        /*
        if((i == ItemListener.coalLump.id || i == ItemListener.nethercoalLump.id) && j == 0) {
            return 200;
        }
        if(i == ItemListener.coalLump.id) {
            return 100;
        }
        */
        if(i == BlockListener.coalBlock.id) {
            return 6400;
        }
        if(i == Item.LAVA_BUCKET.id) {
            return 9600;
        }
        if(i == ItemListener.anthracite.id) {
            return 11200;
        }
        if (i == ItemListener.oilBucket.id) {
            return 12800;
        }

        return 0;

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

    public void setTime(int i){
        requiredTime = i;
    }

    // Methods of heat interface
    @Override
    public int addHeat(int heat) {
        if (scheduledForRemoval) {
            return 0;
        }
        if (isMultiBlock || getItemBurnTime(furnaceItemStacks[9]) > 0 || furnaceBurnTime >= MAXIMUM_ADDED_BURN_TIME) {
            return 0;
        }
        int totalBurnTime = furnaceBurnTime + heat;
        BrickOvenBlock.updateFurnaceBlockState(true, world, x, y, z);
        if (totalBurnTime <= MAXIMUM_ADDED_BURN_TIME) {
            furnaceBurnTime = totalBurnTime;
            return heat;
        }
        furnaceBurnTime = MAXIMUM_ADDED_BURN_TIME;
        return heat - (totalBurnTime - MAXIMUM_ADDED_BURN_TIME);
    }

    @Override
    public int getHeat() {
        return furnaceBurnTime;
    }
}
