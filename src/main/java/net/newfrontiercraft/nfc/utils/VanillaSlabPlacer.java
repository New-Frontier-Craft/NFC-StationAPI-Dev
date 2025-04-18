package net.newfrontiercraft.nfc.utils;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class VanillaSlabPlacer extends SlabPlacer{
    public VanillaSlabPlacer(BlockItem slabBlockItem) {
        super(slabBlockItem);
    }

    @Override
    protected BlockState getFullBlockState(BlockState currentBlockState, int meta) {
        BlockState fullBlockState = Block.DOUBLE_SLAB.getDefaultState();
        switch (meta){
            case 0:
                if(currentBlockState.contains(LazySlabTemplate.ROTATIONS)){
                    int rotation = currentBlockState.get(LazySlabTemplate.ROTATIONS);
                    if(rotation > 1){
                        fullBlockState = BlockListener.doubleStoneSlab.getDefaultState();
                    }
                }
                break;
            case 1:
                fullBlockState = Block.SANDSTONE.getDefaultState();
                break;
            case 2:
                fullBlockState = Block.PLANKS.getDefaultState();
                break;
            case 3:
                fullBlockState = Block.COBBLESTONE.getDefaultState();
                break;
        }
        return fullBlockState;
    }

    @Override
    protected int getFullBlockMeta(BlockState currentBlockState, int meta) {
        if(currentBlockState.contains(LazySlabTemplate.ROTATIONS)){
            int rotation = currentBlockState.get(LazySlabTemplate.ROTATIONS);
            if(rotation == 2 || rotation == 3){
                return 5;
            }
            if(rotation == 4 || rotation == 5){
                return 4;
            }
        }
        return 0;
    }

    @Override
    protected boolean canAttemptMerge(World world, int x, int y, int z, ItemStack stack) {
        int blockId = world.getBlockId(x, y, z);
        int blockMeta = world.getBlockMeta(x, y, z);
        return (blockId == Block.SLAB.id || world.getBlockId(x, y, z) == BlockListener.vanillaSlabs.id) && blockMeta == stack.getDamage();
    }

    @Override
    protected boolean checkIfSlabCanMergeFromSide(BlockState currentBlockState, int side) {
        boolean canPlace = false;
        if(currentBlockState.getBlock().id == BlockListener.vanillaSlabs.id){
            int slabDirection = currentBlockState.get(LazySlabTemplate.ROTATIONS);
            if(slabDirection == 1 && side == 0){
                canPlace = true;
            }
            if(slabDirection == 2 && side == 4){
                canPlace = true;
            }
            if(slabDirection == 3 && side == 5){
                canPlace = true;
            }
            if(slabDirection == 4 && side == 2){
                canPlace = true;
            }
            if(slabDirection == 5 && side == 3){
                canPlace = true;
            }
        }
        if(currentBlockState.getBlock().id == Block.SLAB.id){
            if(side == 1){
                canPlace = true;
            }
        }
        return canPlace;
    }

    @Override
    protected BlockState getSlabBlockStateFromSlabRotation(int rotation) {
        if(rotation == 0){
            return Block.SLAB.getDefaultState();
        }
        return BlockListener.vanillaSlabs.getDefaultState().with(LazySlabTemplate.ROTATIONS, rotation);
    }
}
