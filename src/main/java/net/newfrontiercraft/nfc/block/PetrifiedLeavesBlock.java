package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class PetrifiedLeavesBlock extends LeavesBlock{
    public PetrifiedLeavesBlock(Identifier identifier) {
        super(identifier, BlockListener.petrifiedLog.id);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return Block.DEAD_BUSH.id;
    }
}
