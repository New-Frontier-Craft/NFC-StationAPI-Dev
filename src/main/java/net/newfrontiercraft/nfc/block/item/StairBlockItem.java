package net.newfrontiercraft.nfc.block.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateBlockItem;
import net.newfrontiercraft.nfc.utils.StairPlacer;

public class StairBlockItem extends TemplateBlockItem {
    public StairPlacer stairPlacer;
    public StairBlockItem(int i) {
        super(i);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        stairPlacer = new StairPlacer(this);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        return stairPlacer.useOnBlock(stack, user, world, x, y, z, side);
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return getBlock().getTranslationKey() + stack.getDamage();
    }
}
