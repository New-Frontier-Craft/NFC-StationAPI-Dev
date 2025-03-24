package net.newfrontiercraft.nfc.block;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.registry.ItemRegistry;
import net.modificationstation.stationapi.api.registry.Registries;
import net.modificationstation.stationapi.api.registry.RegistryKey;
import net.modificationstation.stationapi.api.template.block.TemplateDoorBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Map;
import java.util.Random;
import java.util.Set;

public class DoorBlock extends TemplateDoorBlock {
    Identifier doorItem;
    int topTexture;
    int bottomTexture;
    public DoorBlock(Identifier identifier, Material material, Identifier doorItemId) {
        super(identifier, material);
        this.doorItem = doorItemId;
    }

    public void specifyTextures(int topTexture, int bottomTexture){
        this.topTexture = topTexture;
        this.bottomTexture = bottomTexture;
    }

    @Override
    public int getTexture(int side, int meta) {
        if (side != 0 && side != 1) {
            int var3 = this.setOpen(meta);
            if ((var3 == 0 || var3 == 2) ^ side <= 3) {
                return this.bottomTexture;
            } else {
                int var4 = var3 / 2 + (side & 1 ^ var3);
                var4 += (meta & 4) / 4;
                int var5 = ((meta & 8) == 8) ? topTexture : bottomTexture;
                if ((var4 & 1) != 0) {
                    var5 = -var5;
                }

                return var5;
            }
        } else {
            return this.bottomTexture;
        }
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random) {
        if ((blockMeta & 8) != 0) {
            return 0;
        } else {
            Item door = ItemRegistry.INSTANCE.get(doorItem);
            if(door != null){
                return door.id;
            }
            return 0;
        }
    }
}
