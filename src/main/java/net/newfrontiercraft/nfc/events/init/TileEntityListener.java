package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.tileentity.TileEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.tileentities.TileEntityBrickOven;

public class TileEntityListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    private static void registerTileEntities(TileEntityRegisterEvent event)
    {
        event.register(TileEntityBrickOven.class, String.valueOf(Identifier.of(MOD_ID, "tile_entity_brick_oven")));
    }

}
