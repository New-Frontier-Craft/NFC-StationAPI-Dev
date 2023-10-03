package net.newfrontiercraft.nfc.registries;

import net.minecraft.item.ItemInstance;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OvenRecipes implements IRecipeOven {

    public OvenRecipes(ItemInstance itemstack, List list, int time) {
        recipeOutput = itemstack;
        recipeItems = list;
        recipeTime = time;
    }

    public ItemInstance getRecipeOutput() {
        return recipeOutput;
    }

    public int getTime(){
        return recipeTime;
    }

    public boolean matches(ItemInstance[] itemstacks) {
        ArrayList arraylist = new ArrayList(recipeItems);
        int i = 0;
        do {
            if (i >= 3) {
                break;
            }
            for (int j = 0; j < 3; j++) {
                ItemInstance itemstack = itemstacks[j + (i*3)];
                if (itemstack == null) {
                    continue;
                }
                boolean flag = false;
                Iterator iterator = arraylist.iterator();
                do {
                    if (!iterator.hasNext()) {
                        break;
                    }
                    ItemInstance itemstack1 = (ItemInstance) iterator.next();
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

    public ItemInstance getCraftingResult(ItemInstance[] itemstacks) {
        return recipeOutput.copy();
    }

    public int getRecipeSize() {
        return recipeItems.size();
    }

    private final ItemInstance recipeOutput;
    private final List recipeItems;
    private final int recipeTime;
}
