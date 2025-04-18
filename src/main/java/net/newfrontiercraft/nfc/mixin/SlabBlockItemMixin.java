package net.newfrontiercraft.nfc.mixin;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.utils.VanillaSlabPlacer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(SlabBlockItem.class)
public abstract class SlabBlockItemMixin extends BlockItem {
    public VanillaSlabPlacer slabPlacer = new VanillaSlabPlacer(this);
    public SlabBlockItemMixin(int i) {
        super(i);
    }

    @Override
    public boolean useOnBlock(ItemStack stack, PlayerEntity user, World world, int x, int y, int z, int side) {
        return slabPlacer.useOnBlock(stack, user, world, x, y, z, side);
    }
}