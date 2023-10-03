package net.newfrontiercraft.nfc.items;

import net.minecraft.item.tool.ToolMaterial;
import net.modificationstation.stationapi.api.registry.Identifier;
import net.modificationstation.stationapi.api.template.item.tool.TemplatePickaxe;

public class LazyPickaxeTemplate extends TemplatePickaxe {
    public LazyPickaxeTemplate(Identifier identifier, ToolMaterial material) {
        super(identifier, material);
        setTranslationKey(identifier.modID, identifier.id);
    }
}
