package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateBlockItem;
import org.jetbrains.annotations.NotNull;

public class SporeAshBlockItem extends TemplateBlockItem implements CustomTooltipProvider {
    public SporeAshBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Placed on top of a coal block.",
                "Grows into a small coal mushroom",
                "after some time has passed.",
                "See \"Coal Mushroom\" for",
                "growth requirements."
        };
    }
}
