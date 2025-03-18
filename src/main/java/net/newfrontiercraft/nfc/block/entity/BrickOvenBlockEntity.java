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
import net.newfrontiercraft.nfc.block.BrickOvenBlock;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.registry.BrickOvenManager;

public class BrickOvenBlockEntity extends BlockEntity implements Inventory {

    public BrickOvenBlockEntity() {
        furnaceItemStacks = new ItemStack[11];
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
        furnaceCookTime = 0;
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
        boolean flag = furnaceBurnTime > 0;
        boolean flag1 = false;
        if (furnaceBurnTime > 0) {
            furnaceBurnTime--;
        }
        if (!world.isRemote) {
            if (furnaceBurnTime == 0 && canSmelt()) {
                currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[9]);
                if (furnaceBurnTime > 0) {
                    flag1 = true;
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
                furnaceCookTime++;
                if (furnaceCookTime >= requiredTime) {
                    furnaceCookTime = 0;
                    smeltItem();
                    flag1 = true;
                }
            } else {
                furnaceCookTime = 0;
            }
            if (flag != (furnaceBurnTime > 0)) {
                flag1 = true;
                BrickOvenBlock.updateFurnaceBlockState(furnaceBurnTime > 0,
                        world, x, y, z);
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        ItemStack itemstack = BrickOvenManager.smelting().findMatchingRecipe(furnaceItemStacks, this);
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
        ItemStack itemstack = BrickOvenManager.smelting().findMatchingRecipe(furnaceItemStacks, this);
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
        /*
        if(i == BlockListener.Scaffold.id) {
            return 100;
        }
        if(i == BlockListener.fireMushroom.id) {
            return 200;
        }
         */
        if((i == Item.COAL.id  || i == ItemListener.netherAsh.id) && j == 0) {
            return 1600;
        }
        if(i == Item.COAL.id) {
            return 800;
        }
        /*
        if((i == BlockListener.coalLump.id || i == BlockListener.nethercoalLump.id) && j == 0) {
            return 200;
        }
        if(i == BlockListener.coalLump.id) {
            return 100;
        }
        if(i == BlockListener.coalblock.id) {
            return 6400;
        }
         */
        if(i == Item.LAVA_BUCKET.id) {
            return 9600;
        }
        if(i == ItemListener.anthracite.id) {
            return 11200;
        }
        /*
        if (i == BlockListener.bucketOil.id) {
            return 12800;
        }
         */

        return 0;

    }

    public boolean canPlayerUse(PlayerEntity entityplayer) {
        if (world.getBlockEntity(x, y, z) != this) {
            return false;
        }
        return entityplayer.getSquaredDistance((double) x + 0.5D,
                (double) y + 0.5D, (double) z + 0.5D) <= 64D;
    }

    public void setTime(int i){
        requiredTime = i;
    }

    private ItemStack[] furnaceItemStacks;
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    public int requiredTime = 200;
}
