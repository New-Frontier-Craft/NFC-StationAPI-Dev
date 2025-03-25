package net.newfrontiercraft.nfc.block.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class BioluminescentMushroomBlockItem extends BlockItem {
    public BioluminescentMushroomBlockItem(int i) {
        super(i);
        setHasSubtypes(true);
        setTranslationKey("bioluminescent_mushroom");
    }

    @Override
    public int getTextureId(int i) {
        return BlockListener.bioluminescentMushroom.getTexture(0, i);
    }

    @Override
    public int getPlacementMetadata(int meta) {
        return meta;
    }

    @Override
    public String getTranslationKey(ItemStack itemstack) //getItemNameIS
    {
        if (itemstack.getDamage() == 0) {
            return super.getTranslationKey() + "." + "blue";
        } else {
            return super.getTranslationKey() + "." + "purple";
        }
    }

}
