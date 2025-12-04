package net.newfrontiercraft.nfc.registry;

import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.utils.PlantingRequirement;
import net.newfrontiercraft.nfc.wrappers.PlantingRequirementRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TreeFarmPlantingRegistry {
    private static final TreeFarmPlantingRegistry INSTANCE = new TreeFarmPlantingRegistry();
    private final Map<ItemMeta, PlantingRequirement> plantingRequirementMap;

    private TreeFarmPlantingRegistry() {
        plantingRequirementMap = new HashMap<>();
    }

    public static TreeFarmPlantingRegistry getInstance() {
        return INSTANCE;
    }

    public PlantingRequirement getResult(ItemMeta item) {
        return plantingRequirementMap.get(item);
    }

    public void addRecipe(ItemMeta item, PlantingRequirement plantingRequirement) {
        plantingRequirementMap.put(item, plantingRequirement);
    }

    public ArrayList<PlantingRequirementRecipe> getRecipes() {
        ArrayList<PlantingRequirementRecipe> plantingRequirements = new ArrayList<>();
        ArrayList<ItemMeta> inputs = new ArrayList<>();
        ArrayList<PlantingRequirement> outputs = new ArrayList<>();
        for (ItemMeta keyRecord : plantingRequirementMap.keySet()) {
            inputs.add(keyRecord);
            outputs.add(getResult(keyRecord));
        }
        for (int i = 0; i < inputs.size(); i++) {
            if (i >= outputs.size()) break;
            plantingRequirements.add(new PlantingRequirementRecipe(inputs.get(i), outputs.get(i)));
        }
        return plantingRequirements;
    }
}
