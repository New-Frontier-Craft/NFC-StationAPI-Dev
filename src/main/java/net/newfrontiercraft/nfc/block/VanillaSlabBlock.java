package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldRegion;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.mixin.graphics.BlockRenderManagerAccessor;

public class VanillaSlabBlock extends LazySlabTemplate {
    public VanillaSlabBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds, int[] fullBlocks, float[] boundingBoxValues, Block bottomSlabCounterpart) {
        super(identifier, material, hardness, blockSounds, fullBlocks, bottomSlabCounterpart);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 0 && meta == 1) {
            return Block.SANDSTONE.textureId + 16;
        }
        if (side == 1 && meta == 1) {
            return Block.SANDSTONE.textureId - 16;
        }
        if(meta == 0){
            if(side <= 1){
                return 6;
            }
            else{
                return 5;
            }
        }
        return super.getTexture(side, meta);
    }

    @Override
    public int getTextureId(BlockView blockView, int x, int y, int z, int side) {
        int meta = blockView.getBlockMeta(x, y, z);
        if(meta == 0){
            if(blockView instanceof WorldRegion worldRegion){
                BlockState blockState = worldRegion.getBlockState(x, y, z);
                if(blockState.contains(LazySlabTemplate.ROTATIONS)){
                    int rotation = blockState.get(LazySlabTemplate.ROTATIONS);
                    if(rotation == 0 || rotation == 1){
                        if(side <= 1){
                            return 6;
                        }
                        else{
                            return 5;
                        }
                    }
                    if(rotation == 2 || rotation == 3){
                        if(side == 4 || side == 5){
                            return 6;
                        }
                        else{
                            return 5;
                        }
                    }
                    if(rotation == 4 || rotation == 5){
                        if(side == 2 || side == 3){
                            return 6;
                        }
                        else{
                            return 5;
                        }
                    }
                }
            }
        }
        return getTexture(side, meta);
    }

    @Override
    public boolean renderWorld(BlockRenderManager blockRenderManager, BlockView blockView, int x, int y, int z) {
        int meta = blockView.getBlockMeta(x, y, z);
        boolean flag = false;
        if(meta == 0){
            if(blockView instanceof WorldRegion worldRegion) {
                BlockState blockState = worldRegion.getBlockState(x, y, z);
                if (blockState.contains(LazySlabTemplate.ROTATIONS)) {
                    int rotation = blockState.get(LazySlabTemplate.ROTATIONS);
                    if(rotation == 2 || rotation == 3){
                        ((BlockRenderManagerAccessor)blockRenderManager).setTopFaceRotation(1);
                        ((BlockRenderManagerAccessor)blockRenderManager).setBottomFaceRotation(2);
                        ((BlockRenderManagerAccessor)blockRenderManager).setEastFaceRotation(2);
                        ((BlockRenderManagerAccessor)blockRenderManager).setWestFaceRotation(1);
                    }
                    if(rotation == 4 || rotation == 5){
                        ((BlockRenderManagerAccessor)blockRenderManager).setBottomFaceRotation(3);
                        ((BlockRenderManagerAccessor)blockRenderManager).setSouthFaceRotation(1);
                        ((BlockRenderManagerAccessor)blockRenderManager).setNorthFaceRotation(2);
                    }
                    flag = super.renderWorld(blockRenderManager, blockView, x, y, z);
                    ((BlockRenderManagerAccessor)blockRenderManager).setTopFaceRotation(0);
                    ((BlockRenderManagerAccessor)blockRenderManager).setBottomFaceRotation(0);
                    ((BlockRenderManagerAccessor)blockRenderManager).setEastFaceRotation(0);
                    ((BlockRenderManagerAccessor)blockRenderManager).setWestFaceRotation(0);
                    ((BlockRenderManagerAccessor)blockRenderManager).setSouthFaceRotation(0);
                    ((BlockRenderManagerAccessor)blockRenderManager).setNorthFaceRotation(0);
                }
            }
        }
        else {
            flag = super.renderWorld(blockRenderManager, blockView, x, y, z);
        }

        return flag;
    }
}
