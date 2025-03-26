package net.newfrontiercraft.nfc.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.dimension.PortalForcer;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PortalForcer.class)
public class PortalForcerToleranceMixin {

    @WrapOperation(
            method = "teleportToValidPortal",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/World;getBlockId(III)I")
    )
    public int nfcGetBlockId(World instance, int y, int z, int i, Operation<Integer> original) {
        int actualBlockId = original.call(instance, y, z, i);
        if (actualBlockId == BlockListener.unrestrictedNetherPortal.id) {
            return Block.NETHER_PORTAL.id;
        }
        return actualBlockId;
    }
}
