package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class BasicItemChuteBlockItem extends BlockItem implements CustomTooltipProvider {
    public BasicItemChuteBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Can push items into inventories",
                "and pull them out of inventories",
                "above. Can collect items from",
                "the ground."
        };
    }
}
