package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.BooleanProperty;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.inventory.BrickOvenScreenHandler;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;

import java.util.Random;

public class BrickOvenBlock extends TemplateBlockWithEntity {

    public static final BooleanProperty ACTIVE = BooleanProperty.of("active");

    int frontTexture;
    int frontTextureActive;
    int sideTexture;
    private final Random furnaceRand;

    public BrickOvenBlock(Identifier identifier, Material material, float lightEmittance, float hardness) {
        super(identifier, material);
        setLuminance(lightEmittance);
        setTranslationKey(identifier.namespace, identifier.path);
        furnaceRand = new Random();
        setHardness(hardness);
        setDefaultState(getStateManager().getDefaultState().with(ACTIVE, false));
        setLuminance((BlockState blockState) -> blockState.get(ACTIVE) ? 15 : 0);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity tileEntity = world.getBlockEntity(x, y, z);
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
        world.setBlockMeta(i, j, k, byte0);
    }

    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        int meta = world.getBlockMeta(i, j, k);
        if (meta < 6) {
            return;
        }
        float f = (float) i + 0.5F;
        float f1 = (float) j + 0.25F + (random.nextFloat() * 6F) / 16F;
        float f2 = (float) k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        if (meta == 10) {
            world.addParticle("smoke", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f - f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else if (meta == 11) {
            world.addParticle("smoke", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f3, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
        } else if (meta == 8) {
            world.addParticle("smoke", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f4, f1, f2 - f3, 0.0D, 0.0D, 0.0D);
        } else if (meta == 9) {
            world.addParticle("smoke", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", f + f4, f1, f2 + f3, 0.0D, 0.0D, 0.0D);
        }
    }

    public void specifyTextures(int frontTexture, int frontActiveTexture, int sideTexture) {
        this.frontTexture = frontTexture;
        frontTextureActive = frontActiveTexture;
        this.sideTexture = sideTexture;
    }
    
    @Override
    public int getTexture(int side, int meta) {
        if(meta == 0 && side == 3){
            return frontTexture;
        }
        if (side == meta % 6 && meta > 1) {
            return meta > 6 ? frontTextureActive : frontTexture;
        } else {
            return sideTexture;
        }
    }

    public static void updateFurnaceBlockState(boolean active, World world, int x, int y, int z) {
        int meta = world.getBlockMeta(x, y, z);
        boolean update = false;
        BlockState currentState = world.getBlockState(x, y, z);
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (active && meta < 6) {
            world.setBlockStateWithNotify(x, y, z, currentState.with(ACTIVE, true));
            world.setBlockEntity(x, y, z, blockEntity);
            world.setBlockMeta(x, y, z, meta + 6);
            update = true;
        } else if (!active && meta > 6) {
            world.setBlockStateWithNotify(x, y, z, currentState.with(ACTIVE, false));
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
        BrickOvenBlockEntity brickOven = (BrickOvenBlockEntity)world.getBlockEntity(x, y, z);
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
                    ItemEntity var12 = new ItemEntity(world, (double)((float)x + var8), (double)((float)y + var9), (double)((float)z + var10), new ItemStack(var7.getItem(), var11, var7.getDamage()));
                    float var13 = 0.05F;
                    var12.velocityX = (double)((float)this.furnaceRand.nextGaussian() * var13);
                    var12.velocityY = (double)((float)this.furnaceRand.nextGaussian() * var13 + 0.2F);
                    var12.velocityZ = (double)((float)this.furnaceRand.nextGaussian() * var13);
                    world.spawnEntity(var12);
                }
            }
        }
        this.dropStack(world, x, y, z, new ItemStack(BlockListener.brickOven, 1, 3));
        super.onBreak(world, x, y, z);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new BrickOvenBlockEntity();
    }
}
