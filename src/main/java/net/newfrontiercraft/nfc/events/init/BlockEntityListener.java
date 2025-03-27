package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.newfrontiercraft.nfc.block.entity.BookshelfBlockEntity;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;

public class BlockEntityListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public static void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(BrickOvenBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_brick_oven")));
        event.register(BookshelfBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_bookshelf")));
    }

}
