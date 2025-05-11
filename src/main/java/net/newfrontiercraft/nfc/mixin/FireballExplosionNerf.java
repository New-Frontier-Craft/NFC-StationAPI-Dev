package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.projectile.FireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(FireballEntity.class)
public class FireballExplosionNerf {

    @ModifyConstant(method = "tick", constant = {
            @Constant(floatValue = 1.0F)
    })
    float nerfExplosionPower(float original) {
        return 0.5F;
    }
}
