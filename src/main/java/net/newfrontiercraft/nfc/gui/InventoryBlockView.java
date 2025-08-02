package net.newfrontiercraft.nfc.gui;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.biome.source.BiomeSource;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.world.StationFlatteningWorld;

import java.util.HashMap;
import java.util.Map;

public class InventoryBlockView implements BlockView, StationFlatteningWorld {
    private Map<BlockPos, BlockState> blockStates;
    private Map<BlockPos, Integer> metas;

    public InventoryBlockView(){
        blockStates = new HashMap<>();
        metas = new HashMap<>();
    }

    @Override
    public int getBlockId(int x, int y, int z) {
        BlockState blockState = blockStates.get(new BlockPos(x, y, z));
        if(blockState == null || blockState.isAir()) return 0;
        return blockState.getBlock().id;
    }

    @Override
    public BlockEntity getBlockEntity(int x, int y, int z) {
        return null;
    }

    @Override
    public float getNaturalBrightness(int x, int y, int z, int blockLight) {
        return 15;
    }

    @Override
    public float method_1782(int x, int y, int z) {
        return 0;
    }

    @Override
    public int getBlockMeta(int x, int y, int z) {
        Integer meta = metas.get(new BlockPos(x, y, z));
        return meta != null ? meta : 0;
    }

    @Override
    public Material getMaterial(int x, int y, int z) {
        return blockStates.get(new BlockPos(x, y, z)).getMaterial();
    }

    @Override
    public boolean method_1783(int x, int y, int z) {
        return false;
    }

    @Override
    public boolean shouldSuffocate(int x, int y, int z) {
        return false;
    }

    @Override
    public BiomeSource method_1781() {
        return null;
    }

    @Override
    public BlockState getBlockState(int x, int y, int z) {
        BlockState blockState = blockStates.get(new BlockPos(x, y, z));
        return blockState != null ? blockState : States.AIR.get();
    }

    @Override
    public BlockState setBlockState(int x, int y, int z, BlockState blockState) {
        return blockState;
    }

    @Override
    public BlockState setBlockStateWithNotify(BlockPos pos, BlockState blockState) {
        return blockState;
    }

    @Override
    public BlockState setBlockStateWithMetadata(int x, int y, int z, BlockState blockState, int meta) {
        blockStates.put(new BlockPos(x, y, z), blockState);
        return blockState;
    }

    @Override
    public int getBottomY() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 128;
    }
}
