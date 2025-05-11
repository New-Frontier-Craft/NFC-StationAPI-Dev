package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.block.HasCustomBlockItemFactory;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.item.BioluminescentMushroomBlockItem;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.particle.SporeParticle;
import net.newfrontiercraft.nfc.world.gen.feature.BlueMushroomFeature;
import net.newfrontiercraft.nfc.world.gen.feature.GlowingMushroomFeature;
import net.newfrontiercraft.nfc.world.gen.feature.PurpleMushroomFeature;

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
    @Environment(EnvType.CLIENT)
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
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft minecraft) {
            if (world.getBlockMeta(x, y, z) == 1)
                minecraft.particleManager.addParticle(new SporeParticle(world, d, d1, d2, 0F, 0F, 0F, 0.98F, 0.78F, 1));
            else
                minecraft.particleManager.addParticle(new SporeParticle(world, d, d1, d2, 0F, 0F, 0F, 0.78F, 0.98F, 1));
        }
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

    @Override
    public boolean onBonemealUse(World world, int x, int y, int z, BlockState state) {
        Feature feature = new BlueMushroomFeature();
        int mushroomMeta = world.getBlockMeta(x, y, z);
        if (mushroomMeta == 1) {
            feature = new PurpleMushroomFeature();
        }
        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
        if (!feature.generate(world, new Random(), x, y, z)) {
            world.setBlockWithoutNotifyingNeighbors(x, y, z, BlockListener.bioluminescentMushroom.id, mushroomMeta);
            return false;
        }
        return true;
    }
}
