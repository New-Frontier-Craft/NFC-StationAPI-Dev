package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.decoration.painting.PaintingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.registry.PaintingRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(PaintingEntity.class)
public class PaintingEntityMixin {


    @Inject(method = "<init>(Lnet/minecraft/world/World;IIII)V", at = @At(value = "INVOKE", target = "Ljava/util/List;size()I", ordinal = 0))
    void addPaintings(World world, int x, int y, int z, int facing, CallbackInfo ci, @Local ArrayList list){
        list.addAll(PaintingRegistry.INSTANCE.paintingVariants);
    }

    @Environment(EnvType.CLIENT)
    @Inject(method = "<init>(Lnet/minecraft/world/World;IIIILjava/lang/String;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/painting/PaintingEntity;setFacing(I)V", shift = At.Shift.BEFORE))
    void loadPainting(World world, int x, int y, int z, int facing, String id, CallbackInfo ci){
        PaintingEntity paintingEntity = PaintingEntity.class.cast(this);
        if(PaintingRegistry.INSTANCE.isNfcPaining(id)){
            paintingEntity.variant = PaintingRegistry.INSTANCE.getPaintingVariant(id);
        }
    }

    @Inject(method = "readNbt", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/decoration/painting/PaintingEntity;setFacing(I)V", shift = At.Shift.BEFORE))
    void readNbt(NbtCompound par1, CallbackInfo ci, @Local String id){
        PaintingEntity paintingEntity = PaintingEntity.class.cast(this);
        if (PaintingRegistry.INSTANCE.isNfcPaining(id)) {
            paintingEntity.variant = PaintingRegistry.INSTANCE.getPaintingVariant(id);
        }
    }
}
