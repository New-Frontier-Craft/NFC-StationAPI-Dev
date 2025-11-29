package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.item.LazyOreTemplateBlockItem;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;

import java.util.Random;

@HasCustomBlockItemFactory(LazyOreTemplateBlockItem.class)
public class LazyOreTemplate extends TemplateBlock {
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

    public ToolTierEnum getToolTierEnum() {
        return toolTierEnum;
    }
}
