package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.BasicItemChuteBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.inventory.BasicItemChuteScreenHandler;

import java.util.Random;

public class BasicItemChuteBlock extends LazyBlockWithEntityTemplate {

    private final Random random;

    public BasicItemChuteBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        random = new Random();
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new BasicItemChuteBlockEntity(false);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        return Box.createCached((float)x, y, (float)z, (float)(x + 1), y + 0.99F, (float)(z + 1));
    }

    @Override
    public void onEntityCollision(World world, int i, int j, int k, Entity entity) {
        BlockEntity tileEntity = world.getBlockEntity(i, j, k);
        if (tileEntity == null) {
            return;
        }
        if (!(tileEntity instanceof BasicItemChuteBlockEntity)) {
            return;
        }
        if (entity == null) {
            return;
        }
        if (!(entity instanceof ItemEntity)) {
            return;
        }
        ItemStack collidedItem = ((ItemEntity) entity).stack;
        if (collidedItem == null) {
            return;
        }
        int remainingCount = ((BasicItemChuteBlockEntity)tileEntity).handleGroundItem(collidedItem.copy());
        if (remainingCount == 0) {
            entity.markDead();
        } else if (remainingCount != collidedItem.count) {
            collidedItem.count = remainingCount;
        }
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity tileEntity = world.getBlockEntity(x, y, z);
        if (tileEntity instanceof BasicItemChuteBlockEntity basicItemChuteBlockEntity)
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "gui_basic_item_chute"),
                    basicItemChuteBlockEntity, new BasicItemChuteScreenHandler(player.inventory, basicItemChuteBlockEntity));
        return true;
    }

    @Override
    public void onBreak(World world, int i, int j, int k) {
        BasicItemChuteBlockEntity basicItemChuteBlockEntity = (BasicItemChuteBlockEntity) world.getBlockEntity(i, j, k);
        label0: for (int l = 0; l < basicItemChuteBlockEntity.size(); l++) {
            ItemStack itemstack = basicItemChuteBlockEntity.getStack(l);
            if (itemstack == null) {
                continue;
            }
            float f = random.nextFloat() * 0.8F + 0.1F;
            float f1 = random.nextFloat() * 0.8F + 0.1F;
            float f2 = random.nextFloat() * 0.8F + 0.1F;
            do {
                if (itemstack.count <= 0) {
                    continue label0;
                }
                int i1 = random.nextInt(21) + 10;
                if (i1 > itemstack.count) {
                    i1 = itemstack.count;
                }
                itemstack.count -= i1;
                ItemEntity entityitem = new ItemEntity(world,
                        (float) i + f, (float) j + f1, (float) k + f2,
                        new ItemStack(itemstack.getItem(), i1, itemstack.getDamage()));
                float f3 = 0.05F;
                entityitem.velocityX = (float) random.nextGaussian()
                        * f3;
                entityitem.velocityY = (float) random.nextGaussian()
                        * f3 + 0.2F;
                entityitem.velocityZ = (float) random.nextGaussian()
                        * f3;
                world.spawnEntity(entityitem);
            } while (true);
        }
        super.onBreak(world, i, j, k);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (blockEntity == null) {
            return;
        }
        if (!(blockEntity instanceof BasicItemChuteBlockEntity)) {
            return;
        }
        if (world.isEmittingRedstonePower(x, y, z) && id == Block.DETECTOR_RAIL.id) {
            ((BasicItemChuteBlockEntity)blockEntity).activateMinecartSearch();
        } else if (!world.isEmittingRedstonePower(x, y, z) && id == Block.DETECTOR_RAIL.id) {
            ((BasicItemChuteBlockEntity)blockEntity).deactivateMinecartSearch();
        }
    }
}
