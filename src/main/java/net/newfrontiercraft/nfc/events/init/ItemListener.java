package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.TagToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.template.item.TemplatePickaxeItem;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
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
            aluminiumPickaxe,
            bismuthPickaxe,
            copperPickaxe,
            leadPickaxe,
            tinPickaxe,
            zincPickaxe,

            boronPickaxe,
            brassPickaxe,
            bronzePickaxe,
            nickelPickaxe,
            platinumPickaxe,
            silverPickaxe,

            chromePickaxe,
            cobaltPickaxe,
            siliconPickaxe,

            magnetPickaxe,
            steelPickaxe,
            titaniumPickaxe,
            tungstenPickaxe,
            onyxPickaxe,
            sapphirePickaxe,
            rubyPickaxe,
            emeraldPickaxe,

            osmiumPickaxe;


    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        ToolLevel crude = new TagToolLevel(TagKey.of(BlockRegistry.KEY, MOD_ID.id("needs_tool_level_crude")));
        ToolLevel basic = new TagToolLevel(TagKey.of(BlockRegistry.KEY, MOD_ID.id("needs_tool_level_basic")));
        ToolLevel iron = ToolMaterial.IRON.getToolLevel();
        ToolLevel advanced = new TagToolLevel(TagKey.of(BlockRegistry.KEY, MOD_ID.id("needs_tool_level_advanced")));
        ToolLevel diamond = ToolMaterial.DIAMOND.getToolLevel();

        ToolLevel.GRAPH.putEdge(ToolMaterial.STONE.getToolLevel(), crude);
        ToolLevel.GRAPH.putEdge(crude, basic);
        ToolLevel.GRAPH.putEdge(basic, ToolMaterial.IRON.getToolLevel());
        ToolLevel.GRAPH.putEdge(ToolMaterial.IRON.getToolLevel(), advanced);
        ToolLevel.GRAPH.putEdge(advanced, ToolMaterial.DIAMOND.getToolLevel());
        ToolLevel.GRAPH.removeEdge(ToolMaterial.STONE.getToolLevel(), ToolMaterial.IRON.getToolLevel());
        ToolLevel.GRAPH.removeEdge(ToolMaterial.IRON.getToolLevel(), ToolMaterial.DIAMOND.getToolLevel());

        ToolMaterial aluminiumMaterial = ToolMaterialFactory.create("aluminium", 1, 35, 5.0F, 3).toolLevel(crude);
        ToolMaterial bismuthMaterial = ToolMaterialFactory.create("bismuth", 1, 65, 3.5F, 3).toolLevel(crude);
        ToolMaterial copperMaterial = ToolMaterialFactory.create("copper", 1, 50, 4.0F, 3).toolLevel(crude);
        ToolMaterial leadMaterial = ToolMaterialFactory.create("lead", 1, 115, 2.5F, 3).toolLevel(crude);
        ToolMaterial tinMaterial = ToolMaterialFactory.create("tin", 1, 40, 4.5F, 3).toolLevel(crude);
        ToolMaterial zincMaterial = ToolMaterialFactory.create("zinc", 1, 80, 3.0F, 3).toolLevel(crude);

        ToolMaterial boronMaterial = ToolMaterialFactory.create("boron", 1, 50, 10.0F, 4).toolLevel(basic);
        ToolMaterial brassMaterial = ToolMaterialFactory.create("brass", 1, 180, 4.0F, 4).toolLevel(basic);
        ToolMaterial bronzeMaterial = ToolMaterialFactory.create("bronze", 1, 125, 5.0F, 4).toolLevel(basic);
        ToolMaterial nickelMaterial = ToolMaterialFactory.create("nickel", 1, 85, 7.0F, 4).toolLevel(basic);
        ToolMaterial platinumMaterial = ToolMaterialFactory.create("platinum", 1, 215, 3.5F, 4).toolLevel(basic);
        ToolMaterial silverMaterial = ToolMaterialFactory.create("silver", 1, 260, 3.0F, 4).toolLevel(basic);

        ToolMaterial chromeMaterial = ToolMaterialFactory.create("chrome", 1, 200, 8.0F, 6).toolLevel(iron);
        ToolMaterial cobaltMaterial = ToolMaterialFactory.create("cobalt", 1, 700, 4.0F, 6).toolLevel(iron);
        ToolMaterial siliconMaterial = ToolMaterialFactory.create("silicon", 1, 150, 10.0F, 6).toolLevel(iron);

        ToolMaterial magnetMaterial = ToolMaterialFactory.create("magnet", 1, 512, 7.0F, 5).toolLevel(advanced);
        ToolMaterial steelMaterial = ToolMaterialFactory.create("steel", 1, 700, 8.0F, 10).toolLevel(advanced);
        ToolMaterial titaniumMaterial = ToolMaterialFactory.create("titanium", 1, 700, 14.0F, 10).toolLevel(advanced);
        ToolMaterial tungstenMaterial = ToolMaterialFactory.create("tungsten", 1, 1750, 6.0F, 10).toolLevel(advanced);
        ToolMaterial gemMaterial = ToolMaterialFactory.create("gem", 1, 1450, 6.0F, 15).toolLevel(advanced);

        ToolMaterial osmiumMaterial = ToolMaterialFactory.create("osmium", 1, 10000, 9.0F, 20).toolLevel(diamond);

        aluminiumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "aluminium_pickaxe"), aluminiumMaterial);
        bismuthPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "bismuth_pickaxe"), bismuthMaterial);
        copperPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "copper_pickaxe"), copperMaterial);
        leadPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "lead_pickaxe"), leadMaterial);
        tinPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "tin_pickaxe"), tinMaterial);
        zincPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "zinc_pickaxe"), zincMaterial);

        boronPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "boron_pickaxe"), boronMaterial);
        brassPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "brass_pickaxe"), brassMaterial);
        bronzePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "bronze_pickaxe"), bronzeMaterial);
        nickelPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "nickel_pickaxe"), nickelMaterial);
        platinumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "platinum_pickaxe"), platinumMaterial);
        silverPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "silver_pickaxe"), silverMaterial);

        chromePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "chrome_pickaxe"), chromeMaterial);
        cobaltPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "cobalt_pickaxe"), cobaltMaterial);
        siliconPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "silicon_pickaxe"), siliconMaterial);

        magnetPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "magnet_pickaxe"), magnetMaterial);
        steelPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "steel_pickaxe"), steelMaterial);
        titaniumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "titanium_pickaxe"), titaniumMaterial);
        tungstenPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "tungsten_pickaxe"), tungstenMaterial);
        onyxPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "onyx_pickaxe"), gemMaterial);
        sapphirePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "sapphire_pickaxe"), gemMaterial);
        rubyPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "ruby_pickaxe"), gemMaterial);
        emeraldPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "emerald_pickaxe"), gemMaterial);

        osmiumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "osmium_pickaxe"), osmiumMaterial);

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
