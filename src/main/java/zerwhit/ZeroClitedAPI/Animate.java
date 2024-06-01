package zerwhit.ZeroClitedAPI;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(
        name = "ZeroClitedAPI",
        modid = "zero_clited_api",
        version = "1.0"
)
public class Animate {
    public static SimpleNetworkWrapper wrapper;
    @Instance("zero_clited_api")
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
