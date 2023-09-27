package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.achievement.Achievement;
import net.minecraft.item.ItemBase;
import net.modificationstation.stationapi.api.client.gui.screen.menu.AchievementPage;
import net.modificationstation.stationapi.api.event.achievement.AchievementRegisterEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.ModID;

import java.util.*;

public class AchievementListener {

    @Entrypoint.ModID
    private ModID modID;

    @EventListener
    public void registerAchievements(AchievementRegisterEvent event) {
    }
}
