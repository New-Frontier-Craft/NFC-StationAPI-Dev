package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.network.packet.PacketRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.PacketTypeRegistry;
import net.modificationstation.stationapi.api.registry.Registry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.newfrontiercraft.nfc.packet.c2s.BlockPlacementPacket;

public class PacketListener {
    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public void registerPackets(PacketRegisterEvent event){
        Registry.register(PacketTypeRegistry.INSTANCE, Identifier.of(MOD_ID, "block_placement_packet"), BlockPlacementPacket.TYPE);
    }
}
