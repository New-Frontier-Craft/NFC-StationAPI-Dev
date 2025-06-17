package net.newfrontiercraft.nfc.inventory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.newfrontiercraft.nfc.block.entity.CombustionHeaterBlockEntity;

public class CombustionHeaterScreenHandler extends ScreenHandler {

    private final CombustionHeaterBlockEntity combustionHeater;

    public CombustionHeaterScreenHandler(PlayerInventory inventoryplayer, CombustionHeaterBlockEntity tileEntityCombustionHeater) {
        combustionHeater = tileEntityCombustionHeater;
        addSlot(new Slot(tileEntityCombustionHeater, 0, 80, 53));
        for(int i = 0; i < 3; i++) {
            for(int k = 0; k < 9; k++) {
                addSlot(new Slot(inventoryplayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }

        }

        for(int j = 0; j < 9; j++) {
            addSlot(new Slot(inventoryplayer, j, 8 + j * 18, 142));
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return combustionHeater.canPlayerUse(player);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void setProperty(int i, int j) {
        if(i == 1) {
            combustionHeater.furnaceBurnTime = j;
        }
        if(i == 2) {
            combustionHeater.currentItemBurnTime = j;
        }
    }
}
