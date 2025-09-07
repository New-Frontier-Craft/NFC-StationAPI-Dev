package net.newfrontiercraft.nfc.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

public class LazyPickaxeTemplate extends TemplatePickaxeItem implements CustomTooltipProvider {
    private final ToolTierEnum toolTierEnum;

    public LazyPickaxeTemplate(Identifier identifier, ToolMaterial material, ToolTierEnum toolTierEnum) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        this.toolTierEnum = toolTierEnum;
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName()),
                "§9" + "+" + stack.getItem().getAttackDamage(null) + " " + TranslationStorage.getInstance().get("tool_tier.attack_damage")};
    }
}
