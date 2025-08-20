package net.newfrontiercraft.nfc.block.entity;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.newfrontiercraft.nfc.api.TorqueGenerator;

public class MachineGearBoxBlockEntity extends BlockEntity {

    private int torque;
    private int tickTimer;

    @Override
    public void tick() {
        if (tickTimer < 20) {
            tickTimer++;
        } else {
            tickTimer = 0;
            updateTorque();
        }
    }

    private void updateTorque() {
        int belowBlockId = world.getBlockId(x, y - 1, z);
        if (belowBlockId == 0) {
            torque = 0;
            return;
        }
        Block block = Block.BLOCKS[belowBlockId];
        if (block instanceof TorqueGenerator torqueGenerator) {
            torque = torqueGenerator.getTorque(world, x, y - 1, z);
        }
    }

    public int getTorque() {
        return torque;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        torque = nbt.getInt("Torque");
        tickTimer = nbt.getInt("TickTimer");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("Torque", torque);
        nbt.putInt("TickTimer", tickTimer);
    }
}
