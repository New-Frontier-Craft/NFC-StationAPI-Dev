package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class ScaffoldBlock extends LazyBlockTemplate{
    public ScaffoldBlock(Identifier identifier, Material material, float hardness, BlockSoundGroup blockSounds) {
        super(identifier, material, hardness, blockSounds);
    }

    @Override
    public boolean isOpaque() {
        return false;
    }

    @Override
    public boolean canPlaceAt(World world, int x, int y, int z, int side) {
        return world.getBlockId(x, y - 1, z) != this.id && world.shouldSuffocate(x, y - 1, z) ? true : world.getBlockId(x, y - 1, z) == this.id && world.getBlockMeta(x, y - 1, z) < 7 || world.getBlockId(x + 1, y, z) == this.id && world.getBlockMeta(x + 1, y, z) < 7 || world.getBlockId(x - 1, y, z) == this.id && world.getBlockMeta(x - 1, y, z) < 7 || world.getBlockId(x, y, z + 1) == this.id && world.getBlockMeta(x, y, z + 1) < 7 || world.getBlockId(x, y, z - 1) == this.id && world.getBlockMeta(x, y, z - 1) < 7;
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        if(!world.shouldSuffocate(x, y - 1, z)) {
            if(world.getBlockId(x + 1, y, z) != this.id || world.getBlockMeta(x + 1, y, z) >= world.getBlockMeta(x, y, z)) {
                if(world.getBlockId(x - 1, y, z) != this.id || world.getBlockMeta(x - 1, y, z) >= world.getBlockMeta(x, y, z)) {
                    if(world.getBlockId(x, y, z + 1) != this.id || world.getBlockMeta(x, y, z + 1) >= world.getBlockMeta(x, y, z)) {
                        if(world.getBlockId(x, y, z - 1) != this.id || world.getBlockMeta(x, y, z - 1) >= world.getBlockMeta(x, y, z)) {
                            this.dropStacks(world, x, y, z, 0);
                            world.setBlock(x, y, z, 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, int direction) {
        if(world.getBlockId(x, y - 1, z) != this.id) {
            if(world.shouldSuffocate(x, y - 1, z)) {
                return;
            }

            int best = 6;
            if(world.getBlockId(x + 1, y, z) == this.id && world.getBlockMeta(x + 1, y, z) < best) {
                best = world.getBlockMeta(x + 1, y, z);
            }

            if(world.getBlockId(x - 1, y, z) == this.id && world.getBlockMeta(x - 1, y, z) < best) {
                best = world.getBlockMeta(x - 1, y, z);
            }

            if(world.getBlockId(x, y, z + 1) == this.id && world.getBlockMeta(x, y, z + 1) < best) {
                best = world.getBlockMeta(x, y, z + 1);
            }

            if(world.getBlockId(x, y, z - 1) == this.id && world.getBlockMeta(x, y, z - 1) < best) {
                best = world.getBlockMeta(x, y, z - 1);
            }

            world.setBlockMetaWithoutNotifyingNeighbors(x, y, z, best + 1);
        } else {
            world.setBlockMetaWithoutNotifyingNeighbors(x, y, z, world.getBlockMeta(x, y - 1, z) == 0 ? 0 : world.getBlockMeta(x, y - 1, z) + 1);
        }
    }
}
