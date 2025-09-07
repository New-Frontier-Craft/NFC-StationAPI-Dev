package net.newfrontiercraft.nfc.api;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public interface TorqueGenerator {

    /**
     * Gets the torque of a generator.
     * @return Torque value of the generator.
     */
    int getTorque(World world, int x, int y, int z);

    /**
     * Default method to make it more convenient to continue the torque checking chain.
     * @return torque value of the generator below.
     */
    default int getTorqueFromOtherBlock(World world, int x, int y, int z) {
        int blockId = world.getBlockId(x, y, z);
        if (blockId == 0) {
            return 0;
        }
        Block block = Block.BLOCKS[blockId];
        if (block instanceof TorqueGenerator torqueGenerator) {
            return torqueGenerator.getTorque(world, x, y, z);
        }
        return 0;
    }
}
