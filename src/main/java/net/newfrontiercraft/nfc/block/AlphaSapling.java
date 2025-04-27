package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.template.block.TemplateSaplingBlock;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.world.gen.feature.ShrubFeature;
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
        int fertility = 2;
        if (world.getBlockId(x, y - 1, z) == Block.FARMLAND.id) {
            fertility = 4;
            if (world.getBlockMeta(x, y - 1, z) > 0) {
                fertility = 16;
            }
        } else if (world.getBlockId(x, y - 1, z) == BlockListener.planter.id && world.getBlockMeta(x, y - 1, z) > 0) {
            fertility = 8;
            if (world.getBlockMeta(x, y - 1, z) > 1) {
                fertility = 32;
            }
        }
        if (random.nextInt(fertility) == 0 &&
                (world.method_1781().getBiome(x, z) == Biome.SAVANNA ||
                 world.method_1781().getBiome(x, z) == Biome.SHRUBLAND ||
                 world.method_1781().getBiome(x, z) == Biome.DESERT ||
                 world.method_1781().getBiome(x, z) == Biome.TUNDRA)) {
            world.setBlock(x, y, z, 0);
            Feature feature = new ShrubFeature(true);
            if(!feature.generate(world, random, x, y, z)) {
                world.setBlock(x, y, z, this.id, meta);
                return;
            }
            return;
        }
        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
        Feature treeFeature = new AlphaTreeFeature();
        if (random.nextInt(10) == 0 && world.getBlockId(x, y - 1, z) != BlockListener.planter.id) {
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
