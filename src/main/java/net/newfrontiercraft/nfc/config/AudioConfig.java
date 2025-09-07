package net.newfrontiercraft.nfc.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class AudioConfig {
    @ConfigEntry(name = "Music Interval", description = "Determines how long pauses are between songs. Default is 12000", maxValue = Integer.MAX_VALUE)
    public Integer musicInterval = 12000;

    @ConfigEntry(name = "Randomize Music Pitch", description = "Causes the music pitch to be chosen randomly, the values ranging from 0.5 to 1.5")
    public Boolean randomizeMusicPitch = false;
}
