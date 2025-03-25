package net.newfrontiercraft.nfc.block;

import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.block.TemplateSaplingBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.world.gen.feature.AlphaTreeFeature;
import net.newfrontiercraft.nfc.world.gen.feature.LargeAlphaTreeFeature;

import java.util.Random;

public class AlphaSapling extends TemplateSaplingBlock {
    int texture;

    @Override
    public int getTexture(int side) {
        return texture;
    }

    @Override
    public int getTexture(int side, int meta) {
        return this.getTexture(meta);
    }

    public AlphaSapling(Identifier identifier, int j) {
        super(identifier, j);
    }

    @Override
    public void generate(World world, int x, int y, int z, Random random) {
        int meta = world.getBlockMeta(x, y, z) & 3;
        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
        Feature treeFeature = new AlphaTreeFeature();
        if (random.nextInt(10) == 0) {
            treeFeature = new LargeAlphaTreeFeature();
        }

        if (!treeFeature.generate(world, random, x, y, z)) {
            world.setBlockWithoutNotifyingNeighbors(x, y, z, this.id, meta);
        }
    }

    @Override
    public boolean onBonemealUse(World world, int x, int y, int z, BlockState state) {
        this.generate(world, x, y, z, new Random());
        return true;
    }

    public void specifyTextures(int texture){
        this.texture = texture;
    }
}
