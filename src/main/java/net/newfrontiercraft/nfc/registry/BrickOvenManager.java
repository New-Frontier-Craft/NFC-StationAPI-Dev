package net.newfrontiercraft.nfc.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BrickOvenManager {

    public BrickOvenManager() {
        recipes = new ArrayList();

        //Metal Alloys
        addShapelessOvenRecipe(new ItemStack(ItemListener.brassIngot, 6), new Object[] {
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new	ItemStack(ItemListener.zincIngot, 1),
                new ItemStack(ItemListener.zincIngot, 1),
                new ItemStack(ItemListener.zincIngot, 1)}, 1600);

        addShapelessOvenRecipe(new ItemStack(ItemListener.bronzeIngot, 6), new Object[] {
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new	ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.tinIngot, 1)}, 1600);

        addShapelessOvenRecipe(new ItemStack(ItemListener.cupronickelIngot, 6), new Object[] {
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new ItemStack(ItemListener.copperIngot, 1),
                new	ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.nickelIngot, 1)}, 1600);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        // Experimental titanium steel recipes
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        // Experimental tungsten steel recipes
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.tungstenIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.tungstenIngot, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.tungstenIngot, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.tungstenIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        // Experimental titanium-tungsten steel recipes
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new ItemStack(ItemListener.tungstenIngot, 1),
                new	ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new	ItemStack(ItemListener.tungstenIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new	ItemStack(ItemListener.tungstenIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 8), new Object[] {
                new ItemStack(ItemListener.anthracite, 1),
                new ItemStack(ItemListener.chromeIngot, 1),
                new ItemStack(ItemListener.nickelIngot, 1),
                new ItemStack(ItemListener.titaniumIngot, 1),
                new ItemStack(ItemListener.tungstenIngot, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1),
                new ItemStack(Item.IRON_INGOT, 1)}, 6400);

        // Platinum enhanced smelting
        addShapelessOvenRecipe(new ItemStack(ItemListener.osmiumIngot, 5), new Object[] {
                new ItemStack(BlockListener.osmiumOre, 1),
                new ItemStack(BlockListener.osmiumOre, 1),
                new ItemStack(BlockListener.osmiumOre, 1),
                new ItemStack(BlockListener.osmiumOre, 1),
                new ItemStack(ItemListener.platinumIngot, 1)}, 6400);

        addShapelessOvenRecipe(new ItemStack(ItemListener.titaniumIngot, 5), new Object[] {
                new ItemStack(BlockListener.titaniumOre, 1),
                new ItemStack(BlockListener.titaniumOre, 1),
                new ItemStack(BlockListener.titaniumOre, 1),
                new ItemStack(BlockListener.titaniumOre, 1),
                new ItemStack(ItemListener.platinumIngot, 1)}, 4800);

        addShapelessOvenRecipe(new ItemStack(ItemListener.tungstenIngot, 5), new Object[] {
                new ItemStack(BlockListener.tungstenOre, 1),
                new ItemStack(BlockListener.tungstenOre, 1),
                new ItemStack(BlockListener.tungstenOre, 1),
                new ItemStack(BlockListener.tungstenOre, 1),
                new ItemStack(ItemListener.platinumIngot, 1)}, 4800);

        // Experimental boron recipes
        addShapelessOvenRecipe(new ItemStack(ItemListener.magnetiteIngot, 5), new Object[] {
                new ItemStack(BlockListener.magnetiteOre, 1),
                new ItemStack(BlockListener.magnetiteOre, 1),
                new ItemStack(BlockListener.magnetiteOre, 1),
                new ItemStack(BlockListener.magnetiteOre, 1),
                new ItemStack(ItemListener.boronIngot, 1)}, 4800);

        addShapelessOvenRecipe(new ItemStack(ItemListener.cobaltIngot, 5), new Object[] {
                new ItemStack(BlockListener.cobaltOre, 1),
                new ItemStack(BlockListener.cobaltOre, 1),
                new ItemStack(BlockListener.cobaltOre, 1),
                new ItemStack(BlockListener.cobaltOre, 1),
                new ItemStack(ItemListener.boronIngot, 1)}, 4800);

        //Smelting
        addShapelessOvenRecipe(new ItemStack(ItemListener.tungstenIngot, 1), new Object[] {new ItemStack(BlockListener.tungstenOre, 1)}, 200);
        addShapelessOvenRecipe(new ItemStack(ItemListener.titaniumIngot, 1), new Object[] {new ItemStack(BlockListener.titaniumOre, 1)}, 200);
        addShapelessOvenRecipe(new ItemStack(ItemListener.osmiumIngot, 1), new Object[] {new ItemStack(BlockListener.osmiumOre, 1)}, 200);

//		Object[][] oreRecipes;
//
//	    oreRecipes = new Object[][] {
//	    	{Block.oreCoal, ItemListener.anthraciteore, Block.oreIron, Block.oreGold, Block.oreDiamond, ItemListener.aluminumore, ItemListener.bismuthore, ItemListener.boronore, ItemListener.chromeore, BlockListener.cobaltOre, ItemListener.copperore, ItemListener.emeraldore, ItemListener.nickelore, ItemListener.platinumore, ItemListener.rubyore, ItemListener.sapphireore, ItemListener.siliconore, ItemListener.silverore, ItemListener.tinore, ItemListener.leadore, BlockListener.titaniumOre, BlockListener.tungstenOre, ItemListener.zincore, BlockListener.osmiumOre, ItemListener.onyxore, ItemListener.uranite, ItemListener.netheruraninite, ItemListener.nethergoldore, ItemListener.nethercoalore, BlockListener.magnetiteOre},
//	    	{Item.coal, ItemListener.anthracite, Item.IRON_INGOT, Item.ingotGold, Item.diamond, ItemListener.aluminum, ItemListener.bismuth, ItemListener.boronIngot, ItemListener.chromeIngot, ItemListener.cobalt, ItemListener.copper, ItemListener.emerald, ItemListener.nickelIngot, ItemListener.platinumIngot, ItemListener.ruby, ItemListener.sapphire, ItemListener.silicon, ItemListener.silver, ItemListener.tin, ItemListener.lead, ItemListener.titaniumIngot, ItemListener.tungstenIngot, ItemListener.zinc, ItemListener.osmiumIngot, ItemListener.onyx, ItemListener.uranium, ItemListener.uranium, Item.ingotGold, ItemListener.nethercoal, ItemListener.magnet}
//	    };
//
//	    for (int i = 0; i < oreRecipes[0].length; ++i) {
//            Block input = (Block)oreRecipes[0][i];
//            Item output = (Item)oreRecipes[1][i];
//
//            //1
//            addOvenRecipe(new ItemStack(output, 1), new Object[] {
//            		new ItemStack(input, 1)}, 200);
//
//            //2
//            addOvenRecipe(new ItemStack(output, 2), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 400);
//
//            //3
//            addOvenRecipe(new ItemStack(output, 3), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 600);
//
//            //4
//            addOvenRecipe(new ItemStack(output, 4), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 800);
//
//            //5
//            addOvenRecipe(new ItemStack(output, 5), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 1000);
//
//            //6
//            addOvenRecipe(new ItemStack(output, 6), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 1200);
//
//            //7
//            addOvenRecipe(new ItemStack(output, 7), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 1400);
//
//            //8
//            addOvenRecipe(new ItemStack(output, 8), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 1600);
//
//            //9
//            addOvenRecipe(new ItemStack(output, 9), new Object[] {
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1),
//            		new ItemStack(input, 1)}, 1800);
//	    }


        //Chain Armor Melting
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 1), new Object[] {new ItemStack(Item.CHAIN_HELMET, 1, -1)}, 200);
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 1), new Object[] {new ItemStack(Item.CHAIN_CHESTPLATE, 1, -1)}, 200);
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 1), new Object[] {new ItemStack(Item.CHAIN_LEGGINGS, 1, -1)}, 200);
        addShapelessOvenRecipe(new ItemStack(ItemListener.steelIngot, 1), new Object[] {new ItemStack(Item.CHAIN_BOOTS, 1, -1)}, 200);

        //Bricks
        addShapelessOvenRecipe(new ItemStack(BlockListener.osmiumBricks, 8), new Object[] {
                new ItemStack(ItemListener.osmiumIngot, 1),
                new ItemStack(BlockListener.firedBricks, 1),
                new ItemStack(BlockListener.firedBricks, 1),
                new ItemStack(BlockListener.firedBricks, 1),
                new	ItemStack(BlockListener.firedBricks, 1),
                new ItemStack(BlockListener.firedBricks, 1),
                new ItemStack(BlockListener.firedBricks, 1),
                new ItemStack(BlockListener.firedBricks, 1),
                new ItemStack(BlockListener.firedBricks, 1)}, 6400);

        //Stained Glass
        int o = 15;
        for (int i = 0; i < 16; i++) {
            addShapelessOvenRecipe(new ItemStack(BlockListener.stainedGlass, 8, i), new Object[] {
                    new ItemStack(Item.DYE, 1, o),
                    new ItemStack(Block.GLASS, 1, 0), new ItemStack(Block.GLASS, 1, 0),
                    new ItemStack(Block.GLASS, 1, 0), new ItemStack(Block.GLASS, 1, 0),
                    new ItemStack(Block.GLASS, 1, 0), new ItemStack(Block.GLASS, 1, 0),
                    new ItemStack(Block.GLASS, 1, 0), new ItemStack(Block.GLASS, 1, 0)
            }, 100);
            o--;
        }
        
        /*
        //Food
        addShapedOvenRecipe(new ItemStack(Item.cake, 1), new Object[] {
                "AAA", "BEB", "CCC",
                Character.valueOf('A'), Item.bucketMilk,
                Character.valueOf('B'), Item.sugar,
                Character.valueOf('C'), Item.wheat,
                Character.valueOf('E'), Item.egg}, 200);

        addOvenRecipe(new ItemStack(Item.cake), new Object[] {new ItemStack(Item.cake, 1, 1)}, 200);

        addShapedOvenRecipe(new ItemStack(ItemListener.pizza, 1), new Object[] {
                "CCC", "WWW",
                Character.valueOf('C'), ItemListener.cheese,
                Character.valueOf('W'), Item.wheat}, 200);

        addOvenRecipe(new ItemStack(ItemListener.pizza), new Object[] {new ItemStack(ItemListener.pizza, 1, 1)}, 200);

        Collections.sort(recipes, new RecipeSorterOven(this));

         */
    }

    void addShapedOvenRecipe(ItemStack itemStack, Object[] aobj, int time)
    {
        StringBuilder s = new StringBuilder();
        int i = 0;
        int j = 0;
        int k = 0;
        if(aobj[i] instanceof String[])
        {
            String[] as = (String[])aobj[i++];
            for(int l = 0; l < as.length; l++) {
                String s2 = as[l];
                k++;
                j = s2.length();
                s.append(s2);
            }

        } else
        {
            while(aobj[i] instanceof String)
            {
                String s1 = (String)aobj[i++];
                k++;
                j = s1.length();
                s = new StringBuilder(s + s1);
            }
        }
        HashMap hashmap = new HashMap();
        for(; i < aobj.length; i += 2)
        {
            Character character = (Character)aobj[i];
            ItemStack ItemStack1 = null;
            if(aobj[i + 1] instanceof Item)
            {
                ItemStack1 = new ItemStack((Item) aobj[i + 1]);
            } else
            if(aobj[i + 1] instanceof Block)
            {
                ItemStack1 = new ItemStack((Block) aobj[i + 1], 1, -1);
            } else
            if(aobj[i + 1] instanceof ItemStack)
            {
                ItemStack1 = (ItemStack)aobj[i + 1];
            }
            hashmap.put(character, ItemStack1);
        }

        ItemStack[] itemStacks = new ItemStack[j * k];
        for(int i1 = 0; i1 < j * k; i1++)
        {
            char c = s.charAt(i1);
            if(hashmap.containsKey(c))
            {
                itemStacks[i1] = ((ItemStack)hashmap.get(c)).copy();
            } else
            {
                itemStacks[i1] = null;
            }
        }
        recipes.add(new BrickOvenShapedRecipe(j, k, itemStacks, itemStack, time));
    }

    void addShapelessOvenRecipe(ItemStack itemStack, Object[] aobj, int time) {
        ArrayList arraylist = new ArrayList();
        Object aobj1[] = aobj;
        int i = aobj1.length;
        for (int j = 0; j < i; j++) {
            Object obj = aobj1[j];
            if (obj instanceof ItemStack) {
                arraylist.add(((ItemStack) obj).copy());
                continue;
            }
            if (obj instanceof Item) {
                arraylist.add(new ItemStack((Item) obj));
                continue;
            }
            if (obj instanceof Block) {
                arraylist.add(new ItemStack((Block) obj));
            } else {
                throw new RuntimeException("Invalid shapeless recipe!");
            }
        }
        recipes.add(new BrickOvenShapelessRecipe(itemStack, arraylist, time));
    }

    public ItemStack findMatchingRecipe(ItemStack[] ItemStacks, BrickOvenBlockEntity joe) {
        //removed a lot of extra stuff
        for (int i = 0; i < recipes.size(); i++) {
            BrickOvenRecipe var12 = (BrickOvenRecipe) recipes.get(i);
            if (var12.matches(ItemStacks)) {
                joe.setTime(var12.getTime());
                return var12.craft(ItemStacks);
            }
        }

        return null;
    }

    public ArrayList getShapelessRecipes() {
        ArrayList shapelessRecipes = new ArrayList();
        for (Object recipe : recipes) {
            if (recipe instanceof BrickOvenShapelessRecipe) {
                shapelessRecipes.add(recipe);
            }
        }
        return shapelessRecipes;
    }

    public ArrayList getShapedRecipes() {
        ArrayList shapedRecipes = new ArrayList();
        for (Object recipe : recipes) {
            if (recipe instanceof BrickOvenShapedRecipe) {
                shapedRecipes.add(recipe);
            }
        }
        return shapedRecipes;
    }

    public List getRecipeList() {
        return recipes;
    }

    public static final BrickOvenManager getInstance() {
        return instance;
    }

    private static final BrickOvenManager instance = new BrickOvenManager();
    private List recipes;

}

