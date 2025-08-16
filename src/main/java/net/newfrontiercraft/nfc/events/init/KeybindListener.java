package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.client.option.KeyBinding;
import net.modificationstation.stationapi.api.client.event.option.KeyBindingRegisterEvent;
import org.lwjgl.input.Keyboard;

public class KeybindListener {
    public static KeyBinding telescopeKeybinding;
    @EventListener
    public void registerKeybinds(KeyBindingRegisterEvent event){
        event.keyBindings.add(telescopeKeybinding = new KeyBinding("Telescope", Keyboard.KEY_Z));
    }
}
