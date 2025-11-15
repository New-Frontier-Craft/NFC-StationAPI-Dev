package net.newfrontiercraft.nfc.registry;

import net.minecraft.block.Block;
import net.newfrontiercraft.nfc.registry.mutation.BlockMutation;

import java.util.HashMap;
import java.util.Map;

public class BlockMutationRegistry {
    private static final BlockMutationRegistry INSTANCE = new BlockMutationRegistry();
    private final Map<Block, BlockMutation> blockMutationMap;

    private BlockMutationRegistry() {
        blockMutationMap = new HashMap<>();
    }

    public static BlockMutationRegistry instance() {
        return INSTANCE;
    }

    public BlockMutation getMutation(Block block) {
        return blockMutationMap.get(block);
    }

    public void addMutation(Block block, BlockMutation blockMutation) {
        blockMutationMap.put(block, blockMutation);
    }
}
