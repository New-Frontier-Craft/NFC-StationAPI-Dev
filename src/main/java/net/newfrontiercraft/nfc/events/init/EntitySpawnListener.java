package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.world.biome.Biome;
import net.modificationstation.stationapi.api.event.worldgen.biome.BiomeModificationEvent;
import net.newfrontiercraft.nfc.entity.HellCreeperEntity;
import net.newfrontiercraft.nfc.entity.HellSpiderEntity;

public class EntitySpawnListener {
    @EventListener
    public void registerEntitySpawn(BiomeModificationEvent event) {
        if (event.biome == Biome.HELL) {
            event.biome.addHostileEntity(HellCreeperEntity.class, 16);
            event.biome.addHostileEntity(HellSpiderEntity.class, 12);
        }
    }
}
