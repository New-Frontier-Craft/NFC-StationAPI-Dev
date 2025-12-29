package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class MultiblockControllerBlockItem extends BlockItem implements CustomTooltipProvider {
    public MultiblockControllerBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "§cMust§f be built as a",
                "multiblock to function.",
                "Use Always More Items to",
                "view the Multiblock Tab."
        };
    }
}
