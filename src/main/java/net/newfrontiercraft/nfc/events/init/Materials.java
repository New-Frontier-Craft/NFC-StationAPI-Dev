package net.newfrontiercraft.nfc.events.init;

import net.minecraft.block.MapColor;
import net.minecraft.block.material.FluidMaterial;
import net.minecraft.block.material.Material;
import net.newfrontiercraft.nfc.mixin.MapColorInvoker;
import net.newfrontiercraft.nfc.mixin.MaterialInvoker;

public class Materials {
    public static MapColor oilColor = MapColorInvoker.invokeMapColor(14, 0);
    public static Material oil;
    public static Material copper;
    public Materials(){
        oil = ((MaterialInvoker)new FluidMaterial(oilColor)).invokeSetDestroyPistonBehavior();
        copper = ((MaterialInvoker)new Material(MapColor.LIGHT_GRAY2)).invokeSetHandHarvestable();
    }
}