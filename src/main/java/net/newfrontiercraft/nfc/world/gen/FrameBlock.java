package net.newfrontiercraft.nfc.world.gen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.EnvironmentInterface;
import net.minecraft.block.material.Material;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.model.block.BlockWithInventoryRenderer;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.LazyPillarBlockTemplate;
import org.lwjgl.opengl.GL11;

@EnvironmentInterface(value= EnvType.CLIENT, itf= BlockWithInventoryRenderer.class)
public class FrameBlock extends LazyPillarBlockTemplate implements BlockWithInventoryRenderer {
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

    @Override
    public void renderInventory(BlockRenderManager blockRenderManager, int meta) {
        Tessellator tessellator = Tessellator.INSTANCE;
        this.setBoundingBox(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        tessellator.startQuads();
        tessellator.normal(0.0F, -1.0F, 0.0F);
        blockRenderManager.renderBottomFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(0, 0));
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(0.0F, 1.0F, 0.0F);
        blockRenderManager.renderTopFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(1, 0));
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(0.0F, 0.0F, -1.0F);
        blockRenderManager.renderEastFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(2, 0));
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(0.0F, 0.0F, 1.0F);
        blockRenderManager.renderWestFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(3, 0));
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(-1.0F, 0.0F, 0.0F);
        blockRenderManager.renderSouthFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(4, 0));
        tessellator.draw();
        tessellator.startQuads();
        tessellator.normal(1.0F, 0.0F, 0.0F);
        blockRenderManager.renderNorthFace(this, 0.0F, 0.0F, 0.0F, this.getTexture(5, 0));
        tessellator.draw();
        GL11.glTranslatef(0.5F, 0.5F, 0.5F);
        this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);this.setBoundingBox(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
}
