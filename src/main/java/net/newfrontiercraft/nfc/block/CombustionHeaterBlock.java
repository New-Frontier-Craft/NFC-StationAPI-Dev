package net.newfrontiercraft.nfc.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.CombustionHeaterBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.inventory.CombustionHeaterScreenHandler;

import java.util.Random;

public class CombustionHeaterBlock extends LazyBlockWithEntityTemplate {
    private final Random random;
    private final boolean isActive;
    private static boolean keepCombustionHeaterInventory = false;

    public CombustionHeaterBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, boolean isActive) {
        super(identifier, material, hardness, blockSounds);
        random = new Random();
        this.isActive = isActive;
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (!isActive) {
            return;
        }
        int l = world.getBlockMeta(i, j, k);
        float f = (float) i + 0.5F;
        float f1 = (float) j + 0.5F + (random.nextFloat() * 6F) / 16F;
        float f2 = (float) k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        if (l == 3) {
            world.addParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else if (l == 1) {
            world.addParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else if (l == 0) {
            world.addParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        } else if (l == 2) {
            world.addParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity tileEntity = world.getBlockEntity(x, y, z);
        if (tileEntity instanceof CombustionHeaterBlockEntity combustionHeaterBlockEntity)
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "gui_combustion_heater"),
                    combustionHeaterBlockEntity, new CombustionHeaterScreenHandler(player.inventory, combustionHeaterBlockEntity));
        return true;
    }

    public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k) {
        int l = world.getBlockMeta(i, j, k);
        BlockEntity tileentity = world.getBlockEntity(i, j, k);
        keepCombustionHeaterInventory = true;
        if (flag) {
            world.setBlock(i, j, k, BlockListener.combustionHeaterActive.id);
        } else {
            world.setBlock(i, j, k, BlockListener.combustionHeater.id);
        }
        keepCombustionHeaterInventory = false;
        world.setBlockMeta(i, j, k, l);
        tileentity.cancelRemoval();
        world.setBlockEntity(i, j, k, tileentity);
    }

    @Override
    public void onBreak(World world, int i, int j, int k) {
        if (!keepCombustionHeaterInventory) {
            CombustionHeaterBlockEntity tileEntityCombustionHeater = (CombustionHeaterBlockEntity) world.getBlockEntity(i, j, k);
            label0: for (int l = 0; l < tileEntityCombustionHeater.size(); l++) {
                ItemStack itemstack = tileEntityCombustionHeater.getStack(l);
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
        }
        super.onBreak(world, i, j, k);
    }

    @Override
    public void afterBreak(World world, PlayerEntity playerEntity, int x, int y, int z, int meta) {
        if (world.isRemote) return;
        this.dropStack(world, x, y, z, new ItemStack(BlockListener.combustionHeater, 1, 2));
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new CombustionHeaterBlockEntity();
    }
}
