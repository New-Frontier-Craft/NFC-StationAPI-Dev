package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.api.TorqueGenerator;

public class CreativeGeneratorBlock extends LazyBlockTemplate implements TorqueGenerator {
    public CreativeGeneratorBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public int getTorque(World world, int x, int y, int z) {
        return 10 + getTorqueFromOtherBlock(world, x, y - 1, z);
    }
}
