package net.newfrontiercraft.nfc.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;

public class HeatCoilBlock extends LazyBlockWithEntityTemplate {

    public HeatCoilBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new HeatCoilBlockEntity();
    }
}
