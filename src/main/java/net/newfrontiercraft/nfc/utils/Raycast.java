package net.newfrontiercraft.nfc.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class Raycast {
    public static HitResult raycast(LivingEntity origin, double distance, Vec3d lookVector, Vec3d previousPlayerLocation) {
        Vec3d position = getPosition(previousPlayerLocation);

        Vec3d target = position.add(lookVector.x * distance, lookVector.y * distance, lookVector.z * distance);
        return origin.world.raycast(position, target);
    }

    public static Vec3d getPosition(Vec3d previousPlayerLocation) {
        double var2 = previousPlayerLocation.x;
        double var4 = previousPlayerLocation.y;
        double var6 = previousPlayerLocation.z;
        return Vec3d.createCached(var2, var4, var6);
    }
}
