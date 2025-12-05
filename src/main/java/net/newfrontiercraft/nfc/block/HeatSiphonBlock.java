package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;

public class HeatSiphonBlock extends LazyBlockTemplate {
    protected int hotTopTexture;
    protected int hotSideTexture;

    public HeatSiphonBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public void specifyTextures(int topTexture, int hotTopTexture, int sideTexture, int hotSideTexture, int bottomTexture) {
        super.specifyTextures(topTexture, sideTexture, bottomTexture);
        this.hotTopTexture = hotTopTexture;
        this.hotSideTexture = hotSideTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 1 && meta == 1) {
            return hotTopTexture;
        }
        if (side > 1 && meta == 1) {
            return hotSideTexture;
        }
        return super.getTexture(side, meta);
    }

    public void provideHeat(World world, int x, int y, int z) {
        int blockMeta = world.getBlockMeta(x, y, z);
        int belowId = world.getBlockId(x, y - 1, z);
        if ((belowId != Block.LAVA.id && belowId != Block.FLOWING_LAVA.id) || world.getBlockMeta(x, y - 1, z) != 0) {
            if (blockMeta == 1) {
                world.setBlockMeta(x, y, z, 0);
            }
            return;
        } else if (blockMeta == 0) {
            world.setBlockMeta(x, y, z, 1);
        }
        BlockEntity blockEntity = world.getBlockEntity(x, y + 1, z);
        if (blockEntity instanceof HeatCoilBlockEntity heatCoil) {
            if (heatCoil.getHeatLevel() < 400) {
                heatCoil.changeHeatLevel(10);
            }
        }
    }
}
