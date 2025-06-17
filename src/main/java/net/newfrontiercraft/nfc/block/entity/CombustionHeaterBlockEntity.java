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
import net.newfrontiercraft.nfc.block.CombustionHeaterBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.ItemListener;

public class CombustionHeaterBlockEntity extends BlockEntity implements Inventory {

    public CombustionHeaterBlockEntity() {
        furnaceItemStacks = new ItemStack[1];
        furnaceBurnTime = 0;
        currentItemBurnTime = 0;
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
        return "CombustionHeater";
    }

    @Override
    public void readNbt(NbtCompound nbttagcompound) {
        super.readNbt(nbttagcompound);
        NbtList nbttaglist = nbttagcompound.getList("Items");
        furnaceItemStacks = new ItemStack[size()];
        for (int i = 0; i < nbttaglist.size(); i++) {
            NbtCompound nbttagcompound1 = (NbtCompound) nbttaglist.get(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if (byte0 >= 0 && byte0 < furnaceItemStacks.length) {
                furnaceItemStacks[byte0] = new ItemStack(nbttagcompound1);
            }
        }

        furnaceBurnTime = nbttagcompound.getShort("BurnTime");
        currentItemBurnTime = getItemBurnTime(furnaceItemStacks[0]);
    }

    @Override
    public void writeNbt(NbtCompound nbttagcompound) {
        super.writeNbt(nbttagcompound);
        nbttagcompound.putShort("BurnTime", (short) furnaceBurnTime);
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
            BlockEntity tileEntity = world.getBlockEntity(x, y - 1, z);
            if (tileEntity != null) {
                if (tileEntity instanceof HeatCoilBlockEntity) {
                    ((HeatCoilBlockEntity)tileEntity).changeHeatLevel(1);
                }
            }
            furnaceBurnTime--;
        }
        if (!world.isRemote) {
            if (furnaceBurnTime == 0) {
                currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[0]);
                if (furnaceBurnTime > 0) {
                    flag1 = true;
                    if (furnaceItemStacks[0] != null) {
                        if (furnaceItemStacks[0].getItem().hasCraftingReturnItem()) {
                            furnaceItemStacks[0] = new ItemStack(furnaceItemStacks[0].getItem().getCraftingReturnItem());
                        } else {
                            furnaceItemStacks[0].count--;
                        }
                        if (furnaceItemStacks[0].getItem() instanceof BucketItem) {
                            furnaceItemStacks[0] = new ItemStack(Item.BUCKET);
                        } else
                        if (furnaceItemStacks[0].count == 0) {
                            furnaceItemStacks[0] = null;
                        }
                    }
                }
            }
            if (flag != (furnaceBurnTime > 0)) {
                flag1 = true;
                CombustionHeaterBlock.updateFurnaceBlockState(furnaceBurnTime > 0,
                        world, x, y, z);
            }
        }
        if (flag1) {
            this.markDirty();
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
        if (world.getBlockEntity(x, y, z) != this) {
            return false;
        }
        return entityplayer.getSquaredDistance((double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D) <= 64D;
    }

    private ItemStack[] furnaceItemStacks;
    public int furnaceBurnTime;
    public int currentItemBurnTime;
}
