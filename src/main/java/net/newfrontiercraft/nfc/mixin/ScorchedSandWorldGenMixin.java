package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.Block;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.world.gen.chunk.NetherChunkGenerator;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
@Mixin(NetherChunkGenerator.class)
// This mixin is currently not in use so if scorched sand worldgen is already handled somewhere else it can be safely deleted
public class ScorchedSandWorldGenMixin {
    @Shadow private OctavePerlinNoiseSampler perlinNoise2;
    private double[] scorchedSandBuffer = new double[256];
    private boolean flag2;

    @Inject(method = "buildSurfaces", at = @At("HEAD"))
    void nfcCreateNoise(int chunkX, int chunkY, byte[] blocks, CallbackInfo ci){
        double d = 8.0D / 256D;
        this.scorchedSandBuffer = this.perlinNoise2.create(this.scorchedSandBuffer, (chunkX << 4), 0.0D, (chunkY << 4), 16, 1, 16, d, 1.0D, d);
    }

    @Inject(method = "buildSurfaces", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextDouble()D", ordinal = 1, shift = At.Shift.AFTER))
    void nfcSampleNoise(int chunkY, int blocks, byte[] par3, CallbackInfo ci, @Local(ordinal = 3) int var7,  @Local(ordinal = 4) int var8){
        flag2 = this.scorchedSandBuffer[var7 << 4 | var8] > 2.15D;
    }

    @Inject(method = "buildSurfaces", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Block;id:I", ordinal = 7, shift = At.Shift.AFTER))
    void nfcSetScorchedSand(int chunkY, int blocks, byte[] par3, CallbackInfo ci, @Local(ordinal = 9) int var13,  @Local(ordinal = 10) int var14){

    }

    // TODO: get this working or remove it completely
    @WrapOperation(method = "buildSurfaces", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Block;id:I", ordinal = 6))
    int test(Block instance, Operation<Integer> original){
        if(flag2){
            return BlockListener.scorchedSand.id;
        }
        return original.call(instance);
    }
    @WrapOperation(method = "buildSurfaces", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Block;id:I", ordinal = 7))
    int test2(Block instance, Operation<Integer> original){
        if(flag2){
            return BlockListener.scorchedSand.id;
        }
        return original.call(instance);
    }

}
