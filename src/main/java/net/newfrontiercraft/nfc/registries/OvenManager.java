package net.newfrontiercraft.nfc.registries;

import net.minecraft.block.BlockBase;
import net.minecraft.entity.Item;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.tileentities.TileEntityBrickOven;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class OvenManager {

    public OvenManager() {
        recipes = new ArrayList();

        //Metal Alloys
        addOvenRecipe(new ItemInstance(ItemListener.brassIngot, 6), new Object[] {
                new ItemInstance(ItemListener.copperIngot, 1),
                new ItemInstance(ItemListener.copperIngot, 1),
                new ItemInstance(ItemListener.copperIngot, 1),
                new	ItemInstance(ItemListener.zincIngot, 1),
                new ItemInstance(ItemListener.zincIngot, 1),
                new ItemInstance(ItemListener.zincIngot, 1)}, 1600);

        addOvenRecipe(new ItemInstance(ItemListener.bronzeIngot, 6), new Object[] {
                new ItemInstance(ItemListener.copperIngot, 1),
                new ItemInstance(ItemListener.copperIngot, 1),
                new ItemInstance(ItemListener.copperIngot, 1),
                new	ItemInstance(ItemListener.copperIngot, 1),
                new ItemInstance(ItemListener.copperIngot, 1),
                new ItemInstance(ItemListener.tinIngot, 1)}, 1600);

        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 8), new Object[] {
                new ItemInstance(ItemListener.anthracite, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new	ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1)}, 6400);

        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 8), new Object[] {
                new ItemInstance(ItemListener.anthracite, 1),
                new ItemInstance(ItemListener.chromeIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new	ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1)}, 6400);

        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 8), new Object[] {
                new ItemInstance(ItemListener.anthracite, 1),
                new ItemInstance(ItemListener.nickelIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new	ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1)}, 6400);

        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 8), new Object[] {
                new ItemInstance(ItemListener.anthracite, 1),
                new ItemInstance(ItemListener.chromeIngot, 1),
                new ItemInstance(ItemListener.nickelIngot, 1),
                new	ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1),
                new ItemInstance(ItemBase.ironIngot, 1)}, 6400);

        //Smelting
        addOvenRecipe(new ItemInstance(ItemListener.tungstenIngot, 1), new Object[] {new ItemInstance(BlockListener.tungstenOre, 1)}, 200);
        addOvenRecipe(new ItemInstance(ItemListener.titaniumIngot, 1), new Object[] {new ItemInstance(BlockListener.titaniumOre, 1)}, 200);
        addOvenRecipe(new ItemInstance(ItemListener.osmiumIngot, 1), new Object[] {new ItemInstance(BlockListener.osmiumOre, 1)}, 200);

//		Object[][] oreRecipes;
//
//	    oreRecipes = new Object[][] {
//	    	{Block.oreCoal, ItemListener.anthraciteore, Block.oreIron, Block.oreGold, Block.oreDiamond, ItemListener.aluminumore, ItemListener.bismuthore, ItemListener.boronore, ItemListener.chromeore, ItemListener.cobaltore, ItemListener.copperore, ItemListener.emeraldore, ItemListener.nickelore, ItemListener.platinumore, ItemListener.rubyore, ItemListener.sapphireore, ItemListener.siliconore, ItemListener.silverore, ItemListener.tinore, ItemListener.leadore, ItemListener.titaniumore, ItemListener.tungstenore, ItemListener.zincore, ItemListener.osmiumore, ItemListener.onyxore, ItemListener.uranite, ItemListener.netheruraninite, ItemListener.nethergoldore, ItemListener.nethercoalore, ItemListener.magnetiteore},
//	    	{Item.coal, ItemListener.anthracite, Item.ingotIron, Item.ingotGold, Item.diamond, ItemListener.aluminum, ItemListener.bismuth, ItemListener.boron, ItemListener.chrome, ItemListener.cobalt, ItemListener.copper, ItemListener.emerald, ItemListener.nickel, ItemListener.platinum, ItemListener.ruby, ItemListener.sapphire, ItemListener.silicon, ItemListener.silver, ItemListener.tin, ItemListener.lead, ItemListener.titanium, ItemListener.tungsten, ItemListener.zinc, ItemListener.osmium, ItemListener.onyx, ItemListener.uranium, ItemListener.uranium, Item.ingotGold, ItemListener.nethercoal, ItemListener.magnet}
//	    };
//
//	    for (int i = 0; i < oreRecipes[0].length; ++i) {
//            Block input = (Block)oreRecipes[0][i];
//            Item output = (Item)oreRecipes[1][i];
//
//            //1
//            addOvenRecipe(new ItemInstance(output, 1), new Object[] {
//            		new ItemInstance(input, 1)}, 200);
//
//            //2
//            addOvenRecipe(new ItemInstance(output, 2), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 400);
//
//            //3
//            addOvenRecipe(new ItemInstance(output, 3), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 600);
//
//            //4
//            addOvenRecipe(new ItemInstance(output, 4), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 800);
//
//            //5
//            addOvenRecipe(new ItemInstance(output, 5), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 1000);
//
//            //6
//            addOvenRecipe(new ItemInstance(output, 6), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 1200);
//
//            //7
//            addOvenRecipe(new ItemInstance(output, 7), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 1400);
//
//            //8
//            addOvenRecipe(new ItemInstance(output, 8), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 1600);
//
//            //9
//            addOvenRecipe(new ItemInstance(output, 9), new Object[] {
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1),
//            		new ItemInstance(input, 1)}, 1800);
//	    }


        //Chain Armor Melting
        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 1), new Object[] {new ItemInstance(ItemBase.chainHelmet, 1, -1)}, 200);
        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 1), new Object[] {new ItemInstance(ItemBase.chainChestplate, 1, -1)}, 200);
        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 1), new Object[] {new ItemInstance(ItemBase.chainLeggings, 1, -1)}, 200);
        addOvenRecipe(new ItemInstance(ItemListener.steelIngot, 1), new Object[] {new ItemInstance(ItemBase.chainBoots, 1, -1)}, 200);

        //Bricks
        addOvenRecipe(new ItemInstance(BlockListener.osmiumBricks, 8), new Object[] {
                new ItemInstance(ItemListener.osmiumIngot, 1),
                new ItemInstance(BlockListener.firedBricks, 1),
                new ItemInstance(BlockListener.firedBricks, 1),
                new ItemInstance(BlockListener.firedBricks, 1),
                new	ItemInstance(BlockListener.firedBricks, 1),
                new ItemInstance(BlockListener.firedBricks, 1),
                new ItemInstance(BlockListener.firedBricks, 1),
                new ItemInstance(BlockListener.firedBricks, 1),
                new ItemInstance(BlockListener.firedBricks, 1)}, 6400);

        //Stained Glass
        /*
        int o = 15;
        for (int i = 0; i < 16; i++) {
            addOvenRecipe(new ItemInstance(Block.lockedChest, 8, i), new Object[] {
                    new ItemInstance(Item.dyePowder, 1, o),
                    new ItemInstance(Block.glass, 1, 0), new ItemInstance(Block.glass, 1, 0),
                    new ItemInstance(Block.glass, 1, 0), new ItemInstance(Block.glass, 1, 0),
                    new ItemInstance(Block.glass, 1, 0), new ItemInstance(Block.glass, 1, 0),
                    new ItemInstance(Block.glass, 1, 0), new ItemInstance(Block.glass, 1, 0)
            }, 100);
            o--;
        }

        //Food
        addShapedOvenRecipe(new ItemInstance(Item.cake, 1), new Object[] {
                "AAA", "BEB", "CCC",
                Character.valueOf('A'), Item.bucketMilk,
                Character.valueOf('B'), Item.sugar,
                Character.valueOf('C'), Item.wheat,
                Character.valueOf('E'), Item.egg}, 200);

        addOvenRecipe(new ItemInstance(Item.cake), new Object[] {new ItemInstance(Item.cake, 1, 1)}, 200);

        addShapedOvenRecipe(new ItemInstance(ItemListener.pizza, 1), new Object[] {
                "CCC", "WWW",
                Character.valueOf('C'), ItemListener.cheese,
                Character.valueOf('W'), Item.wheat}, 200);

        addOvenRecipe(new ItemInstance(ItemListener.pizza), new Object[] {new ItemInstance(ItemListener.pizza, 1, 1)}, 200);

        Collections.sort(recipes, new RecipeSorterOven(this));

         */
    }

    void addShapedOvenRecipe(ItemInstance ItemInstance, Object aobj[], int time)
    {
        String s = "";
        int i = 0;
        int j = 0;
        int k = 0;
        if(aobj[i] instanceof String[])
        {
            String as[] = (String[])aobj[i++];
            for(int l = 0; l < as.length; l++)
            {
                String s2 = as[l];
                k++;
                j = s2.length();
                s = (new StringBuilder()).append(s).append(s2).toString();
            }

        } else
        {
            while(aobj[i] instanceof String)
            {
                String s1 = (String)aobj[i++];
                k++;
                j = s1.length();
                s = (new StringBuilder()).append(s).append(s1).toString();
            }
        }
        HashMap hashmap = new HashMap();
        for(; i < aobj.length; i += 2)
        {
            Character character = (Character)aobj[i];
            ItemInstance ItemInstance1 = null;
            if(aobj[i + 1] instanceof Item)
            {
                ItemInstance1 = new ItemInstance((ItemBase) aobj[i + 1]);
            } else
            if(aobj[i + 1] instanceof BlockBase)
            {
                ItemInstance1 = new ItemInstance((BlockBase) aobj[i + 1], 1, -1);
            } else
            if(aobj[i + 1] instanceof ItemInstance)
            {
                ItemInstance1 = (ItemInstance)aobj[i + 1];
            }
            hashmap.put(character, ItemInstance1);
        }

        ItemInstance aItemInstance[] = new ItemInstance[j * k];
        for(int i1 = 0; i1 < j * k; i1++)
        {
            char c = s.charAt(i1);
            if(hashmap.containsKey(Character.valueOf(c)))
            {
                aItemInstance[i1] = ((ItemInstance)hashmap.get(Character.valueOf(c))).copy();
            } else
            {
                aItemInstance[i1] = null;
            }
        }
        recipes.add(new OvenRecipesShaped(j, k, aItemInstance, ItemInstance, time));
    }

    void addOvenRecipe(ItemInstance ItemInstance, Object aobj[], int time) {
        ArrayList arraylist = new ArrayList();
        Object aobj1[] = aobj;
        int i = aobj1.length;
        for (int j = 0; j < i; j++) {
            Object obj = aobj1[j];
            if (obj instanceof ItemInstance) {
                arraylist.add(((ItemInstance) obj).copy());
                continue;
            }
            if (obj instanceof Item) {
                arraylist.add(new ItemInstance((ItemBase) obj));
                continue;
            }
            if (obj instanceof BlockBase) {
                arraylist.add(new ItemInstance((BlockBase) obj));
            } else {
                throw new RuntimeException("Invalid shapeless recipe!");
            }
        }
        recipes.add(new OvenRecipes(ItemInstance, arraylist, time));
    }

    public ItemInstance findMatchingRecipe(ItemInstance[] ItemInstances, TileEntityBrickOven joe) {
        //removed a lot of extra stuff
        for (int i = 0; i < recipes.size(); i++)
        {
            IRecipeOven var12 = (IRecipeOven) recipes.get(i);
            if (var12.matches(ItemInstances))
            {
                joe.setTime(var12.getTime());
                return var12.getCraftingResult(ItemInstances);
            }
        }

        return null;
    }

    public List getRecipeList() {
        return recipes;
    }

    public static final OvenManager smelting() {
        return instance;
    }

    private static final OvenManager instance = new OvenManager();
    private List recipes;

}

