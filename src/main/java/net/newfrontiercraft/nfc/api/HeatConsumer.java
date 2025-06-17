package net.newfrontiercraft.nfc.api;

public interface HeatConsumer {

    /**
     * Handles the addition of heat into a consumer.
     * @param heat Amount of potential heat to be added.
     * @return Amount of actually added heat.
     */
    int addHeat(int heat);

    /**
     * Used to check if more heat can be added.
     * @return Amount of heat in consumer.
     */
    int getHeat();
}
