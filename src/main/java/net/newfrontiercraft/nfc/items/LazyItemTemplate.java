package net.newfrontiercraft.nfc.items;

import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyItemTemplate extends TemplateItem {

    public LazyItemTemplate(Identifier identifier) {
        super(identifier);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
