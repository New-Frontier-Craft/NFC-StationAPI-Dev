package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class CombustionHeaterBlockItem extends BlockItem implements CustomTooltipProvider {
    public CombustionHeaterBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Burns fuel to produce heat.",
                "Maximum heat determined by fuel level.",
                "Fuel can be inserted from the top",
                "using item chutes. Heat gets",
                "transferred to a heat coil below",
                "the heater."
        };
    }
}
