package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class PlantBusItemBlock extends BlockItem implements CustomTooltipProvider {
    public PlantBusItemBlock(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Used on some multiblocks to",
                "designate an item chute placed",
                "above the bus to insert into the",
                "plant slots of the machine."
        };
    }
}
