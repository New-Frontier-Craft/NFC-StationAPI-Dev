package net.newfrontiercraft.nfc.mixin;

import net.minecraft.world.World;
import net.minecraft.world.gen.Generator;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CaveWorldCarver.class)
public abstract class CaveWorldCarverMixin extends Generator {

    @Shadow protected abstract void placeTunnels(int chunkX, int chunkZ, byte[] blocks, double x, double y, double z, float baseWidth, float yaw, float pitch, int tunnel, int tunnelCount, double widthHeightRatio);

    @Inject(at = @At("HEAD"), method = "place(Lnet/minecraft/world/World;IIII[B)V", cancellable = true)
    private void buffCaveGeneration(World world, int startChunkX, int startChunkZ, int k, int l, byte[] blocks, CallbackInfo ci) {
        if (random.nextInt(9) == 1) {
            double d = startChunkX * 16 + random.nextInt(16);
            double d1 = (random.nextInt(10) > 4) ? random.nextInt(20)+random.nextInt(15)+35 : random.nextInt(25) + random.nextInt(6);
            double d2 = startChunkZ * 16 + random.nextInt(16);
            int k1 = random.nextInt(4) + 8;
            for (int l1 = 0; l1 < k1; l1++) {
                float f = random.nextFloat() * 3.141593F * 2F;
                float f2 = random.nextFloat() * 2.0F + random.nextFloat();
                placeTunnels(k, l, blocks, d + random.nextInt(8), d1, d2 + random.nextInt(8), f2, f, 0, 0, 32+random.nextInt(8), random.nextFloat()*0.5 + 0.5);
            }
            return;

        }
        if (random.nextInt(6) == 1) {
            double d = startChunkX * 16 + random.nextInt(16);
            double d1 = ((random.nextInt(2) == 0) ? random.nextInt(50) + 20 : random.nextInt(120));
            double d2 = startChunkZ * 16 + random.nextInt(16);
            float f1 = ((random.nextFloat() - 0.5F) * 2.0F) / 8F;
            float f2 = random.nextFloat() * 2.25F + random.nextFloat();
            int ran = 1 + random.nextInt(4);
            for (int l1 = 0; l1 < ran; l1++) {
                float f = random.nextFloat();
                placeTunnels(k, l, blocks, d, d1, d2, f2, f, f1, 0, 0, random.nextFloat() * random.nextFloat() * 0.3D + 0.5D);
            }
        }
        ci.cancel();
    }
}
