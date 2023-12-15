package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.BlockSounds;
import net.minecraft.entity.FallingBlock;
import net.minecraft.level.Level;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateSand;

import java.util.Random;

public class LazySandTemplate extends TemplateSand {

    int textureInternal;
    int fallingRarity;

    public LazySandTemplate(Identifier identifier, float hardness, BlockSounds blockSounds, int fallingRarity) {
        super(identifier, 0);
        setTranslationKey(identifier.modID, identifier.id);
        setHardness(hardness);
        setSounds(blockSounds);
        this.fallingRarity = fallingRarity;
    }

    @Override
    public LazySandTemplate setHardness(float Hardness) {
        return (LazySandTemplate) super.setHardness(Hardness);
    }

    public void specifyTextures(int texture) {
        textureInternal = texture;
    }

    @Override
    public int getTextureForSide(int i) {
        return textureInternal;
    }

    @Override
    public void onScheduledTick(Level arg, int i, int j, int k, Random random) {
        if (random.nextInt(fallingRarity) == 0) attemptFall(arg, i, j, k);
    }

    private void attemptFall(Level arg, int i, int j, int k) {
        if (method_435(arg, i, j - 1, k) && j >= 0) {
            byte var8 = 32;
            if (!fallInstantly && arg.method_155(i - var8, j - var8, k - var8, i + var8, j + var8, k + var8)) {
                FallingBlock var9 = new FallingBlock(arg, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), this.id);
                arg.spawnEntity(var9);
            } else {
                arg.setTile(i, j, k, 0);

                while(method_435(arg, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    arg.setTile(i, j, k, this.id);
                }
            }
        }

    }
}
