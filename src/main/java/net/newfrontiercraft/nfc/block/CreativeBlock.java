package net.newfrontiercraft.nfc.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.Box;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.particle.CreativeBlockParticle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreativeBlock extends LazyMultivariantBlockTemplate {
    private final boolean[] canCollide;
    private int[] particleTextures;
    public static List<CreativeBlockParticle> creativeBlockParticles = new ArrayList<>();

    public CreativeBlock(Identifier identifier, Material material, BlockSoundGroup blockSounds, boolean[] canCollide) {
        super(identifier, material, -1.0F, blockSounds);
        setResistance(6000000F);
        this.canCollide = canCollide;
    }

    @Override
    public int getDroppedItemCount(Random random) {
        return 0;
    }

    @Override
    public Box getCollisionShape(World world, int x, int y, int z) {
        int meta = world.getBlockMeta(x, y, z);
        if (meta >= canCollide.length) {
            return super.getCollisionShape(world, x, y, z);
        }
        return canCollide[meta] ? super.getCollisionShape(world, x, y, z) : null;
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
    public boolean isSideVisible(BlockView blockView, int x, int y, int z, int side) {
        return false;
    }

    public void setParticleTextures(int[] particleTextures) {
        this.particleTextures = particleTextures;
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (FabricLoader.getInstance().getGameInstance() instanceof Minecraft minecraft) {
            PlayerEntity player = minecraft.player;
            ItemStack heldItem = player.getHand();
            if (heldItem == null) {
                return;
            }
            int itemId = heldItem.itemId;
            int meta = heldItem.getDamage();
            Block block = Block.BLOCKS[world.getBlockId(x, y, z)];
            if (block == null) {
                return;
            }
            if (block.asItem().id != itemId || meta != world.getBlockMeta(x, y, z)) {
                return;
            }
            for (CreativeBlockParticle particle : creativeBlockParticles) {
                if((int)particle.x == x && (int)particle.y == y && (int)particle.z == z) {
                    return;
                }
                if (meta >= particleTextures.length) {
                    return;
                }
            }
            minecraft.particleManager.addParticle(new CreativeBlockParticle(world, x + 0.5, y + 0.5, z + 0.5, 0F, 0F, 0F, particleTextures[meta], itemId, meta));
        }
    }
}
