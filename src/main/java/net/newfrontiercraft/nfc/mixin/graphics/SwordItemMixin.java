package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SwordItem.class)
public class SwordItemMixin implements CustomTooltipProvider {
    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum;
        Item item = stack.getItem();
        if (item == Item.WOODEN_SWORD) {
            toolTierEnum = ToolTierEnum.WOOD;
        } else if (item == Item.STONE_SWORD) {
            toolTierEnum = ToolTierEnum.STONE;
        } else if (item == Item.GOLDEN_SWORD) {
            toolTierEnum = ToolTierEnum.BASIC;
        } else if (item == Item.IRON_SWORD) {
            toolTierEnum = ToolTierEnum.IRON;
        } else if (item == Item.DIAMOND_SWORD) {
            toolTierEnum = ToolTierEnum.DIAMOND;
        } else {
            return new String[]{originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip, "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
