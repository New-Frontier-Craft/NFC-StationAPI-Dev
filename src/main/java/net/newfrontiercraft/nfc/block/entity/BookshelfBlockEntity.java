package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

public class BookshelfBlockEntity extends BlockEntity implements Inventory {
    private ItemStack[] shelfContents = new ItemStack[10];
    @Override
    public int size() {
        return 10;
    }

    @Override
    public ItemStack getStack(int slot) {
        return this.shelfContents[slot];
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        if(this.shelfContents[slot] != null) {
            ItemStack itemstack1;
            if(this.shelfContents[slot].count <= amount) {
                itemstack1 = this.shelfContents[slot];
                this.shelfContents[slot] = null;
                this.markDirty();
                return itemstack1;
            } else {
                itemstack1 = this.shelfContents[slot].split(amount);
                if(this.shelfContents[slot].count == 0) {
                    this.shelfContents[slot] = null;
                }

                this.markDirty();
                return itemstack1;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        this.shelfContents[slot] = stack;
        if(stack != null && stack.count > this.getMaxCountPerStack()) {
            stack.count = this.getMaxCountPerStack();
        }

        this.markDirty();
    }

    @Override
    public String getName() {
        return "Bookshelf";
    }

    @Override
    public int getMaxCountPerStack() {
        return 64;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return this.world.getBlockEntity(this.x, this.y, this.z) == this && player.getSquaredDistance((double) this.x + 0.5D, (double) this.y + 0.5D, (double) this.z + 0.5D) <= 64.0D;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        NbtList nbtList = nbt.getList("Items");
        this.shelfContents = new ItemStack[this.size()];

        for(int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = (NbtCompound)nbtList.get(i);
            int slot = nbtCompound.getByte("Slot") & 255;
            if(slot >= 0 && slot < this.shelfContents.length) {
                this.shelfContents[slot] = new ItemStack(nbtCompound);
            }
        }
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        NbtList nbtList = new NbtList();

        for(int i = 0; i < this.shelfContents.length; ++i) {
            if(this.shelfContents[i] != null) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putByte("Slot", (byte)i);
                this.shelfContents[i].writeNbt(nbtCompound);
                nbtList.add(nbtCompound);
            }
        }

        nbt.put("Items", nbtList);
    }
}
