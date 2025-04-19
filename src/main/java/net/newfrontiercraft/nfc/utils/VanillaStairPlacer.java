package net.newfrontiercraft.nfc.utils;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.block.LazyStairsTemplate;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class VanillaStairPlacer extends StairPlacer{
    public static VanillaStairPlacer INSTANCE = new VanillaStairPlacer((BlockItem)BlockListener.stoneVanillaStairs.asItem());
    boolean isStone = false;
    public VanillaStairPlacer(BlockItem stairBlockItem) {
        super(stairBlockItem);
    }

    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side, boolean isStone) {
        if(stack.getItem() instanceof BlockItem blockItem){
            super.stairBlockItem = blockItem;
            this.isStone = isStone;
            return super.useOnBlock(stack, user, world, x, y, z, side);
        };
        return false;
    }

    @Override
    protected BlockState getStairBlockStateFromStairRotation(int rotation) {
        BlockState stairBlockState;
        if(rotation < 4){
            stairBlockState = isStone ? Block.COBBLESTONE_STAIRS.getDefaultState() : Block.WOODEN_STAIRS.getDefaultState();
        }
        else{
            stairBlockState = isStone ?
                    BlockListener.stoneVanillaStairs.getDefaultState().with(LazyStairsTemplate.ROTATIONS, rotation) :
                    BlockListener.woodenVanillaStairs.getDefaultState().with(LazyStairsTemplate.ROTATIONS, rotation);
        }
        return stairBlockState;
    }

    @Override
    protected boolean usesMetaForRotation() {
        return true;
    }

    @Override
    protected int getStairMetadataFromStairRotation(int rotation, int itemMeta) {
        if(rotation < 4){
            return rotation;
        }
        else {
            return  itemMeta;
        }
    }
}
