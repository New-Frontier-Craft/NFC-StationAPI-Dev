package net.newfrontiercraft.nfc.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
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
        for (int xOffset = x - 20; xOffset <= x + 20; xOffset++) {
            for (int yOffset = 127; yOffset > 0; yOffset--) {
                for (int zOffset = z - 20; zOffset <= z + 20; zOffset++) {
                    if (world.getBlockId(xOffset, yOffset, zOffset) == Block.STONE.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.DIRT.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.GRASS_BLOCK.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.GRAVEL.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == BlockListener.pebble.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.LAVA.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.FLOWING_LAVA.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.WATER.id
                            || world.getBlockId(xOffset, yOffset, zOffset) == Block.FLOWING_WATER.id) {
                        world.setBlock(xOffset, yOffset, zOffset, 0);
                    }
                }
            }
        }
        return true;
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
        } else if (side == 2) {
            return frontTexture;
        } else {
            return sideTexture;
        }
    }
}