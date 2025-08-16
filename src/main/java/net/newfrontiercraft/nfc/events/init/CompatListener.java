package net.newfrontiercraft.nfc.events.init;

import net.danygames2014.unitweaks.event.RegisterUniTweaksCompatEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.newfrontiercraft.nfc.item.TelescopeItem;

public class CompatListener {
    @EventListener
    public void registerUniTweaksCompatEvent(RegisterUniTweaksCompatEvent event){
        event.registerFovCompat((fov, partialTicks) -> {
            if(TelescopeItem.isScoping()){
                return 10F;
            }
            return fov;
        });
    }

    public float fovMultiplier(float f1, float f2){
        return f1;
    }
}
