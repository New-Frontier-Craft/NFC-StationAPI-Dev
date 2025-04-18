package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.math.Direction;
import net.modificationstation.stationapi.api.util.math.StationBlockPos;
import net.newfrontiercraft.nfc.block.LazySlabTemplate;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlabBlockItem.class)
public abstract class SlabBlockItemMixin extends BlockItem {
    public SlabBlockItemMixin(int i) {
        super(i);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        StationBlockPos blockOffset = new BlockPos(x, y, z);
        blockOffset = blockOffset.offset(Direction.byId(side));

        System.out.println(side);

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

    public boolean attemptSlabMerge(World world, int x, int y, int z, ItemStack stack, int side, boolean skipPlacementCheck){
        if((world.getBlockId(x, y, z) == Block.SLAB.id || world.getBlockId(x, y, z) == BlockListener.vanillaSlabs.id) && world.getBlockMeta(x, y, z) == stack.getDamage()){
            boolean canPlace = false;
            BlockState currentBlockState = world.getBlockState(x, y, z);
            int meta = world.getBlockMeta(x, y, z);
            BlockState fullBlockState = getFullBlockState(currentBlockState, meta);

            if(!skipPlacementCheck){
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
            }
            if(canPlace || skipPlacementCheck){
                if(this.placeBlock(world, x, y, z, fullBlockState, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean attemptSlabPlace(World world, int x, int y, int z, PlayerEntity user, ItemStack stack, int side){
        if(world.isAir(x, y, z)){
            HitResult hitResult = user.raycast(5, 0);
            double hitOffsetX = hitResult.pos.x - (float)hitResult.blockX;
            double hitOffsetY = hitResult.pos.y - (float)hitResult.blockY;
            double hitOffsetZ = hitResult.pos.z - (float)hitResult.blockZ;

            System.out.println("oX " + (hitResult.pos.x - (float)hitResult.blockX));
            System.out.println("oY " + (hitResult.pos.y - (float)hitResult.blockY));
            System.out.println("oZ " + (hitResult.pos.z - (float)hitResult.blockZ));

            boolean canPlace;

            if(user.isSneaking()){
                int rotation = MathHelper.floor((double)(user.yaw * 4.0F / 360.0F) + 0.5) & 3;
                System.out.println("rotation "+ rotation);
                int slabDirection = 0;
                if(true){
                    switch (rotation){
                        case 0:
                        case 2:
                            if(side == 2 || side == 3){
                                slabDirection = getSlabDirectionFromPlacementDirection(side);
                            }
                            else {
                                if(hitOffsetZ > 0 && hitOffsetZ < 0.5f){
                                    slabDirection = 5;
                                }
                                else {
                                    slabDirection = 4;
                                }
                            }
                            break;
                        case 1:
                        case 3:
                            if(side == 4 || side == 5){
                                slabDirection = getSlabDirectionFromPlacementDirection(side);
                            }
                            else {
                                if(hitOffsetX > 0 && hitOffsetX <= 0.5f){
                                    slabDirection = 3;
                                }
                                else {
                                    slabDirection = 2;
                                }
                            }
                            break;
                    }
                }
                else {
                    slabDirection = getSlabDirectionFromPlacementDirection(side);
                }
                canPlace = this.placeBlock(world, x, y, z, BlockListener.vanillaSlabs.getDefaultState().with(LazySlabTemplate.ROTATIONS, slabDirection), stack.getDamage());
            }
            else {
                if((hitOffsetY < 0.5f && side != 0) || side == 1){
                    canPlace = this.placeBlock(world, x, y, z, Block.SLAB.getDefaultState(), stack.getDamage());
                }
                else {
                    canPlace = this.placeBlock(world, x, y, z, BlockListener.vanillaSlabs.getDefaultState().with(LazySlabTemplate.ROTATIONS, 1), stack.getDamage());
                }
            }
            return canPlace;
        }
        return false;
    }

    public BlockState getFullBlockState(BlockState currentBlockState, int meta){
        BlockState fullBlockState = Block.DOUBLE_SLAB.getDefaultState();
        switch (meta){
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

    public boolean placeBlock(World world, int x, int y, int z, BlockState blockState, int meta){
        Block block = blockState.getBlock();
        if(this.canPlace(world, block.id, x, y, z)){
            world.playSound(x + 0.5F, y + 0.5F, z + 0.5F, block.soundGroup.getSound(), (block.soundGroup.getVolume() + 1.0F) / 2.0F, block.soundGroup.getPitch() * 0.8F);
            world.setBlockStateWithMetadataWithNotify(x, y, z, blockState, meta);
            return true;
        }
        return false;
    }

    public boolean canPlace(World world, int blockId, int x, int y, int z) {
        Block blockToPlace = Block.BLOCKS[blockId];
        Box var10 = blockToPlace.getCollisionShape(world, x, y, z);

        if (var10 != null && !world.canSpawnEntity(var10)) {
            return false;
        }
        return blockId > 0;
    }

    public int getSlabDirectionFromPlacementDirection(int direction){
        switch (direction){
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