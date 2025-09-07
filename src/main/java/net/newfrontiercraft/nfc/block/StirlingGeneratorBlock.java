package net.newfrontiercraft.nfc.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.api.TorqueGenerator;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;

import java.util.Random;

public class StirlingGeneratorBlock extends LazyBlockTemplate implements TorqueGenerator {
    public StirlingGeneratorBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getTorque(World world, int x, int y, int z) {
        int additionalTorque = getTorqueFromHeatCoil(world, x + 1, y, z)
                + getTorqueFromHeatCoil(world, x - 1, y, z)
                + getTorqueFromHeatCoil(world, x, y, z + 1)
                + getTorqueFromHeatCoil(world, x, y, z - 1);
        if (additionalTorque < 1) {
            world.setBlockMeta(x, y, z, 0);
        } else if (additionalTorque < 4) {
            world.setBlockMeta(x, y, z, 1);
        } else if (additionalTorque < 7) {
            world.setBlockMeta(x, y, z, 2);
        } else {
            world.setBlockMeta(x, y, z, 3);
        }
        return additionalTorque + getTorqueFromOtherBlock(world, x, y - 1, z);
    }

    private int getTorqueFromHeatCoil(World world, int x, int y, int z) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (blockEntity instanceof HeatCoilBlockEntity heatCoilBlockEntity) {
            int heat = heatCoilBlockEntity.getHeatLevel();
            if (heat < 250) {
                return 0;
            } else if (heat < 500) {
                heatCoilBlockEntity.changeHeatLevel(-10);
                return 10;
            } else if (heat < 750) {
                heatCoilBlockEntity.changeHeatLevel(-10);
                return 20;
            } else {
                heatCoilBlockEntity.changeHeatLevel(-10);
                return 30;
            }
        }
        return 0;
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        int particleCount = world.getBlockMeta(x, y, z) * 4;
        for (int i = 0; i < particleCount; i++) {
            float xOffset = random.nextFloat();
            float yOffset = random.nextFloat();
            float zOffset = random.nextFloat();
            world.addParticle("smoke", x + xOffset, y + yOffset, z + zOffset, 0.0D, 0.0D, 0.0D);
        }
    }
}
