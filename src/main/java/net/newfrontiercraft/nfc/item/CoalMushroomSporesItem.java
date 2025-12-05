package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import org.jetbrains.annotations.NotNull;

public class CoalMushroomSporesItem extends LazyItemTemplate implements CustomTooltipProvider {
    public CoalMushroomSporesItem(Identifier identifier) {
        super(identifier);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Drops from coal mushrooms. Must be combined",
                "with Nether Ash before planting. Can be",
                "smelted into coal."};
    }
}
