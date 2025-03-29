package net.newfrontiercraft.nfc.particle;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.render.Tessellator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.block.CreativeBlock;
import org.lwjgl.opengl.GL11;

public class CreativeBlockParticle extends Particle implements NfcParticle{
    private final int playerItem;
    private final int playerItemMeta;

    public CreativeBlockParticle(World world, double d, double d1, double d2, double d3, double d4, double d5, int textureId, int playerItem, int playerItemMeta) {
        super(world, d, d1, d2, d3, d4, d5);
        CreativeBlock.creativeBlockParticles.add(this);
        velocityX = d3 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.025F);
        velocityY = d4 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.015F);
        velocityZ = d5 + (double) ((float) (Math.random() * 2D - 1.0D) * 0.025F);
        scale = 1.0F;
        this.textureId = textureId;
        red = green = blue = 1.0F;
        maxParticleAge = 40 + random.nextInt(10);
        this.playerItem = playerItem;
        this.playerItemMeta = playerItemMeta;
    }

    @Override
    public void tick() {
        prevX = x;
        prevY = y;
        prevZ = z;
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft minecraft && particleAge > 30) {
            PlayerEntity playerEntity = minecraft.player;
            ItemStack itemStack = playerEntity.getHand();
            if (this.getDistance(playerEntity) <= 16 && itemStack != null && itemStack.itemId == playerItem && itemStack.getDamage() == playerItemMeta) {
                particleAge -= random.nextInt(20) + 20;
            }
        }
        if (particleAge++ >= maxParticleAge) {
            markDead();
        }
    }

    @Override
    public void markDead() {
        CreativeBlock.creativeBlockParticles.remove(this);
        super.markDead();
    }

    @Override
    public void render(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glBindTexture(3553, textureId);
        float textureX = 0;
        float f7 = textureX + 1;
        float textureY = 0;
        float f9 = textureY + 1;
        float adjustedScale = scale / 2;
        float f11 = (float) ((prevX + (x - prevX) * (double) f) - xOffset);
        float f12 = (float) ((prevY + (y - prevY) * (double) f) - yOffset);
        float f13 = (float) ((prevZ + (z - prevZ) * (double) f) - zOffset);
        float f14 = getBrightnessAtEyes(f) * 1.25F;
        tessellator.color(red * f14, green * f14, blue * f14);
        tessellator.vertex(f11 - f1 * adjustedScale - f4 * adjustedScale, f12 - f2 * adjustedScale, f13 - f3 * adjustedScale - f5 * adjustedScale, f7, f9);
        tessellator.vertex((f11 - f1 * adjustedScale) + f4 * adjustedScale, f12 + f2 * adjustedScale, (f13 - f3 * adjustedScale) + f5 * adjustedScale, f7, textureY);
        tessellator.vertex(f11 + f1 * adjustedScale + f4 * adjustedScale, f12 + f2 * adjustedScale, f13 + f3 * adjustedScale + f5 * adjustedScale, textureX, textureY);
        tessellator.vertex((f11 + f1 * adjustedScale) - f4 * adjustedScale, f12 - f2 * adjustedScale, (f13 + f3 * adjustedScale) - f5 * adjustedScale, textureX, f9);


    }
}
