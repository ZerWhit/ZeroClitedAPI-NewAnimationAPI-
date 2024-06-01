package zerwhit.Example;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import zerwhit.Example.entity.EntityZombieTest;

import java.util.logging.Logger;

@Mod(name = "ZeroClitedAPI Example", modid = "ZeroClitedAPI Example")
public class ZeroClitedExample {
    @SidedProxy(clientSide = "zerwhit.Example.client.ClientProxy", serverSide = "zerwhit.Example.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = Logger.getLogger(event.getSide() == Side.SERVER ? null : "ZeroClitedAPI Example");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        EntityRegistry.registerGlobalEntityID(EntityZombieTest.class, "EntityZombieTest", 107, 0, 0);
        proxy.registerRenderers();
    }
}
