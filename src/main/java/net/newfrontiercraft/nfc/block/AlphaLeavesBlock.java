package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.compat.vbe.VBEBlockIdGetter;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class AlphaLeavesBlock extends LeavesBlock {
    public AlphaLeavesBlock(Identifier identifier) {
        super(identifier, BlockListener.isVbePresent ? VBEBlockIdGetter.getOakLogId() : Block.LOG.id, BlockListener.MOD_ID.id("alpha_sapling"));
    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, BlockState replacedState) {
    }
}
