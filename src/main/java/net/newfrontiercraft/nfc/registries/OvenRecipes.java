package net.newfrontiercraft.nfc.registries;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OvenRecipes implements OvenRecipe {

    public OvenRecipes(ItemStack itemstack, List list, int time) {
        recipeOutput = itemstack;
        recipeItems = list;
        recipeTime = time;
    }

    public ItemStack getRecipeOutput() {
        return recipeOutput;
    }

    public int getTime(){
        return recipeTime;
    }

    public boolean matches(ItemStack[] itemstacks) {
        ArrayList arraylist = new ArrayList(recipeItems);
        int i = 0;
        do {
            if (i >= 3) {
                break;
            }
            for (int j = 0; j < 3; j++) {
                ItemStack itemstack = itemstacks[j + (i*3)];
                if (itemstack == null) {
                    continue;
                }
                boolean flag = false;
                Iterator iterator = arraylist.iterator();
                do {
                    if (!iterator.hasNext()) {
                        break;
                    }
                    ItemStack itemstack1 = (ItemStack) iterator.next();
                    if (itemstack.itemId != itemstack1.itemId
                            || itemstack1.getDamage() != -1
                            && (itemstack.getDamage() != itemstack1
                            .getDamage() || itemstack.getDamage() == -1)) {
                        continue;
                    }
                    flag = true;
                    arraylist.remove(itemstack1);
                    break;
                } while (true);
                if (!flag) {
                    return false;
                }
            }

            i++;
        } while (true);
        return arraylist.isEmpty();
    }

    public ItemStack getCraftingResult(ItemStack[] itemstacks) {
        return recipeOutput.copy();
    }

    public int getRecipeSize() {
        return recipeItems.size();
    }

    private final ItemStack recipeOutput;
    private final List recipeItems;
    private final int recipeTime;
}
