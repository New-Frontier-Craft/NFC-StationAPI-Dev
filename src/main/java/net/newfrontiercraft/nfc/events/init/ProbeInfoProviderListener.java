package net.newfrontiercraft.nfc.events.init;

import net.danygames2014.whatsthis.event.BlockProbeInfoProviderRegistryEvent;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.newfrontiercraft.nfc.compat.whatsthis.NfcProbeInfoProvider;

public class ProbeInfoProviderListener {
    @EventListener
    public void registerBlockProbeInfoProviders(BlockProbeInfoProviderRegistryEvent event) {
        event.registerProvider(new NfcProbeInfoProvider());
    }
}
