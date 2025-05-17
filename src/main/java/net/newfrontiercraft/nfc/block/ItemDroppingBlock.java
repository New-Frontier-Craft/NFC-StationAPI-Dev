package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

public class ItemDroppingBlock extends LazyBlockTemplate {
    private Identifier dropId;
    private int dropMeta;

    public ItemDroppingBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    public void setDropId(Identifier dropId) {
        this.dropId = dropId;
    }

    public void setDropMeta(int dropMeta) {
        this.dropMeta = dropMeta;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, int x, int y, int z, int meta) {
        if (world.isRemote) return;
        dropStack(world, x, y, z, new ItemStack(ItemRegistry.INSTANCE.get(dropId), 1, dropMeta));
    }
}
