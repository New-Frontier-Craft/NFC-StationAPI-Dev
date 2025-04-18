package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.client.model.block.BlockWithWorldRenderer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.mixin.graphics.BlockRenderManagerAccessor;

import java.util.Random;
@EnvironmentInterface(value= EnvType.CLIENT, itf= BlockWithWorldRenderer.class)
public class DoubleStoneSlabBlock extends LazyBlockTemplate implements BlockWithWorldRenderer{
    public DoubleStoneSlabBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getTexture(int side, int meta) {
        return meta == 0 ? (side < 2 ? 6 : 5) : (meta == 1 ? (side == 0 ? 208 : (side != 1 ? 192 : 176)) : (meta == 2 ? 4 : (meta == 3 ? 16 : (meta == 4 ? (side < 2 ? 5 : (side < 4 ? 6 : 5)) : (side < 4 ? 5 : 6)))));
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity living) {
        if(living.pitch > 40.0F) {
            world.setBlock(x, y, z, this.id, 0);
        } else if(living.pitch < -40.0F) {
            world.setBlock(x, y, z, this.id, 0);
        } else {
            int position = MathHelper.floor((double)(living.yaw * 4.0F / 360.0F) + 0.5D) & 3;
            if(position == 0) {
                world.setBlock(x, y, z, this.id, 4);
            } else if(position == 1) {
                world.setBlock(x, y, z, this.id, 5);
            } else if(position == 2) {
                world.setBlock(x, y, z, this.id, 4);
            } else {
                world.setBlock(x, y, z, this.id, 5);
            }
        }
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 2;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return Block.SLAB.id;
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return blockMeta > 3 ? 0 : blockMeta;
    }

    @Override
    public boolean renderWorld(BlockRenderManager blockRenderManager, BlockView blockView, int x, int y, int z) {
        int meta = blockView.getBlockMeta(x, y, z);
        if(meta == 5){
            ((BlockRenderManagerAccessor)blockRenderManager).setTopFaceRotation(1);
            ((BlockRenderManagerAccessor)blockRenderManager).setBottomFaceRotation(2);
            ((BlockRenderManagerAccessor)blockRenderManager).setEastFaceRotation(2);
            ((BlockRenderManagerAccessor)blockRenderManager).setWestFaceRotation(1);
        }
        if(meta == 4){
            ((BlockRenderManagerAccessor)blockRenderManager).setBottomFaceRotation(3);
            ((BlockRenderManagerAccessor)blockRenderManager).setSouthFaceRotation(1);
            ((BlockRenderManagerAccessor)blockRenderManager).setNorthFaceRotation(2);
        }
        boolean flag = blockRenderManager.renderBlock(this, x, y, z);
        ((BlockRenderManagerAccessor)blockRenderManager).setTopFaceRotation(0);
        ((BlockRenderManagerAccessor)blockRenderManager).setBottomFaceRotation(0);
        ((BlockRenderManagerAccessor)blockRenderManager).setEastFaceRotation(0);
        ((BlockRenderManagerAccessor)blockRenderManager).setWestFaceRotation(0);
        ((BlockRenderManagerAccessor)blockRenderManager).setSouthFaceRotation(0);
        ((BlockRenderManagerAccessor)blockRenderManager).setNorthFaceRotation(0);
        return flag;
    }
}
