package net.newfrontiercraft.nfc.compat.whatsthis;

import net.danygames2014.whatsthis.api.*;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.block.BlockState;
import net.newfrontiercraft.nfc.block.entity.HeatCoilBlockEntity;
import net.newfrontiercraft.nfc.block.entity.MachineGearBoxBlockEntity;
import net.newfrontiercraft.nfc.events.init.BlockListener;

public class NfcProbeInfoProvider implements IProbeInfoProvider {
    @Override
    public String getID() {
        return BlockListener.MOD_ID.id("wolves_block").toString(); // We have no idea what this does, it only works with this weirdly wrong name
    }

    @Override
    public void addProbeInfo(ProbeMode probeMode, IProbeInfo iProbeInfo, PlayerEntity playerEntity, World world, BlockState blockState, IProbeHitData iProbeHitData) {
        Block block = blockState.getBlock();
        BlockPos pos = iProbeHitData.getPos();
        if(world.getBlockEntity(pos.x, pos.y, pos.z) instanceof HeatCoilBlockEntity heatCoilBlockEntity){
            iProbeInfo.text(TextStyleClass.INFO + "Heat: " + heatCoilBlockEntity.getHeatLevel());
        }
        if(world.getBlockEntity(pos.x, pos.y, pos.z) instanceof MachineGearBoxBlockEntity machineGearBoxBlockEntity){
            iProbeInfo.text(TextStyleClass.INFO + "Torque: " + machineGearBoxBlockEntity.getTorque());
        }
    }
}
