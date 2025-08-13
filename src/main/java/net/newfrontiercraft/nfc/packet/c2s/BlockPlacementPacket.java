package net.newfrontiercraft.nfc.packet.c2s;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.NetworkHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.util.math.BlockPos;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.States;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.network.packet.ManagedPacket;
import net.modificationstation.stationapi.api.network.packet.PacketType;
import net.modificationstation.stationapi.api.util.SideUtil;
import net.modificationstation.stationapi.api.util.math.StationBlockPos;
import org.jetbrains.annotations.NotNull;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BlockPlacementPacket extends Packet implements ManagedPacket<BlockPlacementPacket> {
    BlockPos blockPos;
    BlockState blockState;
    int meta;

    public static final PacketType<BlockPlacementPacket> TYPE = PacketType.builder(false, true, BlockPlacementPacket::new).build();

    public BlockPlacementPacket(){}
    public BlockPlacementPacket(int x, int y, int z, BlockState blockState, int meta){
        this.blockPos = new BlockPos(x, y, z);
        this.blockState = blockState;
        this.meta = meta;
    }

    @Override
    public void read(DataInputStream stream) {
        try {
            this.blockPos = StationBlockPos.fromLong(stream.readLong());
            this.blockState = States.STATE_IDS.get(stream.readInt());
            this.meta = stream.readInt();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(DataOutputStream stream) {
        int rawId = States.STATE_IDS.getRawId(blockState);
        try {
            stream.writeLong(blockPos.asLong());
            stream.writeInt(rawId);
            stream.writeInt(meta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void apply(NetworkHandler networkHandler) {
        SideUtil.run(() -> {}, () -> handleServer(networkHandler));
    }
    @Environment(EnvType.SERVER)
    public void handleServer(NetworkHandler networkHandler){
        PlayerEntity playerEntity = PlayerHelper.getPlayerFromPacketHandler(networkHandler);
        if(playerEntity == null || playerEntity.getHand() == null || playerEntity.getHand().count <= 0) return;
        playerEntity.world.setBlockStateWithMetadataWithNotify(blockPos.getX(), blockPos.getY(), blockPos.getZ(), blockState, meta);
        playerEntity.getHand().count--;
    }

    @Override
    public int size() {
        return 8 + 4 + 4;
    }

    @Override
    public @NotNull PacketType<BlockPlacementPacket> getType() {
        return TYPE;
    }
}
