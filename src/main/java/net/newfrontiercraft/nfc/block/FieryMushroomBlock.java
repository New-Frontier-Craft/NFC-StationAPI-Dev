package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.mixin.FireImmunityAccessor;
import net.newfrontiercraft.nfc.world.gen.feature.FieryMushroomFeature;

import java.util.Random;

public class FieryMushroomBlock extends LazyMushroomTemplate {
    public FieryMushroomBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, hardness, blockSounds, lightImmune);
        float f = 0.2F;
        setBoundingBox(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setLuminance(0.695F);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(World world, int i, int j, int k, Random random) {
        float m = random.nextFloat() / 1.6F;
        float n = random.nextFloat() / 1.6F;
        float o = random.nextFloat() / 1.6F;
        double d = (float)i + 0.1875F + n;
        double d1 = (float)j + .25 + o;
        double d2 = (float)k + 0.1875F + m;

        if(random.nextInt(4) == 0) {
            world.addParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
            world.addParticle("flame", d, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        Random r = new Random();
        if (r.nextInt(80) == 0 && !(entity instanceof ItemEntity) && !((FireImmunityAccessor) entity).getFireImmune()) {
            entity.damage(null, 1);
            entity.fireTicks = 80;
        }
    }

    @Override
    public void onPlaced(World world, int i, int j, int k) {
        if(!world.isRemote) {
            if (world.getBlockId(i, j - 1, k) == Block.OBSIDIAN.id) {
                world.setBlock(i, j, k, 0);
                if(!Block.NETHER_PORTAL.create(world, i, j, k)) {
                    world.setBlock(i, j, k, BlockListener.fieryMushroom.id);
                }
            }
        }
    }

    @Override
    public boolean onBonemealUse(World world, int x, int y, int z, BlockState state) {
        world.setBlockWithoutNotifyingNeighbors(x, y, z, 0);
        FieryMushroomFeature feature = new FieryMushroomFeature();
        if (!feature.generate(world, new Random(), x, y, z)) {
            world.setBlockWithoutNotifyingNeighbors(x, y, z, BlockListener.fieryMushroom.id);
            return false;
        }
        return true;
    }
}
