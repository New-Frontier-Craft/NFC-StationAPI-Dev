package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyPickaxeTemplate extends TemplatePickaxeItem {
    public LazyPickaxeTemplate(Identifier identifier, ToolMaterial material) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
