package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class CreativeGeneratorBlockItem extends BlockItem implements CustomTooltipProvider {
    public CreativeGeneratorBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "§dCreative/Cheat mode only.",
                "Always produces 10 torque.",
                "Can be vertically stacked",
                "with other generators."
        };
    }
}
