package net.newfrontiercraft.nfc.registry;

import net.newfrontiercraft.nfc.utils.ChanceDrop;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.wrappers.HarvestingRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TreeFarmHarvestingRegistry {
    private static final TreeFarmHarvestingRegistry INSTANCE = new TreeFarmHarvestingRegistry();
    private final Map<ItemMeta, ChanceDrop[]> treeFarmHarvestingMap;

    private TreeFarmHarvestingRegistry() {
        treeFarmHarvestingMap = new HashMap<>();
    }

    public static TreeFarmHarvestingRegistry getInstance() {
        return INSTANCE;
    }

    public ChanceDrop[] getResult(ItemMeta item) {
        return treeFarmHarvestingMap.get(item);
    }

    public void addRecipe(ItemMeta item, ChanceDrop[] chanceDrops) {
        treeFarmHarvestingMap.put(item, chanceDrops);
    }

    public ArrayList<HarvestingRecipe> getRecipes() {
        ArrayList<HarvestingRecipe> harvestingRequirements = new ArrayList<>();
        ArrayList<ItemMeta> inputs = new ArrayList<>();
        ArrayList<ChanceDrop[]> outputs = new ArrayList<>();
        for (ItemMeta keyRecord : treeFarmHarvestingMap.keySet()) {
            inputs.add(keyRecord);
            outputs.add(getResult(keyRecord));
        }
        for (int i = 0; i < inputs.size(); i++) {
            if (i >= outputs.size()) break;
            harvestingRequirements.add(new HarvestingRecipe(inputs.get(i), outputs.get(i)));
        }
        return harvestingRequirements;
    }
}
