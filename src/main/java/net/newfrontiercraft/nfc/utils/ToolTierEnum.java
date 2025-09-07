package net.newfrontiercraft.nfc.utils;

public enum ToolTierEnum {
    WOOD("§f", "tool_tier.wooden"),
    STONE("§7", "tool_tier.stone"),
    CRUDE("§a", "tool_tier.crude"),
    BASIC("§b", "tool_tier.basic"),
    IRON("§d", "tool_tier.iron"),
    ADVANCED("§6", "tool_tier.advanced"),
    DIAMOND("§e", "tool_tier.diamond");

    private String colourCode;
    private String name;

    ToolTierEnum(final String colourCode, final String name) {
        this.colourCode = colourCode;
        this.name = name;
    }

    public String getColourCode() {
        return colourCode;
    }

    public String getName() {
        return name;
    }
}
