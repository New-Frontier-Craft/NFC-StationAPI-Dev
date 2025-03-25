package net.newfrontiercraft.nfc.particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.world.World;

public class SporeParticle extends Particle {
    public SporeParticle(World world, double d, double d1, double d2, double d3, double d4, double d5, float i, float j, float k) {
        super(world, d, d1, d2, d3, d4, d5);
        velocityX = d3 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.025F);
        velocityY = d4 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.015F);
        velocityZ = d5 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.025F);
        scale = 1.0F;
        textureId = 0;
        red *= i;
        green *= j;
        blue *= k;
        maxParticleAge = ((int) (100D / ((double) random.nextFloat() * random.nextFloat() * .40000000000000004D + 0.20000000000000001D)) + 2);
    }
    
    @Override
    public void tick() {
        prevX = x;
        prevY = y;
        prevZ = z;
        if (particleAge++ >= maxParticleAge) {
            markDead();
        }
        velocityY += 0.0004D;
        move(velocityX, velocityY, velocityZ);
        velocityX *= 0.8125D;
        velocityY *= 0.8125D;
        velocityZ *= 0.8125D;
        if (onGround) {
            velocityX *= 0.69999998807907104D;
            velocityZ *= 0.69999998807907104D;
        }
    }

    @Override
    public void render(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float f6 = (float) (textureId % 16) / 16F;
        float f7 = f6 + 0.0624375F;
        float f8 = (float) (textureId / 16) / 16F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * scale;
        float f11 = (float) ((prevX + (x - prevX) * (double) f) - xOffset);
        float f12 = (float) ((prevY + (y - prevY) * (double) f) - yOffset);
        float f13 = (float) ((prevZ + (z - prevZ) * (double) f) - zOffset);
        float f14 = getBrightnessAtEyes(f) * 1.25F;
        tessellator.color(red * f14, green * f14, blue * f14);
        tessellator.vertex(f11 - f1 * f10 - f4 * f10, f12 - f2 * f10, f13 - f3 * f10 - f5 * f10, f7, f9);
        tessellator.vertex((f11 - f1 * f10) + f4 * f10, f12 + f2 * f10, (f13 - f3 * f10) + f5 * f10, f7, f8);
        tessellator.vertex(f11 + f1 * f10 + f4 * f10, f12 + f2 * f10, f13 + f3 * f10 + f5 * f10, f6, f8);
        tessellator.vertex((f11 + f1 * f10) - f4 * f10, f12 - f2 * f10, (f13 + f3 * f10) - f5 * f10, f6, f9);
    }
}
