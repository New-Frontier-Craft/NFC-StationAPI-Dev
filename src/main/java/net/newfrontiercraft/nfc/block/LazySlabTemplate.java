package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldRegion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.mixin.DroppedMetaAccessor;
import org.lwjgl.input.Keyboard;

import java.util.Random;

@EnvironmentInterface(value=EnvType.CLIENT, itf= BlockWithWorldRenderer.class)
public class LazySlabTemplate extends LazyMultivariantBlockTemplate implements BlockWithWorldRenderer {
    public int[] fullBlocks;
    public int[] fullBlockMetas = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public Block bottomSlabCounterpart;
    public static final IntProperty ROTATIONS = IntProperty.of("rotations", 0, 6);

    public LazySlabTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, Block bottomSlabCounterpart) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        this.setOpacity(2);
        this.fullBlocks = fullBlocks;
        this.bottomSlabCounterpart = bottomSlabCounterpart;
    }

    public LazySlabTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, int[] fullBlockMetas, Block bottomSlabCounterpart) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        this.setOpacity(2);
        this.fullBlocks = fullBlocks;
        this.fullBlockMetas = fullBlockMetas;
        this.bottomSlabCounterpart = bottomSlabCounterpart;
    }

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(ROTATIONS);
    }

    @Environment(EnvType.CLIENT)
    @Override
    public Box getBoundingBox(World world, int x, int y, int z) {
        this.updateBoundingBox(world, x, y, z);
        return super.getBoundingBox(world, x, y, z);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        this.updateBoundingBox(world, x, y, z);
        return super.getCollisionShape(world, x, y, z);
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        int blockId = blockView.getBlockId(x, y, z);
        if (blockId == 0) {
            return;
        }
        Block block = Block.BLOCKS[blockId];
        if (!(block instanceof LazySlabTemplate)) {
            return;
        }
        if (blockView instanceof World world) {
            int rotation = world.getBlockState(x, y, z).get(ROTATIONS);
            switch (rotation) {
                case 0:
                    this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
                    break;
                case 1:
                    this.setBoundingBox(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;
                case 2:
                    this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;
                case 3:
                    this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
                    break;
                case 4:
                    this.setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
                    break;
                case 5:
                    this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
                    break;
            }
        }
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        if (bottomSlabCounterpart == null) {
            return super.getDroppedItemMeta(blockMeta);
        }
        return ((DroppedMetaAccessor)bottomSlabCounterpart).invokeDroppedItemMeta(blockMeta);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        if (bottomSlabCounterpart == null) {
            return super.getDroppedItemId(blockMeta, random);
        }
        return bottomSlabCounterpart.getDroppedItemId(blockMeta, random);
    }

    @Override
    public int getDroppedItemCount(Random random) {
        if (bottomSlabCounterpart == null) {
            return super.getDroppedItemCount(random);
        }
        return bottomSlabCounterpart.getDroppedItemCount(random);
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    /* This code needs to be adapted to work with block states
    @Environment(EnvType.CLIENT)
    @Override
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        if (side == 1) {
            return true;
        } else if (!super.isSideVisible(blockView, x, y, z, side)) {
            return false;
        } else {
            return side == 0 || blockView.getBlockId(x, y, z) != this.id;
        }
    }
     */

    //This only exists for testing
    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockState currentState = world.getBlockState(x, y, z);
        int rotation = world.getBlockState(x, y, z).get(ROTATIONS);
        System.out.println(rotation);
        if(Keyboard.isKeyDown(Keyboard.KEY_C)){
            world.setBlockStateWithMetadataWithNotify(x, y, z, currentState.with(ROTATIONS, (rotation + 1) % 6), world.getBlockMeta(x, y, z));
            return true;
        }
        return false;
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z) {
        int var5 = world.getBlockId(x, y, z);
        return var5 == 0 || var5 == id || BLOCKS[var5].material.isReplaceable();
    }

    @Override
    public boolean renderWorld(BlockRenderManager blockRenderManager, BlockView blockView, int x, int y, int z) {
        int blockId = blockView.getBlockId(x, y, z);
        if (blockId == 0) {
            return false;
        }
        Block block = Block.BLOCKS[blockId];
        if (!(block instanceof LazySlabTemplate)) {
            return false;
        }
        int rotation = 0;
        if (blockView instanceof WorldRegion worldRegion) {
            rotation = worldRegion.getBlockState(x, y, z).get(ROTATIONS);
        } else if (blockView instanceof World world) {
            rotation = world.getBlockState(x, y, z).get(ROTATIONS);
        }
        switch (rotation) {
            case 0:
                this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
                blockRenderManager.renderBlock(this, x, y, z);
                break;
            case 1:
                this.setBoundingBox(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
                blockRenderManager.renderBlock(this, x, y, z);
                break;
            case 2:
                this.setBoundingBox(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                blockRenderManager.renderBlock(this, x, y, z);
                break;
            case 3:
                this.setBoundingBox(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
                blockRenderManager.renderBlock(this, x, y, z);
                break;
            case 4:
                this.setBoundingBox(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
                blockRenderManager.renderBlock(this, x, y, z);
                break;
            case 5:
                this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
                blockRenderManager.renderBlock(this, x, y, z);
                break;
        }
        return true;
    }
}
