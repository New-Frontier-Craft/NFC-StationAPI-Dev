package net.newfrontiercraft.nfc.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.entity.player.PlayerHelper;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.ItemListener;
import net.newfrontiercraft.nfc.mixin.PlayerInventoryAccessor;

import java.util.Random;

public class TelescopeItem extends LazyItemTemplate{
    private static final Random telescopeRandom = new Random();

    @Environment(EnvType.CLIENT)
    private static boolean scoping;

    @Environment(EnvType.CLIENT)
    public static boolean isScoping(){
        return TelescopeItem.scoping;
    }

    @Environment(EnvType.CLIENT)
    public static boolean hasTelescope(){
        PlayerEntity player = PlayerHelper.getPlayerFromGame();
        return ((PlayerInventoryAccessor)player.inventory).invokeIndexOf(ItemListener.telescopeItem.id) >= 0;
    }

    @Environment(EnvType.CLIENT)
    public static void setScoping(boolean scoping){
        TelescopeItem.scoping = scoping;
    }

    public TelescopeItem(Identifier identifier) {
        super(identifier);
        this.setMaxCount(1);
    }

    public static void toggleScoping(){
        PlayerEntity player = PlayerHelper.getPlayerFromGame();
        if(TelescopeItem.isScoping()){
            player.world.playSound(player, "nfc:nfc.sound.random.telescope_retract", 0.5F, 1.0F / (telescopeRandom.nextFloat() * 0.4F + 0.8F));
            TelescopeItem.setScoping(false);
        }
        else {
            player.world.playSound(player, "nfc:nfc.sound.random.telescope_extend", 0.5F, 1.0F / (telescopeRandom.nextFloat() * 0.4F + 0.8F));
            TelescopeItem.setScoping(true);
        }
    }

    @Override
    public ItemStack use(ItemStack stack, World world, PlayerEntity user) {
        if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
            TelescopeItem.toggleScoping();
        }
        return stack;
    }
}
