package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class BrickOvenBlockItem extends BlockItem implements CustomTooltipProvider {
    public BrickOvenBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Can smelt multiple items into",
                "alloys and handle ores which",
                "require high temperatures to melt.",
                "Slowly heats up and shows current",
                "heat level alongside the required",
                "one for the currently smelted item.",
                "It also shows the maximum heat",
                "level the fuel in the fuel slot",
                "can reach with green arrows.",
                "Accepts heat from a heat coil",
                "placed behind it.",
                "§aCan§f be built as a multiblock",
                "for extended functionality.",
                "Use Always More Items to",
                "view the Multiblock Tab."
        };
    }
}
