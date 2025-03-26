package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.GlowstoneClusterFeature;
import net.minecraft.world.gen.feature.LakeFeature;
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
        if(world.getBlockId(x, y - 1, z) == Block.SOUL_SAND.id)
        {
            world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
            Feature feature = new DeadTreeFeature();
            if(!feature.generate(world, random, x, y, z)) {
                world.setBlock(x, y, z, this.id, meta);
            }
            ci.cancel();
        }
    }

    @Override
    protected boolean canPlantOnTop(int id) {
        if(id == Block.SOUL_SAND.id){
            return true;
        }
        return super.canPlantOnTop(id);
    }
}
