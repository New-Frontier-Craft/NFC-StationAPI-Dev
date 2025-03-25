package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.mixin.FireImmunityAccessor;

import java.util.Random;

public class ScorchedSandBlock extends LazySandTemplate {
    public ScorchedSandBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, int fallingRarity) {
        super(identifier, hardness, blockSounds, fallingRarity);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        float f = 0.125F;
        return Box.createCached(x, y, z, x + 1, (float)(y + 1) - f, z + 1);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        boolean flag = true;

        if(Block.BLOCKS[world.getBlockId(x, y + 1, z)] != null)
            if(Block.BLOCKS[world.getBlockId(x, y + 1, z)].isOpaque())
                flag = false;

        if(flag) {
            float m = random.nextFloat();
            double d = (float)x + m;
            double d1 = (float)y + 1;
            double d2 = (float)z + m;

            if(random.nextInt(25) == 0) {
                if(random.nextInt(5) == 0) {
                    world.addParticle("largesmoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
                } else {
                    world.addParticle("smoke", d, d1, d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Override
    public void onEntityCollision(World world, int x, int y, int z, Entity entity) {
        ItemStack stack = null;
        if (entity instanceof PlayerEntity) {
            stack = ((PlayerEntity) entity).inventory.getStack(36);
        }

        if (!((FireImmunityAccessor)entity).getFireImmune() && stack == null && !(entity instanceof ItemEntity)) {
            entity.damage(null, 1);
        }
    }
}
