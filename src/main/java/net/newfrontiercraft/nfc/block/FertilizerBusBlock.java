package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.item.FertilizerBusItemBlock;

@HasCustomBlockItemFactory(FertilizerBusItemBlock.class)
public class FertilizerBusBlock extends LazyBlockTemplate {
    public FertilizerBusBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }
}
