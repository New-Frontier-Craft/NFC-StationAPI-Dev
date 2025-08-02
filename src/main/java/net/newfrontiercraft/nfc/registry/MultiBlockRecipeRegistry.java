package net.newfrontiercraft.nfc.registry;

import java.util.ArrayList;
import java.util.List;

public class MultiBlockRecipeRegistry {
    public static MultiBlockRecipeRegistry INSTANCE = new MultiBlockRecipeRegistry();
    private final List<MultiBlockRecipe> recipes;

    public MultiBlockRecipeRegistry(){
        recipes = new ArrayList<>();
    }

    public List<MultiBlockRecipe> getRecipes(){
        return recipes;
    }

    public void addMultiblockRecipe(List<String[]> layers, List<BlockPatternEntry> blockPatterns){
        recipes.add(new MultiBlockRecipe(layers, blockPatterns));
    }
}
