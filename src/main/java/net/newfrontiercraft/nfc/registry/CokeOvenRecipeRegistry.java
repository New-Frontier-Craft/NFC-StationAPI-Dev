package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.utils.CokeOvenResult;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.wrappers.CokeOvenRecipe;

import java.util.ArrayList;
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

    public ArrayList<CokeOvenRecipe> getRecipes() {
        ArrayList<CokeOvenRecipe> cokeOvenRecipes = new ArrayList<>();
        ArrayList<ItemMeta> inputs = new ArrayList<>();
        ArrayList<CokeOvenResult> outputs = new ArrayList<>();
        for (ItemMeta keyRecord : cokeOvenMap.keySet()) {
            inputs.add(keyRecord);
            outputs.add(getResult(keyRecord));
        }
        for (int i = 0; i < inputs.size(); i++) {
            if (i >= outputs.size()) break;
            cokeOvenRecipes.add(new CokeOvenRecipe(new ItemStack(inputs.get(i).item(), 1, inputs.get(i).meta()), outputs.get(i)));
        }
        return cokeOvenRecipes;
    }
}
