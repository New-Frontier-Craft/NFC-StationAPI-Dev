package net.newfrontiercraft.nfc.utils;

import net.minecraft.util.math.Box;
import net.modificationstation.stationapi.api.util.math.Direction;

public class BoxUtil {
    public static Direction getCollisionSideFromBoxes(Box box, Box other){
        double dxMin = other.maxX - box.minX;
        double dxMax = box.maxX - other.minX;

        double dyMin = other.maxY - box.minY;
        double dyMax = box.maxY - other.minY;

        double dzMin = other.maxZ - box.minZ;
        double dzMax = box.maxZ - other.minZ;

        double min = Math.min(Math.min(Math.min(dxMin, dxMax), Math.min(dzMin, dzMax)), Math.min(dyMin, dyMax));
        if(min == dxMin) return Direction.WEST;
        else if(min == dxMax) return Direction.EAST;
        else if(min == dzMin) return Direction.NORTH;
        else if(min == dzMax) return Direction.SOUTH;
        else if(min == dyMin) return Direction.DOWN;
        else if(min == dyMax) return Direction.UP;
        // Should be unreachable
        return Direction.UP;
    }
}
