package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {

        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            CraftingRegistry.addShapedRecipe(new ItemStack(BlockListener.brickOven), "XXX", "X X", "XXX", 'X', new ItemStack(BlockListener.firedBricks));

            CraftingRegistry.addShapedRecipe(new ItemStack(ItemListener.bronzePickaxe), "XXX", " Y ", " Y ", 'X', new ItemStack(ItemListener.bronzeIngot), 'Y', new ItemStack(Item.STICK));

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
        }
        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            SmeltingRegistry.addSmeltingRecipe(Item.EGG.id, new ItemStack(ItemListener.cookedEgg));

            SmeltingRegistry.addSmeltingRecipe(Block.BRICKS.id, new ItemStack(BlockListener.firedBricks));

            SmeltingRegistry.addSmeltingRecipe(BlockListener.aluminiumOre.id, new ItemStack(ItemListener.aluminiumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.copperOre.id, new ItemStack(ItemListener.copperIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.tinOre.id, new ItemStack(ItemListener.tinIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.bismuthOre.id, new ItemStack(ItemListener.bismuthIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.zincOre.id, new ItemStack(ItemListener.zincIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.nickelOre.id, new ItemStack(ItemListener.nickelIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.cobaltOre.id, new ItemStack(ItemListener.cobaltIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.tungstenOre.id, new ItemStack(ItemListener.tungstenIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.magnetiteOre.id, new ItemStack(ItemListener.magnetiteIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.silverOre.id, new ItemStack(ItemListener.silverIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.leadOre.id, new ItemStack(ItemListener.leadIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.siliconOre.id, new ItemStack(ItemListener.siliconIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.chromeOre.id, new ItemStack(ItemListener.chromeIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.titaniumOre.id, new ItemStack(ItemListener.titaniumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.uraniniteOre.id, new ItemStack(ItemListener.uraniumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.platinumOre.id, new ItemStack(ItemListener.platinumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.boronOre.id, new ItemStack(ItemListener.boronIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.osmiumOre.id, new ItemStack(ItemListener.osmiumIngot));
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
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
    }
}
