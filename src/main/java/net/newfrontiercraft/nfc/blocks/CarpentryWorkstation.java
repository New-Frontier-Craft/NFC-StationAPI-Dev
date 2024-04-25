package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class CarpentryWorkstation extends TemplateBlock {
    int frontTexture;
    int sideTexture;
    int bottomTexture;
    int topTexture;

    public CarpentryWorkstation(Identifier identifier, Material material, float hardness) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
        setHardness(hardness);
    }

    @Override
    public int getDroppedItemId(int i, Random random) {
        return BlockListener.carpentryWorkstation.id;
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        return true;
    }

    @Override
    public void onPlaced(World world, int i, int j, int k) {
        super.onPlaced(world, i, j, k);
        if (!world.isRemote) {
            setDefaultDirection(world, i, j, k);
        }
    }

    private void setDefaultDirection(World world, int i, int j, int k) {
        int l = world.getBlockId(i, j, k - 1);
        int i1 = world.getBlockId(i, j, k + 1);
        int j1 = world.getBlockId(i - 1, j, k);
        int k1 = world.getBlockId(i + 1, j, k);
        byte byte0 = 3;
        if (Block.BLOCKS_OPAQUE[l] && !Block.BLOCKS_OPAQUE[i1]) {
            byte0 = 3;
        }
        if (Block.BLOCKS_OPAQUE[i1] && !Block.BLOCKS_OPAQUE[l]) {
            byte0 = 2;
        }
        if (Block.BLOCKS_OPAQUE[j1] && !Block.BLOCKS_OPAQUE[k1]) {
            byte0 = 5;
        }
        if (Block.BLOCKS_OPAQUE[k1] && !Block.BLOCKS_OPAQUE[j1]) {
            byte0 = 4;
        }
        world.method_154(i, j, k, this.id, byte0);
    }

    public void specifyTextures(int frontTexture, int sideTexture, int bottomTexture, int topTexture) {
        this.frontTexture = frontTexture;
        this.sideTexture = sideTexture;
        this.bottomTexture = bottomTexture;
        this.topTexture = topTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 0) {
            return bottomTexture;
        } else if (side == 1) {
            return topTexture;
        } else if (side == meta) {
            return frontTexture;
        } else {
            return sideTexture;
        }
    }

    @Override
    public void onPlaced(World world, int i, int j, int k, LivingEntity entityliving) {
        int l = MathHelper
                .floor((double) ((entityliving.yaw * 4F) / 360F) + 0.5D) & 3;
        if (l == 0) {
            world.method_153(i, j, k, 2);
        }
        if (l == 1) {
            world.method_153(i, j, k, 5);
        }
        if (l == 2) {
            world.method_153(i, j, k, 3);
        }
        if (l == 3) {
            world.method_153(i, j, k, 4);
        }
    }
}