package net.newfrontiercraft.nfc.mixin.graphics;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.impl.client.texture.StationRenderImpl;
import net.newfrontiercraft.nfc.particle.NfcParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.List;

@Mixin(ParticleManager.class)
public class ParticleManagerMixin {
    @Shadow private TextureManager textureManager;
    List<Particle> nfcParticles = new ArrayList<>();

    @Inject(method = "addParticle", at = @At("HEAD"), cancellable = true)
    void nfcAddParticle(Particle particle, CallbackInfo ci){
        if(particle instanceof NfcParticle){
            if (this.nfcParticles.size() >= 4000) {
                this.nfcParticles.remove(0);
            }
            this.nfcParticles.add(particle);
            ci.cancel();
        }
    }

    @Inject(method = "removeDeadParticles", at = @At("TAIL"))
    void nfcRemoveDeadParticles(CallbackInfo ci){
        for(int i = 0; i < this.nfcParticles.size(); ++i) {
            Particle particle = this.nfcParticles.get(i);
            particle.tick();
            if (particle.dead) {
                this.nfcParticles.remove(i--);
            }
        }
    }
    @Inject(method = "setWorld", at = @At("TAIL"))
    void nfcSetWorld(World world, CallbackInfo ci){
        this.nfcParticles.clear();
    }


    @Inject(method = "render", at = @At("TAIL"))
    void nfcRender(Entity entity, float partialTicks, CallbackInfo ci, @Local(ordinal = 1)float var3, @Local(ordinal = 2)float var4, @Local(ordinal = 3)float var5, @Local(ordinal = 4)float var6, @Local(ordinal = 5)float var7){
        Tessellator tessellator = Tessellator.INSTANCE;

        for(int i = 0; i < nfcParticles.size(); i++){
            Particle particle = nfcParticles.get(i);
            tessellator.startQuads();
            particle.render(tessellator, partialTicks, var3, var7, var4, var5, var6);
            tessellator.draw();
        }
    }
}
