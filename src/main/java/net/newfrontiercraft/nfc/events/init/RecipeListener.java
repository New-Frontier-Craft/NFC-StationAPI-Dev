package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemBase;
import net.minecraft.item.ItemInstance;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;
import net.modificationstation.stationapi.api.registry.Identifier;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {

        Identifier type = event.recipeId;
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.brickOven), "XXX", "X X", "XXX", 'X', new ItemInstance(BlockListener.firedBricks));

            CraftingRegistry.addShapedRecipe(new ItemInstance(ItemListener.bronzePickaxe), "XXX", " Y ", " Y ", 'X', new ItemInstance(ItemListener.bronzeIngot), 'Y', new ItemInstance(ItemBase.stick));

            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.coalBlock), "XX", "XX", 'X', new ItemInstance(ItemBase.coal));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.onyxBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.onyx));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.cobaltBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.cobaltIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.aluminiumBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.aluminiumIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.copperBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.copperIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.tinBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.tinIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.zincBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.zincIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.nickelBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.nickelIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.bismuthBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.bismuthIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.osmiumBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.osmiumIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.tungstenBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.tungstenIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.magnetiteBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.magnetiteIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.silverBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.silverIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.leadBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.leadIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.siliconBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.siliconIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.chromeBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.chromeIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.titaniumBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.titaniumIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.uraniumBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.uraniumIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.rubyBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.ruby));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.sapphireBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.sapphire));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.emeraldBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.emerald));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.boronBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.boronIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.platinumBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.platinumIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.bronzeBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.bronzeIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.brassBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.brassIngot));
            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockListener.steelBlock), "XX", "XX", 'X', new ItemInstance(ItemListener.steelIngot));
        }
        if (type == RecipeRegisterEvent.Vanilla.SMELTING.type()) {
            SmeltingRegistry.addSmeltingRecipe(ItemBase.egg.id, new ItemInstance(ItemListener.cookedEgg));

            SmeltingRegistry.addSmeltingRecipe(BlockBase.BRICKS.id, new ItemInstance(BlockListener.firedBricks));

            SmeltingRegistry.addSmeltingRecipe(BlockListener.aluminiumOre.id, new ItemInstance(ItemListener.aluminiumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.copperOre.id, new ItemInstance(ItemListener.copperIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.tinOre.id, new ItemInstance(ItemListener.tinIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.bismuthOre.id, new ItemInstance(ItemListener.bismuthIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.zincOre.id, new ItemInstance(ItemListener.zincIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.nickelOre.id, new ItemInstance(ItemListener.nickelIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.cobaltOre.id, new ItemInstance(ItemListener.cobaltIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.tungstenOre.id, new ItemInstance(ItemListener.tungstenIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.magnetiteOre.id, new ItemInstance(ItemListener.magnetiteIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.silverOre.id, new ItemInstance(ItemListener.silverIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.leadOre.id, new ItemInstance(ItemListener.leadIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.siliconOre.id, new ItemInstance(ItemListener.siliconIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.chromeOre.id, new ItemInstance(ItemListener.chromeIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.titaniumOre.id, new ItemInstance(ItemListener.titaniumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.uraniniteOre.id, new ItemInstance(ItemListener.uraniumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.platinumOre.id, new ItemInstance(ItemListener.platinumIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.boronOre.id, new ItemInstance(ItemListener.boronIngot));
            SmeltingRegistry.addSmeltingRecipe(BlockListener.osmiumOre.id, new ItemInstance(ItemListener.osmiumIngot));
        }
        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type()) {
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemBase.coal, 4), new ItemInstance(BlockListener.coalBlock));
            
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.onyx, 4), new ItemInstance(BlockListener.onyxBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.cobaltIngot, 4), new ItemInstance(BlockListener.cobaltBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.aluminiumIngot, 4), new ItemInstance(BlockListener.aluminiumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.copperIngot, 4), new ItemInstance(BlockListener.copperBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.tinIngot, 4), new ItemInstance(BlockListener.tinBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.zincIngot, 4), new ItemInstance(BlockListener.zincBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.nickelIngot, 4), new ItemInstance(BlockListener.nickelBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.bismuthIngot, 4), new ItemInstance(BlockListener.bismuthBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.osmiumIngot, 4), new ItemInstance(BlockListener.osmiumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.tungstenIngot, 4), new ItemInstance(BlockListener.tungstenBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.magnetiteIngot, 4), new ItemInstance(BlockListener.magnetiteBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.silverIngot, 4), new ItemInstance(BlockListener.silverBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.leadIngot, 4), new ItemInstance(BlockListener.leadBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.siliconIngot, 4), new ItemInstance(BlockListener.siliconBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.chromeIngot, 4), new ItemInstance(BlockListener.chromeBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.titaniumIngot, 4), new ItemInstance(BlockListener.titaniumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.uraniumIngot, 4), new ItemInstance(BlockListener.uraniumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.ruby, 4), new ItemInstance(BlockListener.rubyBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.sapphire, 4), new ItemInstance(BlockListener.sapphireBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.emerald, 4), new ItemInstance(BlockListener.emeraldBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.boronIngot, 4), new ItemInstance(BlockListener.boronBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.platinumIngot, 4), new ItemInstance(BlockListener.platinumBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.bronzeIngot, 4), new ItemInstance(BlockListener.bronzeBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.brassIngot, 4), new ItemInstance(BlockListener.brassBlock));
            CraftingRegistry.addShapelessRecipe(new ItemInstance(ItemListener.steelIngot, 4), new ItemInstance(BlockListener.steelBlock));
        }
    }
}
