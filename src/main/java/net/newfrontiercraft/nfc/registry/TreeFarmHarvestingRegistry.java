package net.newfrontiercraft.nfc.registry;

import net.newfrontiercraft.nfc.utils.ChanceDrop;
import net.newfrontiercraft.nfc.utils.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class TreeFarmHarvestingRegistry {
    private static final TreeFarmHarvestingRegistry INSTANCE = new TreeFarmHarvestingRegistry();
    private final Map<ItemMeta, ChanceDrop[]> cokeOvenMap;

    private TreeFarmHarvestingRegistry() {
        cokeOvenMap = new HashMap<>();
    }

    public static TreeFarmHarvestingRegistry getInstance() {
        return INSTANCE;
    }

    public ChanceDrop[] getResult(ItemMeta item) {
        return cokeOvenMap.get(item);
    }

    public void addRecipe(ItemMeta item, ChanceDrop[] chanceDrops) {
        cokeOvenMap.put(item, chanceDrops);
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
