package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.model.block.BlockWithInventoryRenderer;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.lwjgl.opengl.GL11;

public class FenceGateBlock extends TemplateBlock implements BlockWithWorldRenderer, BlockWithInventoryRenderer {
    public FenceGateBlock(Identifier identifier, Material material) {
        super(identifier, material);
        this.textureId = 4;
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        int meta = (MathHelper.floor((double)(placer.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
        world.setBlockMeta(x, y, z, meta);
    }

    @Override
    public void onBlockBreakStart(World world, int x, int y, int z, PlayerEntity player) {
        this.onUse(world, x, y, z, player);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        int meta = world.getBlockMeta(x, y, z);
        if(isOpen(meta)) {
            world.setBlockMeta(x, y, z, meta & -5);
        } else {
            int i1 = (MathHelper.floor((double)(player.yaw * 4.0F / 360.0F) + 0.5D) & 3) % 4;
            int j1 = facingState(meta);
            if(j1 == (i1 + 2) % 4) {
                meta = i1;
            }

            world.setBlockMeta(x, y, z, meta | 4);
        }

        if(!world.isRemote) {
            world.worldEvent(player, 1003, x, y, z, 0);
        }

        world.setBlocksDirty(x, y, z, x, y, z);
        return true;
    }

    public void onPoweredBlockChange(World world, int x, int y, int z, boolean powered) {
        int meta = world.getBlockMeta(x, y, z);
        boolean flag1 = (world.getBlockMeta(x, y, z) & 4) > 0;
        if(flag1 != powered) {
            if(isOpen(meta)) {
                world.setBlockMeta(x, y, z, meta & -5);
            } else {
                int j1 = facingState(meta);
                world.setBlockMeta(x, y, z, j1 | 4);
            }

            if(!world.isRemote) {
                world.worldEvent(null, 1003, x, y, z, 0);
            }

            world.setBlocksDirty(x, y, z, x, y, z);
        }
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        if(id > 0 && FenceGateBlock.BLOCKS[id].canEmitRedstonePower()){
            boolean powered = world.isEmittingRedstonePower(x, y, z);
            this.onPoweredBlockChange(world, x, y, z, powered);
        }
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        int meta = world.getBlockMeta(x, y, z);
        if (isOpen(meta)) {
            return null;
        }

        if (meta == 2 || meta == 0) {
            return Box.createCached(
                    x, y, z + 0.375F,
                    x + 1, y + 1.5F, z + 0.625F
            );
        } else {
            return Box.createCached(
                    x + 0.375F, y, z,
                    x + 0.625F, y + 1.5F, z + 1
            );
        }
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        int facing = facingState(blockView.getBlockMeta(x, y, z));
        if(facing != 2 && facing != 0) {
            this.setBoundingBox(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
        } else {
            this.setBoundingBox(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
        }
    }

    public static int facingState(int i) {
        return i & 3;
    }

    public static boolean isOpen(int i) {
        return (i & 4) != 0;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean renderWorld(BlockRenderManager blockRenderManager, BlockView blockView, int x, int y, int z) {
        FenceGateBlock fenceGateBlock = this;
        int meta = blockView.getBlockMeta(x, y, z);
        boolean isOpen = FenceGateBlock.isOpen(meta);
        int facing = FenceGateBlock.facingState(meta);
        float f16 = (blockView.getBlockId(x, y, z - 1) != BlockListener.hardWall.id || blockView.getBlockId(x, y, z + 1) != BlockListener.hardWall.id) && (blockView.getBlockId(x - 1, y, z) != BlockListener.hardWall.id || blockView.getBlockId(x + 1, y, z) != BlockListener.hardWall.id) ? 0.0F : 0.1875F;
        float f3;
        float f7;
        float f11;
        float f15;
        if (facing != 3 && facing != 1) {
            f3 = 0.0F;
            f7 = 0.125F;
            f11 = 0.4375F;
            f15 = 0.5625F;
            fenceGateBlock.setBoundingBox(f3, 0.3125F - f16, f11, f7, 1.0F - f16, f15);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            f3 = 0.875F;
            f7 = 1.0F;
            fenceGateBlock.setBoundingBox(f3, 0.3125F - f16, f11, f7, 1.0F - f16, f15);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
        } else {
            f3 = 0.4375F;
            f7 = 0.5625F;
            f11 = 0.0F;
            f15 = 0.125F;
            fenceGateBlock.setBoundingBox(f3, 0.3125F - f16, f11, f7, 1.0F - f16, f15);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            f11 = 0.875F;
            f15 = 1.0F;
            fenceGateBlock.setBoundingBox(f3, 0.3125F - f16, f11, f7, 1.0F - f16, f15);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
        }

        if (!isOpen) {
            if (facing != 3 && facing != 1) {
                f3 = 0.375F;
                f7 = 0.5F;
                f11 = 0.4375F;
                f15 = 0.5625F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                f3 = 0.5F;
                f7 = 0.625F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                f3 = 0.625F;
                f7 = 0.875F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.5625F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                fenceGateBlock.setBoundingBox(f3, 0.75F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                f3 = 0.125F;
                f7 = 0.375F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.5625F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                fenceGateBlock.setBoundingBox(f3, 0.75F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            } else {
                f3 = 0.4375F;
                f7 = 0.5625F;
                f11 = 0.375F;
                f15 = 0.5F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                f11 = 0.5F;
                f15 = 0.625F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                f11 = 0.625F;
                f15 = 0.875F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.5625F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                fenceGateBlock.setBoundingBox(f3, 0.75F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                f11 = 0.125F;
                f15 = 0.375F;
                fenceGateBlock.setBoundingBox(f3, 0.375F - f16, f11, f7, 0.5625F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
                fenceGateBlock.setBoundingBox(f3, 0.75F - f16, f11, f7, 0.9375F - f16, f15);
                blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            }
        } else if (facing == 3) {
            fenceGateBlock.setBoundingBox(0.8125F, 0.375F - f16, 0.0F, 0.9375F, 0.9375F - f16, 0.125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.8125F, 0.375F - f16, 0.875F, 0.9375F, 0.9375F - f16, 1.0F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.5625F, 0.375F - f16, 0.0F, 0.8125F, 0.5625F - f16, 0.125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.5625F, 0.375F - f16, 0.875F, 0.8125F, 0.5625F - f16, 1.0F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.5625F, 0.75F - f16, 0.0F, 0.8125F, 0.9375F - f16, 0.125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.5625F, 0.75F - f16, 0.875F, 0.8125F, 0.9375F - f16, 1.0F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
        } else if (facing == 1) {
            fenceGateBlock.setBoundingBox(0.0625F, 0.375F - f16, 0.0F, 0.1875F, 0.9375F - f16, 0.125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.0625F, 0.375F - f16, 0.875F, 0.1875F, 0.9375F - f16, 1.0F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.1875F, 0.375F - f16, 0.0F, 0.4375F, 0.5625F - f16, 0.125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.1875F, 0.375F - f16, 0.875F, 0.4375F, 0.5625F - f16, 1.0F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.1875F, 0.75F - f16, 0.0F, 0.4375F, 0.9375F - f16, 0.125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.1875F, 0.75F - f16, 0.875F, 0.4375F, 0.9375F - f16, 1.0F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
        } else if (facing == 0) {
            fenceGateBlock.setBoundingBox(0.0F, 0.375F - f16, 0.8125F, 0.125F, 0.9375F - f16, 0.9375F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.875F, 0.375F - f16, 0.8125F, 1.0F, 0.9375F - f16, 0.9375F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.0F, 0.375F - f16, 0.5625F, 0.125F, 0.5625F - f16, 0.8125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.875F, 0.375F - f16, 0.5625F, 1.0F, 0.5625F - f16, 0.8125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.0F, 0.75F - f16, 0.5625F, 0.125F, 0.9375F - f16, 0.8125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.875F, 0.75F - f16, 0.5625F, 1.0F, 0.9375F - f16, 0.8125F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
        } else if (facing == 2) {
            fenceGateBlock.setBoundingBox(0.0F, 0.375F - f16, 0.0625F, 0.125F, 0.9375F - f16, 0.1875F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.875F, 0.375F - f16, 0.0625F, 1.0F, 0.9375F - f16, 0.1875F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.0F, 0.375F - f16, 0.1875F, 0.125F, 0.5625F - f16, 0.4375F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.875F, 0.375F - f16, 0.1875F, 1.0F, 0.5625F - f16, 0.4375F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.0F, 0.75F - f16, 0.1875F, 0.125F, 0.9375F - f16, 0.4375F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
            fenceGateBlock.setBoundingBox(0.875F, 0.75F - f16, 0.1875F, 1.0F, 0.9375F - f16, 0.4375F);
            blockRenderManager.renderBlock(fenceGateBlock, x, y, z);
        }
        fenceGateBlock.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return true;
    }

    @Override
    public void renderInventory(BlockRenderManager blockRenderManager, int meta) {
        Tessellator tessellator = Tessellator.INSTANCE;
        int color = this.getColor(meta);

        float k1 = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f4 = (float)(color & 255) / 255.0F;

        for(int i16 = 0; i16 < 8; ++i16) {
            f1 = 0.4375F;
            f4 = 0.5625F;
            float f8 = 0.0F;
            float f12 = 0.125F;
            if(i16 == 0) {
                this.setBoundingBox(f1, 0.3125F, f8, f4, 1.0F, f12);
            }

            f8 = 0.875F;
            f12 = 1.0F;
            if(i16 == 1) {
                this.setBoundingBox(f1, 0.3125F, f8, f4, 1.0F, f12);
            }

            float f2 = 0.4375F;
            float f6 = 0.5625F;
            float f10 = 0.375F;
            float f14 = 0.5F;
            if(i16 == 2) {
                this.setBoundingBox(f2, 0.375F, f10, f6, 0.9375F, f14);
            }

            f10 = 0.5F;
            f14 = 0.625F;
            if(i16 == 3) {
                this.setBoundingBox(f2, 0.375F, f10, f6, 0.9375F, f14);
            }

            f10 = 0.625F;
            f14 = 0.875F;
            if(i16 == 4) {
                this.setBoundingBox(f2, 0.375F, f10, f6, 0.5625F, f14);
            }

            if(i16 == 5) {
                this.setBoundingBox(f2, 0.75F, f10, f6, 0.9375F, f14);
            }

            f10 = 0.125F;
            f14 = 0.375F;
            if(i16 == 6) {
                this.setBoundingBox(f2, 0.375F, f10, f6, 0.5625F, f14);
            }

            if(i16 == 7) {
                this.setBoundingBox(f2, 0.75F, f10, f6, 0.9375F, f14);
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startQuads();
            tessellator.normal(0.0F, -1.0F, 0.0F);
            blockRenderManager.renderBottomFace(this, 0.0D, -0.125D, 0.0D, this.getTexture(0, meta));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 1.0F, 0.0F);
            blockRenderManager.renderTopFace(this, 0.0D, -0.125D, 0.0D, this.getTexture(1, meta));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 0.0F, -1.0F);
            blockRenderManager.renderEastFace(this, 0.0D, -0.125D, 0.0D, this.getTexture(2, meta));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 0.0F, 1.0F);
            blockRenderManager.renderWestFace(this, 0.0D, -0.125D, 0.0D, this.getTexture(3, meta));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(-1.0F, 0.0F, 0.0F);
            blockRenderManager.renderSouthFace(this, 0.0D, -0.125D, 0.0D, this.getTexture(4, meta));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(1.0F, 0.0F, 0.0F);
            blockRenderManager.renderNorthFace(this, 0.0D, -0.125D, 0.0D, this.getTexture(5, meta));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }

        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
}
