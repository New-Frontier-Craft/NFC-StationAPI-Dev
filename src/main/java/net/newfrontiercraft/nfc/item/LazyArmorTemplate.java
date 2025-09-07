package net.newfrontiercraft.nfc.item;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

public class LazyArmorTemplate extends TemplateArmorItem implements ArmorTextureProvider, CustomTooltipProvider {

    private final String modelTexturePath;
    private final ToolTierEnum toolTierEnum;

    public LazyArmorTemplate(Identifier identifier, int armorType, int damage, String modelTexturePath, ToolTierEnum toolTierEnum) {
        super(identifier, 1, 1, armorType);
        setMaxDamage(damage);
        this.modelTexturePath = modelTexturePath;
        setTranslationKey(identifier.namespace, identifier.path);
        this.toolTierEnum = toolTierEnum;
    }

    @Override
    public Identifier getTexture(ArmorItem armour) {
        return ItemListener.MOD_ID.id(modelTexturePath);
    }

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
