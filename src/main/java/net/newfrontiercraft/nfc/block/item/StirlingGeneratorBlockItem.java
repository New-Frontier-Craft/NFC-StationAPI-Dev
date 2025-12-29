package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class StirlingGeneratorBlockItem extends BlockItem implements CustomTooltipProvider {
    public StirlingGeneratorBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Consumes heat from adjacent heat coils",
                "to generate torque. Each adjacent heat",
                "coil adds to the torque while also",
                "increasing the heat consumption.",
                "Amount of torque depends on heat level",
                "of each adjacent heat coil. Can produce",
                "120 torque at best. Can be vertically",
                "stacked with other generators."
        };
    }
}
