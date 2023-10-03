package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.BlockBase;
import net.minecraft.block.BlockSounds;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateOre;

import java.util.Random;

public class LazyOreTemplate extends TemplateOre {

    int textureInternal;
    int dropID = this.id;

    public LazyOreTemplate(Identifier identifier, float hardness) {
        super(identifier, 0);
        setTranslationKey(identifier.modID, identifier.id);
        setBlastResistance(500F);
        setHardness(hardness);
        setSounds(BlockBase.STONE_SOUNDS);
    }

    @Override
    public int getDropId(int i, Random random) {
        return dropID;
    }

    public void specifyCustomDrop(int dropID)
    {
        this.dropID = dropID;
    }

    public void specifyTextures(int texture)
    {
        textureInternal = texture;
    }

    @Override
    public int getTextureForSide(int i, int j) {
        return textureInternal;
    }
}
