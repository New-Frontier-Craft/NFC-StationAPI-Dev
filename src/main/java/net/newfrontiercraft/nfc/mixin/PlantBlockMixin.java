package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public abstract class PlantBlockMixin extends Block {
    public PlantBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Unique
    boolean isNfcSoil(int id){
        return id == BlockListener.alphaGrass.id;
    }

    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    void nfcCanPlantOnTop(int id, CallbackInfoReturnable<Boolean> cir) {
        if(this.isNfcSoil(id)){
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
    void nfcCanPlaceAt(World world, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        int belowId = world.getBlockId(x, y - 1, z);
        if(id == Block.DANDELION.id) {
            if((belowId != 0 && belowId != Block.LEAVES.id && Block.BLOCKS[belowId].isOpaque())) {
                cir.setReturnValue(true);
            }
        } else {
            cir.setReturnValue(super.canPlaceAt(world, x, y, z) && (canThisPlantGrowOnThisBlockID(belowId) || isFilledPlanterBelow(belowId, world.getBlockMeta(x, y - 1, z))));
        }
    }

    @Unique
    protected boolean isFilledPlanterBelow(int id, int meta) {
        return id == BlockListener.planter.id && meta > 0;
    }

    @Unique
    protected boolean canThisPlantGrowOnThisBlockID(int i) {
        return i == Block.GRASS_BLOCK.id || i == Block.DIRT.id || i == Block.FARMLAND.id || i == BlockListener.alphaGrass.id;
    }
}
