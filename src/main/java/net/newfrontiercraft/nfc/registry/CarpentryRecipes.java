package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.wrappers.CarpentryRecipe;

import java.util.*;

public class CarpentryRecipes {
    private static final CarpentryRecipes INSTANCE = new CarpentryRecipes();
    private Map carpentryList;

    private CarpentryRecipes() {
        carpentryList = new HashMap();
    }

    public static CarpentryRecipes carpentry() {
        return INSTANCE;
    }

    public ItemStack[] getCarpentryResult(int id, int meta) {
        return (ItemStack[]) carpentryList.get(Arrays.asList(id, meta));
    }

    public void addCarpentry(int id, int meta, ItemStack[] itemStack) {
        carpentryList.put(Arrays.asList(id, meta), itemStack);
    }

    public void addCarpentry(int id, int lowerMeta, int upperMeta, ItemStack[] itemStack) {
        for (int i = lowerMeta; i <= upperMeta; i++) {
            carpentryList.put(Arrays.asList(id, i), itemStack);
        }
    }

    public ArrayList<CarpentryRecipe> getRecipes() {
        ArrayList<CarpentryRecipe> carpentryRecipes = new ArrayList<>();
        ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ArrayList<ItemStack>> outputs = new ArrayList<>();
        for (Object obj : carpentryList.keySet()) {
            List<Integer> inputArray = (List<Integer>) obj;
            ItemStack input = new ItemStack(inputArray.get(0), 1, inputArray.get(1));
            inputs.add(input);
            ItemStack[] outputArray = getCarpentryResult(inputArray.get(0), inputArray.get(1));
            ArrayList<ItemStack> outputArrayList = new ArrayList<>(Arrays.asList(outputArray));
            outputs.add(outputArrayList);
        }
        for (int i = 0; i < inputs.size(); i++) {
            if (i >= outputs.size()) break;
            carpentryRecipes.add(new CarpentryRecipe(inputs.get(i), outputs.get(i)));
        }
        return carpentryRecipes;
    }
}
