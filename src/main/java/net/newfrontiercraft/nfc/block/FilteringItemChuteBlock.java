package net.newfrontiercraft.nfc.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.FilteringItemChuteBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.inventory.FilteringItemChuteScreenHandler;

public class FilteringItemChuteBlock extends BasicItemChuteBlock {
    public FilteringItemChuteBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new FilteringItemChuteBlockEntity();
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity tileEntity = world.getBlockEntity(x, y, z);
        if (tileEntity instanceof FilteringItemChuteBlockEntity filteringItemChuteBlockEntity)
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "gui_filtering_item_chute"),
                    filteringItemChuteBlockEntity, new FilteringItemChuteScreenHandler(player.inventory, filteringItemChuteBlockEntity));
        return true;
    }
}
