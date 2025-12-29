package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class PetrifiedLeavesBlock extends LeavesBlock{
    public PetrifiedLeavesBlock(Identifier identifier) {
        super(identifier, BlockListener.petrifiedLog.id, BlockRegistry.INSTANCE.getId(Block.DEAD_BUSH));
    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, BlockState replacedState) {
    }
}
