package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.OreFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(OreFeature.class)
public class OreSuppressionMixin {

    @Shadow private int oreBlockId;

    @Inject(at = @At("HEAD"), method = "generate", cancellable = true)
    private void suppressOres(World world, Random random, int x, int y, int z, CallbackInfoReturnable<Boolean> cir) {
        if (oreBlockId == Block.COAL_ORE.id || oreBlockId == Block.IRON_ORE.id || oreBlockId == Block.GOLD_ORE.id || oreBlockId == Block.DIAMOND_ORE.id) {
            cir.setReturnValue(false);
        }
    }
}
