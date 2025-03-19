package net.newfrontiercraft.nfc.utils;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.SmeltingRecipeManager;

import java.util.List;
import java.util.Map;

public class RecipeRemover {
    @SuppressWarnings({"unchecked"})
    public static void removeRecipe(Item item, int meta, boolean onlyRemoveFirst) {
        List<CraftingRecipe> recipes = CraftingRecipeManager.getInstance().getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            CraftingRecipe recipe = recipes.get(i);
            if (recipe.getOutput().itemId == item.id) {
                //noinspection SimplifiableConditionalExpression
                if ((meta == -1) ? true : (recipe.getOutput().getDamage() == meta)) {
                    recipes.remove(i);
                    i--;
                    if (onlyRemoveFirst) {
                        return;
                    }
                }
            }
        }
    }

    public static void removeRecipe(Item item, boolean onlyRemoveFirst) {
        removeRecipe(item, -1, onlyRemoveFirst);
    }

    public static void removeRecipe(Item item) {
        removeRecipe(item, -1, false);
    }

    @SuppressWarnings({"unchecked"})
    public static void removeSmeltingRecipe(int id) {
        Map<Integer, ItemStack> recipes = SmeltingRecipeManager.getInstance().getRecipes();
        recipes.remove(id);
    }

    public static void removeRecipe(Block block) {
        List<CraftingRecipe> recipes = CraftingRecipeManager.getInstance().getRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            CraftingRecipe recipe = recipes.get(i);
            if (recipe.getOutput().itemId == block.id) {
                recipes.remove(i);
                i--;
            }
        }
    }
}
