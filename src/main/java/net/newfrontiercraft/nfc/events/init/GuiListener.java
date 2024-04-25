package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.guis.BrickOvenGui;
import net.newfrontiercraft.nfc.blockentities.BrickOvenBlockEntity;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GuiListener {

    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event)
    {
        GuiHandlerRegistry registry = event.registry;

        registry.registerValueNoMessage(Identifier.of(MOD_ID, "gui_brick_oven"), BiTuple.of(this::openBrickOven, BrickOvenBlockEntity::new));
    }

    public Screen openBrickOven(PlayerEntity playerBase, Inventory inventoryBase)
    {
        return new BrickOvenGui(playerBase.inventory, (BrickOvenBlockEntity) inventoryBase);
    }

}
