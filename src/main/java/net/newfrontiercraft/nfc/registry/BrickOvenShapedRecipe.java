package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;

public class BrickOvenShapedRecipe implements BrickOvenRecipe {
    private int width;
    private int height;
    private ItemStack input[];
    private ItemStack output;
    private int time;
    public final int outputId;

    public BrickOvenShapedRecipe(int width, int height, ItemStack[] input, ItemStack output, int time) {
        this.outputId = output.itemId;
        this.width = width;
        this.height = height;
        this.input = input;
        this.output = output;
        this.time = time;
    }

    @Override
    public ItemStack getOutput()
    {
        return output;
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public boolean matches(ItemStack[] craftingItems) {
        for(int i = 0; i <= 3 - width; i++) {
            for(int j = 0; j <= 3 - height; j++) {
                if(matchesPattern(craftingItems, i, j, true)) {
                    return true;
                }
                if(matchesPattern(craftingItems, i, j, false)) {
                    return true;
                }
            }

        }

        return false;
    }

    private boolean matchesPattern(ItemStack[] craftingItems, int offsetX, int offsetY, boolean flipped) {
        for(int k = 0; k < 3; k++) {
            for(int l = 0; l < 3; l++) {
                int i1 = k - offsetX;
                int j1 = l - offsetY;
                ItemStack itemInstance = null;
                if(i1 >= 0 && j1 >= 0 && i1 < width && j1 < height) {
                    if(flipped) {
                        itemInstance = input[(width - i1 - 1) + j1 * width];
                    } else {
                        itemInstance = input[i1 + j1 * width];
                    }
                }
                ItemStack itemInstance1 = craftingItems[k + (l*3)];
                if(itemInstance1 == null && itemInstance == null) {
                    continue;
                }
                if(itemInstance1 == null && itemInstance != null || itemInstance1 != null && itemInstance == null) {
                    return false;
                }
                if(itemInstance.itemId != itemInstance1.itemId) {
                    return false;
                }
                if(itemInstance.getDamage() != -1 && itemInstance.getDamage() != itemInstance1.getDamage()) {
                    return false;
                }
            }

        }

        return true;
    }

    @Override
    public ItemStack craft(ItemStack[] craftingItems)
    {
        return new ItemStack(output.itemId, output.count, output.getDamage());
    }

    @Override
    public int getSize()
    {
        return width * height;
    }

    @Override
    public List getInput() {
        return Arrays.stream(input).toList();
    }
}

