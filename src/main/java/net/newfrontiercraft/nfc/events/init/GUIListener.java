package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.ScreenBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.inventory.InventoryBase;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.registry.ModID;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.guis.GUIBrickOven;
import net.newfrontiercraft.nfc.tileentities.TileEntityBrickOven;
import uk.co.benjiweber.expressions.tuple.BiTuple;

public class GUIListener {

    @Entrypoint.ModID
    public static final ModID MOD_ID = Null.get();

    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event)
    {
        GuiHandlerRegistry registry = event.registry;

        registry.registerValueNoMessage(Identifier.of(MOD_ID, "gui_brick_oven"), BiTuple.of(this::openBrickOven, TileEntityBrickOven::new));
    }

    public ScreenBase openBrickOven(PlayerBase playerBase, InventoryBase inventoryBase)
    {
        return new GUIBrickOven(playerBase.inventory, (TileEntityBrickOven) inventoryBase);
    }

}
