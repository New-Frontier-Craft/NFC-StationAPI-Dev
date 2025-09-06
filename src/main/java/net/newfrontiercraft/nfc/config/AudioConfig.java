package net.newfrontiercraft.nfc.config;

import net.glasslauncher.mods.gcapi3.api.ConfigEntry;

public class AudioConfig {
    @ConfigEntry(name = "Music Interval", description = "Determines how the long the pause is between songs. 0 means a constant stream of music, 12000 is the default.")
    public Integer musicInterval = 12000;

    @ConfigEntry(name = "Randomize Music Pitch", description = "Causes the music pitch to be chosen randomly, the values randing from 0.5 to 1.5")
    public Boolean randomizeMusicPitch = false;
}
