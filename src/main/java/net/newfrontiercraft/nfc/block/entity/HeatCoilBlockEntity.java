package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.newfrontiercraft.nfc.api.HeatConsumer;
import net.newfrontiercraft.nfc.block.HeatCoilBlock;

public class HeatCoilBlockEntity extends BlockEntity {
    
    private int heatLevel;
    private int tickTimer;

    private int currentHeatState = 0;

    private int getHeatStateFromHeatLevel(){
        if(heatLevel < 250){
            return 0;
        } else if (heatLevel < 500) {
            return 1;   
        } else if (heatLevel < 750) {
            return 2;
        } else if (heatLevel < 1000) {
            return 3;
        }
        return 3;
    }

    @Override
    public void tick() {
        if (tickTimer < 20) {
            tickTimer++;
            return;
        }
        tickTimer = 0;
        if (heatLevel < 0) {
            System.out.println("!!!!!!!!!!!!!!!! HEAT LEVEL BELOW 0 DETECTED !!!!!!!!!!!!!!!!");
        }
        if (heatLevel <= 0) {
            return;
        }
        balanceHeatLevels(x + 1, y, z);
        balanceHeatLevels(x - 1, y, z);
        balanceHeatLevels(x, y + 1, z);
        balanceHeatLevels(x, y - 1, z);
        balanceHeatLevels(x, y, z + 1);
        balanceHeatLevels(x, y, z - 1);
        sendHeatToConsumerBlock(x + 1, y, z);
        sendHeatToConsumerBlock(x - 1, y, z);
        sendHeatToConsumerBlock(x, y + 1, z);
        sendHeatToConsumerBlock(x, y - 1, z);
        sendHeatToConsumerBlock(x, y, z + 1);
        sendHeatToConsumerBlock(x, y, z - 1);
        if (heatLevel > 0) {
            heatLevel--;
        }

        int heatState = getHeatStateFromHeatLevel();
        if(currentHeatState != heatState){
            if(world.getBlockState(x, y, z).contains(HeatCoilBlock.HEAT)){
                currentHeatState = heatState;
                world.setBlockStateWithNotify(x, y, z, world.getBlockState(x, y, z).with(HeatCoilBlock.HEAT, heatState));
            }
        }
    }

    private void balanceHeatLevels(int x, int y, int z) {
        BlockEntity otherHeatConductor = world.getBlockEntity(x, y, z);
        if (otherHeatConductor == null) {
            return;
        }
        if (!(otherHeatConductor instanceof HeatCoilBlockEntity)) {
            return;
        }
        int otherHeatLevel = ((HeatCoilBlockEntity)otherHeatConductor).getHeatLevel();
        if (otherHeatLevel >= heatLevel) {
            return;
        }
        int transferHeat = (heatLevel - otherHeatLevel)/2;
        heatLevel -= transferHeat;
        ((HeatCoilBlockEntity)otherHeatConductor).changeHeatLevel(transferHeat);
    }

    private void sendHeatToConsumerBlock(int x, int y, int z) {
        BlockEntity heatConsumer = world.getBlockEntity(x, y, z);
        if (heatConsumer == null) {
            return;
        }
        if (!(heatConsumer instanceof HeatConsumer)) {
            return;
        }
        int otherHeatLevel = ((HeatConsumer)heatConsumer).getHeat();
        if (otherHeatLevel >= heatLevel) {
            return;
        }
        int transferHeat = (heatLevel - otherHeatLevel)/2;
        int consumedHeat = ((HeatConsumer)heatConsumer).addHeat(transferHeat);
        heatLevel -= consumedHeat;
    }

    public void changeHeatLevel(int amount) {
        heatLevel += amount;
    }

    public int getHeatLevel() {
        return heatLevel;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        heatLevel = nbt.getInt("HeatLevel");
        tickTimer = nbt.getInt("TickTimer");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("HeatLevel", heatLevel);
        nbt.putInt("TickTimer", tickTimer);
    }
}
