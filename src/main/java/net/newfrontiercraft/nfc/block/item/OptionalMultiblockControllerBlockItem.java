package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class OptionalMultiblockControllerBlockItem extends BlockItem implements CustomTooltipProvider {
    public OptionalMultiblockControllerBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "§aCan§f be built as a multiblock",
                "for extended functionality.",
                "Use Always More Items to",
                "view the Multiblock Tab."
        };
    }
}
