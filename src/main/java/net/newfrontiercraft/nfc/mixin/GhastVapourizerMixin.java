package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GhastEntity.class)
public abstract class GhastVapourizerMixin extends FlyingEntity {

    public GhastVapourizerMixin(World world) {
        super(world);
    }

    @Unique
    @Override
    public boolean damage(Entity damageSource, int amount) {
        if (!world.isRemote && health > 0) {
            this.world.playSound(this, this.getDeathSound(), this.getSoundVolume(), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            health = 0;
        }
        return super.damage(damageSource, amount);
    }
}
