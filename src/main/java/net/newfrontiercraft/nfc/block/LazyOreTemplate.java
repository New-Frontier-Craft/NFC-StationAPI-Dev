package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.resource.language.TranslationStorage;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class LazyOreTemplate extends TemplateBlock implements CustomTooltipProvider {
    private ToolTierEnum toolTierEnum;
    int textureInternal;
    Item droppedItem;

    public LazyOreTemplate(Identifier identifier, float hardness) {
        super(identifier, Material.STONE);
        setTranslationKey(identifier.namespace, identifier.path);
        setResistance(500F);
        setHardness(hardness);
        setSoundGroup(Block.STONE_SOUND_GROUP);
    }

    public LazyOreTemplate(Identifier identifier, float hardness, ToolTierEnum toolTierEnum) {
        this(identifier, hardness);
        this.toolTierEnum = toolTierEnum;
    }

    public void specifyCustomDrop(Item droppedItem) {
        this.droppedItem = droppedItem;
    }

    public void specifyTextures(int texture) {
        textureInternal = texture;
    }

    @Override
    public int getTexture(int side, int meta) {
        return textureInternal;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        if (droppedItem != null) {
            return droppedItem.id;
        }
        return this.asItem().id;
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
