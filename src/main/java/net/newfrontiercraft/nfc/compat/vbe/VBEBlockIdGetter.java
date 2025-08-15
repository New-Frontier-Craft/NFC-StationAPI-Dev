package net.newfrontiercraft.nfc.compat.vbe;

import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.state.property.Properties;
import net.modificationstation.stationapi.api.util.math.Direction;
import paulevs.vbe.block.VBEBlockProperties;
import paulevs.vbe.block.VBEBlocks;

public class VBEBlockIdGetter {
    public static int getOakLogId() {
        return VBEBlocks.OAK_LOG.id;
    }

    public static int getOakLeavesId() {
        return VBEBlocks.OAK_LEAVES.id;
    }

    public static int getBirchLeavesId() {
        return VBEBlocks.BIRCH_LEAVES.id;
    }

    public static int getSpruceLeavesId() {
        return VBEBlocks.SPRUCE_LEAVES.id;
    }

    public static BlockState getRotatedVBEStair(BlockState blockState, int rotation){
        System.out.println(rotation);
        return switch (rotation) {
            case 0 -> blockState.with(Properties.HORIZONTAL_FACING, Direction.SOUTH);
            case 1 -> blockState.with(Properties.HORIZONTAL_FACING, Direction.NORTH);
            case 2 -> blockState.with(Properties.HORIZONTAL_FACING, Direction.WEST);
            case 3 -> blockState.with(Properties.HORIZONTAL_FACING, Direction.EAST);
            default -> blockState;
        };
    }
}
