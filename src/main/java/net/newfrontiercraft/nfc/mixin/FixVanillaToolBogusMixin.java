package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PickaxeItem.class)
public abstract class FixVanillaToolBogusMixin extends ToolItem {

    public FixVanillaToolBogusMixin(int id, int damageBoost, ToolMaterial toolMaterial, Block[] effectiveOn) {
        super(id, damageBoost, toolMaterial, effectiveOn);
    }

    @Inject(at = @At("HEAD"), method = "isSuitableFor", remap = false, cancellable = true)
    private void suppressOres(Block block, CallbackInfoReturnable<Boolean> cir) {
        if (block == Block.IRON_ORE) {
            cir.setReturnValue(toolMaterial.getMiningLevel() >= 2);
        }
    }
}
