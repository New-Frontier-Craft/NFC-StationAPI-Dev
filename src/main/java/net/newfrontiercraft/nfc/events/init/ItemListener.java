package net.newfrontiercraft.nfc.events.init;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.item.tool.TagToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolLevel;
import net.modificationstation.stationapi.api.item.tool.ToolMaterialFactory;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;
import net.modificationstation.stationapi.api.registry.BlockRegistry;
import net.modificationstation.stationapi.api.tag.TagKey;
import net.modificationstation.stationapi.api.template.item.*;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import net.newfrontiercraft.nfc.item.*;
import net.newfrontiercraft.nfc.utils.ToolTierEnum;

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
            cupronickelIngot,
            anthracite,
            netherAsh,
            onyx,
            sapphire,
            ruby,
            emerald,
            blueGlowstoneDust;

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

    public static TemplateSwordItem
            aluminiumSword,
            bismuthSword,
            copperSword,
            leadSword,
            tinSword,
            zincSword,
            boronSword,
            brassSword,
            bronzeSword,
            nickelSword,
            platinumSword,
            silverSword,
            chromeSword,
            cobaltSword,
            siliconSword,
            magnetSword,
            steelSword,
            titaniumSword,
            tungstenSword,
            onyxSword,
            sapphireSword,
            rubySword,
            emeraldSword,
            osmiumSword;

    public static TemplateShovelItem
            aluminiumShovel,
            bismuthShovel,
            copperShovel,
            leadShovel,
            tinShovel,
            zincShovel,
            boronShovel,
            brassShovel,
            bronzeShovel,
            nickelShovel,
            platinumShovel,
            silverShovel,
            chromeShovel,
            cobaltShovel,
            siliconShovel,
            magnetShovel,
            steelShovel,
            titaniumShovel,
            tungstenShovel,
            onyxShovel,
            sapphireShovel,
            rubyShovel,
            emeraldShovel,
            osmiumShovel;

    public static TemplateAxeItem
            aluminiumAxe,
            bismuthAxe,
            copperAxe,
            leadAxe,
            tinAxe,
            zincAxe,
            boronAxe,
            brassAxe,
            bronzeAxe,
            nickelAxe,
            platinumAxe,
            silverAxe,
            chromeAxe,
            cobaltAxe,
            siliconAxe,
            magnetAxe,
            steelAxe,
            titaniumAxe,
            tungstenAxe,
            onyxAxe,
            sapphireAxe,
            rubyAxe,
            emeraldAxe,
            osmiumAxe;

    public static TemplateHoeItem
            aluminiumHoe,
            bismuthHoe,
            copperHoe,
            leadHoe,
            tinHoe,
            zincHoe,
            boronHoe,
            brassHoe,
            bronzeHoe,
            nickelHoe,
            platinumHoe,
            silverHoe,
            chromeHoe,
            cobaltHoe,
            siliconHoe,
            magnetHoe,
            steelHoe,
            titaniumHoe,
            tungstenHoe,
            onyxHoe,
            sapphireHoe,
            rubyHoe,
            emeraldHoe,
            osmiumHoe;

    public static TemplateArmorItem
            aluminiumHelmet,
            bismuthHelmet,
            copperHelmet,
            tinHelmet,
            zincHelmet,
            boronHelmet,
            brassHelmet,
            bronzeHelmet,
            nickelHelmet,
            platinumHelmet,
            silverHelmet,
            chromeHelmet,
            cobaltHelmet,
            siliconHelmet,
            steelHelmet,
            titaniumHelmet,
            tungstenHelmet,
            onyxHelmet,
            sapphireHelmet,
            rubyHelmet,
            emeraldHelmet,
            osmiumHelmet;

    public static TemplateArmorItem
            aluminiumChestplate,
            bismuthChestplate,
            copperChestplate,
            tinChestplate,
            zincChestplate,
            boronChestplate,
            brassChestplate,
            bronzeChestplate,
            nickelChestplate,
            platinumChestplate,
            silverChestplate,
            chromeChestplate,
            cobaltChestplate,
            siliconChestplate,
            steelChestplate,
            titaniumChestplate,
            tungstenChestplate,
            onyxChestplate,
            sapphireChestplate,
            rubyChestplate,
            emeraldChestplate,
            osmiumChestplate;

    public static TemplateArmorItem
            aluminiumLeggings,
            bismuthLeggings,
            copperLeggings,
            tinLeggings,
            zincLeggings,
            boronLeggings,
            brassLeggings,
            bronzeLeggings,
            nickelLeggings,
            platinumLeggings,
            silverLeggings,
            chromeLeggings,
            cobaltLeggings,
            siliconLeggings,
            steelLeggings,
            titaniumLeggings,
            tungstenLeggings,
            onyxLeggings,
            sapphireLeggings,
            rubyLeggings,
            emeraldLeggings,
            osmiumLeggings;

    public static TemplateArmorItem
            aluminiumBoots,
            bismuthBoots,
            copperBoots,
            tinBoots,
            zincBoots,
            boronBoots,
            brassBoots,
            bronzeBoots,
            nickelBoots,
            platinumBoots,
            silverBoots,
            chromeBoots,
            cobaltBoots,
            siliconBoots,
            steelBoots,
            titaniumBoots,
            tungstenBoots,
            onyxBoots,
            sapphireBoots,
            rubyBoots,
            emeraldBoots,
            osmiumBoots;

    public static OilBucketItem oilBucket;
    public static DoorItem copperDoor;
    public static TelescopeItem telescopeItem;

    @Entrypoint.Namespace
    public static Namespace MOD_ID;

    @EventListener
    public void registerItems(ItemRegistryEvent event) {
        // New tool levels
        ToolLevel crude = new TagToolLevel(TagKey.of(BlockRegistry.KEY, MOD_ID.id("needs_tool_level_crude")));
        ToolLevel basic = new TagToolLevel(TagKey.of(BlockRegistry.KEY, MOD_ID.id("needs_tool_level_basic")));
        ToolLevel iron = ToolMaterial.IRON.getToolLevel();
        ToolLevel advanced = new TagToolLevel(TagKey.of(BlockRegistry.KEY, MOD_ID.id("needs_tool_level_advanced")));
        ToolLevel diamond = ToolMaterial.DIAMOND.getToolLevel();

        // Tool level reconnections
        ToolLevel.GRAPH.putEdge(ToolMaterial.STONE.getToolLevel(), crude);
        ToolLevel.GRAPH.putEdge(crude, basic);
        ToolLevel.GRAPH.putEdge(basic, ToolMaterial.IRON.getToolLevel());
        ToolLevel.GRAPH.putEdge(ToolMaterial.IRON.getToolLevel(), advanced);
        ToolLevel.GRAPH.putEdge(advanced, ToolMaterial.DIAMOND.getToolLevel());
        ToolLevel.GRAPH.removeEdge(ToolMaterial.STONE.getToolLevel(), ToolMaterial.IRON.getToolLevel());
        ToolLevel.GRAPH.removeEdge(ToolMaterial.IRON.getToolLevel(), ToolMaterial.DIAMOND.getToolLevel());

        // New tool materials
        ToolMaterial aluminiumMaterial = ToolMaterialFactory.create("aluminium", 1, 35, 5.0F, 3).toolLevel(crude);; // This semicolon exists so my IDE stops falsely greying out the code
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

        // Rebalancing of Vanilla tool durabilities
        Item.WOODEN_SWORD.setMaxDamage(30);
        Item.WOODEN_SHOVEL.setMaxDamage(30);
        Item.WOODEN_PICKAXE.setMaxDamage(30);
        Item.WOODEN_AXE.setMaxDamage(30);
        Item.WOODEN_HOE.setMaxDamage(30);

        Item.STONE_SWORD.setMaxDamage(40);
        Item.STONE_SHOVEL.setMaxDamage(40);
        Item.STONE_PICKAXE.setMaxDamage(40);
        Item.STONE_AXE.setMaxDamage(40);
        Item.STONE_HOE.setMaxDamage(40);

        Item.IRON_SWORD.setMaxDamage(512);
        Item.IRON_SHOVEL.setMaxDamage(512);
        Item.IRON_PICKAXE.setMaxDamage(512);
        Item.IRON_AXE.setMaxDamage(512);
        Item.IRON_HOE.setMaxDamage(512);

        Item.GOLDEN_SWORD.setMaxDamage(20);
        Item.GOLDEN_SHOVEL.setMaxDamage(20);
        Item.GOLDEN_PICKAXE.setMaxDamage(20);
        Item.GOLDEN_AXE.setMaxDamage(20);
        Item.GOLDEN_HOE.setMaxDamage(20);

        Item.DIAMOND_SWORD.setMaxDamage(3250);
        Item.DIAMOND_SHOVEL.setMaxDamage(3250);
        Item.DIAMOND_PICKAXE.setMaxDamage(3250);
        Item.DIAMOND_AXE.setMaxDamage(3250);
        Item.DIAMOND_HOE.setMaxDamage(3250);

        // Rebalancing of Vanilla armour durabilities
        Item.LEATHER_HELMET.setMaxDamage(48);
        Item.LEATHER_CHESTPLATE.setMaxDamage(48);
        Item.LEATHER_LEGGINGS.setMaxDamage(48);
        Item.LEATHER_BOOTS.setMaxDamage(48);

        Item.CHAIN_HELMET.setMaxDamage(96);
        Item.CHAIN_CHESTPLATE.setMaxDamage(96);
        Item.CHAIN_LEGGINGS.setMaxDamage(96);
        Item.CHAIN_BOOTS.setMaxDamage(96);

        Item.IRON_HELMET.setMaxDamage(192);
        Item.IRON_CHESTPLATE.setMaxDamage(192);
        Item.IRON_LEGGINGS.setMaxDamage(192);
        Item.IRON_BOOTS.setMaxDamage(192);

        Item.GOLDEN_HELMET.setMaxDamage(20);
        Item.GOLDEN_CHESTPLATE.setMaxDamage(20);
        Item.GOLDEN_LEGGINGS.setMaxDamage(20);
        Item.GOLDEN_BOOTS.setMaxDamage(20);

        Item.DIAMOND_HELMET.setMaxDamage(800);
        Item.DIAMOND_CHESTPLATE.setMaxDamage(800);
        Item.DIAMOND_LEGGINGS.setMaxDamage(800);
        Item.DIAMOND_BOOTS.setMaxDamage(800);

        // Pickaxes
        aluminiumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "aluminium_pickaxe"), aluminiumMaterial, ToolTierEnum.CRUDE);
        bismuthPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "bismuth_pickaxe"), bismuthMaterial, ToolTierEnum.CRUDE);
        copperPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "copper_pickaxe"), copperMaterial, ToolTierEnum.CRUDE);
        leadPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "lead_pickaxe"), leadMaterial, ToolTierEnum.CRUDE);
        tinPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "tin_pickaxe"), tinMaterial, ToolTierEnum.CRUDE);
        zincPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "zinc_pickaxe"), zincMaterial, ToolTierEnum.CRUDE);
        boronPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "boron_pickaxe"), boronMaterial, ToolTierEnum.BASIC);
        brassPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "brass_pickaxe"), brassMaterial, ToolTierEnum.BASIC);
        bronzePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "bronze_pickaxe"), bronzeMaterial, ToolTierEnum.BASIC);
        nickelPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "nickel_pickaxe"), nickelMaterial, ToolTierEnum.BASIC);
        platinumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "platinum_pickaxe"), platinumMaterial, ToolTierEnum.BASIC);
        silverPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "silver_pickaxe"), silverMaterial, ToolTierEnum.BASIC);
        chromePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "chrome_pickaxe"), chromeMaterial, ToolTierEnum.IRON);
        cobaltPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "cobalt_pickaxe"), cobaltMaterial, ToolTierEnum.IRON);
        siliconPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "silicon_pickaxe"), siliconMaterial, ToolTierEnum.IRON);
        magnetPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "magnet_pickaxe"), magnetMaterial, ToolTierEnum.ADVANCED);
        steelPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "steel_pickaxe"), steelMaterial, ToolTierEnum.ADVANCED);
        titaniumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "titanium_pickaxe"), titaniumMaterial, ToolTierEnum.ADVANCED);
        tungstenPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "tungsten_pickaxe"), tungstenMaterial, ToolTierEnum.ADVANCED);
        onyxPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "onyx_pickaxe"), gemMaterial, ToolTierEnum.ADVANCED);
        sapphirePickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "sapphire_pickaxe"), gemMaterial, ToolTierEnum.ADVANCED);
        rubyPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "ruby_pickaxe"), gemMaterial, ToolTierEnum.ADVANCED);
        emeraldPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "emerald_pickaxe"), gemMaterial, ToolTierEnum.ADVANCED);
        osmiumPickaxe = new LazyPickaxeTemplate(Identifier.of(MOD_ID, "osmium_pickaxe"), osmiumMaterial, ToolTierEnum.DIAMOND);

        // Swords
        aluminiumSword = new LazySwordTemplate(Identifier.of(MOD_ID, "aluminium_sword"), aluminiumMaterial, ToolTierEnum.CRUDE);
        bismuthSword = new LazySwordTemplate(Identifier.of(MOD_ID, "bismuth_sword"), bismuthMaterial, ToolTierEnum.CRUDE);
        copperSword = new LazySwordTemplate(Identifier.of(MOD_ID, "copper_sword"), copperMaterial, ToolTierEnum.CRUDE);
        leadSword = new LazySwordTemplate(Identifier.of(MOD_ID, "lead_sword"), leadMaterial, ToolTierEnum.CRUDE);
        tinSword = new LazySwordTemplate(Identifier.of(MOD_ID, "tin_sword"), tinMaterial, ToolTierEnum.CRUDE);
        zincSword = new LazySwordTemplate(Identifier.of(MOD_ID, "zinc_sword"), zincMaterial, ToolTierEnum.CRUDE);
        boronSword = new LazySwordTemplate(Identifier.of(MOD_ID, "boron_sword"), boronMaterial, ToolTierEnum.BASIC);
        brassSword = new LazySwordTemplate(Identifier.of(MOD_ID, "brass_sword"), brassMaterial, ToolTierEnum.BASIC);
        bronzeSword = new LazySwordTemplate(Identifier.of(MOD_ID, "bronze_sword"), bronzeMaterial, ToolTierEnum.BASIC);
        nickelSword = new LazySwordTemplate(Identifier.of(MOD_ID, "nickel_sword"), nickelMaterial, ToolTierEnum.BASIC);
        platinumSword = new LazySwordTemplate(Identifier.of(MOD_ID, "platinum_sword"), platinumMaterial, ToolTierEnum.BASIC);
        silverSword = new LazySwordTemplate(Identifier.of(MOD_ID, "silver_sword"), silverMaterial, ToolTierEnum.BASIC);
        chromeSword = new LazySwordTemplate(Identifier.of(MOD_ID, "chrome_sword"), chromeMaterial, ToolTierEnum.IRON);
        cobaltSword = new LazySwordTemplate(Identifier.of(MOD_ID, "cobalt_sword"), cobaltMaterial, ToolTierEnum.IRON);
        siliconSword = new LazySwordTemplate(Identifier.of(MOD_ID, "silicon_sword"), siliconMaterial, ToolTierEnum.IRON);
        magnetSword = new LazySwordTemplate(Identifier.of(MOD_ID, "magnet_sword"), magnetMaterial, ToolTierEnum.ADVANCED);
        steelSword = new LazySwordTemplate(Identifier.of(MOD_ID, "steel_sword"), steelMaterial, ToolTierEnum.ADVANCED);
        titaniumSword = new LazySwordTemplate(Identifier.of(MOD_ID, "titanium_sword"), titaniumMaterial, ToolTierEnum.ADVANCED);
        tungstenSword = new LazySwordTemplate(Identifier.of(MOD_ID, "tungsten_sword"), tungstenMaterial, ToolTierEnum.ADVANCED);
        onyxSword = new LazySwordTemplate(Identifier.of(MOD_ID, "onyx_sword"), gemMaterial, ToolTierEnum.ADVANCED);
        sapphireSword = new LazySwordTemplate(Identifier.of(MOD_ID, "sapphire_sword"), gemMaterial, ToolTierEnum.ADVANCED);
        rubySword = new LazySwordTemplate(Identifier.of(MOD_ID, "ruby_sword"), gemMaterial, ToolTierEnum.ADVANCED);
        emeraldSword = new LazySwordTemplate(Identifier.of(MOD_ID, "emerald_sword"), gemMaterial, ToolTierEnum.ADVANCED);
        osmiumSword = new LazySwordTemplate(Identifier.of(MOD_ID, "osmium_sword"), osmiumMaterial, ToolTierEnum.DIAMOND);

        // Shovels
        aluminiumShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "aluminium_shovel"), aluminiumMaterial, ToolTierEnum.CRUDE);
        bismuthShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "bismuth_shovel"), bismuthMaterial, ToolTierEnum.CRUDE);
        copperShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "copper_shovel"), copperMaterial, ToolTierEnum.CRUDE);
        leadShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "lead_shovel"), leadMaterial, ToolTierEnum.CRUDE);
        tinShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "tin_shovel"), tinMaterial, ToolTierEnum.CRUDE);
        zincShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "zinc_shovel"), zincMaterial, ToolTierEnum.CRUDE);
        boronShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "boron_shovel"), boronMaterial, ToolTierEnum.BASIC);
        brassShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "brass_shovel"), brassMaterial, ToolTierEnum.BASIC);
        bronzeShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "bronze_shovel"), bronzeMaterial, ToolTierEnum.BASIC);
        nickelShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "nickel_shovel"), nickelMaterial, ToolTierEnum.BASIC);
        platinumShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "platinum_shovel"), platinumMaterial, ToolTierEnum.BASIC);
        silverShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "silver_shovel"), silverMaterial, ToolTierEnum.BASIC);
        chromeShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "chrome_shovel"), chromeMaterial, ToolTierEnum.IRON);
        cobaltShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "cobalt_shovel"), cobaltMaterial, ToolTierEnum.IRON);
        siliconShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "silicon_shovel"), siliconMaterial, ToolTierEnum.IRON);
        magnetShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "magnet_shovel"), magnetMaterial, ToolTierEnum.ADVANCED);
        steelShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "steel_shovel"), steelMaterial, ToolTierEnum.ADVANCED);
        titaniumShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "titanium_shovel"), titaniumMaterial, ToolTierEnum.ADVANCED);
        tungstenShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "tungsten_shovel"), tungstenMaterial, ToolTierEnum.ADVANCED);
        onyxShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "onyx_shovel"), gemMaterial, ToolTierEnum.ADVANCED);
        sapphireShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "sapphire_shovel"), gemMaterial, ToolTierEnum.ADVANCED);
        rubyShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "ruby_shovel"), gemMaterial, ToolTierEnum.ADVANCED);
        emeraldShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "emerald_shovel"), gemMaterial, ToolTierEnum.ADVANCED);
        osmiumShovel = new LazyShovelTemplate(Identifier.of(MOD_ID, "osmium_shovel"), osmiumMaterial, ToolTierEnum.DIAMOND);

        // Axes
        aluminiumAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "aluminium_axe"), aluminiumMaterial, ToolTierEnum.CRUDE);
        bismuthAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "bismuth_axe"), bismuthMaterial, ToolTierEnum.CRUDE);
        copperAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "copper_axe"), copperMaterial, ToolTierEnum.CRUDE);
        leadAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "lead_axe"), leadMaterial, ToolTierEnum.CRUDE);
        tinAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "tin_axe"), tinMaterial, ToolTierEnum.CRUDE);
        zincAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "zinc_axe"), zincMaterial, ToolTierEnum.CRUDE);
        boronAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "boron_axe"), boronMaterial, ToolTierEnum.BASIC);
        brassAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "brass_axe"), brassMaterial, ToolTierEnum.BASIC);
        bronzeAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "bronze_axe"), bronzeMaterial, ToolTierEnum.BASIC);
        nickelAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "nickel_axe"), nickelMaterial, ToolTierEnum.BASIC);
        platinumAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "platinum_axe"), platinumMaterial, ToolTierEnum.BASIC);
        silverAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "silver_axe"), silverMaterial, ToolTierEnum.BASIC);
        chromeAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "chrome_axe"), chromeMaterial, ToolTierEnum.IRON);
        cobaltAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "cobalt_axe"), cobaltMaterial, ToolTierEnum.IRON);
        siliconAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "silicon_axe"), siliconMaterial, ToolTierEnum.IRON);
        magnetAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "magnet_axe"), magnetMaterial, ToolTierEnum.ADVANCED);
        steelAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "steel_axe"), steelMaterial, ToolTierEnum.ADVANCED);
        titaniumAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "titanium_axe"), titaniumMaterial, ToolTierEnum.ADVANCED);
        tungstenAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "tungsten_axe"), tungstenMaterial, ToolTierEnum.ADVANCED);
        onyxAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "onyx_axe"), gemMaterial, ToolTierEnum.ADVANCED);
        sapphireAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "sapphire_axe"), gemMaterial, ToolTierEnum.ADVANCED);
        rubyAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "ruby_axe"), gemMaterial, ToolTierEnum.ADVANCED);
        emeraldAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "emerald_axe"), gemMaterial, ToolTierEnum.ADVANCED);
        osmiumAxe = new LazyAxeTemplate(Identifier.of(MOD_ID, "osmium_axe"), osmiumMaterial, ToolTierEnum.DIAMOND);

        // Hoes
        aluminiumHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "aluminium_hoe"), aluminiumMaterial, ToolTierEnum.CRUDE);
        bismuthHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "bismuth_hoe"), bismuthMaterial, ToolTierEnum.CRUDE);
        copperHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "copper_hoe"), copperMaterial, ToolTierEnum.CRUDE);
        leadHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "lead_hoe"), leadMaterial, ToolTierEnum.CRUDE);
        tinHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "tin_hoe"), tinMaterial, ToolTierEnum.CRUDE);
        zincHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "zinc_hoe"), zincMaterial, ToolTierEnum.CRUDE);
        boronHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "boron_hoe"), boronMaterial, ToolTierEnum.BASIC);
        brassHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "brass_hoe"), brassMaterial, ToolTierEnum.BASIC);
        bronzeHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "bronze_hoe"), bronzeMaterial, ToolTierEnum.BASIC);
        nickelHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "nickel_hoe"), nickelMaterial, ToolTierEnum.BASIC);
        platinumHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "platinum_hoe"), platinumMaterial, ToolTierEnum.BASIC);
        silverHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "silver_hoe"), silverMaterial, ToolTierEnum.BASIC);
        chromeHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "chrome_hoe"), chromeMaterial, ToolTierEnum.IRON);
        cobaltHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "cobalt_hoe"), cobaltMaterial, ToolTierEnum.IRON);
        siliconHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "silicon_hoe"), siliconMaterial, ToolTierEnum.IRON);
        magnetHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "magnet_hoe"), magnetMaterial, ToolTierEnum.ADVANCED);
        steelHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "steel_hoe"), steelMaterial, ToolTierEnum.ADVANCED);
        titaniumHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "titanium_hoe"), titaniumMaterial, ToolTierEnum.ADVANCED);
        tungstenHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "tungsten_hoe"), tungstenMaterial, ToolTierEnum.ADVANCED);
        onyxHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "onyx_hoe"), gemMaterial, ToolTierEnum.ADVANCED);
        sapphireHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "sapphire_hoe"), gemMaterial, ToolTierEnum.ADVANCED);
        rubyHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "ruby_hoe"), gemMaterial, ToolTierEnum.ADVANCED);
        emeraldHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "emerald_hoe"), gemMaterial, ToolTierEnum.ADVANCED);
        osmiumHoe = new LazyHoeTemplate(Identifier.of(MOD_ID, "osmium_hoe"), osmiumMaterial, ToolTierEnum.DIAMOND);

        // Helmets
        aluminiumHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "aluminium_helmet"), 0, 48, "aluminium");
        bismuthHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "bismuth_helmet"), 0, 48, "bismuth");
        copperHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "copper_helmet"), 0, 48, "copper");
        tinHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "tin_helmet"), 0, 48, "tin");
        zincHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "zinc_helmet"), 0, 48, "zinc");
        boronHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "boron_helmet"), 0, 96, "boron");
        brassHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "brass_helmet"), 0, 96, "brass");
        bronzeHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "bronze_helmet"), 0, 96, "bronze");
        nickelHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "nickel_helmet"), 0, 96, "nickel");
        platinumHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "platinum_helmet"), 0, 96, "platinum");
        silverHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "silver_helmet"), 0, 96, "silver");
        chromeHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "chrome_helmet"), 0, 192, "chrome");
        cobaltHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "cobalt_helmet"), 0, 192, "cobalt");
        siliconHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "silicon_helmet"), 0, 192, "silicon");
        steelHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "steel_helmet"), 0, 400, "steel");
        titaniumHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "titanium_helmet"), 0, 400, "titanium");
        tungstenHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "tungsten_helmet"), 0, 400, "tungsten");
        onyxHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "onyx_helmet"), 0, 400, "onyx");
        sapphireHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "sapphire_helmet"), 0, 400, "sapphire");
        rubyHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "ruby_helmet"), 0, 400, "ruby");
        emeraldHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "emerald_helmet"), 0, 400, "emerald");
        osmiumHelmet = new LazyArmorTemplate(Identifier.of(MOD_ID, "osmium_helmet"), 0, 1235, "osmium");

        // Chestplates
        aluminiumChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "aluminium_chestplate"), 1, 48, "aluminium");
        bismuthChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "bismuth_chestplate"), 1, 48, "bismuth");
        copperChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "copper_chestplate"), 1, 48, "copper");
        tinChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "tin_chestplate"), 1, 48, "tin");
        zincChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "zinc_chestplate"), 1, 48, "zinc");
        boronChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "boron_chestplate"), 1, 96, "boron");
        brassChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "brass_chestplate"), 1, 96, "brass");
        bronzeChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "bronze_chestplate"), 1, 96, "bronze");
        nickelChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "nickel_chestplate"), 1, 96, "nickel");
        platinumChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "platinum_chestplate"), 1, 96, "platinum");
        silverChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "silver_chestplate"), 1, 96, "silver");
        chromeChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "chrome_chestplate"), 1, 192, "chrome");
        cobaltChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "cobalt_chestplate"), 1, 192, "cobalt");
        siliconChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "silicon_chestplate"), 1, 192, "silicon");
        steelChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "steel_chestplate"), 1, 400, "steel");
        titaniumChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "titanium_chestplate"), 1, 400, "titanium");
        tungstenChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "tungsten_chestplate"), 1, 400, "tungsten");
        onyxChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "onyx_chestplate"), 1, 400, "onyx");
        sapphireChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "sapphire_chestplate"), 1, 400, "sapphire");
        rubyChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "ruby_chestplate"), 1, 400, "ruby");
        emeraldChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "emerald_chestplate"), 1, 400, "emerald");
        osmiumChestplate = new LazyArmorTemplate(Identifier.of(MOD_ID, "osmium_chestplate"), 1, 1235, "osmium");

        // Leggings
        aluminiumLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "aluminium_leggings"), 2, 48, "aluminium");
        bismuthLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "bismuth_leggings"), 2, 48, "bismuth");
        copperLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "copper_leggings"), 2, 48, "copper");
        tinLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "tin_leggings"), 2, 48, "tin");
        zincLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "zinc_leggings"), 2, 48, "zinc");
        boronLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "boron_leggings"), 2, 96, "boron");
        brassLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "brass_leggings"), 2, 96, "brass");
        bronzeLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "bronze_leggings"), 2, 96, "bronze");
        nickelLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "nickel_leggings"), 2, 96, "nickel");
        platinumLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "platinum_leggings"), 2, 96, "platinum");
        silverLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "silver_leggings"), 2, 96, "silver");
        chromeLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "chrome_leggings"), 2, 192, "chrome");
        cobaltLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "cobalt_leggings"), 2, 192, "cobalt");
        siliconLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "silicon_leggings"), 2, 192, "silicon");
        steelLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "steel_leggings"), 2, 400, "steel");
        titaniumLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "titanium_leggings"), 2, 400, "titanium");
        tungstenLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "tungsten_leggings"), 2, 400, "tungsten");
        onyxLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "onyx_leggings"), 2, 400, "onyx");
        sapphireLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "sapphire_leggings"), 2, 400, "sapphire");
        rubyLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "ruby_leggings"), 2, 400, "ruby");
        emeraldLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "emerald_leggings"), 2, 400, "emerald");
        osmiumLeggings = new LazyArmorTemplate(Identifier.of(MOD_ID, "osmium_leggings"), 2, 1235, "osmium");

        // Boots
        aluminiumBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "aluminium_boots"), 3, 48, "aluminium");
        bismuthBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "bismuth_boots"), 3, 48, "bismuth");
        copperBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "copper_boots"), 3, 48, "copper");
        tinBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "tin_boots"), 3, 48, "tin");
        zincBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "zinc_boots"), 3, 48, "zinc");
        boronBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "boron_boots"), 3, 96, "boron");
        brassBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "brass_boots"), 3, 96, "brass");
        bronzeBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "bronze_boots"), 3, 96, "bronze");
        nickelBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "nickel_boots"), 3, 96, "nickel");
        platinumBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "platinum_boots"), 3, 96, "platinum");
        silverBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "silver_boots"), 3, 96, "silver");
        chromeBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "chrome_boots"), 3, 192, "chrome");
        cobaltBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "cobalt_boots"), 3, 192, "cobalt");
        siliconBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "silicon_boots"), 3, 192, "silicon");
        steelBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "steel_boots"), 3, 400, "steel");
        titaniumBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "titanium_boots"), 3, 400, "titanium");
        tungstenBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "tungsten_boots"), 3, 400, "tungsten");
        onyxBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "onyx_boots"), 3, 400, "onyx");
        sapphireBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "sapphire_boots"), 3, 400, "sapphire");
        rubyBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "ruby_boots"), 3, 400, "ruby");
        emeraldBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "emerald_boots"), 3, 400, "emerald");
        osmiumBoots = new LazyArmorTemplate(Identifier.of(MOD_ID, "osmium_boots"), 3, 1235, "osmium");

        // Ingots
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
        cupronickelIngot = new LazyItemTemplate(Identifier.of(MOD_ID, "cupronickel_ingot"));

        // Ore drops
        anthracite = new LazyItemTemplate(Identifier.of(MOD_ID, "anthracite"));
        netherAsh = new LazyItemTemplate(Identifier.of(MOD_ID, "nether_ash"));
        onyx = new LazyItemTemplate(Identifier.of(MOD_ID, "onyx"));
        sapphire = new LazyItemTemplate(Identifier.of(MOD_ID, "sapphire"));
        ruby = new LazyItemTemplate(Identifier.of(MOD_ID, "ruby"));
        emerald = new LazyItemTemplate(Identifier.of(MOD_ID, "emerald"));

        // Other drops
        blueGlowstoneDust = new LazyItemTemplate(Identifier.of(MOD_ID, "blue_glowstone_dust"));

        // Food
        cookedEgg = new LazyFoodTemplate(Identifier.of(MOD_ID, "cooked_egg"), 4, false);

        // Oil bucket
        oilBucket = (OilBucketItem) new OilBucketItem(Identifier.of(MOD_ID, "oil_bucket"), BlockListener.oilStill.id).setTranslationKey(Identifier.of(MOD_ID, "oil_bucket"));

        // Doors
        copperDoor = (DoorItem) new DoorItem(Identifier.of(MOD_ID, "copper_door"), BlockListener.copperDoor.id).setTranslationKey(Identifier.of(MOD_ID, "copper_door"));

        // Telescope
        telescopeItem = new TelescopeItem(Identifier.of(MOD_ID, "telescope"));

        // Ore drop specification
        BlockListener.anthraciteOre.specifyCustomDrop(MOD_ID.id("anthracite"));
        BlockListener.netherAshOre.specifyCustomDrop(MOD_ID.id("nether_ash"));
        BlockListener.netherOnyxOre.specifyCustomDrop(MOD_ID.id("onyx"));
        BlockListener.sapphireOre.specifyCustomDrop(MOD_ID.id("sapphire"));
        BlockListener.rubyOre.specifyCustomDrop(MOD_ID.id("ruby"));
        BlockListener.emeraldOre.specifyCustomDrop(MOD_ID.id("emerald"));

        // Set fuel burn time
        FuelRegistry.addFuelItem(oilBucket, 12800);
        FuelRegistry.addFuelItem(netherAsh, 1600);
        FuelRegistry.addFuelItem(anthracite, 11200);
    }
}
