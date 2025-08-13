package net.newfrontiercraft.nfc.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class Raycast {
    public static HitResult raycast(LivingEntity origin, double distance, Vec3d lookVector, Vec3d previousPlayerLocation) {
        Vec3d target = previousPlayerLocation.add(lookVector.x * distance, lookVector.y * distance, lookVector.z * distance);
        return origin.world.raycast(previousPlayerLocation, target);
    }
}
