package net.newfrontiercraft.nfc.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.api.TorqueGenerator;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;

public class StirlingGeneratorBlock extends LazyBlockTemplate implements TorqueGenerator {
    public StirlingGeneratorBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getTorque(World world, int x, int y, int z) {
        return getTorqueFromHeatCoil(world, x + 1, y, z)
                + getTorqueFromHeatCoil(world, x - 1, y, z)
                + getTorqueFromHeatCoil(world, x, y, z + 1)
                + getTorqueFromHeatCoil(world, x, y, z - 1)
                + getTorqueFromOtherBlock(world, x, y - 1, z);
    }

    private int getTorqueFromHeatCoil(World world, int x, int y, int z) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if (blockEntity instanceof HeatCoilBlockEntity heatCoilBlockEntity) {
            int heat = heatCoilBlockEntity.getHeatLevel();
            if (heat < 250) {
                return 0;
            } else if (heat < 500) {
                heatCoilBlockEntity.changeHeatLevel(-10);
                return 1;
            } else if (heat < 750) {
                heatCoilBlockEntity.changeHeatLevel(-10);
                return 2;
            } else {
                heatCoilBlockEntity.changeHeatLevel(-10);
                return 3;
            }
        }
        return 0;
    }
}
