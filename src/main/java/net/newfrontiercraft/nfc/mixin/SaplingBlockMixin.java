package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GlowstoneClusterFeature;
import net.minecraft.world.gen.feature.LakeFeature;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.feature.ShrubFeature;
import net.newfrontiercraft.nfc.world.gen.feature.DeadTreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(SaplingBlock.class)
public abstract class SaplingBlockMixin extends PlantBlock {

    public SaplingBlockMixin(int id, int textureId) {
        super(id, textureId);
    }

    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    void nfcGenerate(World world, int x, int y, int z, Random random, CallbackInfo ci){
        int meta = world.getBlockMeta(x, y, z) & 3;
        if(world.getBlockId(x, y - 1, z) == Block.SOUL_SAND.id) {
            world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
            Feature feature = new DeadTreeFeature();
            if(!feature.generate(world, random, x, y, z)) {
                world.setBlock(x, y, z, this.id, meta);
            }
            ci.cancel();
        }
        int fertility = 2;
        if (world.getBlockId(x, y - 1, z) == Block.FARMLAND.id) {
            fertility = 4;
            if (world.getBlockMeta(x, y - 1, z) > 0) {
                fertility = 16;
            }
        } else if (world.getBlockId(x, y - 1, z) == BlockListener.planter.id && world.getBlockMeta(x, y - 1, z) > 0) {
            fertility = 8;
            if (world.getBlockMeta(x, y - 1, z) > 1) {
                fertility = 32;
            }
        }
        if (random.nextInt(fertility) == 0 &&
                (world.method_1781().getBiome(x, z) == Biome.SAVANNA ||
                 world.method_1781().getBiome(x, z) == Biome.SHRUBLAND ||
                 world.method_1781().getBiome(x, z) == Biome.DESERT ||
                 world.method_1781().getBiome(x, z) == Biome.TUNDRA)) {
            world.setBlock(x, y, z, 0);
            Feature feature = new ShrubFeature(meta);
            if(!feature.generate(world, random, x, y, z)) {
                world.setBlock(x, y, z, this.id, meta);
                return;
            }
            ci.cancel();
        }
    }

    @Override
    protected boolean canPlantOnTop(int id) {
        if(id == Block.SOUL_SAND.id || id == BlockListener.planter.id) {
            return true;
        }
        return super.canPlantOnTop(id);
    }
}
