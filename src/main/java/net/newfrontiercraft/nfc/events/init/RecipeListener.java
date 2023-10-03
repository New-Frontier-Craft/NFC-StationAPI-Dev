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
            SmeltingRegistry.addSmeltingRecipe(BlockListener.magnetiteOre.id, new ItemInstance(ItemListener.magnetIngot));
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
        }
    }
}
