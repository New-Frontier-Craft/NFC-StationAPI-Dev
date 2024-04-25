package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.util.Namespace;

public class AchievementListener {

    @Entrypoint.Namespace
    private Namespace modID;

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
    }
}
