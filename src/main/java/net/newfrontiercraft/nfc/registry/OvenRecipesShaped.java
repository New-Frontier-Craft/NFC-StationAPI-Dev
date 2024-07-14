package net.newfrontiercraft.nfc.registry;

import net.minecraft.item.ItemStack;

public class OvenRecipesShaped implements OvenRecipe
{

    public OvenRecipesShaped(int i, int j, ItemStack itemStacks[], ItemStack itemInstance, int time)
    {
        recipeOutputItemID = itemInstance.itemId;
        recipeWidth = i;
        recipeHeight = j;
        recipeItems = itemStacks;
        recipeOutput = itemInstance;
        recipeTime = time;
    }

    public ItemStack getRecipeOutput()
    {
        return recipeOutput;
    }

    public int getTime(){
        return recipeTime;
    }

    public boolean matches(ItemStack[] itemStacks)
    {
        for(int i = 0; i <= 3 - recipeWidth; i++)
        {
            for(int j = 0; j <= 3 - recipeHeight; j++)
            {
                if(func_21137_a(itemStacks, i, j, true))
                {
                    return true;
                }
                if(func_21137_a(itemStacks, i, j, false))
                {
                    return true;
                }
            }

        }

        return false;
    }

    private boolean func_21137_a(ItemStack[] itemInstances, int i, int j, boolean flag)
    {
        for(int k = 0; k < 3; k++)
        {
            for(int l = 0; l < 3; l++)
            {
                int i1 = k - i;
                int j1 = l - j;
                ItemStack itemInstance = null;
                if(i1 >= 0 && j1 >= 0 && i1 < recipeWidth && j1 < recipeHeight)
                {
                    if(flag)
                    {
                        itemInstance = recipeItems[(recipeWidth - i1 - 1) + j1 * recipeWidth];
                    } else
                    {
                        itemInstance = recipeItems[i1 + j1 * recipeWidth];
                    }
                }
                ItemStack itemInstance1 = itemInstances[k + (l*3)];
                if(itemInstance1 == null && itemInstance == null)
                {
                    continue;
                }
                if(itemInstance1 == null && itemInstance != null || itemInstance1 != null && itemInstance == null)
                {
                    return false;
                }
                if(itemInstance.itemId != itemInstance1.itemId)
                {
                    return false;
                }
                if(itemInstance.getDamage() != -1 && itemInstance.getDamage() != itemInstance1.getDamage())
                {
                    return false;
                }
            }

        }

        return true;
    }

    public ItemStack getCraftingResult(ItemStack[] itemInstances)
    {
        return new ItemStack(recipeOutput.itemId, recipeOutput.count, recipeOutput.getDamage());
    }

    public int getRecipeSize()
    {
        return recipeWidth * recipeHeight;
    }

    private int recipeWidth;
    private int recipeHeight;
    private ItemStack recipeItems[];
    private ItemStack recipeOutput;
    private int recipeTime;
    public final int recipeOutputItemID;
}

