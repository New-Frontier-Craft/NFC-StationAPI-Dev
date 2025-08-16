package net.newfrontiercraft.nfc.events.ingame;

import net.danygames2014.unitweaks.tweaks.morekeybinds.KeyBindingListener;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.client.event.keyboard.KeyStateChangedEvent;
import net.newfrontiercraft.nfc.events.init.KeybindListener;
import net.newfrontiercraft.nfc.item.TelescopeItem;
import org.lwjgl.input.Keyboard;

public class KeyStateChangedListener {
    @EventListener
    public void keyStateChanged(KeyStateChangedEvent event){
        if(event.environment == KeyStateChangedEvent.Environment.IN_GAME){
            if(Keyboard.getEventKeyState() && Keyboard.isKeyDown(KeybindListener.telescopeKeybinding.code)){
                if(TelescopeItem.hasTelescope()){
                    TelescopeItem.toggleScoping();
                }
            }
        }
    }
}
