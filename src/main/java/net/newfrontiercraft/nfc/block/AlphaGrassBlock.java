package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.BlockView;
import net.modificationstation.stationapi.api.template.block.TemplateGrassBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class AlphaGrassBlock extends TemplateGrassBlock {
    int topTexture;
    int sideTexture;
    int sideSnowTexture;
    int bottomTexture;

    public AlphaGrassBlock(Identifier identifier) {
        super(identifier);
    }

    public void specifyTextures(int topTexture, int sideTexture, int sideSnowTexture, int bottomTexture){
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.sideSnowTexture = sideSnowTexture;
        this.bottomTexture = bottomTexture;
    }

    @Override
    public int getTextureId(BlockView blockView, int x, int y, int z, int side) {
        if (side == 0 || side == 1) {
            return getTexture(side);
        }
        else {
            Material var6 = blockView.getMaterial(x, y + 1, z);
            return var6 != Material.SNOW_LAYER && var6 != Material.SNOW_BLOCK ? sideTexture : sideSnowTexture;
        }
    }

    @Override
    public int getTexture(int side) {
        if (side == 1) {
            return topTexture;
        } else if (side == 0) {
            return bottomTexture;
        } else {
            return sideTexture;
        }
    }

    @Override
    public int getColorMultiplier(BlockView blockView, int x, int y, int z) {
        return 0xFFFFFF;
    }
}
