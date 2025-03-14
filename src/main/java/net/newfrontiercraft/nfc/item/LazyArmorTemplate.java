package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ArmorItem;
import net.modificationstation.stationapi.api.client.item.ArmorTextureProvider;
import net.modificationstation.stationapi.api.template.item.TemplateArmorItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.newfrontiercraft.nfc.events.init.ItemListener;

public class LazyArmorTemplate extends TemplateArmorItem implements ArmorTextureProvider {

    private final String modelTexturePath;

    public LazyArmorTemplate(Identifier identifier, int armorType, int damage, String modelTexturePath) {
        super(identifier, 1, 1, armorType);
        setMaxDamage(damage);
        this.modelTexturePath = modelTexturePath;
        setTranslationKey(identifier.namespace, identifier.path);
    }

    @Override
    public Identifier getTexture(ArmorItem armour) {
        return ItemListener.MOD_ID.id(modelTexturePath);
    }
}
