package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ArmorItem.class)
public class ArmorItemMixin implements CustomTooltipProvider {
    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum;
        Item item = stack.getItem();
        if (item == Item.LEATHER_BOOTS || item == Item.LEATHER_LEGGINGS || item == Item.LEATHER_CHESTPLATE || item == Item.LEATHER_HELMET
            || item == Item.GOLDEN_BOOTS || item == Item.GOLDEN_LEGGINGS || item == Item.GOLDEN_CHESTPLATE || item == Item.GOLDEN_HELMET) {
            toolTierEnum = ToolTierEnum.CRUDE;
        } else if (item == Item.CHAIN_BOOTS || item == Item.CHAIN_LEGGINGS || item == Item.CHAIN_CHESTPLATE || item == Item.CHAIN_HELMET) {
            toolTierEnum = ToolTierEnum.BASIC;
        }  else if (item == Item.IRON_BOOTS || item == Item.IRON_LEGGINGS || item == Item.IRON_CHESTPLATE || item == Item.IRON_HELMET) {
            toolTierEnum = ToolTierEnum.IRON;
        } else if (item == Item.DIAMOND_BOOTS || item == Item.DIAMOND_LEGGINGS || item == Item.DIAMOND_CHESTPLATE || item == Item.DIAMOND_HELMET) {
            toolTierEnum = ToolTierEnum.DIAMOND;
        } else {
            return new String[]{originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
