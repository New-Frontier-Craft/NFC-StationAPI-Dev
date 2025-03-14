package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateAxeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyAxeTemplate extends TemplateAxeItem {
    public LazyAxeTemplate(Identifier identifier, ToolMaterial material) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
