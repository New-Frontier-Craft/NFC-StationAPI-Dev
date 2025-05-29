package net.newfrontiercraft.nfc.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class Raycast {
    public static HitResult raycast(LivingEntity origin, double distance, float tickDelta) {
        Vec3d var4 = getPosition(origin, tickDelta);
        Vec3d var5 = origin.getLookVector(tickDelta);
        Vec3d var6 = var4.add(var5.x * distance, var5.y * distance, var5.z * distance);
        return origin.world.raycast(var4, var6);
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
