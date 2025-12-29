package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LogBlock.class)
public abstract class LogRotationMixin extends Block {
    public LogRotationMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public void onPlaced(World world, int i, int j, int k, LivingEntity entityliving) {
        if (BlockListener.isVbePresent) {
            return;
        }
        int logVariant = 0;
        if (entityliving instanceof PlayerEntity) {
            ItemStack heldItem = ((PlayerEntity) entityliving).getHand();
            if (heldItem != null) logVariant = heldItem.getDamage();
        }
        int rotation = getBlockRotation(world, i, j, k, (PlayerEntity)entityliving);
        if (rotation == 0) {
            return;
        }
        if (logVariant == 0) {
            world.setBlock(i, j, k, BlockListener.rotatedOakLog.id);
        } else if (logVariant == 1) {
            world.setBlock(i, j, k, BlockListener.rotatedSpruceLog.id);
        } else {
            world.setBlock(i, j, k, BlockListener.rotatedBirchLog.id);
        }
        world.setBlockMeta(i, j, k, rotation);
    }

    private static int getBlockRotation(World world, int i, int j, int k, PlayerEntity entityplayer) {
        if(MathHelper.abs((float)entityplayer.x - (float)i) < 2.0F && MathHelper.abs((float)entityplayer.z - (float)k) < 2.0F) {
            double d = (entityplayer.y + 1.8200000000000001D) - (double)entityplayer.standingEyeHeight;
            if(d - (double)j > 2D) {
                return 0;
            }
            if((double)j - d > 0.0D) {
                return 0;
            }
        }
        int l = MathHelper.floor((double)((entityplayer.yaw * 4F) / 360F) + 0.5D) & 3;
        if(l == 0) {
            return 1;
        }
        if(l == 1) {
            return 2;
        }
        if(l == 2) {
            return 1;
        }
        return 2;
    }
}
