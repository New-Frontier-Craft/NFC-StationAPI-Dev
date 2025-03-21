package net.newfrontiercraft.nfc.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.hit.HitResultType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateBucketItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.events.init.Materials;

public class OilBucketItem extends TemplateBucketItem {

    private int isFull;
    public OilBucketItem(Identifier identifier, int j) {
        super(identifier, j);
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        float f = 1.0F;
        float f1 = user.prevPitch + (user.pitch - user.prevPitch) * f;
        float f2 = user.prevYaw + (user.yaw - user.prevYaw) * f;
        double d = user.prevX + (user.x - user.prevX) * (double)f;
        double d1 = user.prevY + (user.y - user.prevY) * (double)f + 1.62D - (double)user.standingEyeHeight;
        double d2 = user.prevZ + (user.z - user.prevZ) * (double)f;
        Vec3d vec3d = Vec3d.create(d, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.01745329F - 3.141593F);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - 3.141593F);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f9 = f3 * f5;
        double d3 = 5.0D;
        Vec3d vec3d1 = vec3d.add((double)f7 * d3, (double)f6 * d3, (double)f9 * d3);
        HitResult hitResult = world.raycast(vec3d, vec3d1, true);
        if(hitResult == null) {
            return stack;
        }
        else {
            if (hitResult.type == HitResultType.BLOCK) {
                int i = hitResult.blockX;
                int j = hitResult.blockY;
                int k = hitResult.blockZ;
                if (!world.canInteract(user, i, j, k)) {
                    return stack;
                }

                if (hitResult.side == 0) {
                    --j;
                }

                if (hitResult.side == 1) {
                    ++j;
                }

                if (hitResult.side == 2) {
                    --k;
                }

                if (hitResult.side == 3) {
                    ++k;
                }

                if (hitResult.side == 4) {
                    --i;
                }

                if (hitResult.side == 5) {
                    ++i;
                }

                if (world.isAir(i, j, k) || !world.getMaterial(i, j, k).isSolid()) {
                    world.setBlock(i, j, k, BlockListener.oilFlowing.id, 0);
                    return new ItemStack(Item.BUCKET);
                }
            }
            return stack;
        }
    }
}
