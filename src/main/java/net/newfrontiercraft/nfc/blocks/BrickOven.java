package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.containers.BrickOvenScreenHandler;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.blockentities.BrickOvenBlockEntity;

import java.util.Random;

public class BrickOven extends TemplateBlockWithEntity {

    int frontTexture;
    int frontTextureActive;
    int sideTexture;
    private Random furnaceRand;
    private final boolean ACTIVE;
    private static boolean keepFurnaceInventory = false;
    
    public BrickOven(Identifier identifier, Material material, boolean active, float lightEmittance, float hardness) {
        super(identifier, material);
        setLuminance(lightEmittance);
        setTranslationKey(identifier.namespace, identifier.path);
        furnaceRand = new Random();
        this.ACTIVE = active;
        setHardness(hardness);
    }

    @Override
    public int getDroppedItemId(int i, Random random) {
        return BlockListener.brickOven.id;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity tileEntity = world.method_1777(x, y, z);
        if (tileEntity instanceof BrickOvenBlockEntity brickOvenBlockEntity)
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "gui_brick_oven"),
                    brickOvenBlockEntity, new BrickOvenScreenHandler(player.inventory, brickOvenBlockEntity));
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
        byte byte0 = 3;
        if (Block.BLOCKS_OPAQUE[l] && !Block.BLOCKS_OPAQUE[i1]) {
            byte0 = 3;
        }
        if (Block.BLOCKS_OPAQUE[i1] && !Block.BLOCKS_OPAQUE[l]) {
            byte0 = 2;
        }
        if (Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[k1]) {
            byte0 = 5;
        }
        if (Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[j1]) {
            byte0 = 4;
        }
        world.method_154(i, j, k, this.id, byte0);
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        if (!ACTIVE) {
            return;
        }
        int l = world.getBlockMeta(i, j, k);
        float f = (float) i + 0.5F;
        float f1 = (float) j + 0.25F + (random.nextFloat() * 6F) / 16F;
        float f2 = (float) k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        if (l == 4) {
            world.addParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else if (l == 5) {
            world.addParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else if (l == 2) {
            world.addParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        } else if (l == 3) {
            world.addParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
    }

    public void specifyTextures(int frontTexture, int frontActiveTexture, int sideTexture)
    {
        this.frontTexture = frontTexture;
        frontTextureActive = frontActiveTexture;
        this.sideTexture = sideTexture;
    }
    
    @Override
    public int getTexture(int side, int meta) {
        if (side == meta) {
            return ACTIVE ? frontTextureActive : frontTexture;
        } else {
            return sideTexture;
        }
    }

    public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k) {
        int l = world.getBlockMeta(i, j, k);
        BlockEntity tileentity = world.method_1777(i, j, k);
        keepFurnaceInventory = true;
        if (flag) {
            world.setBlock(i, j, k, BlockListener.brickOvenActive.id);
        } else {
            world.setBlock(i, j, k, BlockListener.brickOven.id);
        }
        keepFurnaceInventory = false;
        world.method_153(i, j, k, l);
        //tileentity.validate();
        world.method_203(i, j, k, tileentity);
    }

    @Override
    public void onPlaced(World world, int i, int j, int k, LivingEntity entityliving) {
        int l = MathHelper
                .floor((double) ((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if (l == 0) {
            world.method_153(i, j, k, 2);
        }
        if (l == 1) {
            world.method_153(i, j, k, 5);
        }
        if (l == 2) {
            world.method_153(i, j, k, 3);
        }
        if (l == 3) {
            world.method_153(i, j, k, 4);
        }
    }

    @Override
    public void onBreak(World arg, int i, int j, int k) {
        if (!keepFurnaceInventory) {
            BrickOvenBlockEntity brickOven = (BrickOvenBlockEntity)arg.method_1777(i, j, k);

            for(int var6 = 0; var6 < brickOven.size(); ++var6) {
                ItemStack var7 = brickOven.getStack(var6);
                if (var7 != null) {
                    float var8 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float var9 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                    float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                    while(var7.count > 0) {
                        int var11 = this.furnaceRand.nextInt(21) + 10;
                        if (var11 > var7.count) {
                            var11 = var7.count;
                        }

                        var7.count -= var11;
                        ItemEntity var12 = new ItemEntity(arg, (double)((float)i + var8), (double)((float)j + var9), (double)((float)k + var10), new ItemStack(var7.itemId, var11, var7.getDamage()));
                        float var13 = 0.05F;
                        var12.velocityX = (double)((float)this.furnaceRand.nextGaussian() * var13);
                        var12.velocityY = (double)((float)this.furnaceRand.nextGaussian() * var13 + 0.2F);
                        var12.velocityZ = (double)((float)this.furnaceRand.nextGaussian() * var13);
                        arg.method_287(var12);
                    }
                }
            }
        }

        super.onBreak(arg, i, j, k);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new BrickOvenBlockEntity();
    }
}
