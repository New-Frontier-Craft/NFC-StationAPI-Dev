package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class HeatSiphonBlockItem extends BlockItem implements CustomTooltipProvider {
    public HeatSiphonBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Heats up a heat coil above itself.",
                "Must be placed above a lava source.",
                "Produces enough heat to power a",
                "Stirling Generator to spin up machines."
        };
    }
}
