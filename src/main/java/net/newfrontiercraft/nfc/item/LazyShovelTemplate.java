package net.newfrontiercraft.nfc.item;

import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.template.item.TemplateShovelItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyShovelTemplate extends TemplateShovelItem {
    public LazyShovelTemplate(Identifier identifier, ToolMaterial material) {
        super(identifier, material);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
