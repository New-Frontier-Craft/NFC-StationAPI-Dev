package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;

import java.util.Random;

public class AlphaLeavesBlock extends LeavesBlock{
    public AlphaLeavesBlock(Identifier identifier) {
        super(identifier, Block.LOG.id);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        return BlockListener.alphaSapling.id;
    }
}
