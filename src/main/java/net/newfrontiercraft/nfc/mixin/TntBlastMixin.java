package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.TntEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(TntEntity.class)
public class TntBlastMixin {

    @ModifyConstant(method = "explode", constant = {
            @Constant(floatValue = 4.0F)
    })
    float buffExplosionPower(float original) {
        return 8;
    }
}
