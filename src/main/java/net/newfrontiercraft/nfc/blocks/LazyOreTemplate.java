package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.template.block.TemplateOreBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LazyOreTemplate extends TemplateOreBlock {

    int textureInternal;
    int dropID = this.id;

    public LazyOreTemplate(Identifier identifier, float hardness) {
        super(identifier, 0);
        setTranslationKey(identifier.namespace, identifier.path);
        setResistance(500F);
        setHardness(hardness);
        setSoundGroup(Block.STONE_SOUND_GROUP);
    }

    @Override
    public int getDroppedItemId(int i, Random random) {
        return dropID;
    }

    public void specifyCustomDrop(int dropID) {
        this.dropID = dropID;
    }

    public void specifyTextures(int texture) {
        textureInternal = texture;
    }

    @Override
    public int getTexture(int side, int meta) {
        return textureInternal;
    }
}
