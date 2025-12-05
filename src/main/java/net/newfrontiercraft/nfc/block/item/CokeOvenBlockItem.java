package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class CokeOvenBlockItem extends BlockItem implements CustomTooltipProvider {
    public CokeOvenBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Processes lower quality",
                "fuel into higher quality",
                "fuel. Uses a temperature",
                "mechanic similar to the",
                "Brick Oven with the addition",
                "of an upper temperature",
                "limit which resets progress",
                "if exceeded during operation.",
                "§cMust§f be built as a",
                "multiblock to function.",
                "Use Always More Items to",
                "view the Multiblock Tab."
        };
    }
}
