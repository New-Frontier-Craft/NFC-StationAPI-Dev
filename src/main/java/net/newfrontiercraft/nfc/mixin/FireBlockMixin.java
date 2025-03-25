package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.FireBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @Shadow
    protected abstract void registerFlammableBlock(int block, int burnChance, int spreadChance);

    @Inject(at = @At("TAIL"), method = "init")
    private void nfcRegisterFlammableBlock(CallbackInfo ci){
        registerFlammableBlock(BlockListener.scaffoldBlock.id, 40, 40);
        registerFlammableBlock(BlockListener.alphaLeaves.id, 30, 60);
    }
}
