package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.modificationstation.stationapi.api.client.gui.screen.GuiHandler;
import net.modificationstation.stationapi.api.client.registry.GuiHandlerRegistry;
import net.modificationstation.stationapi.api.event.registry.GuiHandlerRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.registry.Registry;
import net.newfrontiercraft.nfc.block.entity.BookshelfBlockEntity;
import net.newfrontiercraft.nfc.block.entity.CombustionHeaterBlockEntity;
import net.newfrontiercraft.nfc.gui.BookshelfScreen;
import net.newfrontiercraft.nfc.gui.BrickOvenGui;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;
import net.newfrontiercraft.nfc.gui.CarpentryWorkstationGui;
import net.newfrontiercraft.nfc.gui.CombustionHeaterScreen;

public class ScreenHandlerListener {

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public void registerGuiHandlers(GuiHandlerRegistryEvent event) {
        GuiHandlerRegistry registry = event.registry;

        Registry.register(registry, Identifier.of(MOD_ID, "gui_brick_oven"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openBrickOven, BrickOvenBlockEntity::new));
        Registry.register(registry, Identifier.of(MOD_ID, "gui_carpentry"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openCarpentry, () -> null));
        Registry.register(registry, Identifier.of(MOD_ID, "gui_bookshelf"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openBookshelf, BookshelfBlockEntity::new));
        Registry.register(registry, Identifier.of(MOD_ID, "gui_combustion_heater"), new GuiHandler((GuiHandler.ScreenFactoryNoMessage) this::openCombustionHeater, CombustionHeaterBlockEntity::new));
    }

    public Screen openBrickOven(PlayerEntity playerBase, Inventory inventoryBase) {
        return new BrickOvenGui(playerBase.inventory, (BrickOvenBlockEntity) inventoryBase);
    }

    public Screen openCarpentry(PlayerEntity playerBase, Inventory inventoryBase) {
        return new CarpentryWorkstationGui(playerBase.inventory, playerBase.world, (int) playerBase.x, (int) playerBase.y, (int) playerBase.z);
    }

    public Screen openBookshelf(PlayerEntity playerBase, Inventory inventoryBase) {
        return new BookshelfScreen(playerBase.inventory, (BookshelfBlockEntity) inventoryBase);
    }

    public Screen openCombustionHeater(PlayerEntity playerBase, Inventory inventoryBase) {
        return new CombustionHeaterScreen(playerBase.inventory, (CombustionHeaterBlockEntity) inventoryBase);
    }
}
