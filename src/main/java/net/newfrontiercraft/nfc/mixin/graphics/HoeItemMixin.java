package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(HoeItem.class)
public class HoeItemMixin implements CustomTooltipProvider {
    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum;
        Item item = stack.getItem();
        if (item == Item.WOODEN_HOE) {
            toolTierEnum = ToolTierEnum.WOOD;
        } else if (item == Item.STONE_HOE) {
            toolTierEnum = ToolTierEnum.STONE;
        } else if (item == Item.GOLDEN_HOE) {
            toolTierEnum = ToolTierEnum.BASIC;
        } else if (item == Item.IRON_HOE) {
            toolTierEnum = ToolTierEnum.IRON;
        } else if (item == Item.DIAMOND_HOE) {
            toolTierEnum = ToolTierEnum.DIAMOND;
        } else {
            return new String[]{originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName()),
                "§9" + "+" + stack.getItem().getAttackDamage(null) + " " + TranslationStorage.getInstance().get("tool_tier.attack_damage")};
    }
}
