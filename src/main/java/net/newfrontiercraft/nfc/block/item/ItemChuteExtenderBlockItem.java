package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class ItemChuteExtenderBlockItem extends BlockItem implements CustomTooltipProvider {
    public ItemChuteExtenderBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Can push items into inventories",
                "but not pull them out nor collect",
                "them from the ground. Intended as",
                "a cheap way to extend a line of",
                "chutes."
        };
    }
}
