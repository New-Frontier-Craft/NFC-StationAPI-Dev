package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldRegion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.state.StateManager;
import net.modificationstation.stationapi.api.state.property.IntProperty;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.item.BioluminescentMushroomBlockItem;
import net.newfrontiercraft.nfc.block.item.SlabBlockItem;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.StationBlockPos;
import net.newfrontiercraft.nfc.mixin.DroppedMetaAccessor;
import net.newfrontiercraft.nfc.utils.BlockWithItemRenderBounds;
import org.lwjgl.input.Keyboard;

import java.util.Random;

@EnvironmentInterface(value=EnvType.CLIENT, itf= BlockWithWorldRenderer.class)
@HasCustomBlockItemFactory(SlabBlockItem.class)
public class LazySlabTemplate extends LazyMultivariantBlockTemplate implements BlockWithWorldRenderer, BlockWithItemRenderBounds {
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


    @Environment(EnvType.CLIENT)
    @Override
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        if (!(blockView instanceof WorldRegion)) {
            return true;
        }
        StationBlockPos blockOffset = new BlockPos(x, y, z);
        int mirroredSide = switch (side) {
            case 0 -> 1;
            case 1 -> 0;
            case 2 -> 3;
            case 3 -> 2;
            case 4 -> 5;
            case 5 -> 4;
            default -> 0;
        };
        blockOffset = blockOffset.offset(Direction.byId(mirroredSide));
        if (blockView.getBlockId(blockOffset.getX(), blockOffset.getY(), blockOffset.getZ()) != this.id) {
            return true;
        }
        int rotation = ((WorldRegion)blockView).getBlockState(blockOffset.getX(), blockOffset.getY(), blockOffset.getZ()).get(ROTATIONS);
        if (rotation > 1 && rotation < 4) {
            rotation += 2;
        } else if (rotation > 3) {
            rotation -= 2;
        }
        if (rotation <= 1) {
            rotation = switch (rotation) {
                case 0 -> 1;
                case 1 -> 0;
                default -> 0;
            };
        }
        if (side != rotation) {
            return super.isSideVisible(blockView, x, y, z, side);
        }
        return true;
    }

    // This only exists for testing
    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockState currentState = world.getBlockState(x, y, z);
        int rotation = world.getBlockState(x, y, z).get(ROTATIONS);
        world.setBlockStateWithMetadataWithNotify(x, y, z, currentState.with(ROTATIONS, (rotation + 1) % 6), world.getBlockMeta(x, y, z));
        return true;
    }

    // This is a placeholder, remove when block item merging has been fully implemented
    @Override
    public void onPlaced(World world, int x, int y, int z) {
        int blockId = world.getBlockId(x, y - 1, z);
        int selfMeta = world.getBlockMeta(x, y, z);
        int belowMeta = world.getBlockMeta(x, y - 1, z);
        if (selfMeta != belowMeta) {
            super.onPlaced(world, x, y, z);
            return;
        }
        if (blockId != this.id) {
            super.onPlaced(world, x, y, z);
            return;
        }
        world.setBlock(x, y, z, 0);
        world.setBlock(x, y - 1, z, fullBlocks[selfMeta], fullBlockMetas[selfMeta]);
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

    @Override
    public void setBlockBoundsForItemRender() {
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
}
