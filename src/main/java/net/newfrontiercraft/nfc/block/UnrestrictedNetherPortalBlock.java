package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateNetherPortalBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class UnrestrictedNetherPortalBlock extends TemplateNetherPortalBlock  {
    public UnrestrictedNetherPortalBlock(Identifier identifier) {
        super(identifier, 0);
        setTranslationKey(identifier.namespace, identifier.path);
        setUnbreakable();
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
    }

    @Override
    public int getTexture(int side, int meta) {
        return 14;
    }

    @Environment(EnvType.CLIENT)
    @Override
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        return blockView.getBlockId(x, y, z) != this.id;
    }
}
