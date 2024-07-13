package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyBlockTemplate extends TemplateBlock {

    int topTexture;
    int sideTexture;
    int bottomTexture;
    int frontTexture;
    int backTexture;
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
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
        this.frontTexture = frontTexture;
        this.backTexture = backTexture;
        directional = true;
    }

    public void specifyTextures(int topTexture, int sideTexture, int bottomTexture, int frontTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
        this.frontTexture = frontTexture;
        backTexture = sideTexture;
        directional = true;
    }

    public void specifyTextures(int topTexture, int sideTexture, int bottomTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
        frontTexture = sideTexture;
        backTexture = sideTexture;
    }

    public void specifyTextures(int universalTexture) {
        topTexture = universalTexture;
        sideTexture = universalTexture;
        bottomTexture = universalTexture;
        frontTexture = universalTexture;
        backTexture = universalTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 0) return bottomTexture;
        if (side == 1) return topTexture;
        if (side == 2 && meta % 4 == 0) return frontTexture;
        if (side == 3 && meta % 4 == 2) return frontTexture;
        if (side == 4 && meta % 4 == 3) return frontTexture;
        if (side == 5 && meta % 4 == 1) return frontTexture;
        if (side == 2 && meta % 4 == 2) return backTexture;
        if (side == 3 && meta % 4 == 0) return backTexture;
        if (side == 4 && meta % 4 == 1) return backTexture;
        if (side == 5 && meta % 4 == 3) return backTexture;
        return sideTexture;
    }

    @Override
    public void onPlaced(World level, int x, int y, int z, LivingEntity living) {
        if (!directional) return;
        int facing = MathHelper.floor((double)(living.yaw * 4.0F / 360.0F) + 0.5D) & 3;
        level.setBlockMeta(x, y, z, facing);
    }
}