package net.newfrontiercraft.nfc.items;

import net.modificationstation.stationapi.api.template.item.TemplateFoodItem;
import net.modificationstation.stationapi.api.util.Identifier;

public class LazyFoodTemplate extends TemplateFoodItem {

    public LazyFoodTemplate(Identifier identifier, int healAmount, boolean isWolfFood) {
        super(identifier, healAmount, isWolfFood);
        setTranslationKey(identifier.namespace, identifier.path);
    }
}
