package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.BlockBase;
import net.minecraft.block.BlockSounds;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateOre;

public class LazyOreTemplate extends TemplateOre {

    int textureInternal;

    public LazyOreTemplate(Identifier identifier, float hardness) {
        super(identifier, 0);
        setTranslationKey(identifier.modID, identifier.id);
        setBlastResistance(500F);
        setHardness(hardness);
        setSounds(BlockBase.STONE_SOUNDS);
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
