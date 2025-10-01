package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.entity.EntityRegister;
import net.modificationstation.stationapi.api.event.registry.EntityHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;
import net.newfrontiercraft.nfc.entity.HellCreeperEntity;

public class EntityListener {
    @Entrypoint.Namespace
    public static Namespace NAMESPACE;

    @EventListener
    public static void registerEntities(EntityRegister event) {
        event.register(HellCreeperEntity.class, NAMESPACE.id("HellCreeper").toString());
    }

    @EventListener
    public static void registerMobHandlers(EntityHandlerRegistryEvent event) {
        event.register(NAMESPACE.id("HellCreeper"), HellCreeperEntity::new);
    }
}
