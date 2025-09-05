package net.newfrontiercraft.nfc.config;

import net.glasslauncher.mods.gcapi3.api.ConfigCategory;

public class NewFrontierConfig {
    @ConfigCategory(name = "Audio", description = "Options related to audio (sounds, music, etc)")
    public AudioConfig audioConfig = new AudioConfig();
}
