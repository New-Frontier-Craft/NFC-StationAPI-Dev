package net.newfrontiercraft.nfc.utils;

public enum FuelLevelEnum {
    COLD(250, "fuel_level.cold", "§5"),
    WARM(450, "fuel_level.warm", "§d"),
    HOT(650, "fuel_level.hot", "§4"),
    SEARING(850, "fuel_level.searing", "§c"),
    BLAZING(1050, "fuel_level.blazing", "§6"),
    SUPERHEATED(1250, "fuel_level.superheated", "§e");

    private final int heat;
    private final String name;
    private final String colourCode;

    FuelLevelEnum(int heat, String name, String colourCode) {
        this.heat = heat;
        this.name = name;
        this.colourCode = colourCode;
    }

    public int getHeat() {
        return heat;
    }

    public String getName() {
        return name;
    }

    public String getColourCode() {
        return colourCode;
    }
}
