package net.newfrontiercraft.nfc.tileentities;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.tool.Bucket;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.io.ListTag;
import net.newfrontiercraft.nfc.blocks.BrickOven;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.registries.OvenManager;

public class TileEntityBrickOven extends TileEntityBase implements InventoryBase {

    public TileEntityBrickOven() {
        furnaceItemStacks = new ItemInstance[11];
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
        furnaceCookTime = 0;
    }

    @Override
    public int getInventorySize() {
        return furnaceItemStacks.length;
    }

    @Override
    public ItemInstance getInventoryItem(int i) {
        return furnaceItemStacks[i];
    }

    @Override
    public ItemInstance takeInventoryItem(int i, int j) {
        if (furnaceItemStacks[i] != null) {
            if (furnaceItemStacks[i].count <= j) {
                ItemInstance itemstack = furnaceItemStacks[i];
                furnaceItemStacks[i] = null;
                return itemstack;
            }
            ItemInstance itemstack1 = furnaceItemStacks[i].split(j);
            if (furnaceItemStacks[i].count == 0) {
                furnaceItemStacks[i] = null;
            }
            return itemstack1;
        } else {
            return null;
        }
    }

    @Override
    public void setInventoryItem(int i, ItemInstance itemstack) {
        furnaceItemStacks[i] = itemstack;
        if (itemstack != null && itemstack.count > getMaxItemCount()) {
            itemstack.count = getMaxItemCount();
        }
    }

    @Override
    public String getContainerName() {
        return "Brick Oven";
    }

    public void readIdentifyingData(CompoundTag nbttagcompound) {
        super.readIdentifyingData(nbttagcompound);
        ListTag nbttaglist = nbttagcompound.getListTag("Items");
        furnaceItemStacks = new ItemInstance[getInventorySize()];
        for (int i = 0; i < nbttaglist.size(); i++) {
            CompoundTag nbttagcompound1 = (CompoundTag) nbttaglist
                    .get(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < furnaceItemStacks.length) {
                furnaceItemStacks[byte0] = new ItemInstance(nbttagcompound1);
            }
        }

        furnaceBurnTime = nbttagcompound.getShort("BurnTime");
        furnaceCookTime = nbttagcompound.getShort("CookTime");
        currentItemBurnTime = getItemBurnTime(furnaceItemStacks[9]);
    }

    public void writeIdentifyingData(CompoundTag nbttagcompound) {
        super.writeIdentifyingData(nbttagcompound);
        nbttagcompound.put("BurnTime", (short) furnaceBurnTime);
        nbttagcompound.put("CookTime", (short) furnaceCookTime);
        ListTag nbttaglist = new ListTag();
        for (int i = 0; i < furnaceItemStacks.length; i++) {
            if (furnaceItemStacks[i] != null) {
                CompoundTag nbttagcompound1 = new CompoundTag();
                nbttagcompound1.put("Slot", (byte) i);
                furnaceItemStacks[i].toTag(nbttagcompound1);
                nbttaglist.add(nbttagcompound1);
            }
        }

        nbttagcompound.put("Items", nbttaglist);
    }

    @Override
    public int getMaxItemCount() {
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

    public void tick() {
        boolean flag = furnaceBurnTime > 0;
        boolean flag1 = false;
        if (furnaceBurnTime > 0) {
            furnaceBurnTime--;
        }
        if (!level.isServerSide) {
            if (furnaceBurnTime == 0 && canSmelt()) {
                currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[9]);
                if (furnaceBurnTime > 0) {
                    flag1 = true;
                    if (furnaceItemStacks[9] != null) {
                        furnaceItemStacks[9].count--;
                        if (furnaceItemStacks[9].getType() instanceof Bucket) {
                            furnaceItemStacks[9] = new ItemInstance(ItemBase.bucket);
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
                BrickOven.updateFurnaceBlockState(furnaceBurnTime > 0,
                        level, x, y, z);
            }
        }
        if (flag1) {
            this.markDirty();
        }
    }

    private boolean canSmelt() {
        ItemInstance itemstack = OvenManager.smelting().findMatchingRecipe(furnaceItemStacks, this);
        if (itemstack == null) {
            return false;
        }
        if (furnaceItemStacks[10] == null) {
            return true;
        }
        if (!furnaceItemStacks[10].copy().isDamageAndIDIdentical(itemstack)) {
            return false;
        }
        if (furnaceItemStacks[10].copy().count < getMaxItemCount()
                && furnaceItemStacks[10].copy().count + itemstack.copy().count < furnaceItemStacks[10].copy().getMaxStackSize()) {
            return true;
        }
        return furnaceItemStacks[10].copy().count + itemstack.copy().count <= itemstack.copy().getMaxStackSize();
    }

    public void smeltItem() {
        if (!canSmelt()) {
            return;
        }
        ItemInstance itemstack = OvenManager.smelting().findMatchingRecipe(furnaceItemStacks, this);
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

    private int getItemBurnTime(ItemInstance itemstack) {
        if (itemstack == null) {
            return 0;
        }
        int i = itemstack.itemId;
        int j = itemstack.getDamage();
        if(i == ItemBase.stick.id) {
            return 50;
        }
        if(i == BlockBase.SAPLING.id) {
            return 50;
        }
        if(i < 256 && BlockBase.BY_ID[i].material == Material.WOOD) {
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
        if((i == ItemBase.coal.id  || i == ItemListener.netherAsh.id) && j == 0) {
            return 1600;
        }
        if(i == ItemBase.coal.id) {
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
        if(i == ItemBase.lavaBucket.id) {
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

    public boolean canPlayerUse(PlayerBase entityplayer) {
        if (level.getTileEntity(x, y, z) != this) {
            return false;
        }
        return entityplayer.squaredDistanceTo((double) x + 0.5D,
                (double) y + 0.5D, (double) z + 0.5D) <= 64D;
    }

    public void setTime(int i){
        requiredTime = i;
    }

    private ItemInstance furnaceItemStacks[];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    public int requiredTime = 200;
}
