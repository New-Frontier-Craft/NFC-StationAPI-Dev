package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.modificationstation.stationapi.api.util.Null;
import net.newfrontiercraft.nfc.item.LazyFoodTemplate;
import net.newfrontiercraft.nfc.item.LazyItemTemplate;
import net.newfrontiercraft.nfc.item.LazyPickaxeTemplate;

public class ItemListener {

    public static LazyFoodTemplate
            cookedEgg;

    public static LazyItemTemplate
            aluminiumIngot,
            copperIngot,
            tinIngot,
            bismuthIngot,
            zincIngot,
            nickelIngot,
            cobaltIngot,
            tungstenIngot,
            magnetiteIngot,
            silverIngot,
            leadIngot,
            siliconIngot,
            chromeIngot,
            titaniumIngot,
            uraniumIngot,
            platinumIngot,
            boronIngot,
            brassIngot,
            bronzeIngot,
            steelIngot,
            osmiumIngot,

            anthracite,
            netherAsh,
            onyx,
            sapphire,
            ruby,
            emerald;

    public static TemplatePickaxeItem
            bronzePickaxe;


    @Entrypoint.Namespace
    public static final Namespace MOD_ID = Null.get();

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        //ToolMaterialFactory.create("bronze", 1, 420, 4.0F, 1);
        //ToolMaterial.valueOf("bronze").inheritsFrom(ToolMaterial.STONE);
        //ToolMaterial.valueOf("bronze").requiredBlockTag(Identifier.of("needs_bronze_tool"));
        //ToolMaterial.IRON.inheritsFrom(ToolMaterial.valueOf("bronze"));

        bronzePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "bronze_pickaxe"), ToolMaterial.IRON); // TODO: Replace with actual tool material

        cookedEgg = new LazyFoodTemplate(Identifier.of(MOD_ID, "cooked_egg"), 4, false);

        aluminiumIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "aluminium_ingot"));
        copperIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "copper_ingot"));
        tinIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "tin_ingot"));
        bismuthIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "bismuth_ingot"));
        zincIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "zinc_ingot"));
        nickelIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "nickel_ingot"));
        cobaltIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "cobalt_ingot"));
        tungstenIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "tungsten_ingot"));
        magnetiteIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "magnet_ingot"));
        silverIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "silver_ingot"));
        leadIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "lead_ingot"));
        siliconIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "silicon_ingot"));
        chromeIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "chrome_ingot"));
        titaniumIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "titanium_ingot"));
        uraniumIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "uranium_ingot"));
        platinumIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "platinum_ingot"));
        boronIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "boron_ingot"));
        brassIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "brass_ingot"));
        bronzeIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "bronze_ingot"));
        steelIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "steel_ingot"));
        osmiumIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "osmium_ingot"));

        anthracite = new LazyItemTemplate(Identifier.of(MOD_ID, "anthracite"));
        netherAsh = new LazyItemTemplate(Identifier.of(MOD_ID, "nether_ash"));
        onyx = new LazyItemTemplate(Identifier.of(MOD_ID, "onyx"));
        sapphire = new LazyItemTemplate(Identifier.of(MOD_ID, "sapphire"));
        ruby = new LazyItemTemplate(Identifier.of(MOD_ID, "ruby"));
        emerald = new LazyItemTemplate(Identifier.of(MOD_ID, "emerald"));

        BlockListener.anthraciteOre.specifyCustomDrop(anthracite.id);
        BlockListener.netherAshOre.specifyCustomDrop(netherAsh.id);
        BlockListener.netherOnyxOre.specifyCustomDrop(onyx.id);
        BlockListener.sapphireOre.specifyCustomDrop(sapphire.id);
        BlockListener.rubyOre.specifyCustomDrop(ruby.id);
        BlockListener.emeraldOre.specifyCustomDrop(emerald.id);
    }
}
