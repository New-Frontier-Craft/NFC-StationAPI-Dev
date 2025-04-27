package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.Generator;
import net.minecraft.world.gen.chunk.OverworldChunkGenerator;
import net.newfrontiercraft.nfc.world.gen.carver.RavineWorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(OverworldChunkGenerator.class)
public class OverworldWorldCarversMixin {
    @Shadow private World world;
    @Shadow private Generator cave;
    private RavineWorldCarver ravineWorldCarver = new RavineWorldCarver();
    @Inject(method = "getChunk", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/Generator;place(Lnet/minecraft/world/chunk/ChunkSource;Lnet/minecraft/world/World;II[B)V"))
    void nfcRunCarvers(int chunkX, int chunkZ, CallbackInfoReturnable<Chunk> cir, @Local byte[] blocks){
        OverworldChunkGenerator overworldChunkGenerator = OverworldChunkGenerator.class.cast(this);
        //cave.place(overworldChunkGenerator, world, chunkX, chunkZ, blocks);
        ravineWorldCarver.place(overworldChunkGenerator, world, chunkX, chunkZ, blocks);
    }
}
