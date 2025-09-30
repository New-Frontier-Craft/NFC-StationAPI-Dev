package net.newfrontiercraft.nfc.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.registry.FuelLevelRegistry;
import net.newfrontiercraft.nfc.utils.FuelLevelEnum;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LazyItemTemplate extends TemplateItem implements CustomTooltipProvider {
    private ToolTierEnum toolTierEnum;
    private FuelLevelEnum fuelLevelEnum;

    public LazyItemTemplate(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.namespace, identifier.path);
    }

    public LazyItemTemplate(Identifier identifier, ToolTierEnum toolTierEnum) {
        this(identifier);
        this.toolTierEnum = toolTierEnum;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        if (toolTierEnum == null) {
            ArrayList<String> tooltipList = new ArrayList<>();
            tooltipList.add(originalTooltip);
            String fuelTier;
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
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
