package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ToolItem.class)
public class ToolItemMixin implements CustomTooltipProvider {
    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum;
        Item item = stack.getItem();
        if (item == Item.WOODEN_PICKAXE || item == Item.WOODEN_AXE || item == Item.WOODEN_SHOVEL) {
            toolTierEnum = ToolTierEnum.WOOD;
        } else if (item == Item.STONE_PICKAXE || item == Item.STONE_AXE || item == Item.STONE_SHOVEL) {
            toolTierEnum = ToolTierEnum.STONE;
        } else if (item == Item.GOLDEN_PICKAXE || item == Item.GOLDEN_AXE || item == Item.GOLDEN_SHOVEL) {
            toolTierEnum = ToolTierEnum.BASIC;
        } else if (item == Item.IRON_PICKAXE || item == Item.IRON_AXE || item == Item.IRON_SHOVEL) {
            toolTierEnum = ToolTierEnum.IRON;
        } else if (item == Item.DIAMOND_PICKAXE || item == Item.DIAMOND_AXE || item == Item.DIAMOND_SHOVEL) {
            toolTierEnum = ToolTierEnum.DIAMOND;
        } else {
            return new String[]{originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName()),
                "§9" + "+" + stack.getItem().getAttackDamage(null) + " " + TranslationStorage.getInstance().get("tool_tier.attack_damage")};
    }
}
