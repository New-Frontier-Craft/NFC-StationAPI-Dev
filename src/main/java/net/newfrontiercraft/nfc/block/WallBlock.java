package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.model.block.BlockWithInventoryRenderer;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.util.Identifier;
import org.lwjgl.opengl.GL11;

@EnvironmentInterface(value=EnvType.CLIENT, itf=BlockWithWorldRenderer.class)
@EnvironmentInterface(value=EnvType.CLIENT, itf=BlockWithInventoryRenderer.class)
public class WallBlock extends LazyMultivariantBlockTemplate implements BlockWithWorldRenderer, BlockWithInventoryRenderer {
    public WallBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        this.maxY = 1.5D;
        return super.getCollisionShape(world, x, y, z);
    }

    public void setBlockBoundsBasedOnState(BlockView iblockaccess, int i, int j, int k) {
        boolean isConnectedState = iblockaccess.getBlockMeta(i, j, k) == 2 || iblockaccess.getBlockMeta(i, j, k) == 4;
        boolean flag = isWallAt(iblockaccess, i, j, k - 1, isConnectedState);
        boolean flag1 = isWallAt(iblockaccess, i, j, k + 1, isConnectedState);
        boolean flag2 = isWallAt(iblockaccess, i - 1, j, k, isConnectedState);
        boolean flag3 = isWallAt(iblockaccess, i + 1, j, k, isConnectedState);
        float f = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

        if (flag) {
            f2 = 0.0F;
        }
        if (flag1) {
            f3 = 1.0F;
        }
        if (flag2) {
            f = 0.0F;
        }
        if (flag3) {
            f1 = 1.0F;
        }
        if (flag && flag1 && !flag2 && !flag3) {
            f4 = 0.8125F;
            f = 0.3125F;
            f1 = 0.6875F;
        } else if (!flag && !flag1 && flag2 && flag3) {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }
        setBoundingBox(f, 0.0F, f2, f1, f4, f3);
    }

    @Override
    public void setupRenderBoundingBox() {
        setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    public int getRenderType() {
        return 26;
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return blockMeta % 2;
    }

    public boolean isWallAt(BlockView iblockaccess, int i, int j, int k, boolean isConnected) {
        int l = iblockaccess.getBlockId(i, j, k);
        if (l == id /*|| l == mod_NFC.fenceGate.blockID*/) {
            return true;
        }
        Block block = Block.BLOCKS[l];
        return block != null && block.material.suffocates() && block.isFullCube() && isConnected;
    }

    @Override
    public boolean renderWorld(BlockRenderManager blockRenderManager, BlockView blockView, int i, int j, int k) {
        int meta = blockView.getBlockMeta(i, j, k);
        boolean wrenched = meta > 1;
        boolean flag = blockView.getBlockId(i - 1, j, k) == this.id || /*blockView.getBlockId(i - 1, j, k) == mod_NFC.fenceGate.blockID ||*/ (Block.BLOCKS_OPAQUE[blockView.getBlockId(i - 1, j, k)] && wrenched);
        boolean flag1 = blockView.getBlockId(i + 1, j, k) == this.id || /*blockView.getBlockId(i + 1, j, k) == mod_NFC.fenceGate.blockID ||*/ (Block.BLOCKS_OPAQUE[blockView.getBlockId(i + 1, j, k)] && wrenched);
        boolean flag2 = blockView.getBlockId(i, j, k - 1) == this.id || /*blockView.getBlockId(i, j, k - 1) == mod_NFC.fenceGate.blockID ||*/ (Block.BLOCKS_OPAQUE[blockView.getBlockId(i, j, k - 1)] && wrenched);
        boolean flag3 = blockView.getBlockId(i, j, k + 1) == this.id || /*blockView.getBlockId(i, j, k + 1) == mod_NFC.fenceGate.blockID ||*/ (Block.BLOCKS_OPAQUE[blockView.getBlockId(i, j, k + 1)] && wrenched);
        boolean flag4 = flag2 && flag3 && !flag && !flag1;
        boolean flag5 = !flag2 && !flag3 && flag && flag1;
        boolean flag6 = blockView.getBlockId(i, j + 1, k) == 0;

        float f = 0.8125F;

        if ((flag4 || flag5) && flag6)
        {
            if (flag4)
            {
                this.setBoundingBox(0.3125F, 0.0F, 0.0F, 0.6875F, f, 1.0F);
                blockRenderManager.renderBlock(this, i, j, k);
            }
            else
            {
                this.setBoundingBox(0.0F, 0.0F, 0.3125F, 1.0F, f, 0.6875F);
                blockRenderManager.renderBlock(this, i, j, k);
            }
        }
        else
        {
            this.setBoundingBox(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
            blockRenderManager.renderBlock(this, i, j, k);

            if (flag)
            {
                this.setBoundingBox(0.0F, 0.0F, 0.3125F, 0.25F, f, 0.6875F);
                blockRenderManager.renderBlock(this, i, j, k);
            }

            if (flag1)
            {
                this.setBoundingBox(0.75F, 0.0F, 0.3125F, 1.0F, f, 0.6875F);
                blockRenderManager.renderBlock(this, i, j, k);
            }

            if (flag2)
            {
                this.setBoundingBox(0.3125F, 0.0F, 0.0F, 0.6875F, f, 0.25F);
                blockRenderManager.renderBlock(this, i, j, k);
            }

            if (flag3)
            {
                this.setBoundingBox(0.3125F, 0.0F, 0.75F, 0.6875F, f, 1.0F);
                blockRenderManager.renderBlock(this, i, j, k);
            }
        }

        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return true;
    }

    @Override
    public void renderInventory(BlockRenderManager blockRenderManager, int i) {
        this.setupRenderBoundingBox();
        Tessellator tessellator = Tessellator.INSTANCE;
        for (int k1 = 0; k1 < 2; ++k1)
        {
            if (k1 == 0)
            {
                this.setBoundingBox(0.3125F, 0.0F, 0, 0.6875F, 0.8125F, 1);
            }

            if (k1 == 1)
            {
                this.setBoundingBox(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
            }

            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startQuads();
            tessellator.normal(0.0F, -1.0F, 0.0F);
            blockRenderManager.renderBottomFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(0, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 1.0F, 0.0F);
            blockRenderManager.renderTopFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(1, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 0.0F, -1.0F);
            blockRenderManager.renderEastFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(2, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 0.0F, 1.0F);
            blockRenderManager.renderWestFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(3, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(-1.0F, 0.0F, 0.0F);
            blockRenderManager.renderSouthFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(4, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(1.0F, 0.0F, 0.0F);
            blockRenderManager.renderNorthFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(5, i));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
}
