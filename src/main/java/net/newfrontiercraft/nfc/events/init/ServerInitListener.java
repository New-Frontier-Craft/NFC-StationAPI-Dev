package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.mod.InitEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import org.apache.logging.log4j.Logger;

public class ServerInitListener {

    @Entrypoint.Namespace
    private static final Namespace MOD_ID = Null.get();

    @Entrypoint.Logger
    private static final Logger LOGGER = Null.get();

    @EventListener
    private static void serverInit(InitEvent event) {
        LOGGER.error(MOD_ID.toString());
    }
}
