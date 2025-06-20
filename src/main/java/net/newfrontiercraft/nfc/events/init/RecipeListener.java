package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.registry.CarpentryRecipes;
import net.newfrontiercraft.nfc.utils.RecipeRemover;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {

        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            // Recipe removal
            RecipeRemover.removeRecipe(Block.RAIL);
            RecipeRemover.removeRecipe(Block.POWERED_RAIL);
            RecipeRemover.removeRecipe(Block.DETECTOR_RAIL);
            RecipeRemover.removeRecipe(Block.SLAB);
            RecipeRemover.removeRecipe(Block.WOODEN_STAIRS);
            RecipeRemover.removeRecipe(Block.COBBLESTONE_STAIRS);
            RecipeRemover.removeRecipe(Block.PISTON);
            RecipeRemover.removeRecipe(Block.DISPENSER);

            // Machines
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.brickOven, 1, 3), "XXX", "X X", "XXX", 'X', new ItemStack(BlockListener.firedBricks));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.carpentryWorkstation, 1), "XXX", "XYX", "ZZZ", 'X', Block.PLANKS, 'Y', ItemListener.bronzeIngot, 'Z', Block.COBBLESTONE);

            // Tools
            String[][] toolPatterns = new String[][]
                    {{"XXX", " # ", " # "}, {"X", "#", "#"}, {"XX", "X#", " #"},
                            {"XX", " #", " #"}, {"X", "X", "#"}};
            Object[][] toolItems;
            toolItems = new Object[][] {
                    {ItemListener.aluminiumIngot, ItemListener.bismuthIngot, ItemListener.boronIngot, ItemListener.chromeIngot, ItemListener.cobaltIngot, ItemListener.copperIngot, ItemListener.emerald, ItemListener.nickelIngot, ItemListener.platinumIngot, ItemListener.ruby, ItemListener.sapphire, ItemListener.siliconIngot, ItemListener.silverIngot, ItemListener.tinIngot, ItemListener.leadIngot, ItemListener.titaniumIngot, ItemListener.tungstenIngot, ItemListener.zincIngot, ItemListener.brassIngot, ItemListener.bronzeIngot, ItemListener.steelIngot, ItemListener.osmiumIngot, ItemListener.onyx, ItemListener.magnetiteIngot},
                    {ItemListener.aluminiumPickaxe, ItemListener.bismuthPickaxe, ItemListener.boronPickaxe, ItemListener.chromePickaxe, ItemListener.cobaltPickaxe, ItemListener.copperPickaxe, ItemListener.emeraldPickaxe, ItemListener.nickelPickaxe, ItemListener.platinumPickaxe, ItemListener.rubyPickaxe, ItemListener.sapphirePickaxe, ItemListener.siliconPickaxe, ItemListener.silverPickaxe, ItemListener.tinPickaxe, ItemListener.leadPickaxe, ItemListener.titaniumPickaxe, ItemListener.tungstenPickaxe, ItemListener.zincPickaxe, ItemListener.brassPickaxe, ItemListener.bronzePickaxe, ItemListener.steelPickaxe, ItemListener.osmiumPickaxe, ItemListener.onyxPickaxe, ItemListener.magnetPickaxe},
                    {ItemListener.aluminiumShovel, ItemListener.bismuthShovel, ItemListener.boronShovel, ItemListener.chromeShovel, ItemListener.cobaltShovel, ItemListener.copperShovel, ItemListener.emeraldShovel, ItemListener.nickelShovel, ItemListener.platinumShovel, ItemListener.rubyShovel, ItemListener.sapphireShovel, ItemListener.siliconShovel, ItemListener.silverShovel, ItemListener.tinShovel, ItemListener.leadShovel, ItemListener.titaniumShovel, ItemListener.tungstenShovel, ItemListener.zincShovel, ItemListener.brassShovel, ItemListener.bronzeShovel, ItemListener.steelShovel, ItemListener.osmiumShovel, ItemListener.onyxShovel, ItemListener.magnetShovel},
                    {ItemListener.aluminiumAxe, ItemListener.bismuthAxe, ItemListener.boronAxe, ItemListener.chromeAxe, ItemListener.cobaltAxe, ItemListener.copperAxe, ItemListener.emeraldAxe, ItemListener.nickelAxe, ItemListener.platinumAxe, ItemListener.rubyAxe, ItemListener.sapphireAxe, ItemListener.siliconAxe, ItemListener.silverAxe, ItemListener.tinAxe, ItemListener.leadAxe, ItemListener.titaniumAxe, ItemListener.tungstenAxe, ItemListener.zincAxe, ItemListener.brassAxe, ItemListener.bronzeAxe, ItemListener.steelAxe, ItemListener.osmiumAxe, ItemListener.onyxAxe, ItemListener.magnetAxe},
                    {ItemListener.aluminiumHoe, ItemListener.bismuthHoe, ItemListener.boronHoe, ItemListener.chromeHoe, ItemListener.cobaltHoe, ItemListener.copperHoe, ItemListener.emeraldHoe, ItemListener.nickelHoe, ItemListener.platinumHoe, ItemListener.rubyHoe, ItemListener.sapphireHoe, ItemListener.siliconHoe, ItemListener.silverHoe, ItemListener.tinHoe, ItemListener.leadHoe, ItemListener.titaniumHoe, ItemListener.tungstenHoe, ItemListener.zincHoe, ItemListener.brassHoe, ItemListener.bronzeHoe, ItemListener.steelHoe, ItemListener.osmiumHoe, ItemListener.onyxHoe, ItemListener.magnetHoe},
                    {ItemListener.aluminiumSword, ItemListener.bismuthSword, ItemListener.boronSword, ItemListener.chromeSword, ItemListener.cobaltSword, ItemListener.copperSword, ItemListener.emeraldSword, ItemListener.nickelSword, ItemListener.platinumSword, ItemListener.rubySword, ItemListener.sapphireSword, ItemListener.siliconSword, ItemListener.silverSword, ItemListener.tinSword, ItemListener.leadSword, ItemListener.titaniumSword, ItemListener.tungstenSword, ItemListener.zincSword, ItemListener.brassSword, ItemListener.bronzeSword, ItemListener.steelSword, ItemListener.osmiumSword, ItemListener.onyxSword, ItemListener.magnetSword},
            };
            for (int i = 0; i < toolItems[0].length; ++i) {
                Object object = toolItems[0][i];
                for (int j = 0; j < toolItems.length - 1; ++j) {
                    Item stick = Item.STICK;
                    Item item = (Item)toolItems[j + 1][i];
                    if(item == null)
                        continue;
                    CraftingRegistry.addShapedRecipe(new ItemStack(item, 1), toolPatterns[j], ('X'), object, ('#'), stick);
                }
            }

            //Armor
            String[][] armorPatterns = new String[][]
                    {{"XXX", "X X"}, {"X X", "XXX", "XXX"},{"XXX", "X X", "X X"}, {"X X", "X X"}};
            Object[][] armorItems;
            armorItems = new Object[][] {
                    {ItemListener.aluminiumIngot, ItemListener.bismuthIngot, ItemListener.boronIngot, ItemListener.chromeIngot, ItemListener.cobaltIngot, ItemListener.copperIngot, ItemListener.emerald, ItemListener.nickelIngot, ItemListener.platinumIngot, ItemListener.ruby, ItemListener.sapphire, ItemListener.siliconIngot, ItemListener.silverIngot, ItemListener.tinIngot, ItemListener.titaniumIngot, ItemListener.tungstenIngot, ItemListener.zincIngot, ItemListener.brassIngot, ItemListener.bronzeIngot, ItemListener.steelIngot, ItemListener.osmiumIngot, ItemListener.onyx},
                    {ItemListener.aluminiumHelmet, ItemListener.bismuthHelmet, ItemListener.boronHelmet, ItemListener.chromeHelmet, ItemListener.cobaltHelmet, ItemListener.copperHelmet, ItemListener.emeraldHelmet, ItemListener.nickelHelmet, ItemListener.platinumHelmet, ItemListener.rubyHelmet, ItemListener.sapphireHelmet, ItemListener.siliconHelmet, ItemListener.silverHelmet, ItemListener.tinHelmet, ItemListener.titaniumHelmet, ItemListener.tungstenHelmet, ItemListener.zincHelmet, ItemListener.brassHelmet, ItemListener.bronzeHelmet, ItemListener.steelHelmet, ItemListener.osmiumHelmet, ItemListener.onyxHelmet},
                    {ItemListener.aluminiumChestplate, ItemListener.bismuthChestplate, ItemListener.boronChestplate, ItemListener.chromeChestplate, ItemListener.cobaltChestplate, ItemListener.copperChestplate, ItemListener.emeraldChestplate, ItemListener.nickelChestplate, ItemListener.platinumChestplate, ItemListener.rubyChestplate, ItemListener.sapphireChestplate, ItemListener.siliconChestplate, ItemListener.silverChestplate, ItemListener.tinChestplate, ItemListener.titaniumChestplate, ItemListener.tungstenChestplate, ItemListener.zincChestplate, ItemListener.brassChestplate, ItemListener.bronzeChestplate, ItemListener.steelChestplate, ItemListener.osmiumChestplate, ItemListener.onyxChestplate},
                    {ItemListener.aluminiumLeggings, ItemListener.bismuthLeggings, ItemListener.boronLeggings, ItemListener.chromeLeggings, ItemListener.cobaltLeggings, ItemListener.copperLeggings, ItemListener.emeraldLeggings, ItemListener.nickelLeggings, ItemListener.platinumLeggings, ItemListener.rubyLeggings, ItemListener.sapphireLeggings, ItemListener.siliconLeggings, ItemListener.silverLeggings, ItemListener.tinLeggings, ItemListener.titaniumLeggings, ItemListener.tungstenLeggings, ItemListener.zincLeggings, ItemListener.brassLeggings, ItemListener.bronzeLeggings, ItemListener.steelLeggings, ItemListener.osmiumLeggings, ItemListener.onyxLeggings},
                    {ItemListener.aluminiumBoots, ItemListener.bismuthBoots, ItemListener.boronBoots, ItemListener.chromeBoots, ItemListener.cobaltBoots, ItemListener.copperBoots, ItemListener.emeraldBoots, ItemListener.nickelBoots, ItemListener.platinumBoots, ItemListener.rubyBoots, ItemListener.sapphireBoots, ItemListener.siliconBoots, ItemListener.silverBoots, ItemListener.tinBoots, ItemListener.titaniumBoots, ItemListener.tungstenBoots, ItemListener.zincBoots, ItemListener.brassBoots, ItemListener.bronzeBoots, ItemListener.steelBoots, ItemListener.osmiumBoots, ItemListener.onyxBoots},
            };
            for (int i = 0; i < armorItems[0].length; ++i) {
                Object object = armorItems[0][i];
                for (int j = 0; j < armorItems.length - 1; ++j) {
                    Item item = (Item)armorItems[j + 1][i];
                    if(item == null)
                        continue;
                    CraftingRegistry.addShapedRecipe(new ItemStack(item, 1), armorPatterns[j], ('X'), object);
                }
            }

            // Storage block crafting
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.coalBlock), "XX", "XX", 'X', new ItemStack(Item.COAL));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.onyxBlock), "XX", "XX", 'X', new ItemStack(ItemListener.onyx));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.cobaltBlock), "XX", "XX", 'X', new ItemStack(ItemListener.cobaltIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.aluminiumBlock), "XX", "XX", 'X', new ItemStack(ItemListener.aluminiumIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.copperBlock), "XX", "XX", 'X', new ItemStack(ItemListener.copperIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.tinBlock), "XX", "XX", 'X', new ItemStack(ItemListener.tinIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.zincBlock), "XX", "XX", 'X', new ItemStack(ItemListener.zincIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nickelBlock), "XX", "XX", 'X', new ItemStack(ItemListener.nickelIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.bismuthBlock), "XX", "XX", 'X', new ItemStack(ItemListener.bismuthIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.osmiumBlock), "XX", "XX", 'X', new ItemStack(ItemListener.osmiumIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.tungstenBlock), "XX", "XX", 'X', new ItemStack(ItemListener.tungstenIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.magnetiteBlock), "XX", "XX", 'X', new ItemStack(ItemListener.magnetiteIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.silverBlock), "XX", "XX", 'X', new ItemStack(ItemListener.silverIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.leadBlock), "XX", "XX", 'X', new ItemStack(ItemListener.leadIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.siliconBlock), "XX", "XX", 'X', new ItemStack(ItemListener.siliconIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.chromeBlock), "XX", "XX", 'X', new ItemStack(ItemListener.chromeIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.titaniumBlock), "XX", "XX", 'X', new ItemStack(ItemListener.titaniumIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.uraniumBlock), "XX", "XX", 'X', new ItemStack(ItemListener.uraniumIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.rubyBlock), "XX", "XX", 'X', new ItemStack(ItemListener.ruby));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.sapphireBlock), "XX", "XX", 'X', new ItemStack(ItemListener.sapphire));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.emeraldBlock), "XX", "XX", 'X', new ItemStack(ItemListener.emerald));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.boronBlock), "XX", "XX", 'X', new ItemStack(ItemListener.boronIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.platinumBlock), "XX", "XX", 'X', new ItemStack(ItemListener.platinumIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.bronzeBlock), "XX", "XX", 'X', new ItemStack(ItemListener.bronzeIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.brassBlock), "XX", "XX", 'X', new ItemStack(ItemListener.brassIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.steelBlock), "XX", "XX", 'X', new ItemStack(ItemListener.steelIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.cupronickelBlock), "XX", "XX", 'X', new ItemStack(ItemListener.cupronickelIngot));
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.LAPIS_BLOCK), "XX", "XX", 'X', new ItemStack(Item.DYE, 1, 4));
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.GOLD_BLOCK), "XX", "XX", 'X', new ItemStack(Item.GOLD_INGOT));
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.IRON_BLOCK), "XX", "XX", 'X', new ItemStack(Item.IRON_INGOT));
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.DIAMOND_BLOCK), "XX", "XX", 'X', new ItemStack(Item.DIAMOND));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.blueGlowstone), "XX", "XX", 'X', new ItemStack(ItemListener.blueGlowstoneDust));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.scorchedSandstone), "XX", "XX", 'X', new ItemStack(BlockListener.scorchedSand));

            // Rails
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.RAIL, 24), "X#X", "X#X", "X#X", 'X', Item.IRON_INGOT, '#', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.POWERED_RAIL, 8), "X#X", "I#I", "XRX", 'X', Item.IRON_INGOT, 'R', Item.REDSTONE, '#', Item.STICK, 'I', ItemListener.copperIngot);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.DETECTOR_RAIL, 8), "XOX", "X#X", "XRX", 'X', Item.IRON_INGOT, 'R', Item.REDSTONE, '#', Block.STONE_PRESSURE_PLATE, 'O', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.RAIL, 6), "XYX", "XYX", "XYX", 'X', ItemListener.bronzeIngot, 'Y', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.RAIL, 6), "XYX", "XYX", "XYX", 'X', ItemListener.brassIngot, 'Y', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.RAIL, 36), "XYX", "XYX", "XYX", 'X', ItemListener.steelIngot, 'Y', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.POWERED_RAIL, 12), "XYX", "ZYZ", "X#X", 'X', ItemListener.steelIngot, 'Y', Item.STICK, 'Z', ItemListener.copperIngot, '#', Item.REDSTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.POWERED_RAIL, 10), "XYX", "Z+Z", "X#X", 'X', Item.IRON_INGOT, 'Y', Item.STICK, 'Z', ItemListener.copperIngot, '#', Item.REDSTONE, '+', Item.GOLD_INGOT);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.POWERED_RAIL, 16), "XYX", "Z+Z", "X#X", 'X', ItemListener.steelIngot, 'Y', Item.STICK, 'Z', ItemListener.copperIngot, '#', Item.REDSTONE, '+', Item.GOLD_INGOT);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.DETECTOR_RAIL, 12), "XYX", "XZX", "X#X", 'X', ItemListener.steelIngot, 'Y', Item.STICK, 'Z', Block.STONE_PRESSURE_PLATE, '#', Item.REDSTONE);

            // Vanilla Slabs
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 3), "###", '#', Block.COBBLESTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 0), "###", '#', Block.STONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 1), "###", '#', Block.SANDSTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 2), "###", '#', Block.PLANKS);

            // Modded Slabs
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 0), "###", '#', Block.BRICKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 1), "###", '#', BlockListener.workedStone);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 2), "###", '#', BlockListener.stoneBricksLarge);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 3), "###", '#', BlockListener.stoneBricks);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 4), "###", '#', new ItemStack(BlockListener.mud, 1, 1));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 5), "###", '#', new ItemStack(BlockListener.firedMud, 1, 1));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedSlabs, 6, 6), "###", '#', BlockListener.petrifiedPlanks);
            for (int i = 0; i < 16; i++) {
                CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.stainedPlanksSlabs, 6, i), "###", '#', new ItemStack(BlockListener.stainedPlanks, 1, i));
            }

            // Stairs
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.WOODEN_STAIRS, 8), "#  ", "## ", "###", '#', Block.PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.COBBLESTONE_STAIRS, 8), "#  ", "## ", "###", '#', Block.COBBLESTONE);

            // Modded Stairs
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.stoneVanillaStairs, 8, 1), "#  ", "## ", "###", '#', Block.SANDSTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 0), "#  ", "## ", "###", '#', Block.BRICKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 1), "#  ", "## ", "###", '#', BlockListener.workedStone);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 2), "#  ", "## ", "###", '#', BlockListener.stoneBricksLarge);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 3), "#  ", "## ", "###", '#', BlockListener.stoneBricks);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 4), "#  ", "## ", "###", '#', new ItemStack(BlockListener.mud, 1, 1));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 5), "#  ", "## ", "###", '#', new ItemStack(BlockListener.firedMud, 1, 1));
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.nonDyedStairs, 8, 6), "#  ", "## ", "###", '#', BlockListener.petrifiedPlanks);
            for (int i = 0; i < 16; i++) {
                CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.stainedPlanksStairs, 8, i), "#  ", "## ", "###", '#', new ItemStack(BlockListener.stainedPlanks, 1, i));
            }

            // Machines
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.PISTON, 1), "TTT", "#X#", "#R#", '#', Block.COBBLESTONE, 'X', ItemListener.aluminiumIngot, 'R', Item.REDSTONE, 'T', Block.PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.DISPENSER, 1), "###", "#X#", "#R#", '#', Block.COBBLESTONE, 'X', Item.STRING, 'R', Item.REDSTONE);

            // Torches
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.TORCH, 4), "X", "#", 'X', ItemListener.netherAsh, '#', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.TORCH, 16), "X", "O", 'X', ItemListener.anthracite, 'O', Item.STICK);

            // Windows
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.window, 3, 1), "OXO", 'X', Block.PLANKS, 'O', Block.GLASS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.window, 3, 2), "XOX", 'X', Block.PLANKS, 'O', Block.GLASS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.window, 3, 3), "O", "X", "O", 'X', Block.PLANKS, 'O', Block.GLASS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.window, 9, 4), "XXX", "XOX", "XXX", 'X', Block.PLANKS, 'O', Block.GLASS);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.window, 9, 5), "OXO", "XXX", "OXO", 'X', Block.PLANKS, 'O', Block.GLASS);

            // Tinted glass
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.tintedGlass, 6), " X ", "XOX", " X ", 'X', Block.GLASS, 'O', Block.OBSIDIAN);

            // Convenience blocks
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.scaffoldBlock, 10), "###", "XXX", "XXX", '#', Block.PLANKS, 'X', Block.LADDER);

            // Snow bricks
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.snowBricks, 4), "XX", "XX", 'X', new ItemStack(Block.SNOW_BLOCK));

            // Walls
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.hardWall, 6), "###", "###", '#', Block.COBBLESTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.hardWall, 6, 1), "###", "###", '#', Block.MOSSY_COBBLESTONE);

            // Copper Door
            CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.copperDoor, 1), "##", "##", "##", '#', ItemListener.copperIngot);

            // Fence Gates
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.fenceGate, 1), "YXY", "YXY", 'X', Block.PLANKS, 'Y', Item.STICK);

            // Planks
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.petrifiedPlanks, 4),"#", '#', BlockListener.petrifiedLog);

            // Planter
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.unfiredPlanter, 2), "X X", "X X", "XXX", 'X', Item.CLAY);

            // Heat machines
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.heatCoil, 1), " X ", "XYX", " X ", 'X', ItemListener.cupronickelIngot, 'Y', ItemListener.copperIngot);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.combustionHeater, 1, 2), "XXX", "X X", "XYX", 'X', Block.COBBLESTONE, 'Y', ItemListener.cupronickelIngot);

            // Chutes
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.basicItemChute, 1, 0), "X X", "YZY", "Y Y", 'X', ItemListener.bronzeIngot, 'Y', ItemListener.brassIngot, 'Z', Block.CHEST);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.itemChuteExtender, 1, 0), "X X", "YZY", "Y Y", 'X', ItemListener.bronzeIngot, 'Y', Block.PLANKS, 'Z', Block.CHEST);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.filteringItemChute, 1, 0), "X X", "YZY", "Y Y", 'X', Item.GOLD_INGOT, 'Y', ItemListener.brassIngot, 'Z', Block.CHEST);
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.preciseItemChute, 1, 0), "X X", "YZY", "# #", 'X', Item.GOLD_INGOT, 'Y', ItemListener.brassIngot, 'Z', Block.CHEST, '#', ItemListener.silverIngot);
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            // Storage block un-crafting
            CraftingRegistry.addShapelessRecipe(new ItemStack(Item.COAL, 4), new ItemStack(BlockListener.coalBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.onyx, 4), new ItemStack(BlockListener.onyxBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.cobaltIngot, 4), new ItemStack(BlockListener.cobaltBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.aluminiumIngot, 4), new ItemStack(BlockListener.aluminiumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.copperIngot, 4), new ItemStack(BlockListener.copperBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.tinIngot, 4), new ItemStack(BlockListener.tinBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.zincIngot, 4), new ItemStack(BlockListener.zincBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.nickelIngot, 4), new ItemStack(BlockListener.nickelBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.bismuthIngot, 4), new ItemStack(BlockListener.bismuthBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.osmiumIngot, 4), new ItemStack(BlockListener.osmiumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.tungstenIngot, 4), new ItemStack(BlockListener.tungstenBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.magnetiteIngot, 4), new ItemStack(BlockListener.magnetiteBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.silverIngot, 4), new ItemStack(BlockListener.silverBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.leadIngot, 4), new ItemStack(BlockListener.leadBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.siliconIngot, 4), new ItemStack(BlockListener.siliconBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.chromeIngot, 4), new ItemStack(BlockListener.chromeBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.titaniumIngot, 4), new ItemStack(BlockListener.titaniumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.uraniumIngot, 4), new ItemStack(BlockListener.uraniumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.ruby, 4), new ItemStack(BlockListener.rubyBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.sapphire, 4), new ItemStack(BlockListener.sapphireBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.emerald, 4), new ItemStack(BlockListener.emeraldBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.boronIngot, 4), new ItemStack(BlockListener.boronBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.platinumIngot, 4), new ItemStack(BlockListener.platinumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.bronzeIngot, 4), new ItemStack(BlockListener.bronzeBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.brassIngot, 4), new ItemStack(BlockListener.brassBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.steelIngot, 4), new ItemStack(BlockListener.steelBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(ItemListener.cupronickelIngot, 4), new ItemStack(BlockListener.cupronickelBlock));
            CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DYE, 4, 4), new ItemStack(Block.LAPIS_BLOCK));
            CraftingRegistry.addShapelessRecipe(new ItemStack(Item.GOLD_INGOT, 4), new ItemStack(Block.GOLD_BLOCK));
            CraftingRegistry.addShapelessRecipe(new ItemStack(Item.IRON_INGOT, 4), new ItemStack(Block.IRON_BLOCK));
            CraftingRegistry.addShapelessRecipe(new ItemStack(Item.DIAMOND, 4), new ItemStack(Block.DIAMOND_BLOCK));

            // Window
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.window, 2, 0), Block.GLASS, Block.PLANKS);

            // Mud crafting
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.mud, 2), Item.WHEAT, Block.DIRT, Block.DIRT);

            //Stained planks
            int o = 15;
            for (int i = 0; i < 16; i++) {
                CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.stainedPlanks, 4, i), new ItemStack(Item.DYE, 1, o),
                        new ItemStack(Block.PLANKS), new ItemStack(Block.PLANKS),
                        new ItemStack(Block.PLANKS), new ItemStack(Block.PLANKS));
                o--;
            }

            // Planter
            CraftingRegistry.addShapelessRecipe(new ItemStack(BlockListener.planter, 1, 1), new ItemStack(BlockListener.planter, 1, 0), new ItemStack(Block.DIRT));
        }

        // Stone carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockRegistry.INSTANCE.getId(Block.STONE), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.stoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("worked_stone"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("worked_stone_cross_cut"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("worked_stone_horizontal_cut"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("worked_stone_vertical_cut"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("stone_bricks"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("stone_bricks_large"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("stone_checkers"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("stone_tiling"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("stone_tiling_large"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(Block.STONE)});

        // Plated stone carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("plated_stone"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.platedStoneVerticalCut),
                        new ItemStack(BlockListener.platedStoneHorizontalCut),
                        new ItemStack(BlockListener.platedStoneCrossCut)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("plated_stone_vertical_cut"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.platedStoneHorizontalCut),
                        new ItemStack(BlockListener.platedStoneCrossCut)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("plated_stone_horizontal_cut"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.platedStoneVerticalCut),
                        new ItemStack(BlockListener.platedStoneCrossCut)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("plated_stone_cross_cut"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.platedStoneVerticalCut),
                        new ItemStack(BlockListener.platedStoneHorizontalCut)});

        // Pebble carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("pebble"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.pebbleSmall),
                        new ItemStack(BlockListener.pebbleMedium),
                        new ItemStack(BlockListener.pebbleLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("pebble_small"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.pebble),
                        new ItemStack(BlockListener.pebbleMedium),
                        new ItemStack(BlockListener.pebbleLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("pebble_medium"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.pebble),
                        new ItemStack(BlockListener.pebbleSmall),
                        new ItemStack(BlockListener.pebbleLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("pebble_large"), 0, new ItemStack[] {
                        new ItemStack(BlockListener.pebble),
                        new ItemStack(BlockListener.pebbleSmall),
                        new ItemStack(BlockListener.pebbleMedium)});

        // Mud carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("mud"), 0, 4, new ItemStack[] {
                        new ItemStack(BlockListener.mud, 1, 0),
                        new ItemStack(BlockListener.mud, 1, 1),
                        new ItemStack(BlockListener.mud, 1, 2),
                        new ItemStack(BlockListener.mud, 1, 3),
                        new ItemStack(BlockListener.mud, 1, 4)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("fired_mud"), 0, 4, new ItemStack[] {
                        new ItemStack(BlockListener.firedMud, 1, 0),
                        new ItemStack(BlockListener.firedMud, 1, 1),
                        new ItemStack(BlockListener.firedMud, 1, 2),
                        new ItemStack(BlockListener.firedMud, 1, 3),
                        new ItemStack(BlockListener.firedMud, 1, 4)});

        // Wood carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockRegistry.INSTANCE.getId(Block.LOG), 0, 2, new ItemStack[] {
                        new ItemStack(BlockListener.decorativeWood, 1, 0),
                        new ItemStack(BlockListener.decorativeWood, 1, 1),
                        new ItemStack(BlockListener.decorativeWood, 2, 6),
                        new ItemStack(BlockListener.decorativeWood, 2, 7),
                        new ItemStack(BlockListener.decorativeWood, 2, 8),
                        new ItemStack(BlockListener.decorativeWood, 2, 9)});
        CarpentryRecipes.carpentry().addCarpentry(BlockRegistry.INSTANCE.getId(Block.PLANKS), 0, new ItemStack[] {
                        new ItemStack(BlockListener.decorativeWood, 1, 2),
                        new ItemStack(BlockListener.decorativeWood, 1, 3),
                        new ItemStack(BlockListener.decorativeWood, 1, 4),
                        new ItemStack(BlockListener.decorativeWood, 1, 5)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("decorative_wood"), 0, 1, new ItemStack[] {
                        new ItemStack(BlockListener.decorativeWood, 1, 0),
                        new ItemStack(BlockListener.decorativeWood, 1, 1),
                        new ItemStack(Block.LOG)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("decorative_wood"), 2, 5, new ItemStack[] {
                        new ItemStack(Block.PLANKS),
                        new ItemStack(BlockListener.decorativeWood, 1, 2),
                        new ItemStack(BlockListener.decorativeWood, 1, 3),
                        new ItemStack(BlockListener.decorativeWood, 1, 4),
                        new ItemStack(BlockListener.decorativeWood, 1, 5)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("decorative_wood"), 6, 9, new ItemStack[] {
                        new ItemStack(BlockListener.decorativeWood.id, 1, 6),
                        new ItemStack(BlockListener.decorativeWood.id, 1, 7),
                        new ItemStack(BlockListener.decorativeWood.id, 1, 8),
                        new ItemStack(BlockListener.decorativeWood.id, 1, 9)});

        // Nether stone carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_worked_stone"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_worked_stone_cross_cut"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_worked_stone_horizontal_cut"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_worked_stone_vertical_cut"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_stone_bricks"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_stone_bricks_large"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_stone_checkers"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneTiling),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_stone_tiling"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTilingLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.MOD_ID.id("nether_stone_tiling_large"), 0, new ItemStack[] {
                new ItemStack(BlockListener.netherWorkedStone),
                new ItemStack(BlockListener.netherWorkedStoneCrossCut),
                new ItemStack(BlockListener.netherWorkedStoneHorizontalCut),
                new ItemStack(BlockListener.netherWorkedStoneVerticalCut),
                new ItemStack(BlockListener.netherStoneBricks),
                new ItemStack(BlockListener.netherStoneBricksLarge),
                new ItemStack(BlockListener.netherStoneCheckers),
                new ItemStack(BlockListener.netherStoneTiling)});
    }
}
