package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;

public class BlockEntityListener {

    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    private static void registerBlockEntities(BlockEntityRegisterEvent event)
    {
        event.register(BrickOvenBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_brick_oven")));
    }

}
