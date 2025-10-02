package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LazyPillarBlockTemplate extends LazyBlockTemplate {
    protected int rotatedSideTexture;
    protected Item droppedItem;
    protected int meta;

    public LazyPillarBlockTemplate(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public LazyPillarBlockTemplate changeDroppedItem(Item droppedItem) {
        this.droppedItem = droppedItem;
        return this;
    }

    public LazyPillarBlockTemplate changeDroppedMeta(int meta) {
        this.meta = meta;
        return this;
    }

    @Override
    public int getTexture(int side, int meta) {
        switch (meta) {
            case 0:
                switch (side) {
                    case 0:
                    case 1:
                        return topTexture;
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                        return sideTexture;
                }
            case 1:
                switch (side) {
                    case 0:
                    case 1:
                        return sideTexture;
                    case 2:
                    case 3:
                        return topTexture;
                    case 4:
                    case 5:
                        return rotatedSideTexture;
                }
            case 2:
                switch (side) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        return rotatedSideTexture;
                    case 4:
                    case 5:
                        return topTexture;
                }
        }
        return 0;
    }

    public void specifyTextures(int topTexture, int sideTexture, int rotatedSideTexture) {
        this.topTexture = topTexture;
        this.sideTexture = sideTexture;
        this.rotatedSideTexture = rotatedSideTexture;
    }

    public void onPlaced(World world, int i, int j, int k, LivingEntity entityliving) {
        int rotation = getBlockRotation(world, i, j, k, (PlayerEntity)entityliving);
        world.setBlockMeta(i, j, k, rotation);
    }

    private static int getBlockRotation(World world, int i, int j, int k, PlayerEntity entityplayer) {
        if(MathHelper.abs((float)entityplayer.x - (float)i) < 2.0F && MathHelper.abs((float)entityplayer.z - (float)k) < 2.0F) {
            double d = (entityplayer.y + 1.8200000000000001D) - (double)entityplayer.standingEyeHeight;
            if(d - (double)j > 2D) {
                return 0;
            }
            if((double)j - d > 0.0D) {
                return 0;
            }
        }
        int l = MathHelper.floor((double)((entityplayer.yaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0) {
            return 1;
        }
        if(l == 1) {
            return 2;
        }
        if(l == 2) {
            return 1;
        }
        return 2;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        if (droppedItem == null) {
            return super.getDroppedItemId(blockMeta, random);
        }
        return droppedItem.id;
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return meta;
    }
}
