package zerwhit.ZeroClitedAPI;

public interface IZeroLib {
    /**
     * Send the Animation ID for Animate Entity
     */
    void sendPacketID(int id);

    /**
     * Get the Animation ID for Animate Entity
     */
    int catchPacketID();

    /**
     * Send the Animation Tick for Animate Entity
     */
    void sendPacketTick(int tick);

    /**
     * Get the Animation Tick for Animate Entity
     */
    int catchPacketTick();
}
