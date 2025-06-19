package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.newfrontiercraft.nfc.block.entity.*;

public class BlockEntityListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public static void registerBlockEntities(BlockEntityRegisterEvent event) {
        event.register(BrickOvenBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_brick_oven")));
        event.register(BookshelfBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_bookshelf")));
        event.register(HeatCoilBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_heat_coil")));
        event.register(CombustionHeaterBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_combustion_heater")));
        event.register(BasicItemChuteBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_basic_item_chute")));
        event.register(FilteringItemChuteBlockEntity.class, String.valueOf(Identifier.of(MOD_ID, "block_entity_filtering_item_chute")));
    }

}
