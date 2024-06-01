package zerwhit.ZeroClitedAPI;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.Entity;

@Mod(
        name = "ZeroClitedAPI",
        modid = "ZeroClitedAPI",
        version = "1.0"
)
public class Animate {
    public static SimpleNetworkWrapper wrapper;
    @Instance("ZeroClitedAPI")
    public static Animate instanceAPI;

    @SidedProxy(clientSide = "zerwhit.ZeroClitedAPI.client.ClientProxy", serverSide = "zerwhit.ZeroClitedAPI.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void init(FMLInitializationEvent e) {
        wrapper = NetworkRegistry.INSTANCE.newSimpleChannel("ZeroClited");
        wrapper.registerMessage(MessageHandler.Handler.class, MessageHandler.class, 0, Side.CLIENT);
    }

    public static boolean isClient() {
        return FMLCommonHandler.instance().getSide().isClient();
    }

    public static boolean isEffectiveClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    public static void sendPacket(IZeroLib entity, int animID) {
        if(isEffectiveClient()) return;
        entity.sendPacketID(animID);
        wrapper.sendToAll(new MessageHandler((byte)animID, ((Entity)entity).getEntityId()));
    }
}
