package net.newfrontiercraft.nfc.mixin;

import net.minecraft.world.dimension.OverworldDimension;
import net.modificationstation.stationapi.api.world.dimension.StationDimension;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OverworldDimension.class)
public class OverworldHeightMixin implements StationDimension {

    @Override
    public int getHeight() {
        return 256;
    }
}
