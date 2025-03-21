package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateFlowingLiquidBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class OilFlowingBlock extends TemplateFlowingLiquidBlock {
    public OilFlowingBlock(Identifier identifier, Material material) {
        super(identifier, material);
    }
}
