package net.newfrontiercraft.nfc.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.LazyBlockTemplate;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

public class LazyItemTemplate extends TemplateItem implements CustomTooltipProvider {
    private ToolTierEnum toolTierEnum;

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
            return new String[] {originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
