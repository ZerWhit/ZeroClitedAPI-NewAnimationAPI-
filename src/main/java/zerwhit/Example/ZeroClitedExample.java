package zerwhit.Example;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import zerwhit.Example.entity.EntityZombieTest;

import java.util.logging.Logger;

@Mod(name = "ZeroClitedAPI Example", modid = "zero_clited_api_example")
public class ZeroClitedExample {
    @SidedProxy(clientSide = "zerwhit.Example.client.ClientProxy", serverSide = "zerwhit.Example.CommonProxy")
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.Instance
    public static ZeroClitedExample instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = Logger.getLogger(event.getSide() == Side.SERVER ? null : "ZeroClitedAPI Example");
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        EntityRegistry.registerModEntity(new ResourceLocation("zero_clited_api_example", "zombie_test"), EntityZombieTest.class, "zombie_test", 107, instance, 128, 1, true);
        EntityRegistry.registerEgg(new ResourceLocation("zero_clited_api_example", "zombie_test"), 0x000000, 0xFFFFFF);
        proxy.registerRenderers();
    }
}
