package net.newfrontiercraft.nfc.world.gen;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.LazyPillarBlockTemplate;

public class FrameBlock extends LazyPillarBlockTemplate {
    public FrameBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        int axis = getAxisAlignment(world, x, y, z);
        return switch (axis) {
            case 0 ->
                    Box.createCached(((float) x + 0.5F) - 0.25F, (float) y, ((float) z + 0.5F) - 0.25F, (float) x + 0.5F + 0.25F, (float) y + 1.0F, (float) z + 0.5F + 0.25F);
            case 1 ->
                    Box.createCached(((float) x + 0.5F) - 0.25F, ((float) y + 0.5F) - 0.25F, (float) z, (float) x + 0.5F + 0.25F, (float) y + 0.5F + 0.25F, (float) z + 1.0F);
            default ->
                    Box.createCached((float) x, ((float) y + 0.5F) - 0.25F, ((float) z + 0.5F) - 0.25F, (float) x + 1.0F, (float) y + 0.5F + 0.25F, (float) z + 0.5F + 0.25F);
        };
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        int axis = getAxisAlignment(blockView, x, y, z);
        switch (axis) {
            case 0:
                setBoundingBox(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;
            case 1:
                setBoundingBox(0.25F, 0.25F, 0.0F, 0.75F, 0.75F, 1.0F);
                break;
            default:
                setBoundingBox(0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
                break;
        }
    }

    public int getAxisAlignment(BlockView blockView, int x, int y, int z) {
        return blockView.getBlockMeta(x, y, z);
    }
}
