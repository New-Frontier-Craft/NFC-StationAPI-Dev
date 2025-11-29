package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.Block;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BlockItem.class)
public class BlockItemTooltipMixin implements CustomTooltipProvider {

    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        boolean isOre = false;
        ToolTierEnum toolTierEnum = null;
        Item item = stack.getItem();
        if (item == Block.GOLD_ORE.asItem() || item == Block.GOLD_BLOCK.asItem()) {
            toolTierEnum = ToolTierEnum.WOOD;
            isOre = true;
        } else if (item == Block.IRON_ORE.asItem() || item == Block.IRON_BLOCK.asItem()) {
            toolTierEnum = ToolTierEnum.IRON;
            isOre = true;
        } else if (item == Block.DIAMOND_ORE.asItem() || item == Block.DIAMOND_BLOCK.asItem()) {
            toolTierEnum = ToolTierEnum.DIAMOND;
            isOre = true;
        }
        if (isOre) {
            return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                    "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
        }
        return new String[]{originalTooltip};
    }
}
