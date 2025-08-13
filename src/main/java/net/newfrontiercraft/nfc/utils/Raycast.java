package net.newfrontiercraft.nfc.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class Raycast {
    public static HitResult raycast(LivingEntity origin, double distance, float tickDelta) {
        Vec3d position = getPosition(origin, tickDelta);
        Vec3d lookVector = origin.getLookVector(tickDelta);
        Vec3d target = position.add(lookVector.x * distance, lookVector.y * distance, lookVector.z * distance);
        return origin.world.raycast(position, target);
    }

    public static Vec3d getPosition(LivingEntity origin, float tickDelta) {
        if (tickDelta == 1.0F) {
            return Vec3d.createCached(origin.x, origin.y, origin.z);
        } else {
            double var2 = origin.prevX + (origin.x - origin.prevX) * (double)tickDelta;
            double var4 = origin.prevY + (origin.y - origin.prevY) * (double)tickDelta;
            double var6 = origin.prevZ + (origin.z - origin.prevZ) * (double)tickDelta;
            return Vec3d.createCached(var2, var4, var6);
        }
    }
}
