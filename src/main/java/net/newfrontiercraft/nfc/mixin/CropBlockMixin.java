package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.CropBlock;
import net.minecraft.block.PlantBlock;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends PlantBlock {
    public CropBlockMixin(int id, int textureId) {
        super(id, textureId);
    }

    @Override
    public boolean canPlantOnTop(int id) {
        if(id == BlockListener.planter.id) {
            return true;
        }
        return super.canPlantOnTop(id);
    }
}
