package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.jetbrains.annotations.NotNull;

public class FilteringItemChuteBlockItem extends BlockItem implements CustomTooltipProvider {
    public FilteringItemChuteBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        String[] tooltip;
        if (stack.itemId == BlockListener.filteringItemChute.asItem().id) {
            tooltip = new String[]{
                    originalTooltip,
                    "Can push items into inventories",
                    "and pull them out of inventories",
                    "above. Can collect items from",
                    "the ground. Only picks up items",
                    "which match the stack in the blue",
                    "filter slot."
            };
        } else {
            tooltip = new String[]{
                    originalTooltip,
                    "Can push items into inventories",
                    "and pull them out of inventories",
                    "above. Can collect items from",
                    "the ground. Only picks up items",
                    "which match the stack in the blue",
                    "filter slot. Inserts a limited",
                    "amount of items into the target",
                    "determined by the item count in",
                    "the filter slot."
            };
        }
        return tooltip;
    }
}
