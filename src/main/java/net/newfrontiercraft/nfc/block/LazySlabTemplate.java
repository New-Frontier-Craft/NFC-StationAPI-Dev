package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazySlabTemplate extends LazyMultivariantBlockTemplate {
    public int[] fullBlocks;
    public int[] fullBlockMetas = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public LazySlabTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        this.setOpacity(255);
        this.fullBlocks = fullBlocks;
    }

    public LazySlabTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, int[] fullBlockMetas) {
        super(identifier, material, hardness, blockSounds);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        this.setOpacity(255);
        this.fullBlocks = fullBlocks;
        this.fullBlockMetas = fullBlockMetas;
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
