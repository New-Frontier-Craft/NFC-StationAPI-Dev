package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.TreeFarmBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.inventory.TreeFarmScreenHandler;

import java.util.Random;

public class TreeFarmBlock extends TemplateBlockWithEntity {

    int topTexture;
    int frontTexture;
    int frontActiveTexture;
    int sideTexture;
    int bottomTexture;
    private final Random blockRandom = new Random();

    public TreeFarmBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
    }

    public void specifyTextures(int topTexture, int frontTexture, int frontActiveTexture, int sideTexture, int bottomTexture) {
        this.topTexture = topTexture;
        this.frontTexture = frontTexture;
        this.frontActiveTexture = frontActiveTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 0) {
            return bottomTexture;
        }
        if (side == 1) {
            return topTexture;
        }
        if (meta == 0 && side == 3) {
            return frontTexture;
        }
        if (side == meta % 6 && meta > 1) {
            return meta > 6 ? frontActiveTexture : frontTexture;
        } else {
            return sideTexture;
        }
    }


    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (blockEntity instanceof TreeFarmBlockEntity treeFarmBlockEntity)
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "tree_farm_screen"),
                    treeFarmBlockEntity, new TreeFarmScreenHandler(player.inventory, treeFarmBlockEntity));
        return true;
    }

    @Override
    public void onPlaced(World world, int i, int j, int k, LivingEntity entityliving) {
        int l = MathHelper
                .floor((double) ((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if (l == 0) {
            world.setBlockMeta(i, j, k, 2);
        }
        if (l == 1) {
            world.setBlockMeta(i, j, k, 5);
        }
        if (l == 2) {
            world.setBlockMeta(i, j, k, 3);
        }
        if (l == 3) {
            world.setBlockMeta(i, j, k, 4);
        }
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        TreeFarmBlockEntity treeFarmBlockEntity = (TreeFarmBlockEntity)world.getBlockEntity(x, y, z);
        for(int var6 = 0; var6 < treeFarmBlockEntity.size(); ++var6) {
            ItemStack var7 = treeFarmBlockEntity.getStack(var6);
            if (var7 != null) {
                float var8 = this.blockRandom.nextFloat() * 0.8F + 0.1F;
                float var9 = this.blockRandom.nextFloat() * 0.8F + 0.1F;
                float var10 = this.blockRandom.nextFloat() * 0.8F + 0.1F;
                while(var7.count > 0) {
                    int var11 = this.blockRandom.nextInt(21) + 10;
                    if (var11 > var7.count) {
                        var11 = var7.count;
                    }
                    var7.count -= var11;
                    ItemEntity var12 = new ItemEntity(world, (float)x + var8, (float)y + var9, (float)z + var10, new ItemStack(var7.getItem(), var11, var7.getDamage()));
                    float var13 = 0.05F;
                    var12.velocityX = (float)this.blockRandom.nextGaussian() * var13;
                    var12.velocityY = (float)this.blockRandom.nextGaussian() * var13 + 0.2F;
                    var12.velocityZ = (float)this.blockRandom.nextGaussian() * var13;
                    world.spawnEntity(var12);
                }
            }
        }
        super.onBreak(world, x, y, z);
    }

    public static void updateTreeFarmBlockState(boolean active, World world, int x, int y, int z) {
        int meta = world.getBlockMeta(x, y, z);
        boolean update = false;
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (active && meta < 6) {
            world.setBlockEntity(x, y, z, blockEntity);
            world.setBlockMeta(x, y, z, meta + 6);
            update = true;
        } else if (!active && meta > 6) {
            world.setBlockEntity(x, y, z, blockEntity);
            world.setBlockMeta(x, y, z, meta - 6);
            update = true;
        }
        if (update) {
            world.blockUpdateEvent(x, y, z);
        }
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new TreeFarmBlockEntity();
    }
}
