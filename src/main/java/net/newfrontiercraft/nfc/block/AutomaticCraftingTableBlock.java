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
import net.newfrontiercraft.nfc.block.entity.AutomaticCraftingTableBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.inventory.AutomaticCraftingTableScreenHandler;

import java.util.Random;

public class AutomaticCraftingTableBlock extends TemplateBlockWithEntity {

    int topTexture;
    int frontTexture;
    int frontActiveTexture;
    int sideTexture;
    int bottomTexture;
    private final Random craftingTableRandom;

    public AutomaticCraftingTableBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSoundGroup) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        craftingTableRandom = new Random();
        setHardness(hardness);
        setSoundGroup(blockSoundGroup);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (blockEntity instanceof AutomaticCraftingTableBlockEntity automaticCraftingTableBlockEntity)
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "gui_automatic_crafting_table"),
                    automaticCraftingTableBlockEntity, new AutomaticCraftingTableScreenHandler(player.inventory, automaticCraftingTableBlockEntity));
        return true;
    }

    @Override
    public void onPlaced(World world, int i, int j, int k) {
        super.onPlaced(world, i, j, k);
        if (!world.isRemote) {
            setDefaultDirection(world, i, j, k);
        }
    }

    private void setDefaultDirection(World world, int i, int j, int k) {
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte meta = 3;
        if (Block.BLOCKS_OPAQUE[l] && !Block.BLOCKS_OPAQUE[i1]) {
            meta = 3;
        }
        if (Block.BLOCKS_OPAQUE[i1] && !Block.BLOCKS_OPAQUE[l]) {
            meta = 2;
        }
        if (Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[k1]) {
            meta = 5;
        }
        if (Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[j1]) {
            meta = 4;
        }
        world.setBlockMeta(i, j, k, meta);
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
        if(meta == 0 && side == 3) {
            return frontTexture;
        }
        if (side == meta % 6 && meta > 1) {
            return meta > 6 ? frontActiveTexture : frontTexture;
        } else {
            return sideTexture;
        }
    }

    public static void updateCraftingTableBlockState(boolean active, World world, int x, int y, int z) {
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
        AutomaticCraftingTableBlockEntity automaticCraftingTable = (AutomaticCraftingTableBlockEntity)world.getBlockEntity(x, y, z);
        for(int var6 = 0; var6 < automaticCraftingTable.size(); ++var6) {
            ItemStack var7 = automaticCraftingTable.getStack(var6);
            if (var7 != null) {
                float var8 = this.craftingTableRandom.nextFloat() * 0.8F + 0.1F;
                float var9 = this.craftingTableRandom.nextFloat() * 0.8F + 0.1F;
                float var10 = this.craftingTableRandom.nextFloat() * 0.8F + 0.1F;
                while(var7.count > 0) {
                    int var11 = this.craftingTableRandom.nextInt(21) + 10;
                    if (var11 > var7.count) {
                        var11 = var7.count;
                    }
                    var7.count -= var11;
                    ItemEntity var12 = new ItemEntity(world, (float)x + var8, (float)y + var9, (float)z + var10, new ItemStack(var7.getItem(), var11, var7.getDamage()));
                    float var13 = 0.05F;
                    var12.velocityX = (float)this.craftingTableRandom.nextGaussian() * var13;
                    var12.velocityY = (float)this.craftingTableRandom.nextGaussian() * var13 + 0.2F;
                    var12.velocityZ = (float)this.craftingTableRandom.nextGaussian() * var13;
                    world.spawnEntity(var12);
                }
            }
        }
        this.dropStack(world, x, y, z, new ItemStack(BlockListener.automaticCraftingTable, 1, 0));
        super.onBreak(world, x, y, z);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new AutomaticCraftingTableBlockEntity();
    }
}
