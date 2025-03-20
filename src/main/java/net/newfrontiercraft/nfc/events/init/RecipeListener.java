package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
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

            // Slabs
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 3), "###", '#', Block.COBBLESTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 0), "###", '#', Block.STONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 1), "###", '#', Block.SANDSTONE);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.SLAB, 6, 2), "###", '#', Block.PLANKS);

            // Stairs
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.WOODEN_STAIRS, 8), "#  ", "## ", "###", '#', Block.PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.COBBLESTONE_STAIRS, 8), "#  ", "## ", "###", '#', Block.COBBLESTONE);

            // Machines
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.PISTON, 1), "TTT", "#X#", "#R#", '#', Block.COBBLESTONE, 'X', ItemListener.aluminiumIngot, 'R', Item.REDSTONE, 'T', Block.PLANKS);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.DISPENSER, 1), "###", "#X#", "#R#", '#', Block.COBBLESTONE, 'X', Item.STRING, 'R', Item.REDSTONE);

            // Torches
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.TORCH, 4), "X", "#", 'X', ItemListener.netherAsh, '#', Item.STICK);
            CraftingRegistry.addShapedRecipe(new ItemStack(Block.TORCH, 16), "X", "O", 'X', ItemListener.anthracite, 'O', Item.STICK);
        }
        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            // Food
            SmeltingRegistry.addSmeltingRecipe(Item.EGG.id, new ItemStack(ItemListener.cookedEgg));

            // Building blocks
            SmeltingRegistry.addSmeltingRecipe(Block.BRICKS.id, new ItemStack(BlockListener.firedBricks));

            // Ores
            SmeltingRegistry.addSmeltingRecipe(BlockListener.aluminiumOre.id, new ItemStack(ItemListener.aluminiumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.copperOre.id, new ItemStack(ItemListener.copperIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.tinOre.id, new ItemStack(ItemListener.tinIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.bismuthOre.id, new ItemStack(ItemListener.bismuthIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.zincOre.id, new ItemStack(ItemListener.zincIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.nickelOre.id, new ItemStack(ItemListener.nickelIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.cobaltOre.id, new ItemStack(ItemListener.cobaltIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.magnetiteOre.id, new ItemStack(ItemListener.magnetiteIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.silverOre.id, new ItemStack(ItemListener.silverIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.leadOre.id, new ItemStack(ItemListener.leadIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.siliconOre.id, new ItemStack(ItemListener.siliconIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.chromeOre.id, new ItemStack(ItemListener.chromeIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.uraniniteOre.id, new ItemStack(ItemListener.uraniumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.platinumOre.id, new ItemStack(ItemListener.platinumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.boronOre.id, new ItemStack(ItemListener.boronIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.netherGoldOre.id, new ItemStack(Item.GOLD_INGOT));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.netherUraniniteOre.id, new ItemStack(ItemListener.uraniumIngot));
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
        }

        // Stone carpentry
        CarpentryRecipes.carpentry().addCarpentry(Block.STONE.id, 0,
                new ItemStack[] {
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
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.workedStone.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.workedStoneCrossCut.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.workedStoneHorizontalCut.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.workedStoneVerticalCut.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.stoneBricks.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.stoneBricksLarge.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.stoneCheckers.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneTiling),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.stoneTiling.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.workedStone),
                        new ItemStack(BlockListener.workedStoneCrossCut),
                        new ItemStack(BlockListener.workedStoneHorizontalCut),
                        new ItemStack(BlockListener.workedStoneVerticalCut),
                        new ItemStack(BlockListener.stoneBricks),
                        new ItemStack(BlockListener.stoneBricksLarge),
                        new ItemStack(BlockListener.stoneCheckers),
                        new ItemStack(BlockListener.stoneTilingLarge),
                        new ItemStack(Block.STONE)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.stoneTilingLarge.id, 0,
                new ItemStack[] {
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
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.platedStone.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.platedStoneVerticalCut),
                        new ItemStack(BlockListener.platedStoneHorizontalCut),
                        new ItemStack(BlockListener.platedStoneCrossCut)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.platedStoneVerticalCut.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.platedStoneHorizontalCut),
                        new ItemStack(BlockListener.platedStoneCrossCut)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.platedStoneHorizontalCut.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.platedStoneVerticalCut),
                        new ItemStack(BlockListener.platedStoneCrossCut)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.platedStoneCrossCut.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.platedStone),
                        new ItemStack(BlockListener.platedStoneVerticalCut),
                        new ItemStack(BlockListener.platedStoneHorizontalCut)});

        // Pebble carpentry
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.pebble.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.pebbleSmall),
                        new ItemStack(BlockListener.pebbleMedium),
                        new ItemStack(BlockListener.pebbleLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.pebbleSmall.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.pebble),
                        new ItemStack(BlockListener.pebbleMedium),
                        new ItemStack(BlockListener.pebbleLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.pebbleMedium.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.pebble),
                        new ItemStack(BlockListener.pebbleSmall),
                        new ItemStack(BlockListener.pebbleLarge)});
        CarpentryRecipes.carpentry().addCarpentry(BlockListener.pebbleLarge.id, 0,
                new ItemStack[] {
                        new ItemStack(BlockListener.pebble),
                        new ItemStack(BlockListener.pebbleSmall),
                        new ItemStack(BlockListener.pebbleMedium)});
    }
}
