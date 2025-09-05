package net.newfrontiercraft.nfc.events.init;

import net.glasslauncher.mods.gcapi3.api.ConfigRoot;
import net.newfrontiercraft.nfc.config.NewFrontierConfig;

public class ConfigListener {
    @ConfigRoot(value = "NfcCfg", visibleName = "New Frontier Config")
    public static final NewFrontierConfig NEW_FRONTIER_CONFIG = new NewFrontierConfig();
}
