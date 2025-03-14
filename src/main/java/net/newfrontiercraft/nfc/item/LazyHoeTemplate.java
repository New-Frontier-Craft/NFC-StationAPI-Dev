package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateHoeItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyHoeTemplate extends TemplateHoeItem {
    public LazyHoeTemplate(Identifier identifier, ToolMaterial material) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
