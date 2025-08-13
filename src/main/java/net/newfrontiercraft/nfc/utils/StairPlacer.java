package net.newfrontiercraft.nfc.utils;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
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
import net.modificationstation.stationapi.api.network.packet.PacketHelper;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.StationBlockPos;
import net.newfrontiercraft.nfc.block.LazyStairsTemplate;
import net.newfrontiercraft.nfc.packet.c2s.BlockPlacementPacket;

public class StairPlacer {
    public BlockItem stairBlockItem;
    public StairPlacer(BlockItem stairBlockItem){
        this.stairBlockItem = stairBlockItem;
    }
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER){
            return true;
        }
        StationBlockPos blockOffset = new BlockPos(x, y, z);
        blockOffset = blockOffset.offset(Direction.byId(side));
        if(this.canPlace(world, this.stairBlockItem.getBlock().id, blockOffset.getX(), blockOffset.getY(), blockOffset.getZ())){
            placeStair(world, blockOffset.getX(), blockOffset.getY(), blockOffset.getZ(), user, stack, side);
            return true;
        }
        return false;
    }

    protected void placeStair(World world, int x, int y, int z, PlayerEntity user, ItemStack stack, int side){
        double yaw = user.yaw;
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

        int rotation;
        if(user.isSneaking()) {
            rotation = getVerticalStairRotation(yaw);
        }
        else {
            rotation = getStairRotation(yaw, hitOffset, side);
        }
        BlockState stairBlockState = getStairBlockStateFromStairRotation(rotation);
        this.placeBlock(world, x, y, z, stairBlockState, stack, this.usesMetaForRotation() ? getStairMetadataFromStairRotation(rotation, stack.getDamage()) : stack.getDamage());
    }

    protected int getStairRotation(double yaw, Vec3d hitOffset, int side){
        int rotation = MathHelper.floor((yaw * 4.0F / 360.0F) + 0.5) & 3;

        int offset = 0;
        if(side > 1 && hitOffset.y > 0.5F || side == 0){
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
        if (!world.isRemote) {
            world.setBlockStateWithMetadataWithNotify(x, y, z, blockState, meta);
        }
        else {
            PacketHelper.send(new BlockPlacementPacket(x, y, z, blockState, 3));
        }
        stack.count--;
    }

    protected int getVerticalStairRotation(double yaw){
        int rotation = MathHelper.floor(((yaw + 45.0F) * 4.0F / 360.0F) + 0.5) & 3;

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
