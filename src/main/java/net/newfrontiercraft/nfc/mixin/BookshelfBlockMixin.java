package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BookshelfBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.gui.screen.container.GuiHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.block.entity.BookshelfBlockEntity;
import net.newfrontiercraft.nfc.block.entity.BrickOvenBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockEntityListener;
import net.newfrontiercraft.nfc.inventory.BookshelfScreenHandler;
import net.newfrontiercraft.nfc.inventory.BrickOvenScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BookshelfBlock.class)
public abstract class BookshelfBlockMixin extends Block {
    private Random random = new Random();
    public BookshelfBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    void nfcBookshelf(int id, int textureId, CallbackInfo ci){
        BLOCKS_WITH_ENTITY[id] = true;
    }

    @Override
    public void onPlaced(World world, int x, int y, int z, LivingEntity placer) {
        super.onPlaced(world, x, y, z, placer);
        world.setBlockEntity(x, y, z, getBlockEntity());
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);
        if(blockEntity == null){
            world.setBlockEntity(x, y, z, getBlockEntity());
            blockEntity = world.getBlockEntity(x, y, z);
        }
        if (blockEntity instanceof BookshelfBlockEntity bookshelfBlockEntity){
            GuiHelper.openGUI(player, Identifier.of(BlockEntityListener.MOD_ID, "gui_bookshelf"),
                    bookshelfBlockEntity, new BookshelfScreenHandler(player.inventory, bookshelfBlockEntity));
        }
        return true;
    }

    @Override
    public void onBreak(World world, int x, int y, int z) {
        BlockEntity blockEntity = world.getBlockEntity(x, y, z);

        if(blockEntity != null){
            BookshelfBlockEntity bookshelfBlockEntity = (BookshelfBlockEntity) blockEntity;
            for(int l = 0; l < bookshelfBlockEntity.size(); ++l) {
                ItemStack itemstack = bookshelfBlockEntity.getStack(l);
                if(itemstack != null) {
                    float f = this.random.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.random.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.random.nextFloat() * 0.8F + 0.1F;

                    while(itemstack.count > 0) {
                        int i1 = this.random.nextInt(21) + 10;
                        if(i1 > itemstack.count) {
                            i1 = itemstack.count;
                        }

                        itemstack.count -= i1;
                        ItemEntity entityitem = new ItemEntity(world, ((float)x + f), ((float)y + f1), ((float)z + f2), itemstack.copy());
                        float f3 = 0.05F;
                        entityitem.velocityX = ((float)this.random.nextGaussian() * f3);
                        entityitem.velocityY = ((float)this.random.nextGaussian() * f3 + 0.2F);
                        entityitem.velocityZ = ((float)this.random.nextGaussian() * f3);
                        world.spawnEntity(entityitem);
                    }
                }
            }
        }

        super.onBreak(world, x, y, z);
        world.removeBlockEntity(x, y, z);
    }

    BookshelfBlockEntity getBlockEntity(){
        return new BookshelfBlockEntity();
    }
}
