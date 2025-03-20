package net.newfrontiercraft.nfc.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LadderBlock.class)
public abstract class LadderBlockMixin extends Block {
    public LadderBlockMixin(int id, Material material) {
        super(id, material);
    }

    @Override
    public boolean onUse(World world, int x, int y, int z, PlayerEntity player) {
        if(player.getHand() == null || player.getHand().itemId != this.id){
            return false;
        }
        boolean loop = true;
        boolean found = false;
        int nextLadderY = y;

        while(loop) {
            nextLadderY--;
            int id = world.getBlockId(x, nextLadderY, z);
            if(nextLadderY < 0) {
                break;
            }

            if(id == 0) {
                loop = false;
                found = true;
            } else if(id != Block.LADDER.id) {
                loop = false;
            }
        }

        if(!player.isSneaking() && found) {
            world.setBlock(x, nextLadderY, z, Block.LADDER.id, world.getBlockMeta(x, nextLadderY + 1, z));
            world.playSound(((float)x + 0.5F), ((float)y + 0.5F), ((float)z + 0.5F), this.soundGroup.getSound(), (this.soundGroup.getVolume() + 1.0F) / 2.0F, this.soundGroup.getPitch() * 0.8F);
            player.getHand().count--;
            return true;
        } else {
            return false;
        }
    }

    @Inject(method = "neighborUpdate", at = @At("HEAD"), cancellable = true)
    private void nfcLadder(World world, int x, int y, int z, int id, CallbackInfo ci) {
        if(world.getBlockId(x, y + 1, z) == this.id){
            super.neighborUpdate(world, x, y, z, id);
            ci.cancel();
        }
    }
}
