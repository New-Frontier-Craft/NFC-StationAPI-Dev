package net.newfrontiercraft.nfc.block;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.MachineGearBoxBlockEntity;

public class MachineGearBoxBlock extends LazyBlockWithEntityTemplate {
    public MachineGearBoxBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    protected BlockEntity createBlockEntity() {
        return new MachineGearBoxBlockEntity();
    }
}
