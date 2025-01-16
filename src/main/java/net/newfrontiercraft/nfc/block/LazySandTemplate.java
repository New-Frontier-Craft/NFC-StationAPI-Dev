package net.newfrontiercraft.nfc.block;

import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateSandBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LazySandTemplate extends TemplateSandBlock {

    int textureInternal;
    int fallingRarity;

    public LazySandTemplate(Identifier identifier, float hardness, BlockSoundGroup blockSounds, int fallingRarity) {
        super(identifier, 0);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
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
    public int getTexture(int i) {
        return textureInternal;
    }

    @Override
    public void onTick(World arg, int i, int j, int k, Random random) {
        if (random.nextInt(fallingRarity) == 0) attemptFall(arg, i, j, k);
    }

    private void attemptFall(World arg, int i, int j, int k) {
        if (canFallThrough(arg, i, j - 1, k) && j >= 0) {
            byte var8 = 32;
            if (!fallInstantly && arg.isRegionLoaded(i - var8, j - var8, k - var8, i + var8, j + var8, k + var8)) {
                FallingBlockEntity var9 = new FallingBlockEntity(arg, (double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), this.id);
                arg.spawnEntity(var9);
            } else {
                arg.setBlock(i, j, k, 0);

                while(canFallThrough(arg, i, j - 1, k) && j > 0) {
                    --j;
                }

                if (j > 0) {
                    arg.setBlock(i, j, k, this.id);
                }
            }
        }

    }
}
