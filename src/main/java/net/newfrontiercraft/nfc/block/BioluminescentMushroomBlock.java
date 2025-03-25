package net.newfrontiercraft.nfc.block;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.item.BioluminescentMushroomBlockItem;

import java.util.Random;

@HasCustomBlockItemFactory(BioluminescentMushroomBlockItem.class)
public class BioluminescentMushroomBlock extends LazyMushroomTemplate {
    private int[] metaSpecificTextures;
    private int tick;

    public BioluminescentMushroomBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, hardness, blockSounds, lightImmune);
        float f = 0.2F;
        setBoundingBox(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setLuminance(0.625F);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        float l = 0;
        if (world.getBlockMeta(x, y, z) == 1)
            l = 0.0625F * 2;
        if(tick > 10){
            tick = 0;
        }
        else {
            tick++;
            return;
        }

        double d = (float) x + 0.5F;
        double d1 = (float) y + 0.2F + l;
        double d2 = (float) z + 0.5F;
        if (world.getBlockMeta(x, y, z) == 1)
            world.addParticle("reddust", d, d1, d2, 0.5D, 0.0D, 0.5D); // TODO: Replace particle with sporepurple and set velocity values to 0
        else
            world.addParticle("reddust", d, d1, d2, 0.0D, 0.0D, 0.5D); // TODO: Replace particle with sporeblue and set velocity values to 0
    }

    @Override
    public int getTexture(int side, int meta) {
        if (meta >= metaSpecificTextures.length) {
            return super.getTexture(side, meta);
        }
        return metaSpecificTextures[meta];
    }

    public void specifyTextures(int[] metaSpecificTextures) {
        this.metaSpecificTextures = metaSpecificTextures;
    }

    @Override
    protected int getDroppedItemMeta(int blockMeta) {
        return blockMeta & 1;
    }
}
