package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.item.PlantBusItemBlock;

@HasCustomBlockItemFactory(PlantBusItemBlock.class)
public class PlantBusBlock extends LazyBlockTemplate {
    public PlantBusBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }
}
