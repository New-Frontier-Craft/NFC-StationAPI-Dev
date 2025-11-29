package net.newfrontiercraft.nfc.block.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.block.LazyOreTemplate;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

public class LazyOreTemplateBlockItem extends BlockItem implements CustomTooltipProvider {
    public LazyOreTemplateBlockItem(int i) {
        super(i);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum = ((LazyOreTemplate)this.getBlock()).getToolTierEnum();
        if (toolTierEnum == null) {
            return new String[] {originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
