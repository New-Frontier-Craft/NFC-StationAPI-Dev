package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Item;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;
import net.minecraft.tileentity.TileEntityBase;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.newfrontiercraft.nfc.containers.ContainerBrickOven;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.TileEntityListener;
import net.newfrontiercraft.nfc.tileentities.TileEntityBrickOven;

import java.util.Random;

public class BrickOven extends TemplateBlockWithEntity {

    int frontTextureInternal;
    int frontActiveTextureInternal;
    int sideTextureInternal;
    private Random furnaceRand;
    private final boolean isActive;
    private static boolean keepFurnaceInventory = false;
    
    public BrickOven(Identifier identifier, Material material, boolean isActive, float lightEmittance) {
        super(identifier, material);
        setLightEmittance(lightEmittance);
        setTranslationKey(identifier.modID, identifier.id);
        furnaceRand = new Random();
        this.isActive = isActive;
    }

    @Override
    public int getDropId(int i, Random random) {
        return BlockListener.brickOven.id;
    }

    @Override
    public boolean canUse(Level world, int x, int y, int z, PlayerBase player) {
        TileEntityBase tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityBrickOven tileEntityBrickOven)
            GuiHelper.openGUI(player, Identifier.of(TileEntityListener.MOD_ID, "gui_brick_oven"), tileEntityBrickOven, new ContainerBrickOven(player.inventory, tileEntityBrickOven));
        return true;
    }

    @Override
    public void onBlockPlaced(Level world, int i, int j, int k) {
        super.onBlockPlaced(world, i, j, k);
        if (!world.isServerSide) setDefaultDirection(world, i, j, k);
    }

    private void setDefaultDirection(Level world, int i, int j, int k) {
        int l = world.getTileId(i, j, k - 1);
        int i1 = world.getTileId(i, j, k + 1);
        int j1 = world.getTileId(i - 1, j, k);
        int k1 = world.getTileId(i + 1, j, k);
        byte byte0 = 3;
        if (BlockBase.FULL_OPAQUE[l] && !BlockBase.FULL_OPAQUE[i1]) {
            byte0 = 3;
        }
        if (BlockBase.FULL_OPAQUE[i1] && !BlockBase.FULL_OPAQUE[l]) {
            byte0 = 2;
        }
        if (BlockBase.FULL_OPAQUE[j1] && !BlockBase.FULL_OPAQUE[k1]) {
            byte0 = 5;
        }
        if (BlockBase.FULL_OPAQUE[k1] && !BlockBase.FULL_OPAQUE[j1]) {
            byte0 = 4;
        }
        world.placeBlockWithMetaData(i, j, k, this.id, byte0);
    }

    public void randomDisplayTick(Level world, int i, int j, int k, Random random) {
        if (!isActive) {
            return;
        }
        int l = world.getTileMeta(i, j, k);
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
        frontTextureInternal = frontTexture;
        frontActiveTextureInternal = frontActiveTexture;
        sideTextureInternal = sideTexture;
    }
    
    @Override
    public int getTextureForSide(int i, int j) {
        if (i == j) {
            return isActive ? frontActiveTextureInternal : frontTextureInternal;
        } else {
            return sideTextureInternal;
        }
    }

    public static void updateFurnaceBlockState(boolean flag, Level world, int i, int j, int k) {
        int l = world.getTileMeta(i, j, k);
        TileEntityBase tileentity = world.getTileEntity(i, j, k);
        keepFurnaceInventory = true;
        if (flag) {
            world.setTile(i, j, k, BlockListener.brickOvenActive.id);
        } else {
            world.setTile(i, j, k, BlockListener.brickOven.id);
        }
        keepFurnaceInventory = false;
        world.setTileMeta(i, j, k, l);
        tileentity.validate();
        world.setTileEntity(i, j, k, tileentity);
    }

    @Override
    public void afterPlaced(Level world, int i, int j, int k,
                                Living entityliving) {
        int l = MathHelper
                .floor((double) ((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if (l == 0) {
            world.setTileMeta(i, j, k, 2);
        }
        if (l == 1) {
            world.setTileMeta(i, j, k, 5);
        }
        if (l == 2) {
            world.setTileMeta(i, j, k, 3);
        }
        if (l == 3) {
            world.setTileMeta(i, j, k, 4);
        }
    }

    @Override
    public void onBlockRemoved(Level arg, int i, int j, int k) {
        if (!keepFurnaceInventory) {
            TileEntityBrickOven brickOven = (TileEntityBrickOven)arg.getTileEntity(i, j, k);

            for(int var6 = 0; var6 < brickOven.getInventorySize(); ++var6) {
                ItemInstance var7 = brickOven.getInventoryItem(var6);
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
                        Item var12 = new Item(arg, (double)((float)i + var8), (double)((float)j + var9), (double)((float)k + var10), new ItemInstance(var7.itemId, var11, var7.getDamage()));
                        float var13 = 0.05F;
                        var12.velocityX = (double)((float)this.furnaceRand.nextGaussian() * var13);
                        var12.velocityY = (double)((float)this.furnaceRand.nextGaussian() * var13 + 0.2F);
                        var12.velocityZ = (double)((float)this.furnaceRand.nextGaussian() * var13);
                        arg.spawnEntity(var12);
                    }
                }
            }
        }

        super.onBlockRemoved(arg, i, j, k);
    }

    @Override
    protected TileEntityBase createTileEntity() {
        return new TileEntityBrickOven();
    }
}
