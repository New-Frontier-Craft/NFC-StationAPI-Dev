package net.newfrontiercraft.nfc.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.StationBlockPos;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;

public class SlabPlacer {
    public BlockItem slabBlockItem;

    public SlabPlacer(BlockItem slabBlockItem){
        this.slabBlockItem = slabBlockItem;
    }

    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        StationBlockPos blockOffset = new BlockPos(x, y, z);
        blockOffset = blockOffset.offset(Direction.byId(side));

        if(attemptSlabMerge(world, x, y, z, stack, side, false)){
            return true;
        }
        if(attemptSlabMerge(world, blockOffset.getX(), blockOffset.getY(), blockOffset.getZ(), stack, side, true)){
            return true;
        }
        if(this.attemptSlabPlace(world, blockOffset.getX(), blockOffset.getY(), blockOffset.getZ(), user, stack, side)){
            return true;
        }
        return false;
    }

    protected boolean attemptSlabMerge(World world, int x, int y, int z, ItemStack stack, int side, boolean skipPlacementCheck){
        if(this.canAttemptMerge(world, x, y, z, stack)){
            boolean canPlace = false;
            BlockState currentBlockState = world.getBlockState(x, y, z);
            int meta = world.getBlockMeta(x, y, z);
            BlockState fullBlockState = getFullBlockState(currentBlockState, meta);

            if(!skipPlacementCheck){
                canPlace = checkIfSlabCanMergeFromSide(currentBlockState, side);
            }
            return (canPlace || skipPlacementCheck) &&
                    (world.isRemote || this.placeBlock(world, x, y, z, fullBlockState, stack, getFullBlockMeta(currentBlockState, meta), true));
        }
        return false;
    }

    protected boolean checkIfSlabCanMergeFromSide(BlockState currentBlockState, int side){
        boolean canPlace = false;
        int slabRotation = currentBlockState.get(LazySlabTemplate.ROTATIONS);
        if(slabRotation == 0 && side == 1){
            canPlace = true;
        }
        if(slabRotation == 1 && side == 0){
            canPlace = true;
        }
        if(slabRotation == 2 && side == 4){
            canPlace = true;
        }
        if(slabRotation == 3 && side == 5){
            canPlace = true;
        }
        if(slabRotation == 4 && side == 2){
            canPlace = true;
        }
        if(slabRotation == 5 && side == 3){
            canPlace = true;
        }
        return canPlace;
    }

    protected boolean canAttemptMerge(World world, int x, int y, int z, ItemStack stack){
        int blockId = world.getBlockId(x, y, z);
        int blockMeta = world.getBlockMeta(x, y, z);
        return (blockId == slabBlockItem.getBlock().id) && blockMeta == stack.getDamage();
    }

    protected boolean attemptSlabPlace(World world, int x, int y, int z, PlayerEntity user, ItemStack stack, int side){
        int rotation = MathHelper.floor((double)(user.yaw * 4.0F / 360.0F) + 0.5) & 3;
        Vec3d lookVector = user.getLookVector(0);
        Vec3d previousPlayerLocation = Vec3d.create(user.prevX, user.prevY, user.prevZ);
        HitResult hitResult = Raycast.raycast(user, 5, lookVector, previousPlayerLocation);

        Vec3d hitOffset;
        if(hitResult == null){
            hitOffset = Vec3d.create(0, 0, 0);
        }
        else {
            hitOffset = Vec3d.create(
                    hitResult.pos.x - (float)hitResult.blockX,
                    hitResult.pos.y - (float)hitResult.blockY,
                    hitResult.pos.z - (float)hitResult.blockZ
            );
        }

        boolean canPlace;

        if(user.isSneaking()){
            int slabRotation = getVerticalSlabRotation(hitOffset, side, rotation);
            canPlace = this.placeBlock(world, x, y, z, getSlabBlockStateFromSlabRotation(slabRotation), stack, stack.getDamage(), false);
        }
        else {
            if((hitOffset.y < 0.5f && side != 0) || side == 1){
                canPlace = this.placeBlock(world, x, y, z, getSlabBlockStateFromSlabRotation(0), stack, stack.getDamage(), false);
            }
            else {
                canPlace = this.placeBlock(world, x, y, z, getSlabBlockStateFromSlabRotation(1), stack, stack.getDamage(), false);
            }
        }
        return canPlace;
    }

    protected BlockState getSlabBlockStateFromSlabRotation(int rotation){
        return slabBlockItem.getBlock().getDefaultState().with(LazySlabTemplate.ROTATIONS, rotation);
    }

    protected int getVerticalSlabRotation(Vec3d hitOffset, int side, int rotation){
        int slabRotation = 0;

        switch (rotation){
            case 0:
            case 2:
                if(side == 2 || side == 3){
                    slabRotation = getSlabRotationFromSide(side);
                }
                else {
                    if(hitOffset.z > 0 && hitOffset.z < 0.5f){
                        slabRotation = 5;
                    }
                    else {
                        slabRotation = 4;
                    }
                }
                break;
            case 1:
            case 3:
                if(side == 4 || side == 5){
                    slabRotation = getSlabRotationFromSide(side);
                }
                else {
                    if(hitOffset.x > 0 && hitOffset.x <= 0.5f){
                        slabRotation = 3;
                    }
                    else {
                        slabRotation = 2;
                    }
                }
                break;
        }
        return slabRotation;
    }

    protected BlockState getFullBlockState(BlockState currentBlockState, int meta){
        return Block.BLOCKS[((LazySlabTemplate) slabBlockItem.getBlock()).fullBlocks[meta]].getDefaultState();
    }

    protected int getFullBlockMeta(BlockState currentBlockState, int meta){
        return ((LazySlabTemplate) slabBlockItem.getBlock()).fullBlockMetas[meta];
    }

    protected boolean placeBlock(World world, int x, int y, int z, BlockState blockState, ItemStack stack, int meta, boolean ignoreExistingBlock){
        Block block = blockState.getBlock();
        if(this.canPlace(world, block.id, x, y, z, ignoreExistingBlock)){
            world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, block.soundGroup.getSound(), (block.soundGroup.getVolume() + 1.0F) / 2.0F, block.soundGroup.getPitch() * 0.8F);
            if (!world.isRemote) {
                world.setBlockStateWithMetadataWithNotify(x, y, z, blockState, meta);
            }
            stack.count--;
            return true;
        }
        return false;
    }

    protected boolean canPlace(World world, int blockId, int x, int y, int z, boolean ignoreExistingBlock) {
        Block blockToPlace = Block.BLOCKS[blockId];

        int existingBlockId = world.getBlockId(x, y, z);
        Block existingBlock = Block.BLOCKS[existingBlockId];

        if (existingBlock.material.isReplaceable()) {
            existingBlock = null;
        }

        Box var10 = blockToPlace.getCollisionShape(world, x, y, z);

        if (var10 != null && !world.canSpawnEntity(var10)) {
            return false;
        }
        return blockId > 0 && (existingBlock == null || ignoreExistingBlock);
    }

    protected int getSlabRotationFromSide(int side){
        switch (side){
            case 2:
                return 4;
            case 3:
                return 5;
            case 4:
                return 2;
            case 5:
                return 3;
        }
        return 0;
    }
}
