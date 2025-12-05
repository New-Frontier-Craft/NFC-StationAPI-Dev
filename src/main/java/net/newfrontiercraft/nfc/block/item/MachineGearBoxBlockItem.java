package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import org.jetbrains.annotations.NotNull;

public class MachineGearBoxBlockItem extends BlockItem implements CustomTooltipProvider {
    public MachineGearBoxBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{
                originalTooltip,
                "Power source of mechanical machines.",
                "Takes torque from generators below it.",
                "The gear starts spinning when",
                "it gets any amount of torque."
        };
    }
}
