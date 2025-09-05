package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.sound.SoundManager;
import net.newfrontiercraft.nfc.events.init.ConfigListener;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import paulscode.sound.SoundSystem;

import java.util.Random;

@Mixin(SoundManager.class)
public class MusicTweaksMixin {

    @Shadow
    private static SoundSystem soundSystem;

    @Shadow
    private Random random;

    @WrapOperation(
            method = "tick",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/sound/SoundManager;timeUntilNextSong:I",
                    opcode = Opcodes.PUTFIELD,
                    ordinal = 1
            )
    )
    public void tick(SoundManager instance, int value, Operation<Void> original) {
        original.call(instance, random.nextInt(Math.max(1, ConfigListener.NEW_FRONTIER_CONFIG.audioConfig.musicInterval)) + Math.abs(ConfigListener.NEW_FRONTIER_CONFIG.audioConfig.musicInterval));
    }

    @Inject(at = @At(value = "INVOKE", target = "Lpaulscode/sound/SoundSystem;setVolume(Ljava/lang/String;F)V", shift = At.Shift.AFTER), method = "tick")
    public void randomizePitch(CallbackInfo ci) {
        if (ConfigListener.NEW_FRONTIER_CONFIG.audioConfig.randomizeMusicPitch) {
            soundSystem.setPitch("BgMusic", random.nextFloat() + 0.5F);
        }
    }
}
