package net.newfrontiercraft.nfc.block;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateMushroomPlantBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyMushroomTemplate extends TemplateMushroomPlantBlock {
    private final boolean lightImmune;
    private int topTexture;

    public LazyMushroomTemplate(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, 0);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
        this.lightImmune = lightImmune;
    }

    @Override
    public boolean canGrow(World world, int x, int y, int z) {
        return y >= 0 && y < 128 && (world.getBrightness(x, y, z) < 13 || lightImmune) && this.canPlantOnTop(world.getBlockId(x, y - 1, z));
    }

    public void specifyTextures(int topTexture) {
        this.topTexture = topTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        return topTexture;
    }
}
