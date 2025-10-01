package net.newfrontiercraft.nfc.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.World;

public class HellCreeperEntity extends CreeperEntity {

    public HellCreeperEntity(World world) {
        super(world);
        texture = "/assets/nfc/stationapi/textures/entity/redcreeper.png";
        fireImmune = true;
    }

    public HellCreeperEntity(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
    }

    @Override
    public void tick() {
        super.tick();
        // TODO: Reactivate with Blood Moon
//        if(!world.getWorldInfo().isSpecialMoonActive() && bloodMoonSpawned) {
//            CreeperEntity entitycreeper = new CreeperEntity(world);
//            entitycreeper.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
//            world.entityJoinedWorld(entitycreeper);
//            setEntityDead();
//            for (int j = 0; j < 12; j++) {
//                double d = rand.nextGaussian() * 0.02D;
//                double d1 = rand.nextGaussian() * 0.02D;
//                double d2 = rand.nextGaussian() * 0.02D;
//                world.spawnParticle("explode",
//                        (entitycreeper.posX + (double) (rand.nextFloat() * entitycreeper.width * 2.0F))
//                                - (double) entitycreeper.width,
//                        entitycreeper.posY + (double) (rand.nextFloat() * entitycreeper.height),
//                        (entitycreeper.posZ + (double) (rand.nextFloat() * entitycreeper.width * 2.0F))
//                                - (double) entitycreeper.width, d, d1, d2);
//            }
//            return;
//        }
    }

    @Override
    protected void attack(Entity entity, float f)
    {
        if(world.isRemote) {
            return;
        }
        int l = getFuseSpeed();
        if(l <= 0 && f < 3F || l > 0 && f < 7F)
        {
            if(lastFuseTime == 0)
            {
                world.playSound(this, "random.fuse", 1.0F, 0.5F);
            }
            setFuseSpeed(1);
            lastFuseTime++;
            if(lastFuseTime >= 30)
            {
                world.createExplosion(this, x, y, z, 4F, true);
                int roundedX = (int) Math.round(x);
                int roundedY = (int) Math.round(y);
                int roundedZ = (int) Math.round(z);
                for(int i = -4; i <= 4; i++) {
                    for(int j = 0; j <= 4; j++) {
                        for(int k = -4; k <= 4; k++) {
                            if(world.isAir(roundedX + i, roundedY - j, roundedZ + k)
                                    && Block.BLOCKS_OPAQUE[world.getBlockId(roundedX + i, (roundedY - j) - 1, roundedZ + k)]
                                    && random.nextInt(6) == 0) {
                                world.setBlock(roundedX + i, roundedY - j, roundedZ + k, Block.FIRE.id);
                            }
                        }
                    }
                }
                markDead();
            }
            // TODO: This seems to be added by a major base edit in the original version.
            //       So this will have to be revisited to find out what it does and if it should be ported as a mixin
//            hasAttacked = true;
        } else
        {
            setFuseSpeed(-1);
            lastFuseTime--;
            if(lastFuseTime < 0)
            {
                lastFuseTime = 0;
            }
        }
    }
    
    @Override
    protected void dropItems() {
        int i = getDroppedItemId();
        if (i > 0) {
            int j = random.nextInt(9);
            for (int k = 0; k < j; k++) {
                dropItem(i, 1);
            }

        }
    }
}
