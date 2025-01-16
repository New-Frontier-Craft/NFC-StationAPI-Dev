package net.newfrontiercraft.nfc.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeSource.class)
public class BiomeEnlargerMixin {

    @Shadow public Biome[] biomes;

    @Shadow public double[] temperatureMap;

    @Shadow public double[] downfallMap;

    @Shadow public double[] weirdnessMap;

    @Shadow private OctaveSimplexNoiseSampler temperatureSampler;

    @Shadow private OctaveSimplexNoiseSampler downfallSampler;

    @Shadow private OctaveSimplexNoiseSampler weirdnessSampler;

    @Environment(EnvType.CLIENT)
    @Inject(at = @At("HEAD"), method = "getTemperature", remap = false, cancellable = true)
    private void stretchBiomes(int x, int z, CallbackInfoReturnable<Double> cir) {
        temperatureMap = temperatureSampler.sample(temperatureMap, (double) x, (double) z, 1, 1, 0.01875, 0.01875, 0.5);
        cir.setReturnValue(temperatureMap[0]);
    }

    @Inject(at = @At("HEAD"), method = "getBiomesInArea([Lnet/minecraft/world/biome/Biome;IIII)[Lnet/minecraft/world/biome/Biome;", remap = false, cancellable = true)
    private void stretchBiomes(Biome[] biomes, int x, int z, int width, int depth, CallbackInfoReturnable<Biome[]> cir) {
        if (biomes == null || biomes.length < width * depth) {
            biomes = new Biome[width * depth];
        }

        temperatureMap = temperatureSampler.sample(temperatureMap, x, z, width, width, 0.015, 0.015, 0.25);
        downfallMap = downfallSampler.sample(downfallMap, x, z, width, width, 0.03000000074505806, 0.03000000074505806, 0.3333333333333333);
        weirdnessMap = weirdnessSampler.sample(weirdnessMap, x, z, width, width, 0.4, 0.4, 0.58823529411764708);
        int var6 = 0;

        for(int var7 = 0; var7 < width; ++var7) {
            for(int var8 = 0; var8 < depth; ++var8) {
                double var9 = weirdnessMap[var6] * 1.1 + 0.5;
                double var11 = 0.01;
                double var13 = 1.0 - var11;
                double var15 = (temperatureMap[var6] * 0.15 + 0.7) * var13 + var9 * var11;
                var11 = 0.002;
                var13 = 1.0 - var11;
                double var17 = (downfallMap[var6] * 0.15 + 0.5) * var13 + var9 * var11;
                var15 = 1.0 - (1.0 - var15) * (1.0 - var15);
                if (var15 < 0.0) {
                    var15 = 0.0;
                }

                if (var17 < 0.0) {
                    var17 = 0.0;
                }

                if (var15 > 1.0) {
                    var15 = 1.0;
                }

                if (var17 > 1.0) {
                    var17 = 1.0;
                }

                temperatureMap[var6] = var15;
                downfallMap[var6] = var17;
                biomes[var6++] = Biome.getBiome(var15, var17);
            }
        }

        cir.setReturnValue(biomes);
    }
}
