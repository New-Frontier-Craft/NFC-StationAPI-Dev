package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.block.Block;
import net.minecraft.block.OreStorageBlock;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OreStorageBlock.class)
public class OreStorageBlockMixin implements CustomTooltipProvider {
    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        ToolTierEnum toolTierEnum;
        Item item = stack.getItem();
        if (item == Block.GOLD_BLOCK.asItem()) {
            toolTierEnum = ToolTierEnum.WOOD;
        } else if (item == Block.IRON_BLOCK.asItem()) {
            toolTierEnum = ToolTierEnum.IRON;
        } else if (item == Block.DIAMOND_BLOCK.asItem()) {
            toolTierEnum = ToolTierEnum.DIAMOND;
        } else {
            return new String[]{originalTooltip};
        }
        return new String[]{toolTierEnum.getColourCode() + originalTooltip,
                "§7" + TranslationStorage.getInstance().get("tool_tier.tier") + ": " + toolTierEnum.getColourCode() + TranslationStorage.getInstance().get(toolTierEnum.getName())};
    }
}
