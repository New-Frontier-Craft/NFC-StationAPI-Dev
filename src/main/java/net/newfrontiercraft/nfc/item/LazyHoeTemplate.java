package net.newfrontiercraft.nfc.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateHoeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

public class LazyHoeTemplate extends TemplateHoeItem implements CustomTooltipProvider {
    private final ToolTierEnum toolTierEnum;

    public LazyHoeTemplate(Identifier identifier, ToolMaterial material, ToolTierEnum toolTierEnum) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        this.toolTierEnum = toolTierEnum;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack itemStack, String originalTooltip) {
        return new String[]{toolTierEnum.getColourCode() + originalTooltip, "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
