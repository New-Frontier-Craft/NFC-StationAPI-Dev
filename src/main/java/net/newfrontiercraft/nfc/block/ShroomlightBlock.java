package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.util.Identifier;

public class ShroomlightBlock extends LazyBlockTemplate {
    public ShroomlightBlock(Identifier identifier) {
        super(identifier, Material.SOLID_ORGANIC, 1.0F, Block.WOOD_SOUND_GROUP);
        setLuminance(0.8F);
    }
}
