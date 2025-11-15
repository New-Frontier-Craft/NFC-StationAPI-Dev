package net.newfrontiercraft.nfc.block;

import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.client.item.CustomTooltipProvider;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.BlockListener;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CoalMushroomBlock extends LazyMushroomTemplate implements CustomTooltipProvider {
    public CoalMushroomBlock(Identifier identifier, float hardness, BlockSoundGroup blockSounds, boolean lightImmune) {
        super(identifier, hardness, blockSounds, lightImmune);
        setTickRandomly(true);
    }

    @Override
    protected boolean canPlantOnTop(int id) {
        return id == BlockListener.coalBlock.id;
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        if (world.getBlockId(x, y + 1, z) != 0) {
            return;
        }
        boolean consumedMushroom = false;
        for (int side = 0; side < 4; side++) {
            consumedMushroom = switch (side) {
                case 0 -> consumePurpleMushroom(world, x + 1, y, z);
                case 1 -> consumePurpleMushroom(world, x - 1, y, z);
                case 2 -> consumePurpleMushroom(world, x, y, z + 1);
                case 3 -> consumePurpleMushroom(world, x, y, z - 1);
                default -> false;
            };
            if (consumedMushroom) {
                break;
            }
        }
        if (!consumedMushroom) {
            return;
        }
        int blockMeta = world.getBlockMeta(x, y, z);
        if (blockMeta < 3) {
            world.setBlockMeta(x, y, z, blockMeta + 1);
            return;
        }
        world.setBlock(x, y, z, BlockListener.coalMushroomBottom.id);
        world.setBlock(x, y + 1, z, BlockListener.coalMushroomTop.id);
    }

    private boolean consumePurpleMushroom(World world, int x, int y, int z) {
        if (world.getBlockId(x, y, z) == BlockListener.bioluminescentMushroom.id && world.getBlockMeta(x, y, z) == 1) {
            world.setBlock(x, y, z, 0);
            return true;
        }
        return false;
    }


    @Override
    public @NotNull String[] getTooltip(ItemStack stack, String originalTooltip) {
        return new String[] {
                originalTooltip,
                "Grows on coal blocks and consumes nearby purple mushrooms"
        };
    }
}
