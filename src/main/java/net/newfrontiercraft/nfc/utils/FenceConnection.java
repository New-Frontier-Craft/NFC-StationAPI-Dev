package net.newfrontiercraft.nfc.utils;

import net.minecraft.world.BlockView;

public interface FenceConnection {
    boolean connectFenceAt(BlockView blockView, int i, int j, int k, boolean isConnected);
}
