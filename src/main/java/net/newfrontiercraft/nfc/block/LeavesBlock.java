package net.newfrontiercraft.nfc.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.template.block.TemplateLeavesBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class LeavesBlock extends TemplateLeavesBlock {
    int[] decayRegion;
    int fastTexture;
    int fancyTexture;
    int logBlockId;
    Identifier dropID;

    public LeavesBlock(Identifier identifier, int logBlockId, Identifier dropID) {
        super(identifier, 0);
        this.logBlockId = logBlockId;
        this.dropID = dropID;
    }

    public void specifyTextures(int fastTexture, int fancyTexture){
        this.fastTexture = fastTexture;
        this.fancyTexture = fancyTexture;
    }

    @Override
    public void setFancyGraphics(boolean bl) {
        this.renderSides = bl;
        this.textureId = bl ? fancyTexture : fastTexture;
    }

    @Override
    public int getTexture(int side) {
        return textureId;
    }

    @Override
    public int getColorMultiplier(BlockView blockView, int x, int y, int z) {
        return 0xFFFFFF;
    }

    @Override
    public int getColor(int meta) {
        return 0xFFFFFF;
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        byte var5 = 1;
        int var6 = var5 + 1;
        if (world.isRegionLoaded(x - var6, y - var6, z - var6, x + var6, y + var6, z + var6)) {
            for(int var7 = -var5; var7 <= var5; ++var7) {
                for(int var8 = -var5; var8 <= var5; ++var8) {
                    for(int var9 = -var5; var9 <= var5; ++var9) {
                        int var10 = world.getBlockId(x + var7, y + var8, z + var9);
                        if (var10 == this.id) {
                            int var11 = world.getBlockMeta(x + var7, y + var8, z + var9);
                            world.setBlockMetaWithoutNotifyingNeighbors(x + var7, y + var8, z + var9, var11 | 8);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onTick(World world, int x, int y, int z, Random random) {
        if (!world.isRemote) {
            int blockMeta = world.getBlockMeta(x, y, z);
            if ((blockMeta & 8) != 0) {
                byte decayRadius = 4;
                int var8 = decayRadius + 1;
                byte cubeSize = 32;
                int cubeVolume = cubeSize * cubeSize;
                int var11 = cubeSize / 2;
                if (this.decayRegion == null) {
                    this.decayRegion = new int[cubeSize * cubeSize * cubeSize];
                }

                if (world.isRegionLoaded(x - var8, y - var8, z - var8, x + var8, y + var8, z + var8)) {
                    for(int xOffset = -decayRadius; xOffset <= decayRadius; ++xOffset) {
                        for(int yOffset = -decayRadius; yOffset <= decayRadius; ++yOffset) {
                            for(int zOffset = -decayRadius; zOffset <= decayRadius; ++zOffset) {
                                int blockId = world.getBlockId(x + xOffset, y + yOffset, z + zOffset);
                                int index = (xOffset + var11) * cubeVolume + (yOffset + var11) * cubeSize + zOffset + var11;
                                if (blockId == logBlockId) {
                                    this.decayRegion[index] = 0;
                                } else if (blockId == id) {
                                    this.decayRegion[index] = -2;
                                } else {
                                    this.decayRegion[index] = -1;
                                }
                            }
                        }
                    }

                    for(int var16 = 1; var16 <= 4; ++var16) {
                        for(int var18 = -decayRadius; var18 <= decayRadius; ++var18) {
                            for(int var19 = -decayRadius; var19 <= decayRadius; ++var19) {
                                for(int var20 = -decayRadius; var20 <= decayRadius; ++var20) {
                                    if (this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11] == var16 - 1) {
                                        if (this.decayRegion[(var18 + var11 - 1) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11] == -2) {
                                            this.decayRegion[(var18 + var11 - 1) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11] = var16;
                                        }

                                        if (this.decayRegion[(var18 + var11 + 1) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11] == -2) {
                                            this.decayRegion[(var18 + var11 + 1) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11] = var16;
                                        }

                                        if (this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11 - 1) * cubeSize + var20 + var11] == -2) {
                                            this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11 - 1) * cubeSize + var20 + var11] = var16;
                                        }

                                        if (this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11 + 1) * cubeSize + var20 + var11] == -2) {
                                            this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11 + 1) * cubeSize + var20 + var11] = var16;
                                        }

                                        if (this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11) * cubeSize + (var20 + var11 - 1)] == -2) {
                                            this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11) * cubeSize + (var20 + var11 - 1)] = var16;
                                        }

                                        if (this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11 + 1] == -2) {
                                            this.decayRegion[(var18 + var11) * cubeVolume + (var19 + var11) * cubeSize + var20 + var11 + 1] = var16;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                int var17 = this.decayRegion[var11 * cubeVolume + var11 * cubeSize + var11];
                if (var17 >= 0) {
                    world.setBlockMetaWithoutNotifyingNeighbors(x, y, z, blockMeta & -9);
                } else {
                    this.breakLeaves(world, x, y, z);
                }
            }

        }
    }

    private void breakLeaves(World world, int x, int y, int z) {
        this.dropStacks(world, x, y, z, world.getBlockMeta(x, y, z));
        world.setBlock(x, y, z, 0);
    }

    @Override
    public void afterBreak(World world, PlayerEntity playerEntity, int x, int y, int z, int meta) {
        if (!world.isRemote && playerEntity.getHand() != null && playerEntity.getHand().itemId == Item.SHEARS.id) {
//            playerEntity.increaseStat(Stats.MINE_BLOCK[this.id], 1);
            this.dropStack(world, x, y, z, new ItemStack(id, 1, meta & 3));
        } else {
            int droppedCount = getDroppedItemCount(new Random());
            if (droppedCount > 0) {
                dropStack(world, x, y, z, new ItemStack(ItemRegistry.INSTANCE.get(dropID), droppedCount, 0));
            }
        }
    }
}
