package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class HeatCoilBlockItem extends BlockItem implements CustomTooltipProvider {
    public HeatCoilBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Spreads heat to other heat coils.",
                "Heated up using other machines.",
                "Provides heat to some multiblocks.",
                "Heat level is visually indicated.",
                "Higher heat levels damage anything",
                "touching the heat coils."
        };
    }
}
