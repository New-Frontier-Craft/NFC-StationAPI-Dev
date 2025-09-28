package net.newfrontiercraft.nfc.utils;

public enum FuelLevelEnum {
    COLD(250),
    WARM(450),
    HOT(650),
    SEARING(850),
    BLAZING(1050),
    SUPERHEATED(1250);

    private final int heat;

    FuelLevelEnum(int heat) {
        this.heat = heat;
    }

    public int getHeat() {
        return heat;
    }
}
