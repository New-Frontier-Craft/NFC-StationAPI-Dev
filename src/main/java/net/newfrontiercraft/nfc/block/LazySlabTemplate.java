package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.mixin.DroppedMetaAccessor;

import java.util.Random;

public class LazySlabTemplate extends LazyMultivariantBlockTemplate {
    public int[] fullBlocks;
    public int[] fullBlockMetas = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public Block bottomSlabCounterpart;

    public LazySlabTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, float[] boundingBoxValues, Block bottomSlabCounterpart) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(boundingBoxValues[0], boundingBoxValues[1], boundingBoxValues[2], boundingBoxValues[3], boundingBoxValues[4], boundingBoxValues[5]);
        this.setOpacity(255);
        this.fullBlocks = fullBlocks;
        this.bottomSlabCounterpart = bottomSlabCounterpart;
    }

    public LazySlabTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, int[] fullBlockMetas, float[] boundingBoxValues, Block bottomSlabCounterpart) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(boundingBoxValues[0], boundingBoxValues[1], boundingBoxValues[2], boundingBoxValues[3], boundingBoxValues[4], boundingBoxValues[5]);
        this.setOpacity(255);
        this.fullBlocks = fullBlocks;
        this.fullBlockMetas = fullBlockMetas;
        this.bottomSlabCounterpart = bottomSlabCounterpart;
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
        if (side == 1) {
            return true;
        } else if (!super.isSideVisible(blockView, x, y, z, side)) {
            return false;
        } else {
            return side == 0 || blockView.getBlockId(x, y, z) != this.id;
        }
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
}
