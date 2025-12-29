package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.registry.FuelLevelRegistry;
import net.newfrontiercraft.nfc.utils.FuelLevelEnum;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

import java.util.ArrayList;

@Mixin(Item.class)
public class ItemMixin implements CustomTooltipProvider {

    FuelLevelEnum fuelLevelEnum;

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum = null;
        Item item = stack.getItem();
        boolean hasToolTier = false;
        if (item == Item.GOLD_INGOT) {
            hasToolTier = true;
            toolTierEnum = ToolTierEnum.WOOD;
        } else if (item == Item.IRON_INGOT) {
            hasToolTier = true;
            toolTierEnum = ToolTierEnum.IRON;
        } else if (item == Item.DIAMOND) {
            hasToolTier = true;
            toolTierEnum = ToolTierEnum.DIAMOND;
        }
        ArrayList<String> tooltipList = new ArrayList<>();
        tooltipList.add(originalTooltip);
        String toolTierLine;
        if (hasToolTier) {
            toolTierLine = "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName());
            tooltipList.add(toolTierLine);
        }
        String fuelTier = "";
        fuelLevelEnum = FuelLevelRegistry.fuelLevel().getRawFuelLevel(new ItemMeta(stack.getItem(), stack.getDamage()));
        if (fuelLevelEnum != null) {
            fuelTier = "Fuel tier: " + fuelLevelEnum.getColourCode() + TranslationStorage.getInstance().get(fuelLevelEnum.getName());
            tooltipList.add(fuelTier);
        }
        String[] finalTooltip = new String[tooltipList.size()];
        for (int i = 0; i < finalTooltip.length; i++) {
            finalTooltip[i] = tooltipList.get(i);
        }
        return finalTooltip;
    }
}
