package net.newfrontiercraft.nfc.mixin.graphics;

import net.minecraft.item.CoalItem;
import net.minecraft.item.Item;
import net.newfrontiercraft.nfc.events.init.TextureListener;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(CoalItem.class)
public abstract class CharcoalTextureMixin extends Item {
    public CharcoalTextureMixin(int id) {
        super(id);
    }

    @Override
    public int getTextureId(int damage) {
        if (damage == 1) {
            return TextureListener.charcoal;
        }
        return super.getTextureId(damage);
    }
}
