package net.newfrontiercraft.nfc.registry;

import net.newfrontiercraft.nfc.utils.CokeOvenResult;
import net.newfrontiercraft.nfc.utils.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class CokeOvenRecipeRegistry {
    private static final CokeOvenRecipeRegistry INSTANCE = new CokeOvenRecipeRegistry();
    private final Map<ItemMeta, CokeOvenResult> cokeOvenMap;

    private CokeOvenRecipeRegistry() {
        cokeOvenMap = new HashMap<>();
    }

    public static CokeOvenRecipeRegistry getInstance() {
        return INSTANCE;
    }

    public CokeOvenResult getResult(ItemMeta item) {
        return cokeOvenMap.get(item);
    }

    public void addRecipe(ItemMeta item, CokeOvenResult cokeOvenResult) {
        cokeOvenMap.put(item, cokeOvenResult);
    }
}
