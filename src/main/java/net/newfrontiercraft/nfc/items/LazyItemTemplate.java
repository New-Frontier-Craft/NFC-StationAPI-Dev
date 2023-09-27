package net.newfrontiercraft.nfc.items;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.TemplateItemBase;

public class LazyItemTemplate extends TemplateItemBase {

    public LazyItemTemplate(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.modID, identifier.id);
    }
}
