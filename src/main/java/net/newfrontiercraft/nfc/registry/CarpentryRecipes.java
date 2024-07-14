package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
}
