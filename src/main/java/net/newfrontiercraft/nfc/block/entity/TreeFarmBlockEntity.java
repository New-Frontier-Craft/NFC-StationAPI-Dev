package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.newfrontiercraft.nfc.block.TreeFarmBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.registry.TreeFarmHarvestingRegistry;
import net.newfrontiercraft.nfc.registry.TreeFarmPlantingRegistry;
import net.newfrontiercraft.nfc.utils.BlockAndMetaRange;
import net.newfrontiercraft.nfc.utils.ChanceDrop;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.utils.PlantingRequirement;

import java.util.Random;

public class TreeFarmBlockEntity extends BlockEntity implements Inventory {

    public static final int RECIPE_TIME = 1000;
    private static final int SAPLING_START = 0;
    private static final int SAPLING_END = 2;
    private static final int FERTILIZER_START = 3;
    private static final int FERTILIZER_END = 5;
    private static final int OUTPUT_START = 6;
    private static final int OUTPUT_END = 14;

    private ItemStack[] treeFarmItemStacks;
    private final Random random = new Random();
    public int craftingProgress;
    private int torque;
    private int checkTimer;
    public boolean isMultiBlock;
    private int xSize;
    private int ySize;
    private int zSize;
    private int xStart;
    private int yStart;
    private int zStart;

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
        /// Harvesting logic
        for (int xOffset = 0; xOffset < xSize; xOffset++) {
            for (int yOffset = 0; yOffset < ySize; yOffset++) {
                for (int zOffset = 0; zOffset < zSize; zOffset++) {
                    int combinedX = xStart + xOffset;
                    int combinedY = yStart + yOffset;
                    int combinedZ = zStart + zOffset;
                    int blockId = world.getBlockId(combinedX, combinedY, combinedZ);
                    if (blockId == 0) {
                        continue;
                    }
                    Block block = Block.BLOCKS[blockId];
                    if (block == null) {
                        continue;
                    }
                    ChanceDrop[] chanceDrops = TreeFarmHarvestingRegistry.getInstance().getResult(new ItemMeta(block.asItem(), world.getBlockMeta(combinedX, combinedY, combinedZ)));
                    if (chanceDrops == null) {
                        continue;
                    }
                    for (ChanceDrop chanceDrop : chanceDrops) {
                        if (random.nextFloat() > chanceDrop.chance()) {
                            continue;
                        }
                        insertItem(chanceDrop.drop());
                    }
                    world.setBlock(combinedX, combinedY, combinedZ, 0);
                }
            }
        }
        /// Replanting
        plantPlant();
        /// Fertilize
        // TODO: Add fertilizing logic
        fertilizePlant();
    }

    private void fertilizePlant() {
        // Check if there is fertilizer
        boolean foundFertilizer = false;
        int fertilizerIndex = FERTILIZER_START;
        for (; fertilizerIndex <= FERTILIZER_END; fertilizerIndex++) {
            if (treeFarmItemStacks[fertilizerIndex] != null) {
                foundFertilizer = true;
                break;
            }
        }
        if (!foundFertilizer) {
            return;
        }
        if (treeFarmItemStacks[fertilizerIndex].getItem() != Item.DYE && treeFarmItemStacks[fertilizerIndex].getDamage() != 15) {
            return;
        }
        // Find block to fertilize
        for (int xOffset = 0; xOffset < xSize; xOffset++) {
            for (int zOffset = 0; zOffset < zSize; zOffset++) {
                int combinedX = xStart + xOffset;
                int combinedZ = zStart + zOffset;
                int blockId = world.getBlockId(combinedX, yStart, combinedZ);
                if (blockId == 0) {
                    continue;
                }
                Block block = Block.BLOCKS[blockId];
                if (block == null) {
                    continue;
                }
                if (!block.onBonemealUse(world, combinedX, yStart, combinedZ, block.getDefaultState())) {
                    continue;
                }
                treeFarmItemStacks[fertilizerIndex].count--;
                if (treeFarmItemStacks[fertilizerIndex].count == 0) {
                    treeFarmItemStacks[fertilizerIndex] = null;
                    return;
                }
            }
        }
    }

    private void plantPlant() {
        /// Replanting logic
        // Check if there is anything to plant
        boolean foundPlant = false;
        int plantIndex = SAPLING_START;
        for (; plantIndex <= SAPLING_END; plantIndex++) {
            if (treeFarmItemStacks[plantIndex] != null) {
                foundPlant = true;
                break;
            }
        }
        if (!foundPlant) {
            return;
        }
        // Verify that it can be planted
        ItemStack plantItem = treeFarmItemStacks[plantIndex];
        if (plantItem == null) {
            return;
        }
        PlantingRequirement plantingRequirement = TreeFarmPlantingRegistry.getInstance().getResult(new ItemMeta(plantItem.getItem(), plantItem.getDamage()));
        if (plantingRequirement == null) {
            return;
        }
        // Attempt to plant
        for (int xOffset = 0; xOffset < xSize; xOffset++) {
            for (int zOffset = 0; zOffset < zSize; zOffset++) {
                int combinedX = xStart + xOffset;
                int combinedZ = zStart + zOffset;
                if (world.getBlockId(combinedX, yStart, combinedZ) != 0) {
                    continue;
                }
                int belowBlock = world.getBlockId(combinedX, yStart - 1, combinedZ);
                int blowMeta = world.getBlockMeta(combinedX, yStart - 1, combinedZ);
                boolean foundMatch = false;
                for (int i = 0; i < plantingRequirement.validOptions().length; i++) {
                    BlockAndMetaRange validOption = plantingRequirement.validOptions()[i];
                    if (belowBlock != validOption.block().id) {
                        continue;
                    }
                    for (int j = 0; j < validOption.metaValues().length; j++) {
                        if (blowMeta == validOption.metaValues()[j]) {
                            foundMatch = true;
                            break;
                        }
                    }
                    if (!foundMatch) {
                        continue;
                    }
                    world.setBlock(combinedX, yStart, combinedZ, plantingRequirement.placedBlock().id, plantingRequirement.placedBlockMeta());
                    treeFarmItemStacks[plantIndex].count--;
                    if (treeFarmItemStacks[plantIndex].count == 0) {
                        treeFarmItemStacks[plantIndex] = null;
                        return;
                    }
                    break;
                }
            }
        }
    }

    private void insertItem(ItemMeta itemMeta) {
        for (int i = OUTPUT_START; i <= OUTPUT_END; i++) {
            if (treeFarmItemStacks[i] == null) {
                treeFarmItemStacks[i] = new ItemStack(itemMeta.item(), 1, itemMeta.meta());
                break;
            } else if (treeFarmItemStacks[i].itemId == itemMeta.item().id && treeFarmItemStacks[i].getDamage() == itemMeta.meta() && treeFarmItemStacks[i].count < treeFarmItemStacks[i].getMaxCount()) {
                treeFarmItemStacks[i].count++;
                break;
            }
        }
    }

    private boolean checkMultiBlockStructure() {
        /// Calculate central coordinates
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
        /// Check structural integrity
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
        /// Frame calculation
        // Determine corner
        int leftFrameX = 0;
        int leftFrameZ = 0;
        int rightFrameX = 0;
        int rightFrameZ = 0;
        switch (meta) {
            case 2:
                leftFrameX = 1;
                rightFrameX = -1;
                leftFrameZ = 3;
                rightFrameZ = 3;
                break;
            case 3:
                leftFrameX = -1;
                rightFrameX = 1;
                leftFrameZ = -3;
                rightFrameZ = -3;
                break;
            case 4:
                leftFrameX = 3;
                rightFrameX = 3;
                leftFrameZ = -1;
                rightFrameZ = 1;
                break;
            case 5:
                leftFrameX = -3;
                rightFrameX = -3;
                leftFrameZ = 1;
                rightFrameZ = -1;
                break;
        }
        boolean foundLeftCorner = world.getBlockId(x + leftFrameX, y - 1, z + leftFrameZ) == BlockListener.machineFrame.id;
        boolean foundRightCorner = world.getBlockId(x + rightFrameX, y - 1, z + rightFrameZ) == BlockListener.machineFrame.id;
        if (foundLeftCorner) {
            switch (meta) {
                case 2:
                    measureFrame(x + leftFrameX, y - 1, z + leftFrameZ, -1, 1);
                    break;
                case 3:
                    measureFrame(x + leftFrameX, y - 1, z + leftFrameZ, 1, -1);
                    break;
                case 4:
                    measureFrame(x + leftFrameX, y - 1, z + leftFrameZ, 1, 1);
                    break;
                case 5:
                    measureFrame(x + leftFrameX, y - 1, z + leftFrameZ, -1, -1);
                    break;
            }
        } else if (foundRightCorner) {
            switch (meta) {
                case 2:
                    measureFrame(x + rightFrameX, y - 1, z + rightFrameZ, 1, 1);
                    break;
                case 3:
                    measureFrame(x + rightFrameX, y - 1, z + rightFrameZ, -1, -1);
                    break;
                case 4:
                    measureFrame(x + rightFrameX, y - 1, z + rightFrameZ, 1, -1);
                    break;
                case 5:
                    measureFrame(x + rightFrameX, y - 1, z + rightFrameZ, -1, 1);
                    break;
            }
        }
        /// Automatic input
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
        /// Automatic output
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

    // x direction and z direction determine where to search for frames
    // Frame meta to coordinate axis: 0 -> y, 1 -> z, 2 -> x
    private void measureFrame(int localX, int localY, int localZ, int xDirection, int zDirection) {
        /// Measure first 3 lines from the starting point
        // Measure x length
        int xLength = measureXLine(localX, localY, localZ, xDirection);
        if (xLength == 0) {
            return;
        }
        // Measure z length
        int zLength = measureZLine(localX, localY, localZ, zDirection);
        if (zLength == 0) {
            return;
        }
        /// Verify parallel lines on ground
        int movedX = localX + (xLength - 1) * xDirection;
        int movedZ = localZ + (zLength - 1) * zDirection;
        // Verify second x length
        if (xLength != measureXLine(localX, localY, movedZ, xDirection)) {
            return;
        }
        // Verify second z length
        if (zLength != measureZLine(movedX, localY, localZ, zDirection)) {
            return;
        }
        /// Measure and verify all vertical lines
        // Measure y length
        int yLength = measureYLine(localX, localY, localZ);
        if (yLength == 0) {
            return;
        }
        // Verify other 3 vertical lines
        if (yLength != measureYLine(movedX, localY, localZ)) {
            return;
        }
        if (yLength != measureYLine(localX, localY, movedZ)) {
            return;
        }
        if (yLength != measureYLine(movedX, localY, movedZ)) {
            return;
        }
        /// Verify upper layer
        int movedY = localY + yLength - 1;
        // Verify upper x lines
        if (xLength != measureXLine(localX, movedY, localZ, xDirection)) {
            return;
        }
        if (xLength != measureXLine(localX, movedY, movedZ, xDirection)) {
            return;
        }
        // Verify upper z lines
        if (zLength != measureZLine(localX, movedY, localZ, zDirection)) {
            return;
        }
        if (zLength != measureZLine(movedX, movedY, localZ, zDirection)) {
            return;
        }
        /// Update machine values to new measurements
        // Save size values
        xSize = xLength;
        ySize = yLength;
        zSize = zLength;
        // Save starting position
        yStart = localY;
        if (xDirection == 1 && zDirection == 1) {
            xStart = localX;
            zStart = localZ;
        } else if (xDirection == -1 && zDirection == 1) {
            xStart = movedX;
            zStart = localZ;
        } else if (xDirection == 1 && zDirection == -1) {
            xStart = localX;
            zStart = movedZ;
        } else if (xDirection == -1 && zDirection == -1) {
        xStart = movedX;
        zStart = movedZ;
    }
}

    private int measureXLine(int localX, int localY, int localZ, int xDirection) {
        if (xDirection == 1) {
            for (int xOffset = 1; xOffset < 32; xOffset++) {
                int blockId = world.getBlockId(localX + xOffset, localY, localZ);
                if (blockId == BlockListener.machineFrame.id) {
                    return xOffset + 1;
                } else if (blockId != BlockListener.frame.id || world.getBlockMeta(localX + xOffset, localY, localZ) != 2) {
                    xSize = 0;
                    ySize = 0;
                    zSize = 0;
                    return 0;
                }
            }
        } else {
            for (int xOffset = -1; xOffset > -32; xOffset--) {
                int blockId = world.getBlockId(localX + xOffset, localY, localZ);
                if (blockId == BlockListener.machineFrame.id) {
                    return -(xOffset - 1);
                } else if (blockId != BlockListener.frame.id || world.getBlockMeta(localX + xOffset, localY, localZ) != 2) {
                    xSize = 0;
                    ySize = 0;
                    zSize = 0;
                    return 0;
                }
            }
        }
        return 0;
    }

    private int measureZLine(int localX, int localY, int localZ, int zDirection) {
        if (zDirection == 1) {
            for (int zOffset = 1; zOffset < 32; zOffset++) {
                int blockId = world.getBlockId(localX, localY, localZ + zOffset);
                if (blockId == BlockListener.machineFrame.id) {
                    return zOffset + 1;
                } else if (blockId != BlockListener.frame.id || world.getBlockMeta(localX, localY, localZ + zOffset) != 1) {
                    xSize = 0;
                    ySize = 0;
                    zSize = 0;
                    return 0;
                }
            }
        } else {
            for (int zOffset = -1; zOffset > -32; zOffset--) {
                int blockId = world.getBlockId(localX, localY, localZ + zOffset);
                if (blockId == BlockListener.machineFrame.id) {
                    return -(zOffset - 1);
                } else if (blockId != BlockListener.frame.id || world.getBlockMeta(localX, localY, localZ + zOffset) != 1) {
                    xSize = 0;
                    ySize = 0;
                    zSize = 0;
                    return 0;
                }
            }
        }
        return 0;
    }

    private int measureYLine(int localX, int localY, int localZ) {
        for (int yOffset = 1; yOffset < 32; yOffset++) {
            int blockId = world.getBlockId(localX, localY + yOffset, localZ);
            if (blockId == BlockListener.machineFrame.id) {
                return yOffset + 1;
            } else if (blockId != BlockListener.frame.id || world.getBlockMeta(localX, localY + yOffset, localZ) != 0) {
                xSize = 0;
                ySize = 0;
                zSize = 0;
                return 0;
            }
        }
        return 0;
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
        xSize = nbtCompound.getInt("XSize");
        ySize = nbtCompound.getInt("YSize");
        zSize = nbtCompound.getInt("ZSize");
        xStart = nbtCompound.getInt("XStart");
        yStart = nbtCompound.getInt("YStart");
        zStart = nbtCompound.getInt("ZStart");
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
        nbtCompound.putInt("XSize", xSize);
        nbtCompound.putInt("YSize", ySize);
        nbtCompound.putInt("ZSize", zSize);
        nbtCompound.putInt("XStart", xStart);
        nbtCompound.putInt("YStart", yStart);
        nbtCompound.putInt("ZStart", zStart);
    }

    public boolean hasFrame() {
        return xSize > 0 && ySize > 0 && zSize > 0;
    }

    public int getScaledCraftingProgress(int scale) {
        return (craftingProgress * scale) / RECIPE_TIME;
    }
}
