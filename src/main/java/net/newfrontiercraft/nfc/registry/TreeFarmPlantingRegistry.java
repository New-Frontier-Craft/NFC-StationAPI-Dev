package net.newfrontiercraft.nfc.registry;

import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.utils.PlantingRequirement;

import java.util.HashMap;
import java.util.Map;

public class TreeFarmPlantingRegistry {
    private static final TreeFarmPlantingRegistry INSTANCE = new TreeFarmPlantingRegistry();
    private final Map<ItemMeta, PlantingRequirement> cokeOvenMap;

    private TreeFarmPlantingRegistry() {
        cokeOvenMap = new HashMap<>();
    }

    public static TreeFarmPlantingRegistry getInstance() {
        return INSTANCE;
    }

    public PlantingRequirement getResult(ItemMeta item) {
        return cokeOvenMap.get(item);
    }

    public void addRecipe(ItemMeta item, PlantingRequirement plantingRequirement) {
        cokeOvenMap.put(item, plantingRequirement);
    }

//    TODO: AMI tab
//    public ArrayList<CokeOvenRecipe> getRecipes() {
//        ArrayList<CokeOvenRecipe> plantingRequirements = new ArrayList<>();
//        ArrayList<ItemMeta> inputs = new ArrayList<>();
//        ArrayList<PlantingRequirement> outputs = new ArrayList<>();
//        for (ItemMeta keyRecord : cokeOvenMap.keySet()) {
//            inputs.add(keyRecord);
//            outputs.add(getResult(keyRecord));
//        }
//        for (int i = 0; i < inputs.size(); i++) {
//            if (i >= outputs.size()) break;
//            plantingRequirements.add(new CokeOvenRecipe(new ItemStack(inputs.get(i).item(), 1, inputs.get(i).meta()), outputs.get(i)));
//        }
//        return plantingRequirements;
//    }
}
