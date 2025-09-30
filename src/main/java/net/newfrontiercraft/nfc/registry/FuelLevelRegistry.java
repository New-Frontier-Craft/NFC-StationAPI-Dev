package net.newfrontiercraft.nfc.registry;

import net.newfrontiercraft.nfc.utils.FuelLevelEnum;
import net.newfrontiercraft.nfc.utils.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class FuelLevelRegistry {
    private static final FuelLevelRegistry INSTANCE = new FuelLevelRegistry();
    private final Map<ItemMeta, FuelLevelEnum> fuelLevelMap;

    private FuelLevelRegistry() {
        fuelLevelMap = new HashMap<>();
    }

    public static FuelLevelRegistry fuelLevel() {
        return INSTANCE;
    }

    public FuelLevelEnum getFuelLevel(ItemMeta item) {
        FuelLevelEnum fuelLevelEnum = getRawFuelLevel(item);
        if (fuelLevelEnum == null) {
            return FuelLevelEnum.COLD;
        }
        return fuelLevelEnum;
    }

    public FuelLevelEnum getRawFuelLevel(ItemMeta item) {
        return fuelLevelMap.get(item);
    }

    public void addFuelLevel(ItemMeta item, FuelLevelEnum fuelLevelEnum) {
        fuelLevelMap.put(item, fuelLevelEnum);
    }

}
