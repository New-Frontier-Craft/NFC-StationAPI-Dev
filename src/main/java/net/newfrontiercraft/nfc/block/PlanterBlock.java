package net.newfrontiercraft.nfc.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.HasMetaNamedBlockItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.TextureListener;

import java.util.Random;

@HasMetaNamedBlockItem
public class PlanterBlock extends LazyBlockTemplate {
    public PlanterBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
        setTickRandomly(true);
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side == 1) {
            switch (meta) {
                case 0:
                    return TextureListener.planterEmpty;
                case 1:
                    return TextureListener.planterFilled;
                case 2:
                    return TextureListener.planterIrrigated;
            }
        }
        return TextureListener.planterSide;
    }

    @Override
    protected int getDroppedItemMeta(int i) {
        return Math.min(i, 1);
    }

    @Override
    public int getTickRate() {
        return 8;
    }

    @Override
    public void onTick(World world, int i, int j, int k, Random random) {
        int metadata = world.getBlockMeta(i, j, k);
        if (metadata < 1) {
            return;
        }
        int aboveId = world.getBlockId(i, j + 1, k);
        if (metadata == 2 && (aboveId == Block.WHEAT.id || aboveId == Block.SAPLING.id
                || aboveId == Block.SUGAR_CANE.id /*|| aboveId == mod_NFC.berryBush.id TODO: Reactivate when berry bushes are added*/)) {
            world.scheduleBlockUpdate(i, j, k, this.id, getTickRate());
            world.scheduleBlockUpdate(i, j + 1, k, aboveId, getTickRate());
        }
        if(random.nextInt(5) == 0) {
            if(isWaterNearby(world, i, j, k) /*|| world.canBlockBeRainedOn(i, j + 1, k)*/) {
                world.setBlockMeta(i, j, k, 2);
            } else if(metadata > 1) {
                world.setBlockMeta(i, j, k, metadata - 1);
            }
        }
    }

    private boolean isWaterNearby(World world, int i, int j, int k) {
        for(int l = i - 4; l <= i + 4; l++) {
            for(int i1 = j; i1 <= j + 1; i1++) {
                for(int j1 = k - 4; j1 <= k + 4; j1++) {
                    if(world.getMaterial(l, i1, j1) == Material.WATER) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
