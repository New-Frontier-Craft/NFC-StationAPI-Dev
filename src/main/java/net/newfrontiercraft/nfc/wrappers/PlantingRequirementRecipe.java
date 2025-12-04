package net.newfrontiercraft.nfc.wrappers;

import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.utils.BlockAndMetaRange;
import net.newfrontiercraft.nfc.utils.ItemMeta;
import net.newfrontiercraft.nfc.utils.PlantingRequirement;

import java.util.ArrayList;

public class PlantingRequirementRecipe {
    private final ItemStack block;
    private final ArrayList<ArrayList<ItemStack>> outputs;

    public PlantingRequirementRecipe(ItemMeta blockToPlant, PlantingRequirement plantingRequirement) {
        block = new ItemStack(blockToPlant.item(), 1, blockToPlant.meta());
        int requirementQuantity = plantingRequirement.validOptions().length;
        outputs = new ArrayList<>();
        // Add placed block to outputs
        ArrayList<ItemStack> placedBlock = new ArrayList<>();
        placedBlock.add(new ItemStack(plantingRequirement.placedBlock(), 1, plantingRequirement.placedBlockMeta()));
        outputs.add(placedBlock);
        // Add soil requirements
        for (int requirementIndex = 0; requirementIndex < requirementQuantity; requirementIndex++) {
            BlockAndMetaRange range = plantingRequirement.validOptions()[requirementIndex];
            java.util.ArrayList<ItemStack> ranges = new ArrayList<>();
            // Put all valid meta values into a list. This will make AMI cycle through the meta values.
            for (int rangeIndex = 0; rangeIndex < range.metaValues().length; rangeIndex++) {
                ranges.add(new ItemStack(range.block(), 1, range.metaValues()[rangeIndex]));
            }
            outputs.add(ranges);
        }
    }

    public ArrayList<ArrayList<ItemStack>> getOutputs() {
        return outputs;
    }

    public ItemStack getBlock() {
        return block;
    }
}
