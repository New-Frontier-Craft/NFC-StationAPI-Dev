package net.newfrontiercraft.nfc.utils;

public enum FuelLevelEnum {
    COLD(500),
    WARM(1500),
    HOT(2500),
    SEARING(3500),
    BLAZING(4500),
    SUPERHEATED(5500);

    private final int heat;

    FuelLevelEnum(int heat) {
        this.heat = heat;
    }

    public int getHeat() {
        return heat;
    }
}
