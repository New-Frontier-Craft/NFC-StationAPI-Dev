package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldRegion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.model.block.BlockWithInventoryRenderer;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.util.Identifier;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

@EnvironmentInterface(value= EnvType.CLIENT, itf= BlockWithInventoryRenderer.class)
@EnvironmentInterface(value= EnvType.CLIENT, itf= BlockWithWorldRenderer.class)
public class LazyStairsTemplate extends LazyMultivariantBlockTemplate implements BlockWithWorldRenderer, BlockWithInventoryRenderer {
    public static final IntProperty ROTATIONS = IntProperty.of("rotations", 0, 12);

    public LazyStairsTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        this.setOpacity(2);
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ROTATIONS);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    // This only exists for testing
    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockState currentState = world.getBlockState(x, y, z);
        int rotation = world.getBlockState(x, y, z).get(ROTATIONS);
        world.setBlockStateWithMetadataWithNotify(x, y, z, currentState.with(ROTATIONS, (rotation + 1) % 12), world.getBlockMeta(x, y, z));
        return true;
    }

    @Override
    public void addIntersectingBoundingBox(World world, int x, int y, int z, Box box, ArrayList boxes) {
        int rotation = world.getBlockState(x, y, z).get(ROTATIONS);
        if(rotation == 0) {
            setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 1) {
            setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 2) {
            setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 3) {
            setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 4) {
            setBoundingBox(0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 5) {
            setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 6) {
            setBoundingBox(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 7) {
            setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            setBoundingBox(0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 8) {
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            this.setBoundingBox(0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 9) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            this.setBoundingBox(0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 10) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        } else if(rotation == 11) {
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F);
            super.addIntersectingBoundingBox(world, x, y, z, box, boxes);
        }
        setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public boolean renderWorld(BlockRenderManager blockRenderManager, BlockView blockView, int x, int y, int z) {
        int blockId = blockView.getBlockId(x, y, z);
        if (blockId == 0) {
            return false;
        }
        Block block = Block.BLOCKS[blockId];
        if (!(block instanceof LazyStairsTemplate)) {
            return false;
        }
        int rotation = 0;
        if (blockView instanceof WorldRegion worldRegion) {
            rotation = worldRegion.getBlockState(x, y, z).get(ROTATIONS);
        } else if (blockView instanceof World world) {
            rotation = world.getBlockState(x, y, z).get(ROTATIONS);
        }
        boolean flag = false;
        if(rotation == 0) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 0.5F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 1) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 2) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 0.5F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 3) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 4) {
            this.setBoundingBox(0.0F, 0.5F, 0.0F, 0.5F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 5) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.5F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 6) {
            this.setBoundingBox(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 0.5F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 7) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.0F, 0.5F, 0.5F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 8) {
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.0F, 0.0F, 0.5F, 0.5F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 9) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.5F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 10) {
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        } else if(rotation == 11) {
            this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            blockRenderManager.renderBlock(this, x, y, z);
            this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 0.5F);
            blockRenderManager.renderBlock(this, x, y, z);
            flag = true;
        }
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        return flag;
    }

    @Override
    public void renderInventory(BlockRenderManager blockRenderManager, int i) {
        this.setupRenderBoundingBox();
        Tessellator tessellator = Tessellator.INSTANCE;
        for(int l = 0; l < 2; l++) {
            if(l == 0) {
                this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
            } else {
                this.setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 0.5F, 1.0F);
            }
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            tessellator.startQuads();
            tessellator.normal(0.0F, -1F, 0.0F);
            blockRenderManager.renderBottomFace(this, 0.0D, 0.0D, 0.0D, this.getTexture(0, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 1.0F, 0.0F);
            blockRenderManager.renderTopFace(this, 0.0D, 0.0D, 0.0D, this.getTexture(1, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 0.0F, -1F);
            blockRenderManager.renderEastFace(this, 0.0D, 0.0D, 0.0D, this.getTexture(2, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(0.0F, 0.0F, 1.0F);
            blockRenderManager.renderWestFace(this, 0.0D, 0.0D, 0.0D, this.getTexture(3, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(-1F, 0.0F, 0.0F);
            blockRenderManager.renderNorthFace(this, 0.0D, 0.0D, 0.0D, this.getTexture(4, i));
            tessellator.draw();
            tessellator.startQuads();
            tessellator.normal(1.0F, 0.0F, 0.0F);
            blockRenderManager.renderSouthFace(this, 0.0D, 0.0D, 0.0D, this.getTexture(5, i));
            tessellator.draw();
            GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        }
    }
}
