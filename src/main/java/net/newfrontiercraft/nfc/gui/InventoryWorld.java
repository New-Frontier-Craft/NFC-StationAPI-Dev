package net.newfrontiercraft.nfc.gui;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.storage.WorldStorage;
import net.modificationstation.stationapi.api.world.StationFlatteningWorld;

public class InventoryWorld extends World {
    public InventoryWorld(InventoryDimension dimension) {
        super(dimension, "inventory_world", dimension, 0);
        dimension.setWorld(this);
    }

}
