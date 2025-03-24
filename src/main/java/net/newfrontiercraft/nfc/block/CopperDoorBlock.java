package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class CopperDoorBlock extends DoorBlock {
    public CopperDoorBlock(Identifier identifier, Material material, Identifier doorItemId) {
        super(identifier, material, doorItemId);
    }

    @Override
    public void neighborUpdate(World world, int x, int y, int z, int id) {
        int var6 = world.getBlockMeta(x, y, z);
        if ((var6 & 8) != 0) {
            if (world.getBlockId(x, y - 1, z) != this.id) {
                world.setBlock(x, y, z, 0);
            }
        } else {
            boolean var7 = false;
            if (world.getBlockId(x, y + 1, z) != this.id) {
                world.setBlock(x, y, z, 0);
                var7 = true;
            }

            if (!world.shouldSuffocate(x, y - 1, z)) {
                world.setBlock(x, y, z, 0);
                var7 = true;
                if (world.getBlockId(x, y + 1, z) == this.id) {
                    world.setBlock(x, y + 1, z, 0);
                }
            }

            if (var7) {
                if (!world.isRemote) {
                    this.dropStacks(world, x, y, z, var6);
                }
            }
        }
    }
}
