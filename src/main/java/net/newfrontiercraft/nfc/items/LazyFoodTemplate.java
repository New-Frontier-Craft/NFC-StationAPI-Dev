package net.newfrontiercraft.nfc.items;

import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.food.TemplateFoodBase;

public class LazyFoodTemplate extends TemplateFoodBase {

    public LazyFoodTemplate(Identifier identifier, int healAmount, boolean isWolfFood) {
        super(identifier, healAmount, isWolfFood);
        setTranslationKey(identifier.modID, identifier.id);
    }
}
