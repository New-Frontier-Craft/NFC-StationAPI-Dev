package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyBlockTemplate extends TemplateBlock {

    int topTextureInternal;
    int sideTextureInternal;
    int bottomTextureInternal;
    int frontTextureInternal;
    int backTextureInternal;
    boolean directional = false;

    public LazyBlockTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
        setSoundGroup(blockSounds);
    }

    @Override
    public LazyBlockTemplate setHardness(float Hardness) {
        return (LazyBlockTemplate) super.setHardness(Hardness);
    }

    public void specifyTextures(int topTexture, int sideTexture, int bottomTexture, int frontTexture, int backTexture) {
        topTextureInternal = topTexture;
        sideTextureInternal = sideTexture;
        bottomTextureInternal = bottomTexture;
        frontTextureInternal = frontTexture;
        backTextureInternal = backTexture;
        directional = true;
    }

    public void specifyTextures(int topTexture, int sideTexture, int bottomTexture, int frontTexture) {
        topTextureInternal = topTexture;
        sideTextureInternal = sideTexture;
        bottomTextureInternal = bottomTexture;
        frontTextureInternal = frontTexture;
        backTextureInternal = sideTexture;
        directional = true;
    }

    public void specifyTextures(int topTexture, int sideTexture, int bottomTexture) {
        topTextureInternal = topTexture;
        sideTextureInternal = sideTexture;
        bottomTextureInternal = bottomTexture;
        frontTextureInternal = sideTexture;
        backTextureInternal = sideTexture;
    }

    public void specifyTextures(int universalTexture) {
        topTextureInternal = universalTexture;
        sideTextureInternal = universalTexture;
        bottomTextureInternal = universalTexture;
        frontTextureInternal = universalTexture;
        backTextureInternal = universalTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 0) return bottomTextureInternal;
        if (side == 1) return topTextureInternal;
        if (side == 2 && meta % 4 == 0) return frontTextureInternal;
        if (side == 3 && meta % 4 == 2) return frontTextureInternal;
        if (side == 4 && meta % 4 == 3) return frontTextureInternal;
        if (side == 5 && meta % 4 == 1) return frontTextureInternal;
        if (side == 2 && meta % 4 == 2) return backTextureInternal;
        if (side == 3 && meta % 4 == 0) return backTextureInternal;
        if (side == 4 && meta % 4 == 1) return backTextureInternal;
        if (side == 5 && meta % 4 == 3) return backTextureInternal;
        return sideTextureInternal;
    }

    @Override
    public void onPlaced(World level, int x, int y, int z, LivingEntity living) {
        if (!directional) return;
        int facing = MathHelper.floor((double)(living.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        level.method_153(x, y, z, facing);
    }
}