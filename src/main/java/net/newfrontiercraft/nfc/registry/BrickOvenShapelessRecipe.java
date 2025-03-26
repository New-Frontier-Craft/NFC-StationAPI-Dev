package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BrickOvenShapelessRecipe implements BrickOvenRecipe {
    private final ItemStack output;
    private final List input;
    private final int time;
    public BrickOvenShapelessRecipe(ItemStack output, List input, int time) {
        this.output = output;
        this.input = input;
        this.time = time;
    }

    @Override
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public boolean matches(ItemStack[] craftingItems) {
        ArrayList arraylist = new ArrayList(input);
        int i = 0;
        do {
            if (i >= 3) {
                break;
            }
            for (int j = 0; j < 3; j++) {
                ItemStack itemstack = craftingItems[j + (i*3)];
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

    @Override
    public ItemStack craft(ItemStack[] craftingItems) {
        return output.copy();
    }

    @Override
    public int getSize() {
        return input.size();
    }

    @Override
    public List getInput() {
        return input;
    }
}
