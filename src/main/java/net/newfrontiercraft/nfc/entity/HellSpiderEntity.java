package net.newfrontiercraft.nfc.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.EntityListener;

import java.util.Random;

public class HellSpiderEntity extends SpiderEntity implements MobSpawnDataProvider {

    public HellSpiderEntity(World world) {
        super(world);
        texture = "/assets/nfc/stationapi/textures/entity/brownspider.png";
        fireImmune = true;
        health = 30;
    }

    public HellSpiderEntity(World world, double x, double y, double z) {
        this(world);
        setPosition(x, y, z);
    }

    public void tick() {
        super.tick();

        // TODO: Reactivate with Blood Moon
//        if(!world.worldInfo.isSpecialMoonActive() && bloodMoonSpawned) {
//            EntitySpider entityspider = new EntitySpider(world);
//            entityspider.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
//            world.entityJoinedWorld(entityspider);
//            setEntityDead();
//            for (int j = 0; j < 12; j++) {
//                double d = rand.nextGaussian() * 0.02D;
//                double d1 = rand.nextGaussian() * 0.02D;
//                double d2 = rand.nextGaussian() * 0.02D;
//                world.spawnParticle("explode",
//                        (entityspider.posX + (double) (rand.nextFloat() * entityspider.width * 2.0F))
//                                - (double) entityspider.width,
//                        entityspider.posY + (double) (rand.nextFloat() * entityspider.height),
//                        (entityspider.posZ + (double) (rand.nextFloat() * entityspider.width * 2.0F))
//                                - (double) entityspider.width, d, d1, d2);
//            }
//            return;
//        }

        Random rand = new Random();
        if (rand.nextInt(6) == 0) {
            double particleX = (rand.nextDouble() + rand.nextDouble() + x) - 1;
            double particleY = (rand.nextDouble() + y);
            double particleZ = (rand.nextDouble() + rand.nextDouble() + z) - 1;
            world.addParticle("flame", particleX, particleY, particleZ, 0, 0, 0);
            world.addParticle("smoke", particleX, particleY, particleZ, 0, 0, 0);
        }
    }

    protected void dropItems() {
        int i = getDroppedItemId();
        if (i > 0) {
            int j = random.nextInt(9);
            for (int k = 0; k < j; k++) {
                dropItem(i, 1);
            }

        }
    }

    protected Entity getTargetInRange()
    {
        double d = 16D;
        return world.getClosestPlayer(this, d);
    }

    protected void attack(Entity entity, float f)
    {
        if(f > 2.0F && f < 6F && random.nextInt(10) == 0)
        {
            if(onGround)
            {
                double d = entity.x - x;
                double d1 = entity.z - z;
                float f2 = MathHelper.sqrt(d * d + d1 * d1);
                velocityX = (d / (double)f2) * 0.5D * 0.80000001192092896D + velocityX * 0.20000000298023224D;
                velocityZ = (d1 / (double)f2) * 0.5D * 0.80000001192092896D + velocityZ * 0.20000000298023224D;
                velocityY = 0.40000000596046448D;
            }
        } else if(attackCooldown <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
        {
            entity.fireTicks = 60;
            attackCooldown = 20;
            entity.damage(this, attackDamage);
        }
    }

    @Override
    public Identifier getHandlerIdentifier() {
        return EntityListener.NAMESPACE.id("HellSpider");
    }
}
