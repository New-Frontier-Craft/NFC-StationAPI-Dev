package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import net.newfrontiercraft.nfc.utils.FenceConnection;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FenceBlock.class)
public abstract class FenceBlockMixin extends Block implements FenceConnection {

    public FenceBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        this.updateBoundingBox(world, x, y, z);
        this.maxY = 1.5D;
        return super.getCollisionShape(world, x, y, z);
    }

    @Override
    public void updateBoundingBox(BlockView blockView, int x, int y, int z) {
        boolean isConnectedState = blockView.getBlockMeta(x, y, z) == 1;
        boolean flag = this.connectFenceAt(blockView, x, y, z - 1, isConnectedState);
        boolean flag1 = this.connectFenceAt(blockView, x, y, z + 1, isConnectedState);
        boolean flag2 = this.connectFenceAt(blockView, x - 1, y, z, isConnectedState);
        boolean flag3 = this.connectFenceAt(blockView, x + 1, y, z, isConnectedState);
        float f = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.375F;
        float f3 = 0.625F;
        if(flag) {
            f2 = 0.0F;
        }

        if(flag1) {
            f3 = 1.0F;
        }

        if(flag2) {
            f = 0.0F;
        }

        if(flag3) {
            f1 = 1.0F;
        }

        this.setBoundingBox(f, 0.0F, f2, f1, 1.0F, f3);
    }

    @Override
    public boolean connectFenceAt(BlockView blockView, int i, int j, int k, boolean isConnected) {
        int l = blockView.getBlockId(i, j, k);
        if(l != this.id && l != BlockListener.scaffoldBlock.id) {
            Block block = Block.BLOCKS[l];
            return block != null && block.material.suffocates() && block.isFullCube() && isConnected ? block.material != Material.PUMPKIN : false;
        } else {
            return true;
        }
    }
}
