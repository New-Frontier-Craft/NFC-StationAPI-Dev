package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
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
        int blockX = x;
        int blockY = y;
        int blockZ = z;

        HitResult hitResult = user.raycast(5, 0);
        double hitOffsetX = hitResult.pos.x - (float)hitResult.blockX;
        double hitOffsetY = hitResult.pos.y - (float)hitResult.blockY;
        double hitOffsetZ = hitResult.pos.z - (float)hitResult.blockZ;
        System.out.println(hitResult.pos.x - (float)hitResult.blockX);
        System.out.println(hitResult.pos.y - (float)hitResult.blockY);
        System.out.println(hitResult.pos.z - (float)hitResult.blockZ);

        System.out.println(hitResult.pos.x + " - " + hitResult.blockX);

        System.out.println(side);
        // Merge existing slab into full block
        if((world.getBlockId(x, y, z) == Block.SLAB.id || world.getBlockId(x, y, z) == BlockListener.vanillaSlabs.id) && world.getBlockMeta(x, y, z) == stack.getDamage()){
            boolean canPlace = false;
            BlockState currentBlockState = world.getBlockState(x, y, z);
            int meta = world.getBlockMeta(x, y, z);
            BlockState fullBlockState = getFullBlockState(meta);

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
            if(canPlace){
                this.placeBlock(world, x, y, z, fullBlockState, 0);
                return true;
            }
        }
        else {
            switch (side){
                case 0:
                    blockY--;
                    break;
                case 1:
                    blockY++;
                    break;
                case 2:
                    blockZ--;
                    break;
                case 3:
                    blockZ++;
                    break;
                case 4:
                    blockX--;
                    break;
                case 5:
                    blockX++;
                    break;
            }
            // Merge existing slab into full block
            if(world.getBlockId(blockX, blockY, blockZ) == Block.SLAB.id || world.getBlockId(blockX, blockY, blockZ) == BlockListener.vanillaSlabs.id){
                int meta = world.getBlockMeta(blockX, blockY, blockZ);
                BlockState fullBlockState = getFullBlockState(meta);
                this.placeBlock(world, blockX, blockY, blockZ, fullBlockState, 0);
                return true;
            }
            if(world.isAir(blockX, blockY, blockZ)){
                if(user.isSneaking()){
                    int rotation = MathHelper.floor((double)(user.yaw * 4.0F / 360.0F) + 0.5) & 3;
                    System.out.println("rotation "+ rotation);
                    int slabDirection = 0;
                    if(side == 0 || side == 1){
                        switch (rotation){
                            case 0:
                            case 2:
                                if(hitOffsetZ < 0.5f){
                                    slabDirection = 5;
                                }
                                else {
                                    slabDirection = 4;
                                }
                                break;
                            case 1:
                            case 3:
                                if(hitOffsetX < 0.5f){
                                    slabDirection = 3;
                                }
                                else {
                                    slabDirection = 2;
                                }
                                break;
                        }
                    }
                    else {
                        slabDirection = getSlabDirectionFromPlacementDirection(side);
                    }
                    this.placeBlock(world, blockX, blockY, blockZ, BlockListener.vanillaSlabs.getDefaultState().with(LazySlabTemplate.ROTATIONS, slabDirection), stack.getDamage());
                }
                else {
                    if((hitOffsetY < 0.5f && side != 0) || side == 1){
                        this.placeBlock(world, blockX, blockY, blockZ, Block.SLAB.getDefaultState(), stack.getDamage());
                    }
                    else {
                        this.placeBlock(world, blockX, blockY, blockZ, BlockListener.vanillaSlabs.getDefaultState().with(LazySlabTemplate.ROTATIONS, 1), stack.getDamage());
                    }
                }
                return true;
            }
        }
        return false;
    }

    public BlockState getFullBlockState(int meta){
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

    public void placeBlock(World world, int x, int y, int z, BlockState blockState, int meta){
        world.setBlockStateWithMetadataWithNotify(x, y, z, blockState, meta);
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