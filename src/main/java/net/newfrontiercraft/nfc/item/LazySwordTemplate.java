package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateSwordItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazySwordTemplate extends TemplateSwordItem {
    public LazySwordTemplate(Identifier identifier, ToolMaterial material) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
