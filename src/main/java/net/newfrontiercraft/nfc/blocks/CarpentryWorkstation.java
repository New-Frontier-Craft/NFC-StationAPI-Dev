package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.BlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Living;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.level.Level;
import net.minecraft.util.maths.MathHelper;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.block.TemplateBlockBase;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class CarpentryWorkstation extends TemplateBlockBase {
    int frontTexture;
    int sideTexture;
    int bottomTexture;
    int topTexture;

    public CarpentryWorkstation(Identifier identifier, Material material, float hardness) {
        super(identifier, material);
        setTranslationKey(identifier.modID, identifier.id);
        setHardness(hardness);
    }

    @Override
    public int getDropId(int i, Random random) {
        return BlockListener.brickOven.id;
    }

    @Override
    public boolean canUse(Level world, int x, int y, int z, PlayerBase player) {
        return true;
    }

    @Override
    public void onBlockPlaced(Level world, int i, int j, int k) {
        super.onBlockPlaced(world, i, j, k);
        if (!world.isServerSide) {
            setDefaultDirection(world, i, j, k);
        }
    }

    private void setDefaultDirection(Level world, int i, int j, int k) {
        int l = world.getTileId(i, j, k - 1);
        int i1 = world.getTileId(i, j, k + 1);
        int j1 = world.getTileId(i - 1, j, k);
        int k1 = world.getTileId(i + 1, j, k);
        byte byte0 = 3;
        if (BlockBase.FULL_OPAQUE[l] && !BlockBase.FULL_OPAQUE[i1]) {
            byte0 = 3;
        }
        if (BlockBase.FULL_OPAQUE[i1] && !BlockBase.FULL_OPAQUE[l]) {
            byte0 = 2;
        }
        if (BlockBase.FULL_OPAQUE[j1] && !BlockBase.FULL_OPAQUE[k1]) {
            byte0 = 5;
        }
        if (BlockBase.FULL_OPAQUE[k1] && !BlockBase.FULL_OPAQUE[j1]) {
            byte0 = 4;
        }
        world.placeBlockWithMetaData(i, j, k, this.id, byte0);
    }

    public void specifyTextures(int frontTexture, int sideTexture, int bottomTexture, int topTexture) {
        this.frontTexture = frontTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
        this.topTexture = topTexture;
    }

    @Override
    public int getTextureForSide(int i, int j) {
        if (i == 0) {
            return bottomTexture;
        } else if (i == 1) {
            return topTexture;
        } else if (i == j) {
            return frontTexture;
        } else {
            return sideTexture;
        }
    }

    @Override
    public void afterPlaced(Level world, int i, int j, int k, Living entityliving) {
        int l = MathHelper
                .floor((double) ((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if (l == 0) {
            world.setTileMeta(i, j, k, 2);
        }
        if (l == 1) {
            world.setTileMeta(i, j, k, 5);
        }
        if (l == 2) {
            world.setTileMeta(i, j, k, 3);
        }
        if (l == 3) {
            world.setTileMeta(i, j, k, 4);
        }
    }
}