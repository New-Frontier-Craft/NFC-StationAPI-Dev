package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.registry.BlockMutationRegistry;
import net.newfrontiercraft.nfc.registry.mutation.BlockMutation;

import java.util.Random;

public class ProximityMutatorBlock extends LazyBlockTemplate {
    public ProximityMutatorBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        int blockMeta = world.getBlockMeta(x, y, z);
        if (!isValidStructure(world, x, y, z)) {
            return;
        }
        if (blockMeta == 0) {
            if (world.getBlockId(x, y + 1, z) == BlockListener.uraniumBlock.id) {
                world.setBlock(x, y + 1, z, 0);
                world.setBlockMeta(x, y, z, 15);
            }
            return;
        }
        for (int offsetX = x - 4; offsetX <= x + 4; offsetX++) {
            for (int offsetY = y - 4; offsetY <= y + 4; offsetY++) {
                for (int offsetZ = z - 4; offsetZ <= z + 4; offsetZ++) {
                    handleMutation(world, offsetX, offsetY, offsetZ, random);
                }
            }
        }
        world.setBlockMeta(x, y, z, blockMeta - 1);
    }

    private boolean isValidStructure(World world, int x, int y, int z) {
        if (world.getBlockId(x, y - 1, z) != BlockListener.titaniumBlock.id) {
            return false;
        }
        if (missingBlockInCorners(world, x, y - 1, z, BlockListener.leadBlock.id)) {
            return false;
        }
        if (missingBlockInCorners(world, x, y + 1, z, BlockListener.leadBlock.id)) {
            return false;
        }
        if (missingBlockInCorners(world, x, y, z, BlockListener.bismuthBlock.id)) {
            return false;
        }
        if (missingBlockAroundCenter(world, x, y - 1, z, BlockListener.titaniumBlock.id)) {
            return false;
        }
        return !missingBlockAroundCenter(world, x, y + 1, z, BlockListener.bismuthBlock.id);
    }

    private boolean missingBlockInCorners(World world, int x, int y, int z, int blockId) {
        if (world.getBlockId(x + 1, y, z + 1) != blockId) {
            return true;
        }
        if (world.getBlockId(x - 1, y, z + 1) != blockId) {
            return true;
        }
        if (world.getBlockId(x + 1, y, z - 1) != blockId) {
            return true;
        }
        return world.getBlockId(x - 1, y, z - 1) != blockId;
    }

    private boolean missingBlockAroundCenter(World world, int x, int y, int z, int blockId) {
        if (world.getBlockId(x + 1, y, z) != blockId) {
            return true;
        }
        if (world.getBlockId(x - 1, y, z) != blockId) {
            return true;
        }
        if (world.getBlockId(x, y, z + 1) != blockId) {
            return true;
        }
        return world.getBlockId(x, y, z - 1) != blockId;
    }

    private void handleMutation(World world, int x, int y, int z, Random random) {
        float randomX = random.nextFloat();
        float randomY = random.nextFloat();
        float randomZ = random.nextFloat();
        world.addParticle("reddust", x + randomX, y + randomY, z + randomZ, 0, 1, 0);
        Block block = Block.BLOCKS[world.getBlockId(x, y, z)];
        if (block == null) {
            return;
        }
        BlockMutation mutation = BlockMutationRegistry.instance().getMutation(block);
        if (mutation == null) {
            return;
        }
        mutation.mutateBlock(world, x, y, z, random);
    }
}
