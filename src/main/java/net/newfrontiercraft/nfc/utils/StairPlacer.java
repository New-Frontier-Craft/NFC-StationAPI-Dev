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
import net.newfrontiercraft.nfc.block.LazyStairsTemplate;

public class StairPlacer {
    public BlockItem stairBlockItem;
    public StairPlacer(BlockItem stairBlockItem){
        this.stairBlockItem = stairBlockItem;
    }
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        StationBlockPos blockOffset = new BlockPos(x, y, z);
        blockOffset = blockOffset.offset(Direction.byId(side));
        if(this.canPlace(world, this.stairBlockItem.getBlock().id, blockOffset.getX(), blockOffset.getY(), blockOffset.getZ())){
            placeStair(world, blockOffset.getX(), blockOffset.getY(), blockOffset.getZ(), user, stack, side);
            return true;
        }
        return false;
    }

    protected void placeStair(World world, int x, int y, int z, PlayerEntity user, ItemStack stack, int side){

        HitResult hitResult = Raycast.raycast(user, 5, 0);
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

        int rotation;
        if(user.isSneaking()) {
            rotation = getVerticalStairRotation(user, hitOffset, side);
        }
        else {
            rotation = getStairRotation(user, hitOffset, side);
        }
        BlockState stairBlockState = getStairBlockStateFromStairRotation(rotation);
        this.placeBlock(world, x, y, z, stairBlockState, stack, this.usesMetaForRotation() ? getStairMetadataFromStairRotation(rotation, stack.getDamage()) : stack.getDamage());
    }

    protected int getStairRotation(PlayerEntity user, Vec3d hitOffset, int side){
        int rotation = MathHelper.floor((double)(user.yaw * 4.0F / 360.0F) + 0.5) & 3;

        int offset = 0;
        if(side > 1 && hitOffset.y > 0.5F){
            offset = 4;
        }

        switch (rotation){
            case 0:
                return 2 + offset;
            case 1:
                return 1 + offset;
            case 2:
                return 3 + offset;
            case 3:
                return offset;
        }
        return 0;
    }

    protected void placeBlock(World world, int x, int y, int z, BlockState blockState, ItemStack stack, int meta){
        Block block = blockState.getBlock();
        world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, block.soundGroup.getSound(), (block.soundGroup.getVolume() + 1.0F) / 2.0F, block.soundGroup.getPitch() * 0.8F);
        world.setBlockStateWithMetadataWithNotify(x, y, z, blockState, meta);
        stack.count--;
    }

    protected int getVerticalStairRotation(PlayerEntity user, Vec3d hitOffset, int side){
        int rotation = MathHelper.floor((double)((user.yaw + 45.0F) * 4.0F / 360.0F) + 0.5) & 3;

        return rotation + 8;
    }

    protected boolean canPlace(World world, int blockId, int x, int y, int z) {
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
        return blockId > 0 && existingBlock == null;
    }

    protected BlockState getStairBlockStateFromStairRotation(int rotation){
        return stairBlockItem.getBlock().getDefaultState().with(LazyStairsTemplate.ROTATIONS, rotation);
    }

    protected boolean usesMetaForRotation(){
        return false;
    }

    protected int getStairMetadataFromStairRotation(int rotation, int itemMeta){
        return 0;
    }
}
