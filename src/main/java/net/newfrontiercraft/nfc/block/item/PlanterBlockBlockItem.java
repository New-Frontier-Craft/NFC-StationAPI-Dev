package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.MetaNamedBlockItem;
import org.jetbrains.annotations.NotNull;

public class PlanterBlockBlockItem extends MetaNamedBlockItem implements CustomTooltipProvider {
    public PlanterBlockBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        String[] tooltip;
        if (stack.getDamage() > 0) {
            tooltip = new String[]{
                    originalTooltip,
                    "Accelerates plant growth",
                    "and prevents oak saplings",
                    "from growing into large",
                    "oak trees. Improves the",
                    "chance of saplings",
                    "growing into proper trees",
                    "in arid biomes."
            };
        } else {
            tooltip = new String[]{
                    originalTooltip,
                    "Must be filled with soil",
                    "in order to function."
            };
        }
        return tooltip;
    }
}
