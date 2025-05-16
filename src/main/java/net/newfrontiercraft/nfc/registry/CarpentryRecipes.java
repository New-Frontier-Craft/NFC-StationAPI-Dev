package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.wrappers.CarpentryRecipe;
import net.newfrontiercraft.nfc.wrappers.IdMeta;

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

    public ItemStack[] getCarpentryResult(Identifier id, int meta) {
        return (ItemStack[]) carpentryList.get(new IdMeta(id, meta));
    }

    public void addCarpentry(Identifier id, int meta, ItemStack[] itemStack) {
        carpentryList.put(new IdMeta(id, meta), itemStack);
    }

    public void addCarpentry(Identifier id, int lowerMeta, int upperMeta, ItemStack[] itemStack) {
        for (int i = lowerMeta; i <= upperMeta; i++) {
            carpentryList.put(new IdMeta(id, i), itemStack);
        }
    }

    public ArrayList<CarpentryRecipe> getRecipes() {
        ArrayList<CarpentryRecipe> carpentryRecipes = new ArrayList<>();
        ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ArrayList<ItemStack>> outputs = new ArrayList<>();
        for (Object obj : carpentryList.keySet()) {
            IdMeta keyRecord = (IdMeta) obj;
            ItemStack input = new ItemStack(ItemRegistry.INSTANCE.get(keyRecord.id()), 1, keyRecord.meta());
            inputs.add(input);
            ItemStack[] outputArray = getCarpentryResult(keyRecord.id(), keyRecord.meta());
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
